package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcServer;
import com.lingfeng.jrpc.JrpcSerializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:06
 * @Description:
 */
public class NettyJrpcServerChannelInitializer extends ChannelInitializer {

	private JrpcServer jrpcCollector;

	public NettyJrpcServerChannelInitializer(JrpcServer jrpcCollector) {
		this.jrpcCollector = jrpcCollector;
	}

	protected void initChannel(Channel ch) throws Exception {

		final long idleTimeoutMills = jrpcCollector.idleTimeoutMills();
		final JrpcSerializer serializer = jrpcCollector.serializer();
		ch
				.pipeline()
				.addLast(new NettyJrpcDecoder())
				.addLast(new NettyJrpcEncoder())
				.addLast(new NettyJrpcObjectAggregator(serializer, false))
				.addLast(new IdleStateHandler(idleTimeoutMills, idleTimeoutMills, idleTimeoutMills, TimeUnit.MILLISECONDS))
				.addLast(new NettyJrpcServerHandler());
	}
}

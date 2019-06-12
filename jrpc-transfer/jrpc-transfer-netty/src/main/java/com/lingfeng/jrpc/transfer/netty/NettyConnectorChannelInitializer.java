package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcConnector;
import com.lingfeng.jrpc.JrpcSerializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:06
 * @Description:
 */
public class NettyConnectorChannelInitializer extends ChannelInitializer {

	private JrpcConnector connector;

	public NettyConnectorChannelInitializer(JrpcConnector connector) {
		this.connector = connector;
	}

	protected void initChannel(Channel ch) throws Exception {

		final JrpcSerializer serializer = connector.serializer();
		ch
				.pipeline()
				.addLast(new NettyJrpcDecoder(serializer))
				.addLast(new NettyJrpcEncoder(serializer))
				.addLast(new NettyJrpcHandler());
	}
}

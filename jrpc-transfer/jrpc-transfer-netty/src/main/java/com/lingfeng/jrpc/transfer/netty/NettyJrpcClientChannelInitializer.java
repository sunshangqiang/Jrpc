package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcClient;
import com.lingfeng.jrpc.JrpcSerializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:06
 * @Description:
 */
public class NettyJrpcClientChannelInitializer extends ChannelInitializer {

	private JrpcClient client;

	public NettyJrpcClientChannelInitializer(JrpcClient client) {
		this.client = client;
	}

	protected void initChannel(Channel ch) throws Exception {

		final JrpcSerializer serializer = client.serializer();
		ch
				.pipeline()
				.addLast(new NettyJrpcDecoder())
				.addLast(new NettyJrpcEncoder())
				.addLast(new NettyJrpcObjectAggregator(serializer))
				.addLast(new NettyJrpcClientHandler());
	}
}

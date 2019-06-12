package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcChannel;
import com.lingfeng.jrpc.JrpcListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.DefaultChannelPromise;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:32
 * @Description:
 */
public class NettyJrpcChannel implements JrpcChannel {

	private Channel nettyChannel;

	public NettyJrpcChannel(Channel nettyChannel) {
		this.nettyChannel = nettyChannel;
	}

	public JrpcChannel sendMessage(Object message, final JrpcListener jrpcListener) {

		if (nettyChannel == null) {
			jrpcListener.fail(null);
			return this;
		}

		if (!nettyChannel.isActive()) {
			jrpcListener.fail(null);
			return this;
		}

		nettyChannel.writeAndFlush(message)
				.addListener(new ChannelFutureListener() {
					public void operationComplete(ChannelFuture cf) throws Exception {
						if (cf.isSuccess()) {
							jrpcListener.success();
							return;
						}
						jrpcListener.fail(cf.cause());
					}
				});
		return null;
	}

	public boolean isActive() {
		return false;
	}
}

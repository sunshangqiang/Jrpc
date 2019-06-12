package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcConnector;
import com.lingfeng.jrpc.JrpcChannel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:16 PM
 */

@Slf4j
public class NettyJrpcConnector extends JrpcConnector {

	private EventLoopGroup child = null;

	public JrpcChannel connect(String ip, int port, long connecTimeoutMills) {
		ChannelFuture cf = boot()
				.connect(ip, port);
		try {
			cf.await(connecTimeoutMills, TimeUnit.MILLISECONDS);
			if (cf.isSuccess()) {
				return new NettyJrpcChannel(cf.channel());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Bootstrap boot() {
		final Bootstrap b = new Bootstrap();
		final int cpu = Runtime.getRuntime().availableProcessors();
		if (Epoll.isAvailable()) {
			b
					.group(child = new EpollEventLoopGroup(cpu, new DefaultThreadFactory("NettyJrpcConnector-Epoll")))
					.channel(EpollSocketChannel.class);
		} else {
			b
					.group(child = new NioEventLoopGroup(cpu, new DefaultThreadFactory("NettyJrpcConnector-Nio")))
					.channel(NioSocketChannel.class);
		}
		return b
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.SO_RCVBUF, 16 * 1024 * 1024)
				.option(ChannelOption.SO_SNDBUF, 16 * 1024 * 1024)
				.handler(new NettyConnectorChannelInitializer(this));
	}


}

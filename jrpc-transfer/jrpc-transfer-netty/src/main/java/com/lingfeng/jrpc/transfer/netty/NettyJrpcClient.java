package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcClient;
import com.lingfeng.jrpc.JrpcChannel;
import com.lingfeng.jrpc.JrpcClientConnectException;
import com.lingfeng.jrpc.JrpcListener;
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
public class NettyJrpcClient extends JrpcClient {

	private EventLoopGroup child = null;

	public State start(String ip, int port) throws JrpcClientConnectException {
		state(State.STARTING);
		ChannelFuture cf = boot()
				.connect(ip, port);
		try {
			cf.await(connectTimeoutMills, TimeUnit.MILLISECONDS);
			if (cf.isSuccess()) {
				JRPC_CHANNELS.add(this);
				state(State.STARTED);
				return State.STARTED;
			}
			state(State.UNSTART);
			throw new JrpcClientConnectException("NettyJrpcClient start server fail, server address:" + ip + ":" + port);
		} catch (InterruptedException e) {
			state(State.UNSTART);
			throw new JrpcClientConnectException("NettyJrpcClient start server exception, server address:" + ip + ":" + port, e);
		}
	}

	public void sendMessage(Object message, JrpcListener jrpcListener) {

	}

	public boolean isActive() {
		return false;
	}

	private Bootstrap boot() {
		final Bootstrap b = new Bootstrap();
		final int cpu = Runtime.getRuntime().availableProcessors();
		if (Epoll.isAvailable()) {
			b
					.group(child = new EpollEventLoopGroup(cpu, new DefaultThreadFactory("NettyJrpcClient-Epoll")))
					.channel(EpollSocketChannel.class);
		} else {
			b
					.group(child = new NioEventLoopGroup(cpu, new DefaultThreadFactory("NettyJrpcClient-Nio")))
					.channel(NioSocketChannel.class);
		}
		return b
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.SO_RCVBUF, 16 * 1024 * 1024)
				.option(ChannelOption.SO_SNDBUF, 16 * 1024 * 1024)
				.handler(new NettyJrpcClientChannelInitializer(this));
	}

	public void shutdown() {
		log.info("NettyJrpcClient shutdown......");
		if (child != null) {
			child.shutdownGracefully();
		}
		log.info("NettyJrpcClient shutdown success");
	}


}

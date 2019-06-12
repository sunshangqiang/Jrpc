package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcCollector;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 22:56
 * @Description:
 */

@Slf4j
public class NettyJrpcCollector extends JrpcCollector {

	private EventLoopGroup main;

	private EventLoopGroup child;

	public JrpcCollector start() {
		ServerBootstrap sb = boot();
		try {
			ChannelFuture cf = sb
					.bind(ip(), port())
					.sync();
			if (cf.isSuccess()) {
				log.info("Netty collector start success");
				return this;
			}
			log.info("Netty collector start fail");
		} catch (InterruptedException e) {
			log.info("Netty collector start exception", e);
		}
		return this;
	}

	public void shutdown() {
		log.info("Netty collector shutdown......");
		if (main != null) {
			main.shutdownGracefully();
		}
		if (child != null) {
			child.shutdownGracefully();
		}
		log.info("Netty collector shutdown done");
	}

	private ServerBootstrap boot() {

		return new ServerBootstrap()
				.group(main = new NioEventLoopGroup(1),
						child = new NioEventLoopGroup(4))
				.channel(NioServerSocketChannel.class)
				.childOption(ChannelOption.SO_RCVBUF, 1024 * 1024)
				.childOption(ChannelOption.SO_SNDBUF, 1024 * 1024)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childOption(ChannelOption.TCP_NODELAY, true)
				.childHandler(new NettyCollectorChannelInitializer(NettyJrpcCollector.this));
	}
}

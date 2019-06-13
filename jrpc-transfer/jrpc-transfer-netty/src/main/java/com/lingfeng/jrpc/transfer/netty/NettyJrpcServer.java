package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcServer;
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
public class NettyJrpcServer extends JrpcServer {

	private EventLoopGroup main;

	private EventLoopGroup child;

	public JrpcServer start() {
		log.info("NettyServer starting......");
		ServerBootstrap sb = boot();
		try {
			ChannelFuture cf = sb
					.bind(ip(), port())
					.sync();
			if (cf.isSuccess()) {
				log.info("Netty collector connect success");
				return this;
			}
			log.info("Netty collector connect fail");
		} catch (InterruptedException e) {
			log.info("Netty collector connect exception", e);
		}
		return this;
	}

	public void shutdown() {
		log.info("Netty collector shutdown......");
		try {
			if (main != null) {
				main.shutdownGracefully();
			}
		} catch (Exception e) {
			log.error("Netty collector shutdown main EventLoopGroup exception", e);
		}
		try {
			if (child != null) {
				child.shutdownGracefully();
			}
		} catch (Exception e) {
			log.error("Netty collector shutdown child EventLoopGroup exception", e);
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
				.childHandler(new NettyJrpcServerChannelInitializer(NettyJrpcServer.this));
	}
}

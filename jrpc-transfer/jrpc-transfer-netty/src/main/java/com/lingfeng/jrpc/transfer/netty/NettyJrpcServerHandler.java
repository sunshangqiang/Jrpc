package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 22:49
 * @Description:
 */

@Slf4j
public class NettyJrpcServerHandler extends SimpleChannelInboundHandler<JrpcProtocol.JrpcRequest> {

	protected void channelRead0(ChannelHandlerContext ctx, JrpcProtocol.JrpcRequest msg) throws Exception {
		log.info(msg.toString());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("netty create a new channel, address:{}", ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("netty close a channel, address:{}", ctx.channel().remoteAddress());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {

		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	}
}

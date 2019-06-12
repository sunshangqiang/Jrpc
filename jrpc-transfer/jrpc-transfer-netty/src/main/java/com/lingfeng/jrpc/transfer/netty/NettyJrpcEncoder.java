package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 22:35
 * @Description:
 */
public class NettyJrpcEncoder extends MessageToByteEncoder {

	private JrpcSerializer jrpcSerializer;

	public NettyJrpcEncoder(JrpcSerializer jrpcSerializer) {
		this.jrpcSerializer = jrpcSerializer;
	}

	protected void encode(ChannelHandlerContext ctx, Object o, ByteBuf byteBuf) throws Exception {
		byte[] bytes = jrpcSerializer.serialize(o);
		ByteBuf buf = ctx.alloc().buffer();
		buf.writeInt(0xABAF);
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
		ctx.writeAndFlush(buf);
	}
}

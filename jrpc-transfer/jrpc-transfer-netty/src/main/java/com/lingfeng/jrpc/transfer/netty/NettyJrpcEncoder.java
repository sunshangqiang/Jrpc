package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 22:35
 * @Description:
 */
public class NettyJrpcEncoder extends MessageToByteEncoder<byte[]> {

	protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
		ByteBuf buf = ctx.alloc().buffer();
		buf.writeInt(JrpcService.MAGIC_CODE);
		buf.writeInt(msg.length);
		buf.writeBytes(msg);
		ctx.writeAndFlush(buf);
	}
}

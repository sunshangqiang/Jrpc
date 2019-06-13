package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcProtocol;
import com.lingfeng.jrpc.JrpcProtocol.JrpcRequest;
import com.lingfeng.jrpc.JrpcProtocol.JrpcResponse;
import com.lingfeng.jrpc.JrpcSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-06-13
 * Time: 4:03 PM
 */

public class NettyJrpcObjectAggregator extends MessageToMessageCodec<byte[], JrpcProtocol> {

	private JrpcSerializer serializer;
	private boolean isClient;

	public NettyJrpcObjectAggregator(JrpcSerializer serializer) {
		this(serializer, true);
	}

	public NettyJrpcObjectAggregator(JrpcSerializer serializer, boolean isClient) {
		this.serializer = serializer;
		this.isClient = isClient;
	}

	protected void encode(ChannelHandlerContext ctx, JrpcProtocol msg, List<Object> out) throws Exception {
		out.add(serializer.serialize(msg));
	}

	protected void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
		if (isClient) {
			out.add(serializer.unserialize(msg, JrpcResponse.class));
			return;
		}
		JrpcRequest r = serializer.unserialize(msg, JrpcRequest.class);
		out.add(r);
	}
}

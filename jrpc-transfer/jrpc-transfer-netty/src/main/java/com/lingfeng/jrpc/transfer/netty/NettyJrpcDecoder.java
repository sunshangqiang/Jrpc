package com.lingfeng.jrpc.transfer.netty;

import com.lingfeng.jrpc.JrpcMagicErrorException;
import com.lingfeng.jrpc.JrpcService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 22:33
 * @Description:
 */
public class NettyJrpcDecoder extends ReplayingDecoder<NettyJrpcDecoder.State> {

	private final static int MAGIC = JrpcService.MAGIC_CODE;

	public enum State {

		MAGIC,

		HEADER,

		DATA

	}

	private int length;

	public NettyJrpcDecoder() {
		this(State.MAGIC);
	}

	public NettyJrpcDecoder(State initialState) {
		super(initialState);
	}

	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {

		switch (state()) {

			case MAGIC: {
				int l = in.readableBytes();
				if (l < 4) {
					return;
				}
				int _magic = in.readInt();
				if (_magic != MAGIC) {
					throw new JrpcMagicErrorException();
				}
				checkpoint(State.HEADER);
			}
			case HEADER: {
				int l = in.readableBytes();
				if (l < 4) {
					return;
				}
				length = in.readInt();
				checkpoint(State.DATA);
			}

			case DATA: {
				int l = in.readableBytes();
				if (l < length) {
					return;
				}
				byte[] bytes = new byte[length];
				in.readBytes(bytes);
				list.add(bytes);
				checkpoint(State.MAGIC);
			}
		}


	}
}

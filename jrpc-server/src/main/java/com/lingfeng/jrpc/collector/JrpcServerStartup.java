package com.lingfeng.jrpc.collector;

import com.lingfeng.jrpc.serializer.ProtoSerializer;
import com.lingfeng.jrpc.transfer.netty.NettyJrpcServer;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:21
 * @Description:
 */
public class JrpcServerStartup {

	public static void main(String[] args) {

		new NettyJrpcServer()
				.port(18080)
				.serializer(new ProtoSerializer())
				.start();
	}
}

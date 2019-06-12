package com.lingfeng.jrpc.collector;

import com.lingfeng.jrpc.serializer.ProtoSerializer;
import com.lingfeng.jrpc.transfer.netty.NettyJrpcCollector;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:21
 * @Description:
 */
public class JrpcCollectorStartup {

	public static void main(String[] args) {

		new NettyJrpcCollector()
				.ip("0.0.0.0")
				.port(18080)
				.serializer(new ProtoSerializer())
				.start();


	}
}

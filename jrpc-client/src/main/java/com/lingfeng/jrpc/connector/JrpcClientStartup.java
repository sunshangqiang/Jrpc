package com.lingfeng.jrpc.connector;

import com.lingfeng.jrpc.JrpcClient;
import com.lingfeng.jrpc.JrpcClientConnectException;
import com.lingfeng.jrpc.JrpcListener;
import com.lingfeng.jrpc.JrpcProtocol;
import com.lingfeng.jrpc.serializer.ProtoSerializer;
import com.lingfeng.jrpc.transfer.netty.NettyJrpcClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:27
 * @Description:
 */

@Slf4j
public class JrpcClientStartup {

	public static void main(String[] args) throws JrpcClientConnectException, InterruptedException {

		JrpcClient client = new NettyJrpcClient()
				.ip("192.168.1.104")
				.serializer(new ProtoSerializer())
				.connect();



		int i = 0;

		while (true) {
			if (client.isActive()) {
				JrpcProtocol.JrpcRequest r = new JrpcProtocol.JrpcRequest();
				r.msg("sdkfhasdjkfhasd" + (i++));
				client.sendMessage(r, new JrpcListener() {
					public void success() {
						log.info("message => {} success");
					}

					public void fail(Throwable e) {
						log.info("message => {} fail", e);
					}

					public void result(Object msg) {

					}
				});
			}
			Thread.sleep(3000);
		}


	}

}

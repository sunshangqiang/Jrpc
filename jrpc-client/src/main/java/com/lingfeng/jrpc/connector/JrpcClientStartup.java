package com.lingfeng.jrpc.connector;

import com.lingfeng.jrpc.JrpcChannel;
import com.lingfeng.jrpc.JrpcClientConnectException;
import com.lingfeng.jrpc.JrpcListener;
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

		JrpcChannel jrpcChannel = new NettyJrpcClient()
				.serializer(new ProtoSerializer())
				.start("127.0.0.1", 18080);

		int i = 0;

		while (true) {
			final String a;
			jrpcChannel.sendMessage(a = ("sdkfhasdjkfhasd" + (i++)), new JrpcListener() {
				public void success() {
					log.info("message => {} success", a);
				}

				public void fail(Throwable e) {
					log.info("message => {} fail", a, e);
				}
			});
			Thread.sleep(3000);
		}


	}

}

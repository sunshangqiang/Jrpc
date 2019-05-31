package com.lingfeng.jrpc;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:08 PM
 */

public interface JrpcConnector extends JrpcService {

	long DEFAULT_CONNECOT_TIMEOUT_MILLS = 30 * 1000;

	JrpcSerializer serializer();

	JrpcConnector serializer(JrpcSerializer serializer);

	default JrpcChannel connect(String ip, int port) {
		return connect(ip, port, DEFAULT_CONNECOT_TIMEOUT_MILLS);
	}

	JrpcChannel connect(String ip, int port, long timeoutMills);

}

package com.lingfeng.jrpc;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-06-13
 * Time: 5:34 PM
 */

public class JrpcClientConnectException extends JrpcException {

	public JrpcClientConnectException() {
	}

	public JrpcClientConnectException(String message) {
		super(message);
	}

	public JrpcClientConnectException(String message, Throwable cause) {
		super(message, cause);
	}

	public JrpcClientConnectException(Throwable cause) {
		super(cause);
	}
}

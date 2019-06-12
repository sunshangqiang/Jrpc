package com.lingfeng.jrpc;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-01 08:14
 * @Description:
 */
public class JrpcException extends Exception {

	public JrpcException() {
	}

	public JrpcException(String message) {
		super(message);
	}

	public JrpcException(String message, Throwable cause) {
		super(message, cause);
	}

	public JrpcException(Throwable cause) {
		super(cause);
	}

	public JrpcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

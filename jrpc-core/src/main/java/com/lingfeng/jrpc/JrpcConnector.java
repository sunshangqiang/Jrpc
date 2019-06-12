package com.lingfeng.jrpc;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:13 PM
 */

@Data
public abstract class JrpcConnector {

	protected  final static long DEFAULT_CONNECOT_TIMEOUT_MILLS = 30 * 1000;

	protected  final static int DEFAULT_HEARTBEAT_INTERVAL_MILLS = 5 * 1000;

	private JrpcSerializer serializer;

	private int heartbeatIntervalMills = DEFAULT_HEARTBEAT_INTERVAL_MILLS;

	public JrpcChannel connect(String ip, int port) {
		return connect(ip, port, DEFAULT_CONNECOT_TIMEOUT_MILLS);
	}

	public abstract JrpcChannel connect(String ip, int port, long connecTimeoutMills);

}

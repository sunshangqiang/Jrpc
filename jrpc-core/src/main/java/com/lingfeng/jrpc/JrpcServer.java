package com.lingfeng.jrpc;

import lombok.Data;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:00
 * @Description:
 */

@Data
public abstract class JrpcServer implements JrpcService {

	private final static String DEFAULT_BIND_IP = "0.0.0.0";

	private final static int DEFAULT_BIND_PORT = 18080;

	private final static long DEFAULT_IDLE_TIMEOUT_MILLS = 30000;

	private String ip = DEFAULT_BIND_IP;

	private int port = DEFAULT_BIND_PORT;

	private long idleTimeoutMills = DEFAULT_IDLE_TIMEOUT_MILLS;

	private JrpcSerializer serializer;

	public abstract JrpcServer start();

	public abstract void shutdown();


}

package com.lingfeng.jrpc;

import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:13 PM
 */

@Data
public abstract class JrpcClient implements JrpcService {

	protected final static long DEFAULT_CONNECOT_TIMEOUT_MILLS = 30 * 1000;

	protected final static int DEFAULT_HEARTBEAT_INTERVAL_MILLS = 5 * 1000;

	protected final static int DEFAULT_CONNECT_TIMEOUT_MILLS = 30 * 1000;

	protected final static String DEFAULT_IP = "127.0.0.1";

	protected final static int DEFAULT_PORT = 18080;

	private int heartbeatIntervalMills = DEFAULT_HEARTBEAT_INTERVAL_MILLS;

	protected String ip = DEFAULT_IP;

	protected int port = DEFAULT_PORT;

	protected JrpcSerializer serializer;

	protected int connectTimeoutMills = DEFAULT_CONNECT_TIMEOUT_MILLS;

	protected State state = State.UNSTART;

	public abstract JrpcClient connect() throws JrpcClientConnectException;

	public abstract void shutdown();

	public abstract void sendMessage(Object message, JrpcListener jrpcListener);

	public abstract boolean isActive();

	public enum State {

		STARTING,

		STARTED,

		UNSTART

	}

}

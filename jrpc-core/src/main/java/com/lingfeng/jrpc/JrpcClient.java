package com.lingfeng.jrpc;

import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:13 PM
 */

@Data
public abstract class JrpcClient {

	protected final ScheduledExecutorService scheduled;

	protected  final static long DEFAULT_CONNECOT_TIMEOUT_MILLS = 30 * 1000;

	protected  final static int DEFAULT_HEARTBEAT_INTERVAL_MILLS = 5 * 1000;

	protected final static int DEFAULT_CONNECT_TIMEOUT_MILLS = 30 * 1000;

	private int heartbeatIntervalMills = DEFAULT_HEARTBEAT_INTERVAL_MILLS;

	protected JrpcSerializer serializer;

	protected int connectTimeoutMills = DEFAULT_CONNECT_TIMEOUT_MILLS;

	protected State state = State.UNSTART;

	public abstract State start(String ip, int port) throws JrpcClientConnectException;

	public abstract void shutdown();

	public abstract void sendMessage(Object message, JrpcListener jrpcListener);

	public abstract boolean isActive();

	public enum State {

		STARTING,

		STARTED,

		UNSTART

	}

}

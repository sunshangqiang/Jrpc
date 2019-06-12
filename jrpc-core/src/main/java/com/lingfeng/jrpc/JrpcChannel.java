package com.lingfeng.jrpc;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:10 PM
 */

public interface JrpcChannel {

	JrpcChannel sendMessage(Object message, JrpcListener jrpcListener);

	boolean isActive();

}

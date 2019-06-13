package com.lingfeng.jrpc;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-12 23:39
 * @Description:
 */
public interface JrpcListener {

	void success();

	void fail(Throwable e);

	void result(Object msg);

}

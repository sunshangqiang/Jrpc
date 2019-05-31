package com.lingfeng.jrpc;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-05-31
 * Time: 4:08 PM
 */

public interface JrpcSerializer {

	<T> byte[] serialize(T t);

	<T> T unserialize(byte[] bytes, Class<T> clazz);

}

package com.lingfeng.jrpc.serializer;

import com.lingfeng.jrpc.JrpcSerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @Author: sunshangqiang
 * @Date: 2019-06-01 08:20
 * @Description:
 */
public class ProtoSerializer implements JrpcSerializer {

	@Override
	public <T> byte[] serialize(T t) {
		Schema schema = RuntimeSchema.getSchema(t.getClass());
		LinkedBuffer buf = LinkedBuffer.allocate(1024);
		return ProtostuffIOUtil.toByteArray(t, schema, buf);
	}

	@Override
	public <T> T unserialize(byte[] bytes, Class<T> clazz) {
		Schema schema = RuntimeSchema.getSchema(clazz);
		T t;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
		ProtostuffIOUtil.mergeFrom(bytes, t, schema);
		return t;
	}
}

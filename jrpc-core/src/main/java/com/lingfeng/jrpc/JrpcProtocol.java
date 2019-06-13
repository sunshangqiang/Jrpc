package com.lingfeng.jrpc;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shangqiang.sun
 * Date: 2019-06-13
 * Time: 4:11 PM
 */

@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JrpcProtocol implements java.io.Serializable{

	private long id;

	private Object msg;

	/**
	 * Created with IntelliJ IDEA.
	 * Description:
	 * User: shangqiang.sun
	 * Date: 2019-06-13
	 * Time: 5:16 PM
	 */

	public static class JrpcRequest extends JrpcProtocol {

		private final static AtomicInteger idGenerator = new AtomicInteger(0);

		@Override
		public JrpcRequest msg(Object msg) {
			id(idGenerator.incrementAndGet());
			super.msg(msg);
			return this;
		}
	}

	/**
	 * Created with IntelliJ IDEA.
	 * Description:
	 * User: shangqiang.sun
	 * Date: 2019-06-13
	 * Time: 5:16 PM
	 */

	public static class JrpcResponse extends JrpcProtocol {

		public JrpcResponse(JrpcRequest request) {
			super();
			id(request.id());
		}

	}
}


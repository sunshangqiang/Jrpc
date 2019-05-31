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
public abstract class AbstractJrpcConnector implements JrpcConnector {

	private JrpcSerializer serializer;

}

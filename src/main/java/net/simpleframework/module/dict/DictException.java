package net.simpleframework.module.dict;

import net.simpleframework.common.th.RuntimeExceptionEx;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictException extends RuntimeExceptionEx {

	public DictException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

	public static DictException of(final String msg) {
		return _of(DictException.class, msg);
	}

	public static DictException of(final Throwable throwable) {
		return _of(DictException.class, null, throwable);
	}

	private static final long serialVersionUID = -8419009865582459983L;
}

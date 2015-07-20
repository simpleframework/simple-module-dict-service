package net.simpleframework.module.dict.impl;

import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.ctx.service.ado.db.AbstractDbBeanService;
import net.simpleframework.module.dict.IDictContextAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractDictService<T> extends AbstractDbBeanService<T> implements
		IDictContextAware {

	@Override
	public IModuleContext getModuleContext() {
		return dictContext;
	}
}

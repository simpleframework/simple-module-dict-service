package net.simpleframework.module.dict;

import net.simpleframework.ctx.IModuleContextAware;
import net.simpleframework.ctx.ModuleContextFactory;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IDictContextAware extends IModuleContextAware {

	static final IDictContext dictContext = ModuleContextFactory.get(IDictContext.class);

	static final IDictService _dictService = dictContext.getDictService();
	static final IDictItemService _dictItemService = dictContext.getDictItemService();
}

package net.simpleframework.module.dict;

import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.ctx.IModuleRef;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IDictContext extends IModuleContext {

	static final String MODULE_NAME = "simple-module-dict";

	/**
	 * 获取字典管理器
	 * 
	 * @return
	 */
	IDictService getDictService();

	/**
	 * 获取字典条目管理器
	 * 
	 * @return
	 */
	IDictItemService getDictItemService();

	/**
	 * 获取机构的引用对象
	 * 
	 * @return
	 */
	IModuleRef getOrganizationRef();
}

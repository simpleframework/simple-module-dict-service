package net.simpleframework.module.dict;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.ctx.AbstractModuleRef;
import net.simpleframework.ctx.ModuleContextFactory;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class DictRef extends AbstractModuleRef {

	@Override
	public IDictContext getModuleContext() {
		return ModuleContextFactory.get(IDictContext.class);
	}

	public DictItem getDictItem(final String dictName, final String codeNo) {
		return getDictItemService().getItemByCode(getDictByName(dictName), codeNo);
	}

	public Dict getDictByName(final String dictName) {
		return getDictService().getDictByName(dictName);
	}

	public IDataQuery<DictItem> queryItems(final String dictName) {
		return getDictItemService().queryItems(getDictByName(dictName));
	}

	/**
	 * 获取字典服务
	 * 
	 * @return
	 */
	public IDictService getDictService() {
		return getModuleContext().getDictService();
	}

	/**
	 * 获取字典条目服务
	 * 
	 * @return
	 */
	public IDictItemService getDictItemService() {
		return getModuleContext().getDictItemService();
	}
}

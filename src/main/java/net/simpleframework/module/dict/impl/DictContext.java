package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.IADOManagerFactory;
import net.simpleframework.ado.db.DbManagerFactory;
import net.simpleframework.ctx.AbstractADOModuleContext;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.ctx.IModuleRef;
import net.simpleframework.ctx.Module;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.DictItem;
import net.simpleframework.module.dict.IDictContext;
import net.simpleframework.module.dict.IDictItemService;
import net.simpleframework.module.dict.IDictService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class DictContext extends AbstractADOModuleContext implements IDictContext {

	@Override
	public void onInit(final IApplicationContext application) throws Exception {
		super.onInit(application);

		final IADOManagerFactory aFactory = getADOManagerFactory();
		if (aFactory instanceof DbManagerFactory) {
			((DbManagerFactory) aFactory).regist(Dict.TBL, DictItem.TBL);
		}
	}

	@Override
	public String getManagerRole() {
		return ROLE_DICT_MANAGER;
	}

	@Override
	protected Module createModule() {
		return new Module().setName(MODULE_NAME).setText($m("DictContext.0")).setOrder(35);
	}

	@Override
	public IDictService getDictService() {
		return singleton(DictService.class);
	}

	@Override
	public IDictItemService getDictItemService() {
		return singleton(DictItemService.class);
	}

	@Override
	public IModuleRef getOrganizationRef() {
		return getRef("net.simpleframework.module.dict.DictOrganizationRef");
	}
}

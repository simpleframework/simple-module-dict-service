package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.ColumnData;
import net.simpleframework.ado.db.DbEntityTable;
import net.simpleframework.ctx.AbstractADOModuleContext;
import net.simpleframework.ctx.IModuleRef;
import net.simpleframework.ctx.Module;
import net.simpleframework.ctx.ModuleRefUtils;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.DictItem;
import net.simpleframework.module.dict.DictItemStat;
import net.simpleframework.module.dict.IDictContext;
import net.simpleframework.module.dict.IDictItemService;
import net.simpleframework.module.dict.IDictItemStatService;
import net.simpleframework.module.dict.IDictService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictContext extends AbstractADOModuleContext implements IDictContext {
	public static String ROLE_DICT_MANAGER;

	@Override
	protected DbEntityTable[] createEntityTables() {
		return new DbEntityTable[] {
				new DbEntityTable(Dict.class, "sf_dict"),
				new DbEntityTable(DictItem.class, "sf_dict_item").setDefaultOrder(ColumnData
						.ASC("oorder")), new DbEntityTable(DictItemStat.class, "sf_dict_item_stat") };
	}

	@Override
	protected Module createModule() {
		return new Module() {
			@Override
			public String getManagerRole() {
				return ROLE_DICT_MANAGER;
			}

			@Override
			public String getRole() {
				return ROLE_DICT_MANAGER;
			}
		}.setName(MODULE_NAME).setText($m("DictContext.0")).setOrder(35);
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
	public IDictItemStatService getDictItemStatService() {
		return singleton(DictItemStatService.class);
	}

	@Override
	public IModuleRef getOrganizationRef() {
		return ModuleRefUtils.getRef("net.simpleframework.module.dict.DictOrganizationRef");
	}
}

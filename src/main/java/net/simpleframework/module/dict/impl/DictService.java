package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.ColumnData;
import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.ctx.ModuleContextException;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.EDictMark;
import net.simpleframework.module.dict.IDictService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictService extends AbstractDictService<Dict> implements IDictService {
	@Override
	public Dict getDictByName(final String name) {
		return getBean("name=?", name);
	}

	@Override
	protected ColumnData[] getDefaultOrderColumns() {
		return ORDER_OORDER;
	}

	@Override
	public void onInit() throws Exception {
		super.onInit();

		addListener(new DbEntityAdapterEx<Dict>() {
			@Override
			public void onBeforeDelete(final IDbEntityManager<Dict> manager,
					final IParamsValue paramsValue) throws Exception {
				super.onBeforeDelete(manager, paramsValue);
				for (final Dict dict : coll(manager, paramsValue)) {
					// 存在下级字典
					if (queryChildren(dict).getCount() > 0) {
						throw ModuleContextException.of($m("DictService.0"));
					}
				}
			}

			@Override
			public void onBeforeUpdate(final IDbEntityManager<Dict> service, final String[] columns,
					final Dict[] beans) throws Exception {
				super.onBeforeUpdate(service, columns, beans);
				for (final Dict dict : beans) {
					if (dict.getDictMark() == EDictMark.builtIn) {
						throw ModuleContextException.of($m("DictService.1"));
					}
				}
			}
		});
	}
}

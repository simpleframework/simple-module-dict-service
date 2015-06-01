package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.ColumnData;
import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.ctx.ModuleContextException;
import net.simpleframework.ctx.service.ado.db.AbstractDbBeanService;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.EDictMark;
import net.simpleframework.module.dict.IDictContextAware;
import net.simpleframework.module.dict.IDictService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictService extends AbstractDbBeanService<Dict> implements IDictService,
		IDictContextAware {
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

		addListener(new DbEntityAdapterEx() {
			@Override
			public void onBeforeDelete(final IDbEntityManager<?> service,
					final IParamsValue paramsValue) throws Exception {
				super.onBeforeDelete(service, paramsValue);
				for (final Dict dict : coll(paramsValue)) {
					// 存在下级字典
					if (queryChildren(dict).getCount() > 0) {
						throw ModuleContextException.of($m("DictService.0"));
					}
				}
			}

			@Override
			public void onBeforeUpdate(final IDbEntityManager<?> service, final String[] columns,
					final Object[] beans) throws Exception {
				super.onBeforeUpdate(service, columns, beans);
				for (final Object o : beans) {
					final Dict dict = (Dict) o;
					if (dict.getDictMark() == EDictMark.builtIn) {
						throw ModuleContextException.of($m("DictService.1"));
					}
				}
			}
		});
	}
}

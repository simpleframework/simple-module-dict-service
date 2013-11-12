package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.ctx.ModuleException;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.EDictMark;
import net.simpleframework.module.dict.IDictService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class DictService extends AbstractDictService<Dict> implements IDictService {
	@Override
	public Dict getDictByName(final String name) {
		return getBean("name=?", name);
	}

	@Override
	public void onInit() throws Exception {
		addListener(new DbEntityAdapterEx() {
			@Override
			public void onBeforeDelete(final IDbEntityManager<?> service,
					final IParamsValue paramsValue) {
				super.onBeforeDelete(service, paramsValue);
				for (final Dict dict : coll(paramsValue)) {
					// 存在下级字典
					if (queryChildren(dict).getCount() > 0) {
						throw ModuleException.of($m("DictService.0"));
					}
				}
			}

			@Override
			public void onBeforeUpdate(final IDbEntityManager<?> service, final String[] columns,
					final Object[] beans) {
				super.onBeforeUpdate(service, columns, beans);
				for (final Object o : beans) {
					final Dict dict = (Dict) o;
					assertParentId(dict);
					if (dict.getDictMark() == EDictMark.builtIn) {
						throw ModuleException.of($m("DictService.1"));
					}
				}
			}
		});
	}
}

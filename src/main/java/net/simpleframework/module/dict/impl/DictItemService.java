package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;

import java.util.Collection;
import java.util.Map;

import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.ado.db.common.SqlUtils;
import net.simpleframework.ado.query.DataQueryUtils;
import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.Convert;
import net.simpleframework.common.ID;
import net.simpleframework.ctx.ModuleContextException;
import net.simpleframework.ctx.service.ado.db.AbstractDbBeanService;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.DictItem;
import net.simpleframework.module.dict.EDictItemMark;
import net.simpleframework.module.dict.IDictContextAware;
import net.simpleframework.module.dict.IDictItemService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictItemService extends AbstractDbBeanService<DictItem> implements IDictItemService,
		IDictContextAware {

	@Override
	public DictItem getItemByCode(final Dict dict, final String codeNo) {
		if (dict == null) {
			return null;
		}
		return getBean("dictId=? and codeNo=?", dict.getId(), codeNo);
	}

	@Override
	public IDataQuery<DictItem> queryItems(final Dict dict) {
		if (dict == null) {
			return DataQueryUtils.nullQuery();
		}
		return query("dictId=?", dict.getId());
	}

	@Override
	public IDataQuery<DictItem> queryItems(final Dict dict, final String text) {
		return query("dictId=? and text like '%" + SqlUtils.sqlEscape(text) + "%'", dict.getId());
	}

	@Override
	public IDataQuery<DictItem> queryRoot(final Dict dict) {
		if (dict == null) {
			return DataQueryUtils.nullQuery();
		}
		return query("dictId=? and parentId is null", dict.getId());
	}

	@Override
	public int queryCount(final Dict dict) {
		if (COUNT_STATS.size() == 0) {
			final IDataQuery<Map<String, Object>> dq = getQueryManager().query(
					"select dictId, count(*) as cc from sf_dict_item group by dictId");
			for (Map<String, Object> row; (row = dq.next()) != null;) {
				COUNT_STATS.put(Convert.toString(row.get("dictId")), Convert.toInt(row.get("cc")));
			}
		}
		return Convert.toInt(COUNT_STATS.get(dict.getId().toString()));
	}

	@Override
	public Map<ID, Collection<DictItem>> queryAllTree(final Dict dict) {
		return treeToMap(queryItems(dict).setFetchSize(0));
	}

	@Override
	public void onInit() throws Exception {
		super.onInit();
		addListener(new DbEntityAdapterEx() {
			@Override
			public void onBeforeDelete(final IDbEntityManager<?> service,
					final IParamsValue paramsValue) {
				super.onBeforeDelete(service, paramsValue);
				for (final DictItem item : coll(paramsValue)) {
					if (item.getItemMark() != EDictItemMark.normal) {
						throw ModuleContextException.of($m("DictItemService.0"));
					}
				}
			}

			@Override
			public void onBeforeUpdate(final IDbEntityManager<?> service, final String[] columns,
					final Object[] beans) {
				super.onBeforeUpdate(service, columns, beans);
				for (final Object o : beans) {
					final DictItem item = (DictItem) o;
					if (item.getItemMark() == EDictItemMark.builtIn_r) {
						throw ModuleContextException.of($m("DictItemService.1"));
					}
				}
			}
		});
	}
}

package net.simpleframework.module.dict.impl;

import static net.simpleframework.common.I18n.$m;

import java.util.ArrayList;
import java.util.List;

import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.ado.db.common.SqlUtils;
import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.ID;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.DictException;
import net.simpleframework.module.dict.DictItem;
import net.simpleframework.module.dict.DictItem.EDictItemMark;
import net.simpleframework.module.dict.DictItemStat;
import net.simpleframework.module.dict.IDictItemService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictItemService extends AbstractDictService<DictItem> implements IDictItemService {

	@Override
	public IDataQuery<DictItem> queryItems(final Dict dict, final ID domainId) {
		return _queryItems(dict, domainId, false);
	}

	@Override
	public IDataQuery<DictItem> queryRoot(final Dict dict, final ID domainId) {
		return _queryItems(dict, domainId, true);
	}

	private IDataQuery<DictItem> _queryItems(final Dict dict, final ID domainId, final boolean root) {
		final StringBuilder sb = new StringBuilder("1=1");
		final List<Object> params = new ArrayList<Object>();
		if (dict != null) {
			sb.append(" and dictid=?");
			params.add(dict.getId());
		}

		if (domainId != null) {
			sb.append(" and (domainid=? or domainid is null)");
			params.add(domainId);
		}

		if (root) {
			sb.append(" and parentid is null");
		}
		return query(sb, params.toArray());
	}

	@Override
	public DictItem getItemByCode(final Dict dict, final String codeNo) {
		if (dict == null) {
			return null;
		}
		return getBean("dictId=? and codeNo=?", dict.getId(), codeNo);
	}

	@Override
	public IDataQuery<DictItem> queryItemsByText(final Dict dict, final String text) {
		return query("dictId=? and text like '%" + SqlUtils.sqlEscape(text) + "%'", dict.getId());
	}

	@Override
	public void onInit() throws Exception {
		super.onInit();
		addListener(new DbEntityAdapterEx<DictItem>() {
			@Override
			public void onBeforeDelete(final IDbEntityManager<DictItem> manager,
					final IParamsValue paramsValue) throws Exception {
				super.onBeforeDelete(manager, paramsValue);
				for (final DictItem item : coll(manager, paramsValue)) {
					if (item.getItemMark() != EDictItemMark.normal) {
						throw DictException.of($m("DictItemService.0"));
					}
					// 含有下级不能删除
				}
			}

			@Override
			public void onAfterDelete(final IDbEntityManager<DictItem> manager,
					final IParamsValue paramsValue) throws Exception {
				super.onAfterDelete(manager, paramsValue);
				for (final DictItem item : coll(manager, paramsValue)) {
					// 更新状态
					updateStats(item);
				}
			}

			@Override
			public void onBeforeUpdate(final IDbEntityManager<DictItem> manager,
					final String[] columns, final DictItem[] beans) throws Exception {
				super.onBeforeUpdate(manager, columns, beans);
				for (final DictItem item : beans) {
					if (item.getItemMark() == EDictItemMark.builtIn) {
						throw DictException.of($m("DictItemService.1"));
					}
				}
			}

			@Override
			public void onAfterInsert(final IDbEntityManager<DictItem> manager, final DictItem[] beans)
					throws Exception {
				super.onAfterInsert(manager, beans);
				for (final DictItem item : beans) {
					// 更新状态
					updateStats(item);
				}
			}
		});
	}

	void updateStats(final DictItem item) {
		final DictItemStatService _diStatServiceImpl = (DictItemStatService) _dictItemStatService;
		final DictItemStat stat = _diStatServiceImpl.getDictItemStat(item.getDictId(),
				item.getDomainId());
		_diStatServiceImpl.setDictItemStat(stat);
		_diStatServiceImpl.update(stat);
	}
}

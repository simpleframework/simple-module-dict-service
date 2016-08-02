package net.simpleframework.module.dict.impl;

import java.util.List;

import net.simpleframework.ado.db.common.SQLValue;
import net.simpleframework.common.ID;
import net.simpleframework.common.coll.ArrayUtils;
import net.simpleframework.module.dict.DictItem;
import net.simpleframework.module.dict.DictItemStat;
import net.simpleframework.module.dict.IDictItemStatService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictItemStatService extends AbstractDictService<DictItemStat>
		implements IDictItemStatService {

	@Override
	public int getAllNums(final ID dictId, final String prop) {
		return sum(prop, "dictid=?", dictId).intValue();
	}

	@Override
	public DictItemStat getDictItemStat(final ID dictId, final ID domainId) {
		final StringBuilder sql = new StringBuilder("dictid=?");
		final List<Object> params = ArrayUtils.toParams(dictId);
		if (domainId != null) {
			sql.append(" and domainid=?");
			params.add(domainId);
		} else {
			sql.append(" and domainid is null");
		}
		DictItemStat stat = getBean(sql, params.toArray());
		if (stat == null) {
			stat = createBean();
			stat.setDictId(dictId);
			stat.setDomainId(domainId);
			setDictItemStat(stat);
			insert(stat);
		}
		return stat;
	}

	void setDictItemStat(final DictItemStat stat) {
		final List<Object> params = ArrayUtils.toParams(stat.getDictId());
		final StringBuilder sql = new StringBuilder("select count(*) as c from ")
				.append(getTablename(DictItem.class)).append(" i where i.dictid=?");
		final ID domainId = stat.getDomainId();
		if (domainId != null) {
			sql.append(" and i.domainid=?");
			params.add(domainId);
		} else {
			sql.append(" and i.domainid is null");
		}
		stat.setNums(getQueryManager().queryForInt(new SQLValue(sql, params.toArray())));
	}
}
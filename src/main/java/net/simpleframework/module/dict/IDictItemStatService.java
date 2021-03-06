package net.simpleframework.module.dict;

import net.simpleframework.common.ID;
import net.simpleframework.ctx.service.ado.db.IDbBeanService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IDictItemStatService extends IDbBeanService<DictItemStat> {

	/**
	 * 获取字典统计信息
	 * 
	 * @param dictId
	 * @param domainId
	 * @return
	 */
	DictItemStat getDictItemStat(ID dictId, ID domainId);

	int getAllNums(ID dictId, String prop);
}

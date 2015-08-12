package net.simpleframework.module.dict;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.ID;
import net.simpleframework.ctx.service.ado.ITreeBeanServiceAware;
import net.simpleframework.ctx.service.ado.db.IDbBeanService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IDictItemService extends IDbBeanService<DictItem>, ITreeBeanServiceAware<DictItem> {

	/**
	 * 获取指定字典的条目
	 * 
	 * @param dict
	 * @param domainId
	 * @return
	 */
	IDataQuery<DictItem> queryItems(Dict dict, ID domainId);

	/**
	 * 获取指定字典的第一级根条目
	 * 
	 * @param dict
	 * @param domainId
	 * @return
	 */
	IDataQuery<DictItem> queryRoot(Dict dict, ID domainId);

	/**
	 * 根据值获取字典条目
	 * 
	 * @param dict
	 * @param codeNo
	 * @return
	 */
	DictItem getItemByCode(Dict dict, String codeNo);

	/**
	 * 根据文本查找
	 * 
	 * @param dict
	 * @param text
	 * @return
	 */
	IDataQuery<DictItem> queryItemsByText(Dict dict, String text);
}

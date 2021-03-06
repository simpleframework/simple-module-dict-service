package net.simpleframework.module.dict;

import net.simpleframework.ctx.service.ado.ITreeBeanServiceAware;
import net.simpleframework.ctx.service.ado.db.IDbBeanService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IDictService extends IDbBeanService<Dict>, ITreeBeanServiceAware<Dict> {

	/**
	 * 根据名字获取字典
	 * 
	 * @param name
	 * @return
	 */
	Dict getDictByName(String name);
}

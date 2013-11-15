package net.simpleframework.module.dict;

import net.simpleframework.ado.bean.INameBeanAware;
import net.simpleframework.ado.db.DbEntityTable;
import net.simpleframework.ado.db.common.EntityInterceptor;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@EntityInterceptor(listenerTypes = { "net.simpleframework.module.log.EntityDeleteLogAdapter" })
public class Dict extends AbstractDict implements INameBeanAware {

	private String name;

	private EDictMark dictMark;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public EDictMark getDictMark() {
		return dictMark == null ? EDictMark.normal : dictMark;
	}

	public void setDictMark(final EDictMark dictMark) {
		this.dictMark = dictMark;
	}

	public static DbEntityTable TBL = new DbEntityTable(Dict.class, "sf_dict");

	private static final long serialVersionUID = -1642651214282707289L;
}

package net.simpleframework.module.dict;

import net.simpleframework.ado.bean.AbstractIdBean;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictItemStat extends AbstractIdBean {
	/* 类目id */
	private ID dictId;
	/* 域id */
	private ID domainId;

	public ID getDictId() {
		return dictId;
	}

	public void setDictId(final ID dictId) {
		this.dictId = dictId;
	}

	public ID getDomainId() {
		return domainId;
	}

	public void setDomainId(final ID domainId) {
		this.domainId = domainId;
	}

	private static final long serialVersionUID = 51479280726627150L;
}

package net.simpleframework.module.dict;

import net.simpleframework.ado.bean.AbstractTextDescriptionBean;
import net.simpleframework.ado.bean.IOrderBeanAware;
import net.simpleframework.ado.bean.ITreeBeanAware;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@SuppressWarnings("serial")
public abstract class AbstractDict extends AbstractTextDescriptionBean implements ITreeBeanAware,
		IOrderBeanAware {

	private ID parentId;

	private long oorder;

	@Override
	public ID getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(final ID parentId) {
		this.parentId = parentId;
	}

	@Override
	public long getOorder() {
		return oorder;
	}

	@Override
	public void setOorder(final long oorder) {
		this.oorder = oorder;
	}
}

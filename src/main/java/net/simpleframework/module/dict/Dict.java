package net.simpleframework.module.dict;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.bean.IDomainBeanAware;
import net.simpleframework.ado.bean.INameBeanAware;
import net.simpleframework.ado.db.common.EntityInterceptor;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@EntityInterceptor(listenerTypes = { "net.simpleframework.module.log.EntityDeleteLogAdapter" })
public class Dict extends AbstractDict implements INameBeanAware, IDomainBeanAware {
	/* 域 */
	private ID domainId;

	private String name;

	private EDictMark dictMark;

	@Override
	public ID getDomainId() {
		return domainId;
	}

	@Override
	public void setDomainId(final ID domainId) {
		this.domainId = domainId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public void setParentId(final ID parentId) {
		final Dict parent = _dictService.getBean(parentId);
		if (parent != null && parent.getDictMark() != EDictMark.category) {
			throw DictException.of($m("Dict.0"));
		}
		super.setParentId(parentId);
	}

	public EDictMark getDictMark() {
		return dictMark == null ? EDictMark.normal : dictMark;
	}

	public void setDictMark(final EDictMark dictMark) {
		this.dictMark = dictMark;
	}

	public static enum EDictMark {

		normal {
			@Override
			public String toString() {
				return $m("EDictMark.normal");
			}
		},

		/**
		 * 目录标识
		 */
		category {
			@Override
			public String toString() {
				return $m("EDictMark.category");
			}
		},

		/**
		 * 内置的字典标识
		 */
		builtIn {
			@Override
			public String toString() {
				return $m("EDictMark.builtIn");
			}
		}
	}

	private static final long serialVersionUID = -1642651214282707289L;
}

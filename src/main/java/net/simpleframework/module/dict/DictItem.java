package net.simpleframework.module.dict;

import java.util.Date;

import net.simpleframework.ado.ColumnData;
import net.simpleframework.ado.db.DbEntityTable;
import net.simpleframework.ado.db.common.EntityInterceptor;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
@EntityInterceptor(listenerTypes = { "net.simpleframework.module.log.EntityUpdateLogAdapter",
		"net.simpleframework.module.log.EntityDeleteLogAdapter" })
public class DictItem extends AbstractDict {

	private ID dictId;

	/**
	 * 编码
	 */
	private String codeNo;

	private EDictItemMark itemMark;

	/**
	 * 字典条目的创建日期
	 */
	private Date createDate;

	/**
	 * 字典条目的拥有人，null为全局
	 */
	private ID userId;

	public ID getDictId() {
		return dictId;
	}

	public void setDictId(final ID dictId) {
		this.dictId = dictId;
	}

	public EDictItemMark getItemMark() {
		return itemMark == null ? EDictItemMark.normal : itemMark;
	}

	public void setItemMark(final EDictItemMark itemMark) {
		this.itemMark = itemMark;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(final String codeNo) {
		this.codeNo = codeNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public ID getUserId() {
		return userId;
	}

	public void setUserId(final ID userId) {
		this.userId = userId;
	}

	public static DbEntityTable TBL = new DbEntityTable(DictItem.class, "sf_dict_item")
			.setDefaultOrder(ColumnData.ORDER);

	private static final long serialVersionUID = 5683629062027025972L;
}

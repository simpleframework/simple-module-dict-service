package net.simpleframework.module.dict;

import java.util.Date;

import net.simpleframework.ado.bean.IDateAwareBean;
import net.simpleframework.ado.bean.IDomainBeanAware;
import net.simpleframework.ado.db.common.EntityInterceptor;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@EntityInterceptor(listenerTypes = { "net.simpleframework.module.log.EntityUpdateLogAdapter",
		"net.simpleframework.module.log.EntityDeleteLogAdapter" }, columns = { "codeNo" })
public class DictItem extends AbstractDict implements IDateAwareBean, IDomainBeanAware {

	private ID dictId;

	/* 编码 */
	private String codeNo;

	private EDictItemMark itemMark;
	/* 字典条目的创建日期 */
	private Date createDate;

	/* 字典条目的拥有人，null为全局 */
	private ID userId;
	/* 域id */
	private ID domainId;

	/* 扩展字段1 */
	private String ext1;
	/* 扩展字段2 */
	private int ext2;
	/* 扩展字段3 */
	private String ext3;
	/* 扩展字段4 */
	private long ext4;

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

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public ID getUserId() {
		return userId;
	}

	public void setUserId(final ID userId) {
		this.userId = userId;
	}

	@Override
	public ID getDomainId() {
		return domainId;
	}

	@Override
	public void setDomainId(final ID domainId) {
		this.domainId = domainId;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(final String ext1) {
		this.ext1 = ext1;
	}

	public int getExt2() {
		return ext2;
	}

	public void setExt2(final int ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(final String ext3) {
		this.ext3 = ext3;
	}

	public long getExt4() {
		return ext4;
	}

	public void setExt4(final long ext4) {
		this.ext4 = ext4;
	}

	private static final long serialVersionUID = 5683629062027025972L;
}

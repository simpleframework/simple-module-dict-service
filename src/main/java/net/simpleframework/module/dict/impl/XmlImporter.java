package net.simpleframework.module.dict.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import net.simpleframework.common.StringUtils;
import net.simpleframework.ctx.common.xml.XmlDocument;
import net.simpleframework.ctx.common.xml.XmlElement;
import net.simpleframework.module.dict.Dict;
import net.simpleframework.module.dict.DictItem;
import net.simpleframework.module.dict.EDictItemMark;
import net.simpleframework.module.dict.EDictMark;
import net.simpleframework.module.dict.IDictItemService;
import net.simpleframework.module.dict.IDictService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class XmlImporter extends AbstractImporter {

	public void doImport(final InputStream iStream) {
		if (iStream != null) {
			try {
				doDict(new XmlDocument(iStream).getRoot().elementIterator("dict"), null);
			} catch (final Exception e) {
				log.warn(e);
			}
		}
	}

	private void doDict(final Iterator<XmlElement> it, final Dict parent) {
		final IDictService service = dictContext.getDictService();
		while (it.hasNext()) {
			final XmlElement element = it.next();
			final String name = element.attributeValue("name");
			if (!StringUtils.hasText(name)) {
				continue;
			}
			Dict dict = service.getDictByName(name);
			if (dict != null) { // 已存在
				continue;
			}

			dict = service.createBean();
			dict.setName(name);
			dict.setText(StringUtils.text(element.attributeValue("text"), name));
			final String str = element.attributeValue("dictMark");
			EDictMark dictMark = null;
			if (StringUtils.hasText(str)) {
				try {
					dictMark = EDictMark.valueOf(str);
				} catch (final Exception ex) {
				}
			}
			dict.setDictMark(dictMark == null ? EDictMark.builtIn : dictMark);
			dict.setDescription(element.elementText("description"));
			if (parent != null) {
				dict.setParentId(parent.getId());
			}

			service.insert(dict);

			doDictItem(element.elementIterator("item"), dict);
			doDict(element.elementIterator("dict"), dict);
		}
	}

	private void doDictItem(final Iterator<XmlElement> it, final Dict dict) {
		final IDictItemService service = dictContext.getDictItemService();
		while (it.hasNext()) {
			final XmlElement element = it.next();
			final String text = element.attributeValue("text");
			if (!StringUtils.hasText(text)) {
				continue;
			}

			final DictItem item = service.createBean();
			item.setDictId(dict.getId());
			item.setText(text);
			item.setCodeNo(element.attributeValue("codeNo"));
			final String str = element.attributeValue("itemMark");
			EDictItemMark itemMark = null;
			if (StringUtils.hasText(str)) {
				try {
					itemMark = EDictItemMark.valueOf(str);
				} catch (final Exception ex) {
				}
			}
			item.setItemMark(itemMark == null ? EDictItemMark.builtIn_r : itemMark);
			item.setCreateDate(new Date());
			item.setDescription(element.elementText("description"));
			service.insert(item);
		}
	}
}

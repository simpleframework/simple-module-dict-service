package net.simpleframework.module.dict.impl;

import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.object.ObjectFactory;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.ctx.common.IDataImportHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DefaultDictImportHandler implements IDataImportHandler {

	@Override
	public void doImport(final IApplicationContext application) throws Exception {
		ObjectFactory.singleton(XmlImporter.class).doImport(
				ClassUtils.getResourceAsStream(DefaultDictImportHandler.class, "dict-data.xml"));
	}

}

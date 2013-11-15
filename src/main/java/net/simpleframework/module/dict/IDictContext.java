package net.simpleframework.module.dict;

import static net.simpleframework.ctx.permission.IPermissionConst.ROLECHART_SYSTEM;
import net.simpleframework.ctx.IModuleRef;
import net.simpleframework.ctx.permission.PermissionRole;
import net.simpleframework.ctx.service.ado.db.IDbModuleContext;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IDictContext extends IDbModuleContext {

	static final String MODULE_NAME = "simple-module-dict";

	static final String ROLE_DICT_MANAGER = PermissionRole.toUniqueRolename(ROLECHART_SYSTEM,
			"dict_manager");

	/**
	 * 获取字典管理器
	 * 
	 * @return
	 */
	IDictService getDictService();

	/**
	 * 获取字典条目管理器
	 * 
	 * @return
	 */
	IDictItemService getDictItemService();

	/**
	 * 获取机构的引用对象
	 * 
	 * @return
	 */
	IModuleRef getOrganizationRef();
}

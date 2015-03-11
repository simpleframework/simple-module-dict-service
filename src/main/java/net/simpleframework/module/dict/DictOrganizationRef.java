package net.simpleframework.module.dict;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.module.dict.impl.DictContext;
import net.simpleframework.organization.OrganizationRef;
import net.simpleframework.organization.RolenameConst;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictOrganizationRef extends OrganizationRef {

	@Override
	public void onInit(final IModuleContext context) throws Exception {
		super.onInit(context);

		DictContext.ROLE_DICT_MANAGER = RolenameConst.toUniqueRolename(
				RolenameConst.ROLECHART_SYSTEM, "dict_manager");
		createRole_SystemChart(DictContext.ROLE_DICT_MANAGER, $m("DictOrganizationFace.0"));
	}
}

package net.simpleframework.module.dict;

import static net.simpleframework.common.I18n.$m;
import static net.simpleframework.module.dict.impl.DictContext.ROLE_DICT_MANAGER;
import net.simpleframework.ctx.IContextBase;
import net.simpleframework.organization.OrganizationRef;
import net.simpleframework.organization.bean.Role.ERoleType;
import net.simpleframework.organization.role.RolenameW;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class DictOrganizationRef extends OrganizationRef {

	@Override
	public void onInit(final IContextBase context) throws Exception {
		super.onInit(context);

		ROLE_DICT_MANAGER = RolenameW.toUniqueRolename(RolenameW.ROLECHART_ORG_DEFAULT, "dictmgr");
		RolenameW.registRole("dictmgr", $m("DictOrganizationRef.0"), null, ERoleType.normal);
	}
}

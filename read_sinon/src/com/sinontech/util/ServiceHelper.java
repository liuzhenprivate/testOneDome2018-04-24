package com.sinontech.util;

import com.sinontech.service.system.menu.MenuService;
import com.sinontech.service.system.role.RoleService;
import com.sinontech.service.system.user.UserService;

/**
 * @author Administrator 获取Spring容器中的service bean
 */
public final class ServiceHelper {

	public static Object getService(String serviceName) {
		// WebApplicationContextUtils.
		return Const.WEB_APP_CONTEXT.getBean(serviceName);
	}

	public static UserService getUserService() {
		return (UserService) getService("userService");
	}

	public static RoleService getRoleService() {
		return (RoleService) getService("roleService");
	}

	public static MenuService getMenuService() {
		return (MenuService) getService("menuService");
	}
}

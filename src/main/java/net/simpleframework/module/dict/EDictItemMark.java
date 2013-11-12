package net.simpleframework.module.dict;

import static net.simpleframework.common.I18n.$m;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public enum EDictItemMark {

	/**
	 * 可读写，删除
	 */
	normal {

		@Override
		public String toString() {
			return $m("EDictItemMark.normal");
		}
	},

	/**
	 * 内置的条目标识，只读
	 */
	builtIn_r {

		@Override
		public String toString() {
			return $m("EDictItemMark.builtIn_r");
		}
	},

	/**
	 * 内置的条目标识，可编辑
	 */
	builtIn_w {

		@Override
		public String toString() {
			return $m("EDictItemMark.builtIn_w");
		}
	}
}

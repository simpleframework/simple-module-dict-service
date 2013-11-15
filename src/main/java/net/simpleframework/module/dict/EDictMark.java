package net.simpleframework.module.dict;

import static net.simpleframework.common.I18n.$m;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public enum EDictMark {

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

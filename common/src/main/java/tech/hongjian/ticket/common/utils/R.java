/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package tech.hongjian.ticket.common.utils;


import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static final String KEY_CODE = "code";
	public static final String KEY_MSG = "msg";
	public static final String KEY_DATA = "data";
	
	public R() {
		put(KEY_CODE, 0);
		put(KEY_MSG, "success");
	}
	
	public static R error() {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put(KEY_CODE, code);
		r.put(KEY_MSG, msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put(KEY_MSG, msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public R data(Object data) {
		super.put(KEY_DATA, data);
		return this;
	}
}

package com.sf.repo.api.utils;

import org.json.JSONObject;

public class ObjetoJson {

	public static Object tentaRecuperarObjeto(JSONObject jsonObj, String key) {
		return jsonObj.has(key) ? jsonObj.opt(key) : null;
	}
}

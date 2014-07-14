package net.flower.ixmsxms_server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class DefaultController {	
	public void addAllAttributes(HttpServletRequest request, Map<String, Object> map) {
		String[] keys = map.keySet().toArray(new String[0]);
		for (int i = 0; i < keys.length; i++) {
			request.setAttribute(keys[i], map.get(keys[i]));
		}
	}
	
	public void addAllAttributes(HttpServletRequest request, Object object) {
		request.setAttribute("item", object);
	}
}
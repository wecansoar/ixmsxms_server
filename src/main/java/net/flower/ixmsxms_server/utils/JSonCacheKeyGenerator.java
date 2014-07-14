package net.flower.ixmsxms_server.utils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;

public class JSonCacheKeyGenerator implements CacheKeyGenerator<String> {
	@Override
	public String generateKey(MethodInvocation methodInvocation) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Method method = methodInvocation.getMethod();
		map.put("declaringClass", method.getDeclaringClass().getName());
		map.put("returnType", method.getReturnType().getName());
		map.put("methodName", method.getName());
		map.put("arguments", methodInvocation.getArguments());
		return GsonHttpMessageConverter.getDefaulGsonBuilder().create().toJson(map);
	}

	@Override
	public String generateKey(Object... data) {
		return GsonHttpMessageConverter.getDefaulGsonBuilder().create().toJson(data);
	}
}
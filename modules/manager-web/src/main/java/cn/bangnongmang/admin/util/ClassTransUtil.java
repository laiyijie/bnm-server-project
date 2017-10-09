package cn.bangnongmang.admin.util;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class ClassTransUtil<Target> {

	public Target transToTarget(Class<Target> targetClass, Object origin) {
		String jsonString = JSON.toJSONString(origin);
		return (Target) JSON.parseObject(jsonString, targetClass);
	}

	public List<Target> transToTarget(Class<Target> targetClass, List<?> pageResultOrigin) {
		List<Target> result = new ArrayList<>();
		for (Object item : pageResultOrigin) {
			String jsonString = JSON.toJSONString(item);
			result.add((Target) JSON.parseObject(jsonString, targetClass));
		}
		return result;
	}

	public PageResult<Target> transToTarget(Class<Target> targetClass, PageResult<?> pageResultOrigin) {
		List<?> origin = pageResultOrigin.getResult();
		List<Target> result = new ArrayList<>();
		for (Object item : origin) {
			String jsonString = JSON.toJSONString(item);
			result.add((Target) JSON.parseObject(jsonString, targetClass));
		}
		return new PageResult<Target>(pageResultOrigin, result);
	}
}

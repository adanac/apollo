package com.github.adanac.util.apollo.utils.data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {

	public static void main(String[] args) {

		CommonDto bean = new CommonDto();
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("id", "1001");
		mp.put("name", "商品部001");
		mp.put("age", 901);

		Map<String, Object> mp2 = new HashMap<String, Object>();
		mp2.put("id", "1001");
		mp2.put("name", "商品部001");
		mp2.put("age", "901");

		// 将map转换为bean
		transMap2Bean2(mp2, bean);
		System.out.println("--- transMap2Bean2 Map Info: ");
		for (Map.Entry<String, Object> entry : mp2.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		transMap2Bean(mp, bean);
		System.out.println("--- transMap2Bean Map Info: ");
		for (Map.Entry<String, Object> entry : mp.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println("--- Bean Info: ");
		System.out.println("id: " + bean.getId());
		System.out.println("name: " + bean.getName());
		System.out.println("age: " + bean.getAge());

		// 将javaBean 转换为map
		Map<String, Object> map = transBean2Map(bean);

		System.out.println("--- transBean2Map Map Info: ");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

	/**
	 * 推荐使用此方法
	 * 
	 * @param map
	 * @param obj
	 */
	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	/**
	 * 使用时map put值的类型必须要与dto中属性类型保持一致，否则抛异常
	 * 
	 * @param map
	 * @param obj
	 */
	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
			System.out.println("error " + e.toString());
			System.out.println("error " + e.getMessage());
		}

		return;

	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
}
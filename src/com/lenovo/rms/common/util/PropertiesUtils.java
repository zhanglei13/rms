package com.lenovo.rms.common.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	/* 外部不能实例化该类 */
	private PropertiesUtils() {
	}

	private static Properties props = new Properties();
	private static InputStream is = null;

	/**
	 * 获取properties文件属性
	 */

	public static String read(String key, String propName) {
		try {
			is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(propName);
			props.load(is);
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
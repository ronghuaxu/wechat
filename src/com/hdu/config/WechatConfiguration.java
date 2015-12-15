package com.hdu.config;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * 读取Properties文件 File: TestProperties.java User: xuronghua Date: 2015-12-15
 * 18:38:40
 */
public final class WechatConfiguration {
	private static String param1;
	private static String param2;

	static {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/wechat4j.properties");
		try {
			prop.load(in);
			param1 = prop.getProperty("wechat.token").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 私有构造方法，不需要创建对象
	 */
	private WechatConfiguration() {
	}

	public static String getParam1() {
		return param1;
	}

	public static String getParam2() {
		return param2;
	}

}

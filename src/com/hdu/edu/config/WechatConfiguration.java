package com.hdu.edu.config;

import org.apache.log4j.Logger;

/**
 * 读取Properties文件 File: TestProperties.java User: xuronghua Date: 2015-12-15 18:38:40
 */
/**
 * @author t
 *        
 */
public class WechatConfiguration
{
    
    public static final String bandingresponse = getString("bandingresponse");
    
    public static final String alreadybandingresponse = getString("alreadybandingresponse");
    
    public static final String baidu_translate_api = getString("baidu_translate_api");
    
   
    
    private static final Logger LOGGER = Logger.getLogger(WechatConfiguration.class);
    // static {
    // Properties prop = new Properties();
    // InputStream in = Object.class.getResourceAsStream("/wechatconfiguration.properties");
    // try {
    // prop.load(in);
    // bandingresponse = prop.getProperty("bandingresponse").trim();
    // alreadybandingresponse = prop.getProperty("alreadybandingresponse").trim();
    // baidu_translate_api = prop.getProperty("baidu_translate_api").trim();
    // sina_weather_api = prop.getProperty("sina_weather_api").trim();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    
    public static void main(String[] args)
    {
        System.out.println(WechatConfiguration.alreadybandingresponse);
    }
    
    
    
   
    
    private static String getString(String name)
    {
        return com.hdu.edu.config.Configuration.getInstance().getSingleValue(name, String.class);
    }
    
 
    
}

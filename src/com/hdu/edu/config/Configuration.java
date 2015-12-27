package com.hdu.edu.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration
{
    // 缓存对象
    private static final HashMap<String, String> CACHE = new HashMap<String, String>();
    
    private static final Logger LOGGER = Logger.getLogger(Configuration.class);
    
    /**
     * 私有构造方法，不需要创建对象
     */
    private Configuration()
    {
        load();
    }
    
    private static class SingletonHolder
    {
        private static final Configuration INSTANCE = new Configuration();
    }
    
    public static final Configuration getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
    
    public <T> String getSingleValue(String key, Class<T> t)
    {
        String value = getValueList(key);
        
        return value;
        
    }
    
    private String getValueList(String key)
    {
        
        return CACHE.get(key);
        
    }
    
    private void load()
    {
        Properties prop = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("wechatconfiguration.properties");
        
        try
        {
            prop.load(in);
        }
        catch (IOException e)
        {
            LOGGER.info("------> config found: ");
            e.printStackTrace();
        }
        for (Object o : prop.keySet())
        {
            String k = (String)o;
            // if (CACHE.containsKey(k))
            // {
            // continue;
            // }
            // else
            // {
            
            if (prop.containsKey(k))
            {
                String a = prop.getProperty(k);
                CACHE.put(k, prop.getProperty(k));
            }
            else
            {
                LOGGER.warn("警告！没有找到参数的值，参数名为:" + k);
            }
            // }
        }
        
        // try {
        // prop.load(in);
        // bandingresponse = prop.getProperty("bandingresponse").trim();
        // alreadybandingresponse = prop.getProperty("alreadybandingresponse").trim();
        // baidu_translate_api = prop.getProperty("baidu_translate_api").trim();
        // sina_weather_api = prop.getProperty("sina_weather_api").trim();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
    
}

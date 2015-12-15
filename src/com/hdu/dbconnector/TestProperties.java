package com.hdu.dbconnector;

import java.util.Properties;   
import java.io.InputStream;   
import java.io.IOException;   
  
/**  
* 读取Properties文件的例子  
* File: TestProperties.java  
* User: leizhimin  
* Date: 2008-2-15 18:38:40  
*/   
public final class TestProperties {   
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
    private TestProperties() {   
    }   
  
    public static String getParam1() {   
        return param1;   
    }   
  
    public static String getParam2() {   
        return param2;   
    }   
  
    public static void main(String args[]){   
        System.out.println(getParam1());   
    }   
}  
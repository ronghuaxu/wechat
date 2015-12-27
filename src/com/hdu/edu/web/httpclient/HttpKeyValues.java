package com.hdu.edu.web.httpclient;

import java.util.HashMap;
import java.util.Map;

public class HttpKeyValues
{
    private Map<String, String> map = new HashMap<String, String>();
    
    public HttpKeyValues addKeyValue(String key, String value)
    {
        this.map.put(key, value);
        
        return this;
    }
    
    public Map<String, String> getKeyValues()
    {
        return this.map;
    }
    
    public HttpKeyValues addKeyValue(Map<String, String> map)
    {
        this.map.putAll(map);
        
        return this;
    }
}

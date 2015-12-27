package com.hdu.edu.web.httpclient;

import net.dongliu.requests.RequestBuilder;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Response;
import net.dongliu.requests.Session;
import net.dongliu.requests.struct.Cookies;

import java.util.Map;

public class HttpClientHelper
{
    private Session session = Requests.session();
    
    private Cookies cookie = null;
    
    private HttpKeyValues defaultHeader = new HttpKeyValues();
    
    public void init(HttpKeyValues header)
    {
        defaultHeader = header;
    }
    
    public Cookies getCookie()
    {
        return this.cookie;
    }
    
    public String getContent(String url, HttpKeyValues header, HttpKeyValues param)
    {
        return get(url, header, param).getBody();
    }
    
    public String postContent(String url, HttpKeyValues header, HttpKeyValues param)
    {
        return post(url, header, param).getBody();
    }
    
    public String postContent(String url, HttpKeyValues header, HttpKeyValues param, Map<String, String> cookies)
    {
        return post(url, header, param, cookies).getBody();
    }
    
    public Response<String> get(String url, HttpKeyValues header, HttpKeyValues param)
    {
        Response<String> ret = null;
        
        if (header == null)
            header = new HttpKeyValues();
        if (param == null)
            param = new HttpKeyValues();
            
        this.overrideHeader(header);
        
        try
        {
            ret = session.get(url)
                .headers(header.getKeyValues())
                .params(param.getKeyValues())
                .allowRedirects(true)
                .text();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = null;
        }
        
        return ret;
    }
    
    public Response<String> post(String url, HttpKeyValues header, HttpKeyValues param)
    {
        Response<String> ret = null;
        
        if (header == null)
            header = new HttpKeyValues();
        if (param == null)
            param = new HttpKeyValues();
            
        this.overrideHeader(header);
        
        try
        {
            // 设置允许url请求的allowPostRedirects设置为true
            RequestBuilder rb =
                session.post(url).headers(header.getKeyValues()).data(param.getKeyValues()).allowPostRedirects(true);
            this.cookie = rb.bytes().getCookies();
            ret = rb.text();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = null;
        }
        
        return ret;
    }
    
    public Response<String> get(String url, HttpKeyValues header, HttpKeyValues param, Map<String, String> cookies)
    {
        Response<String> ret = null;
        
        if (header == null)
            header = new HttpKeyValues();
        if (param == null)
            param = new HttpKeyValues();
            
        this.overrideHeader(header);
        
        try
        {
            RequestBuilder rb = session.get(url).headers(header.getKeyValues()).data(param.getKeyValues());
            
            if (cookies != null)
                for (String key : cookies.keySet())
                {
                    rb.addCookie(key, cookies.get(key));
                }
                
            ret = rb.text();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = null;
        }
        
        return ret;
    }
    
    public RequestBuilder getRb(String url, HttpKeyValues header, HttpKeyValues param, Map<String, String> cookies)
    {
        RequestBuilder ret = null;
        
        if (header == null)
            header = new HttpKeyValues();
        if (param == null)
            param = new HttpKeyValues();
            
        this.overrideHeader(header);
        
        try
        {
            RequestBuilder rb = session.get(url).headers(header.getKeyValues()).data(param.getKeyValues());
            
            if (cookies != null)
                for (String key : cookies.keySet())
                {
                    rb.addCookie(key, cookies.get(key));
                }
                
            ret = rb;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = null;
        }
        
        return ret;
    }
    
    public RequestBuilder getPostRb(String url, HttpKeyValues header, HttpKeyValues param, Map<String, String> cookies)
    {
        RequestBuilder ret = null;
        
        if (header == null)
            header = new HttpKeyValues();
        if (param == null)
            param = new HttpKeyValues();
            
        this.overrideHeader(header);
        
        try
        {
            RequestBuilder rb = session.post(url).headers(header.getKeyValues()).data(param.getKeyValues());
            
            if (cookies != null)
                for (String key : cookies.keySet())
                {
                    rb.addCookie(key, cookies.get(key));
                }
                
            ret = rb;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = null;
        }
        
        return ret;
    }
    
    public Response<String> post(String url, HttpKeyValues header, HttpKeyValues param, Map<String, String> cookies)
    {
        Response<String> ret = null;
        
        if (header == null)
            header = new HttpKeyValues();
        if (param == null)
            param = new HttpKeyValues();
            
        this.overrideHeader(header);
        
        try
        {
            RequestBuilder rb = session.post(url).headers(header.getKeyValues()).data(param.getKeyValues());
            
            if (cookies != null)
                for (String key : cookies.keySet())
                {
                    rb.addCookie(key, cookies.get(key));
                }
                
            ret = rb.text();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = null;
        }
        
        return ret;
    }
    
    private void overrideHeader(HttpKeyValues newHeader)
    {
        for (String key : this.defaultHeader.getKeyValues().keySet())
        {
            if (!newHeader.getKeyValues().containsKey(key))
            {
                newHeader.addKeyValue(key, this.defaultHeader.getKeyValues().get(key));
            }
        }
    }
}

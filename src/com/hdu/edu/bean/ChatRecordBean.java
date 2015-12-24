package com.hdu.edu.bean;

import java.sql.Date;

public class ChatRecordBean
{
    private String open_id;
    private String request_msg;
    private String response_msg;
    private String category;
    private Date chat_date;
    
    public String getOpen_id()
    {
        return open_id;
    }
    public void setOpen_id(String open_id)
    {
        this.open_id = open_id;
    }
    public String getRequest_msg()
    {
        return request_msg;
    }
    public void setRequest_msg(String request_msg)
    {
        this.request_msg = request_msg;
    }
    public String getResponse_msg()
    {
        return response_msg;
    }
    public void setResponse_msg(String response_msg)
    {
        this.response_msg = response_msg;
    }
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }
    public Date getChat_date()
    {
        return chat_date;
    }
    public void setChat_date(Date chat_date)
    {
        this.chat_date = chat_date;
    }
  
    
}

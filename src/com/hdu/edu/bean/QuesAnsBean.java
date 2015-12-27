package com.hdu.edu.bean;

import java.util.Date;

public class QuesAnsBean
{
    
    private int id;
    
    private String question;
    
    private String answer;
    
    private String category;
    
    private Date operatingtime;
    
    private String flowername;
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public void setQuestion(String question)
    {
        this.question = question;
    }
    
    public String getAnswer()
    {
        return answer;
    }
    
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    
    public Date getOperatingtime()
    {
        return operatingtime;
    }
    
    public void setOperatingtime(Date operatingtime)
    {
        this.operatingtime = operatingtime;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getFlowername()
    {
        return flowername;
    }
    
    public void setFlowername(String flowername)
    {
        this.flowername = flowername;
    }
    
}

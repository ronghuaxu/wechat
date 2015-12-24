package com.hdu.edu.web.httpclient;

import java.text.MessageFormat;

public class getDataFromWeb
{
    
//    private final static String TEMPURL = "http://zhidao.189.cn/ask/getQuestionCon/";
    private final static String TEMPURL = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=WtzfFYTtXyTocv7wjUrfGR9W&q={0}&from=auto&to=zh";
    private final static int ENDDATE = 1596568;
    
    private final static int STARTDATE = 1596000;
    
   
    
    
    
    public static void main(String[] args)
    {
        // 创建一个HttpClientHelper对象
        HttpClientHelper httpClientHelper = new HttpClientHelper();
        
        String finalUrl=MessageFormat.format(TEMPURL, "hello");
//        System.out.println(finalUrl);
        String htmlbody = httpClientHelper.getContent(finalUrl, null, null);
        System.out.println(htmlbody);
        
        
//        for (int i = ENDDATE-1; i >= STARTDATE; i--)
//        {
//            String finalUrl = TEMPURL + String.valueOf(i) + ".html";
//            
//            String htmlbody = httpClientHelper.getContent(finalUrl, null, null);
//            
//            // 将源代码转化为Document对象
//            Document doc = Jsoup.parse(htmlbody);
//            
//            // 检测页面元素是否存在
//            Elements question = doc.getElementsByClass("ask-title");
//            
//            if (question.size()==0)
//            {
//                System.out.println(finalUrl + "该页面的问题已经不存在了");
//                continue;
//            }
//            else
//            {
//                
//                System.out.println("该页面的问题是"+question.get(0).text());
//                
//                Elements answer = doc.getElementsByClass("supply");
//                
//                System.out.println("该页面的问题是"+answer.get(0).text());
//                
//                QuesAnsBean quesansbean = new QuesAnsBean();
//                
//                Date now = new Date(System.currentTimeMillis());
//                
//                quesansbean.setOperatingtime(now);
//                
//                quesansbean.setFlowername("华子");
//                
//                quesansbean.setQuestion(question.get(0).text());
//                
//                quesansbean.setAnswer(answer.get(0).text());
//                
//                quesansbean.setCategory("电信爱问FAQ");
//                
//                QuesAnsMapperimpl quesansMapperimpl = new QuesAnsMapperimpl();
//                
//                quesansMapperimpl.addtwo(quesansbean);
//                
//                
//                System.out.println("获取第"+i +"个页面数据成功");
//            }
//            
//            
//        }
        
    }
    
}

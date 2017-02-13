## 微信基于电信业务的自动问答系统 (微信公众号的开发)
本系统使用lucene的全文检索引擎工具包和基于java语言开发的轻量级的中文分词工具包IK Analyzer,来分析用户对微信平台的提问,并从数据库中检索出最匹配的问题,如果精确度够高,就直接返回对应数据库中该问题的答案,如果精确度属于中等阈值,系统会给用户提示一些较相似的问题提示用户输入更精确的问题.如果阈值偏低,则默认用户的问题过于复杂,系统给予友好提示等.
##系统准备
1.Git and JDK 6 update 20 or later		
2.mysql 5.5 or later
##系统配置修改
配置修改:	
wechat4j.properties		
	you server url	
wechat.url=http://inc.dareyan.cn/
	you wechat token
wechat.token=xurh	
	message secret key,if don't set then message is cleartext	
wechat.encodingaeskey=******		
	wechat appid
wechat.appid=******			
	wechat app secret		
wechat.appsecret=******			
数据库配置:			
db/DBHelper.java			
`public static final String URL = "jdbc:mysql://127.0.0.1/tmp_grad?characterEncoding=UTF-8";`
    
` public static final String NAME = "com.mysql.jdbc.Driver";`
    
  `  public static final String USER = "root";`
    
   ` public static final String PASSWORD = "123qwe,./";`



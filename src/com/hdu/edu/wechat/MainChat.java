/**
 * Project Name:WeChat
 * File Name:mainchat.java
 * Package Name:com.hdu.wechat
 * Date:2015年11月16日下午1:36:01
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.hdu.edu.wechat;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.lang.JaxbParser;
import org.sword.lang.StreamUtils;
import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.common.ValidateSignature;
import org.sword.wechat4j.event.MsgType;
import org.sword.wechat4j.param.SignatureParam;
import org.sword.wechat4j.request.WechatRequest;
import org.sword.wechat4j.response.WechatResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdu.edu.bean.ChatRecordBean;
import com.hdu.edu.bean.translatebean;
import com.hdu.edu.config.ConstantsDailog;
import com.hdu.edu.db.DBHelper;
import com.hdu.edu.db.DBUtil;
import com.hdu.edu.lucene.seacher.NormalLuceneSearch;
import com.hdu.edu.lucene.seacher.PersonalLuceneSearch;
import com.hdu.edu.questionutil.QuestionProcessUtil;
import com.hdu.edu.web.httpclient.HttpClientHelper;

/**
 * ClassName:mainchat <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年11月16日 下午1:36:01 <br/>
 * 
 * @author xuronghua
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainChat extends WechatSupport
{
    
    private final Logger logger = Logger.getLogger(MainChat.class);
    
    private HttpServletRequest request;
    
    private ChatRecordBean chatrecordBean = new ChatRecordBean();

    public String execute(List<String> answerStore)
    {
        
        logger.info("WechatSupport run");
        SignatureParam param = new SignatureParam(request);
        String signature = param.getSignature();
        String timestamp = param.getTimestamp();
        String nonce = param.getNonce();
        String echostr = param.getEchostr();
        String token = Config.instance().getToken();
        
        ValidateSignature validateSignature = new ValidateSignature(signature, timestamp, nonce, token);
        
        if (!validateSignature.check())
        {
            return "error";
        }
        if (StringUtils.isNotBlank(echostr))
        {
            return echostr;
        }
        
        String postDataStr = null;
        try
        {
            postDataStr = StreamUtils.streamToString(request.getInputStream());
        }
        catch (IOException e)
        {
            logger.error("post data deal failed!");
            e.printStackTrace();
        }
        
        logger.info("parse post data--------------->:" + postDataStr);
        try
        {
            JaxbParser jaxbParser = new JaxbParser(WechatRequest.class);
            this.wechatRequest = (WechatRequest)jaxbParser.toObj(postDataStr);
            
        }
        catch (Exception e)
        {
            logger.error("post data parse error");
            e.printStackTrace();
        }
        
        dispatchMessage(answerStore);
        
        String result = null;
        try
        {
            
            JaxbParser jaxbParser = new JaxbParser(WechatResponse.class);
            // 设置
            jaxbParser.setCdataNode(WechatResponse.CDATA_TAG);
            
            result = jaxbParser.toXML(wechatResponse);
            
            logger.info("+++++++++++++++" + result);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public MainChat(HttpServletRequest request)
    {
        
        super(request);
        this.request = request;
        
    }
    
    /**
     * 消息事件分发
     */
    private void dispatchMessage(List<String> answerStore)
    {
        logger.info("distributeMessage start");
        if (StringUtils.isBlank(wechatRequest.getMsgType()))
        {
            logger.info("msgType is null");
        }
        MsgType msgType = MsgType.valueOf(wechatRequest.getMsgType());
        logger.info("msgType is " + msgType.name());
        switch (msgType)
        {
            case text:
                onText(answerStore);
                break;
            case image:
                onImage();
                break;
            case voice:
                onVoice();
                break;
            case video:
                onVideo();
                break;
            case location:
                onLocation();
                break;
            case link:
                onLink();
                break;
            default:
                onUnknown();
                break;
        }
    }
    
    @Override
    protected void click()
    {
        
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void location()
    {
        
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void locationSelect()
    {
        
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void onImage()
    {
        
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void onLink()
    {
        
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void onLocation()
    {
        
        // TODO Auto-generated method stub
        
    }
    
    protected void onText(List<String> answerStore)
    {
        NormalLuceneSearch normallucenesearch = new NormalLuceneSearch();
        
        PersonalLuceneSearch personallucenesearch = new PersonalLuceneSearch();
        
        String responseText = wechatRequest.getContent();
        
        String sql = "select telephone from telephone_openid_mapping where openid =" + "'"
            + wechatRequest.getFromUserName() + "'";
            
        DBHelper dbhelper = new DBHelper();
        
        dbhelper.getConnection(sql.toString());
        
        ResultSet resultSet = null;
        try
        {
            resultSet = dbhelper.pst.executeQuery();
        }
        catch (SQLException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if(answerStore.size()>0&&QuestionProcessUtil.isNumeric(responseText)){
            
            int i=Integer.parseInt(responseText);
            if(i<=0||i>answerStore.size()){
                chatrecordBean.setResponse_msg("不好意思,请您输入1-"+answerStore.size()+"以内的数字，亲~");
            }
            else{
                chatrecordBean.setResponse_msg(answerStore.get(i-1));
            }
            
        }
        else if (QuestionProcessUtil.strIsEnglish(responseText))
        {
            
            String finalURL =
                MessageFormat.format(ConstantsDailog.BAIDU_TRANSLATE_API, URLEncoder.encode(responseText));
                
            logger.info("finalURL==============" + finalURL);
            // 创建一个HttpClientHelper对象
            HttpClientHelper httpClientHelper = new HttpClientHelper();
            
            String htmlbody = httpClientHelper.getContent(finalURL, null, null);
            
            JSONObject object = JSONObject.parseObject(htmlbody);
            
            JSONArray result = object.getJSONArray("trans_result");
            
            List<translatebean> links = JSON.parseArray(result.toJSONString(), translatebean.class);
            
            logger.info(links.get(0).getDst());
            
            try
            {
                chatrecordBean.setRequest_msg(responseText);
                chatrecordBean.setOpen_id(wechatRequest.getFromUserName());
                if (resultSet.next())
                {
                    chatrecordBean = personallucenesearch.readytoAnswer(links.get(0).getDst(), chatrecordBean);
                    if (chatrecordBean.getCategory() == null)
                    {
                        chatrecordBean = normallucenesearch.readytoAnswer(links.get(0).getDst(), chatrecordBean,answerStore);
                        
                        // 为了保存用户输入的原始问题信息
                        
                        DBUtil.chatRecordTosql(chatrecordBean);
                    }
                }
                else
                {
                    chatrecordBean = normallucenesearch.readytoAnswer(links.get(0).getDst(), chatrecordBean,answerStore);
                    DBUtil.chatRecordTosql(chatrecordBean);
                }
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        else if (QuestionProcessUtil.isMobile(responseText))
        {
            
            try
            {
                
                if (resultSet.next())
                {
                    // 说明数据库中有该用户
                    // answer = "您已经进行用户绑定，绑定的手机号码为：" + resultSet.getString("telephone");
                    chatrecordBean
                        .setResponse_msg(ConstantsDailog.ALREADYBANDINGRESPONSE + resultSet.getString("telephone"));
                }
                else
                {
                    // 不存在对应用户,需要插入到数据库中
                    sql = "insert into telephone_openid_mapping (telephone,openid) values(?,?)";
                    
                    int index = 1;
                    
                    dbhelper.getConnection(sql);
                    
                    dbhelper.pst.setString(index++, responseText);
                    
                    dbhelper.pst.setString(index++, wechatRequest.getFromUserName());
                    
                    int size = -1;
                    size = dbhelper.pst.executeUpdate();
                    
                    if (size != -1)
                    {
                        // 数据库出错处理
                        // answer = "恭喜,绑定成功！";
                        chatrecordBean.setResponse_msg(ConstantsDailog.BANDINGRESPONSE);
                    }
                    
                }
                
                // 将用户的Fromusername和用户id号码进行绑定 并插入到数据库
                
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        else
        {
            
            try
            {
                chatrecordBean.setRequest_msg(responseText);
                
                chatrecordBean.setOpen_id(wechatRequest.getFromUserName());
                if (resultSet.next())
                {
                    // 查询该用户应该从用户的索引文件夹里面找
                    chatrecordBean = personallucenesearch.readytoAnswer(responseText, chatrecordBean);
                    
                    if (chatrecordBean.getCategory() == null)
                    {
                        chatrecordBean = normallucenesearch.readytoAnswer(responseText, chatrecordBean,answerStore);
                        
                        // 为了保存用户输入的原始问题信息
                        DBUtil.chatRecordTosql(chatrecordBean);
                    }
                }
                else
                {
                    chatrecordBean = normallucenesearch.readytoAnswer(responseText, chatrecordBean,answerStore);
                    DBUtil.chatRecordTosql(chatrecordBean);
                }
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        responseText(chatrecordBean.getResponse_msg());
        
    }
    
    @Override
    protected void onVideo()
    {
           
    }
    
    @Override
    protected void onVoice()
    {
        
        // TODO Auto-generated method stub
        
    }
    
   
    
    @Override
    protected void onUnknown()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void subscribe()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void unSubscribe()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void scan()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void view()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void templateMsgCallback()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void scanCodePush()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void scanCodeWaitMsg()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void picSysPhoto()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void picPhotoOrAlbum()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void picWeixin()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void onText()
    {
        // TODO Auto-generated method stub
        
    }
    
}

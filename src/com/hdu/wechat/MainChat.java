/**
 * Project Name:WeChat
 * File Name:mainchat.java
 * Package Name:com.hdu.wechat
 * Date:2015年11月16日下午1:36:01
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.hdu.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.lang.JaxbParser;
import org.sword.lang.StreamUtils;
import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.common.ValidateSignature;
import org.sword.wechat4j.param.SignatureParam;
import org.sword.wechat4j.request.WechatRequest;
import org.sword.wechat4j.response.WechatResponse;

import com.hdu.lucene.seacher.LuceneSearch;

/**
 * ClassName:mainchat <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年11月16日 下午1:36:01 <br/>
 * @author   t
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MainChat extends WechatSupport{
	
	Logger logger = Logger.getLogger(MainChat.class);
	
	private HttpServletRequest request;
	
	@Override
	public String execute() {
		
		logger.debug("WechatSupport run");
		SignatureParam param = new SignatureParam(request);
		String signature =param.getSignature();
		String timestamp = param.getTimestamp();
		String nonce = param.getNonce();
		String echostr = param.getEchostr();
		String token = Config.instance().getToken();
		
		ValidateSignature validateSignature = new ValidateSignature(signature, 
				timestamp, nonce, token);
		if(!validateSignature.check()){
			return "error";
		}
		if(StringUtils.isNotBlank(echostr)){
     		return echostr;
		}
		
		String postDataStr = null;
		try {
			 postDataStr = StreamUtils.streamToString(request.getInputStream());
		} catch (IOException e) {
			logger.error("post data deal failed!");
			e.printStackTrace();
		}
		
		logger.info("parse post data--------------->:" + postDataStr);
		try {
			JaxbParser jaxbParser = new JaxbParser(WechatRequest.class);
			this.wechatRequest = (WechatRequest)jaxbParser.toObj(postDataStr);
		} catch (Exception e) {
			logger.error("post data parse error");
			e.printStackTrace();
		}
		
		System.out.println("----------------->content:"+wechatRequest.getContent());
		
		LuceneSearch lucenesearch =new LuceneSearch ();
		
		String answer = lucenesearch.readytoAnswer(wechatRequest.getContent());
		
		responseText(answer);
		
		String result = null;
		try {
			JaxbParser jaxbParser = new JaxbParser(WechatResponse.class);
			//设置
			jaxbParser.setCdataNode(WechatResponse.CDATA_TAG);
			result = jaxbParser.toXML(wechatResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public MainChat(HttpServletRequest request) {
		
		super(request);
		this.request=request;
		
	}
	
	@Override
	protected void click() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void location() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void locationSelect() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onImage() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onLink() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onLocation() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onText() {
		responseText("hello world!");
	}

	@Override
	protected void onUnknown() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onVideo() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onVoice() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void picPhotoOrAlbum() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void picSysPhoto() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void picWeixin() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void scan() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void scanCodePush() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void scanCodeWaitMsg() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void subscribe() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void templateMsgCallback() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void unSubscribe() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void view() {
		
		// TODO Auto-generated method stub
		
	}

	protected void onShortVideo() {
		// TODO Auto-generated method stub
		
	}

	protected void kfCreateSession() {
		// TODO Auto-generated method stub
		
	}

	protected void kfCloseSession() {
		// TODO Auto-generated method stub
		
	}

	protected void kfSwitchSession() {
		// TODO Auto-generated method stub
		
	}

}


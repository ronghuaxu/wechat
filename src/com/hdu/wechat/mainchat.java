/**
 * Project Name:WeChat
 * File Name:mainchat.java
 * Package Name:com.hdu.wechat
 * Date:2015年11月16日下午1:36:01
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.hdu.wechat;

import javax.servlet.http.HttpServletRequest;

import org.sword.wechat4j.WechatSupport;

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
public class mainchat extends WechatSupport{

	public mainchat(HttpServletRequest request) {
		
		super(request);
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

}


/**
 * Project Name:WeChat
 * File Name:WechatServlet.java
 * Package Name:com.hdu.mainservlet
 * Date:2015年11月16日下午2:03:47
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.hdu.mainservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hdu.wechat.MainChat;

/**
 * ClassName:WechatServlet <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年11月16日 下午2:03:47 <br/>
 * 
 * @author t
 * @version
 * @since JDK 1.6
 * @see
 */
public class WechatServlet extends HttpServlet {

	Logger logger = Logger.getLogger(WechatServlet.class);

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	public WechatServlet() {

		super();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MainChat myWechat = new MainChat(req);

		String result = myWechat.execute();

		resp.getOutputStream().write(result.getBytes());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MainChat myWechat = new MainChat(req);

		String result = myWechat.execute();

		resp.getOutputStream().write(result.getBytes());
	}

}

package com.giscafer.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.giscafer.entity.User;
import com.giscafer.util.SessionUtil;
@WebListener
public class MyServletRequestLitener implements ServletRequestListener {
	//在线用户list
	private ArrayList<User> userList;
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		userList=(ArrayList<User>) arg0.getServletContext().getAttribute("userList");
		if(userList==null){
			userList=new ArrayList<User>();
		}
		HttpServletRequest req=(HttpServletRequest) arg0.getServletRequest();
		HttpSession session=req.getSession();
		//获取sessionId
		String sessionIdString=session.getId();
		if(SessionUtil.getUserBySessionId(userList,sessionIdString)==null){
			User user=new User();
			user.setSessionIdString(sessionIdString);
			user.setFirstTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			user.setIpString(req.getRemoteAddr());
			userList.add(user);
		}
		arg0.getServletContext().setAttribute("userList",userList);
	}

}

package com.giscafer.listener;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.giscafer.entity.User;
import com.giscafer.util.SessionUtil;
@WebListener
public class MyHttpSessionListener implements HttpSessionListener{
	private int userNumber=0;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		userNumber++;
		arg0.getSession().getServletContext().setAttribute("userNumber",userNumber);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		userNumber--;
		arg0.getSession().getServletContext().setAttribute("userNumber",userNumber);
		//在线用户list
		ArrayList<User> userList=(ArrayList<User>) arg0.getSession().getServletContext().getAttribute("userList");//
		Object obj=SessionUtil.getUserBySessionId(userList, arg0.getSession().getId());
		
		if(obj!=null){
			userList.remove(obj);
		}
	}
	
}

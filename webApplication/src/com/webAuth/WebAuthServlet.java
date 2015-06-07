package com.webAuth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Information;

public class WebAuthServlet extends HttpServlet
{     
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
		{
		    //WebApplicationContext cont=WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
            //(WebAuth) cont.getBean("webAuth");
		    WebAuth webAuth=new WebAuth();
            WebAuth.init();
            webAuth.setAction(new Information());
            webAuth.prepare(request,response); 
		}
		
		@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
        {
		    this.doPost(request, response);
	        
        }
		


}

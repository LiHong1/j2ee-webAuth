package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webAuth.WebAuth;

public class userResetPasswordServlet extends HttpServlet {

    

  
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Map map=WebAuth.getPassword(request.getParameter("password"), request);
         System.out.println(map.get("oldPassword"));
         System.out.println(map.get("newPassword1"));
         System.out.println(map.get("newPassword2"));
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          doGet(request,response);
    }

    

}

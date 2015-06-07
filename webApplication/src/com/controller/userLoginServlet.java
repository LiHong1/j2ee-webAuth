package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webAuth.WebAuth;

public class userLoginServlet extends HttpServlet {

    

  
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         System.out.println(WebAuth.loginAble(request, request.getParameter("password")));
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          doGet(request,response);
    }

    

}

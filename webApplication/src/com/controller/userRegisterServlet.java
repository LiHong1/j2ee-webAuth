package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webAuth.WebAuth;

public class userRegisterServlet extends HttpServlet {

   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String information=request.getParameter("password");
        System.out.println(information);
        Map<String,String> map=WebAuth.getUser(request,information);
        String userName=map.get("userName");
        String password=map.get("password");
        System.out.println(userName);
        System.out.println(password);
    }

   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

    

}

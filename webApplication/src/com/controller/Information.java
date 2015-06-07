package com.controller;

import javax.servlet.http.HttpServletRequest;

import com.webAuth.WebAuthInterface;

public class Information implements WebAuthInterface{

       @Override
    public String getUserPassword(HttpServletRequest request) {
        String userId=request.getParameter("username");
        if(userId.equals("1111"))
            return "0000";
        return null;
    }

}

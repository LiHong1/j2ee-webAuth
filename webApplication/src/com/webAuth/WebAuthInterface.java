package com.webAuth;

import javax.servlet.http.HttpServletRequest;

public interface WebAuthInterface {
  
    public String getUserPassword(HttpServletRequest request);
}

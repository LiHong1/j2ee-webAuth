<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    <script type="text/javascript" src="./js/webAuth/webAuth.js"></script>
    <script type="text/javascript" src="./js/webAuth/jquery-2.1.1.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/webAuth/jquery.safe.js"></script>
  </head>
  <script type="text/javascript">
   $(document).ready(function(){
	  
	   $(document).InitLogin({usernameId:'username',
           passwordId:'password',
           buttonId:'button',
           formId:'login',
           url:'webAuthServlet'
	   });
	  
   });
  </script>
  
  <body>
  
   <form id="login" name="login" action="${pageContext.request.contextPath}/servlet/userLoginServlet" method="post">
    <table>
    <tr>       
         <td width="100">  用户名：</td>
         <td width="200"><input type="text" name="username" id="username"/></td>
    </tr>
    <tr>
         <td>　密码：</td>
         <td> <input type="password" id="password" name="password"/></td>
    </tr>
    <tr>
         <td>　邮箱：</td>
         <td> <input type="text"  name="email"/></td>
    </tr>
    
    
    <tr>
         <td colspan="2"><input id="button" type="button" value="提 交" /></td>
         
    </tr>
        
    </table>
    
   </form>
   
    </body>
</html>


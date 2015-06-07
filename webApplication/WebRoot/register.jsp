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
	   $(document).InitRegister({usernameId:'username',
		                         passwordId:'password',
		                         buttonId:'button',
		                         formId:'register',
		                         url:'webAuthServlet'
		                         }
	   );
   });
  </script>
  <body>
   <form id="register" action="${pageContext.request.contextPath}/servlet/userRegisterServlet" method="post">
          用户名：<input type="text" name="username" id="username"/>
        　密码：<input type="password" id="password" name="password"/>
        <input id="button" type="button" value="注册" />
    </form>

    </body>
</html>


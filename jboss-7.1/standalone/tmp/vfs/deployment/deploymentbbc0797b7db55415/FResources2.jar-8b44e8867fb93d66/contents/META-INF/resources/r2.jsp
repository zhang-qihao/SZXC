<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: ZQ
  Date: 2016/11/8
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>R1.jsp

  你好:<%=request.getAttribute("loginUser") %>，现在时间是<%= new Date() %>

  $END$
  </body>
</html>

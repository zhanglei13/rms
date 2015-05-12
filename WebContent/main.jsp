<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>欢迎光临RMS</title>
  </head>
  
  <body>
   <BR/><BR/><BR/><BR/><BR/><BR/><BR/><BR/><BR/>
   <center>欢迎光临RMS</center>
  </body>
</html>

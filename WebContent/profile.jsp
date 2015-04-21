<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>RMS-Login</title>
    <meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="res/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="res/jquery-easyui-1.2.6/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="res/jquery-easyui-1.2.6/themes/icon.css" />
	<script type="text/javascript" src="res/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="res/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
  </head>
  <body >
  <BR/>
  <div id="logindiv" class="easyui-panel"  title="Personal Information"  >
    				<table>
    					<tr >
    						<td colspan="4" rowspan="4"><img id="avatar"  src="res/img/profile-pic.jpg" /></td>
    						<td align="right">Name:</td>
    						<td align="left">GAOHaiGang</td>
    						
    					</tr>
    					<tr>
    						<td align="right">IT code:</td>
    						<td align="left">123456</td>
    					</tr>
    					<tr >
    						<td align="right">Tower:</td>
    						<td align="left">FES</td>
    					</tr>  
    					<tr >
    						<td align="right">Password:</td>
    						<td align="left">Password:</td>
    					</tr> 					 					    					    					    					    					    					    					    					
    				</table>
  </div>	
  </body>
  </body>
</html>

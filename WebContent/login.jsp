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
	<script type="text/javascript">
	$(function(){
	$('#loginbutton').click(function(){
				$('#loginform').form('submit',{
						url:'login' , 
						onSubmit:function(){
							if(!$('#loginform').form('validate')){
								$.messager.show({
									title:'Notice' , 
									msg:'username!'
								});
								return false ;		//当表单验证不通过的时候 必须要return false 
							}
						} ,
						success:function(result){
							var result = $.parseJSON(result);
							$.messager.show({
								title:result.status , 
								msg:result.notice
							});
						}
				});
			});
	});
	</script>
  </head>
  <body >
  <center>
  <div id="logindiv" class="easyui-panel" style="width:400px;height:350px;align:center" title="RMS-Login"  >
    		<form id="loginform" action="" method="post">
    				<table>
    					<tr>
    						<td>Username:</td>
    						<td><input type="text" name="username" class="easyui-validatebox" required=true  missingMessage="must"  value="" /></td>
    					</tr>
    					<tr>
    						<td>Password:</td>
    						<td><input type="password" name="password" class="easyui-validatebox" required=true  missingMessage="must" value="" /></td>
    					</tr>
    					<tr align="center">
    						<td colspan="2">
    							<a id="loginbutton" class="easyui-linkbutton">Login</a>
    						</td>
    					</tr>  
    					<tr align="center">
    						<text id="notice"></text>
    					</tr> 					 					    					    					    					    					    					    					    					
    				</table>
    		</form>
    	</div>
    	</center>		
  </body>
  </body>
</html>

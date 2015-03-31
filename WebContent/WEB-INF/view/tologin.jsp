<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="common/header.jsp" %>
<title>Login - RMS Admin</title>
<script type="text/javascript">
       window.location.href="login.jsp?flag=${flag}&info=${info}"; 
</script>
</head>
<body>
<%@include file="common/importJs.jsp" %> 
</body>
</html>
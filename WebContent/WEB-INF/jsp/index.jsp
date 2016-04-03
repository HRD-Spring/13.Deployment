<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Home</title>
<link href='<c:url value="/css/style.css"></c:url>' rel="stylesheet">
<script type="text/javascript" src='<c:url value="/js/demo.js"></c:url>'></script>
</head>
<body>
	<a class="demo" href="${pageContext.request.contextPath}/login">Login</a>
	<a class="demo" href="${pageContext.request.contextPath}/signup">Sign Up</a><br>
	<img alt="demo" src='<c:url value="/img/pd.png" />' />
	
	<a onclick="hello()">sign up</a>
</body>
</html>
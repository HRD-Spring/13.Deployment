<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<h1>Login</h1>

	<label style="color: red;">${errorMsg}</label>
	<form:form action="${pageContext.request.contextPath}/login" commandName="user"
		method="post">
  First name:<br>
		<form:input type="text" path="username" name="username" />
		<br>
		<form:errors path="username" style="color:red"></form:errors>
		<br>
  Password:<br>
		<form:input type="text" path="password" name="password" />
		<br>
		<form:errors path="password" style="color:red"></form:errors>
		<br>
		<input type="submit" value="login" />
	</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>

<script type="text/javascript">
	function doValidation(){
		var username = document.getElementById("username").value;
		if(parseInt(username.length) < 4){
			alert("Username must be at least 4 characters long");
			return false;
		}
		
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		
		if(parseInt(password.length) < 6){
			alert("Password must be at least 6 characters long");
			return false;
		}
		
		if(password != repassword){
			alert("Password and re-password are not same.");
			return false;
		}
		
		return true;
	}

</script>
</head>
<body>

<div>
<label>${message }</label>
			<form  action="${pageContext.request.contextPath}/signup" method="post" enctype="multipart/form-data" onsubmit="return doValidation()"> 
				<h1>Sign Up</h1> 
				<p> 
					<label for="username" > Your email or username </label>
					<input id="username" name="username" type="text" placeholder="username"/>
				</p>
				<p> 
					<label for="password"> Your password </label>
					<input id="password" name="password" type="password" placeholder="eg. X8df!90EO" /> 
				</p>
				<p> 
					<label for="repassword">Re-enter Your password </label>
					<input id="repassword" name="repassword" type="password" placeholder="eg. X8df!90EO" /> 
				</p>
				<p> 
					<label>Choose Gender</label>
					<input type="radio" name="gender" value="Male" checked/> 
					<input type="radio" name="gender" value="Female" /> 
				</p>
				<p> 
					<label>Select Vehicle </label>Car:
					<input type="checkbox" name="vehicle" value="Car" />Bike:
					<input type="checkbox" name="vehicle" value="Bike" />
				</p>
				<p> 
					<label>Select Country</label>
					<select name="country">
						<option value="khmer">Khmer</option>
						<option value="usa">USA</option>
						<option value="uk" selected>UK</option>
					</select>
				</p>
				
				<p> 
					<label>Choose File</label>
					<input type="file" name="image" />
				</p>
				
				<p class="Sign Up button"> 
					<input type="submit" value="sign up" /> 
				</p>
			</form>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
</head>
<body>
	<table border="1" id="productTable">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Image</th>
		</tr>

		<tr align="center" id="tr_${product.id}">
			<td>${product.name}</td>
			<td>$ ${product.price}</td>
			<td>${product.description}</td>
			<td><img width="250px" height="200px"
				src="${product.image}"></td>
		</tr>


	</table>

</body>
</html>
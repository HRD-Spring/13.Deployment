<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function deleteProduct(id){
		id = id.split("_")[1];
		alert("call");
		$.ajax({
			
			url:"${pageContext.request.contextPath}/deleteProduct",
			type:"post",
			data:"productId="+id,
			
			success:function(respone){
				var table =  document.getElementById("productTable");
				var tr = document.getElementById("tr_"+id);
				table.deleteRow(tr.rowIndex);
				alert(respone);
			},
			error:function(error){
				alert(error);
			}
			
		});
	}

</script>
</head>
<body>
	<table border="1" id="productTable">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Image</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${allProducts}" var="product">
			<tr align="center" id="tr_${product.id}">
				<td>${product.name}</td>
				<td>$ ${product.price}</td>
				<td>${product.description}</td>
				<td><img width="250px" height="200px"
					src="${pageContext.request.contextPath}/img/${product.image}"></td>
					<td><input type="button" value="Delete" id="btn_${product.id}" onclick="deleteProduct(this.id)"></td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>
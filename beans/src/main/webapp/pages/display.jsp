<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/lightbox.min.css" />
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" />
<title>Display</title>
</head>
<style type="text/css">
	table{
		margin:0 100px;
		float:left;
	}
	#clear{
		clear:both;
	}
	#photo{
		height:500px;

	}

</style>
<script type="text/javascript" src="../js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function(){
		$("tr:even td").css('background-color','pink');
		$("IMG").css('border','3px solid black');
		console.log("hello");
		
		
		$("tbody tr td:nth-child(2)").mouseover(function() {
			
// 			var thisCell = $(this).parent().children(':last-child').html();
// 			console.log(thisCell);
// 			$("#photo").html("<img src='/beans/pages/photo.view?photoid="+thisCell+"'>");
			var thisCell = $(this).parent().children(':first-child').text();
			
			$("#photo").html("<img src='/beans/pages/photo.view?photoid="+thisCell+"'>");
			console.log(thisCell);
			$("#photo").fadeToggle(1000,1,0.5);
		});
		
		$("tbody tr td:nth-child(2)").mouseout(function() {
			$("#photo").html("");
		});
		
		$("tbody tr td:last-child a").attr("data-lightbox", "light");
		$('table').DataTable();
		
		
		
	});
	

	function doMouseOut(){
		document.getElementById("photo").innerHTML ="";
	}

	function doMouseOver(url){
		$("#photo").html("<img src='"+url+"'>");
// 		imgNode = document.createElement("IMG");
// 		imgNode.src = url;
// 		console.log(url);
// 		document.getElementById("photo").appendChild(imgNode);
	}
	
</script>
<body>

<h3>Select Product Table Result : XXX row(s) selected</h3>
<c:if test="${select !=null}">
<table>
	<thead>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Price</th>
		<th>Make</th>
		<th>Expire</th>
		<th>Photo</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="bean" items="${select}">
	<tr>
	<c:url var="path" value="/pages/product.jsp">
		<c:param name="id" value="${bean.id}"></c:param>
		<c:param name="name" value="${bean.name}"></c:param>
		<c:param name="price" value="${bean.price}"></c:param>
		<c:param name="make" value="${bean.make}"></c:param>
		<c:param name="expire" value="${bean.expire}"></c:param>
	</c:url>
	<c:url var="url" value="/pages/photo.view">
		<c:param name="photoid" value="${bean.id}"></c:param>
	</c:url>
		<td><a href="${path}">${bean.id}</a></td>
		<td>${bean.name}</td>
		<td>${bean.price}</td>
		<td><label onmouseover="doMouseOver('${url}')" onmouseout="doMouseOut()">${bean.make}</label></td>
		<td>${bean.expire}</td>
		<td><a href="${url}"> Photo</a></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
	
</c:if>
<div id="photo"></div>
<h3 id="clear"><a href="/beans/pages/product.jsp">Product Table</a></h3>

<script type="text/javascript" src="../js/lightbox.min.js"></script>

</body>
</html>
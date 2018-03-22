<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
	table#stockTable {
		width: 50%;
		background-color: #f1f1c1;
	}
	
	table#stockTable tr:nth-child(even) {
		background-color: #eee;
	}
	
	table#stockTable tr:nth-child(odd) {
		background-color: #fff;
	}
	
	table#stockTable th {
		color: white;
		background-color: black;
	}
	
	table, th, td {
		border-collapse: collapse;
	}
	
	th, td {
		padding: 5px;
		text-align: left;
	}
</style>
<title>Stock Market</title>
</head>
<body>
	<div class="container">

		<table id="stockTable">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Current Price</th>
			</tr>
			<c:forEach items="${stocks}" var="stock">
				<tr>
					<td><c:out value="${stock.id}" /></td>
					<td><c:out value="${stock.name}" /></td>
					<td><c:out value="${stock.currentPrice}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
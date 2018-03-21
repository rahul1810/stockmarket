<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Stock Market</title>
</head>
<body>
	<div class="container">

<table>
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
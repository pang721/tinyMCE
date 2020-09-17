<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html>
<head>
    <title>展示</title>
</head>
<body>
<table>

    <c:forEach var="vac" items="${v}">
        <tr>
            <td>${vac.id}</td>
            <td>${vac.ymmc}</td>
            <td>${vac.ymlb}</td>
            <td>${vac.zc}</td>
            <td>${vac.zcms}</td>
        </tr>
    </c:forEach>
</table>
<div class="page-link-content">
    <ul class="pagination pagination-sm">${pageStr}</ul>
</div>
</body>
</html>

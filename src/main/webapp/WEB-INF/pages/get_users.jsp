<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<c:forEach items="${users}" var="users">
    <h6>${users.id}</h6>
    <h6>${users.username}</h6>
    <h6>${users.userLocation}</h6>
    <h6>${users.registrationDate}</h6>
    <h3>----------------------</h3>
</c:forEach>
</body>
</html>

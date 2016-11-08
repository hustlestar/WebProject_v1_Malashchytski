<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <c:if test="${requestScope.get('errorMessage')!=null}">
        <c:out value="${requestScope.get('errorMessage')}"/>
    </c:if>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<HTML>
<head>
    <title>Test JSP Page</title>
</head>
<BODY>
<%
    System.out.println("Evaluating date now");
    java.util.Date date = new java.util.Date();
%>

Hello! The time is now <%= date %>
${key}
<c:forEach items="${privilegeEntityList}" var="privilegeEntity">
    <h1>${privilegeEntity.privilegeName}</h1>
</c:forEach>
<c:forEach items="${pp}" var="name">
    <h1>${name}</h1>
</c:forEach>

</BODY>
</HTML>
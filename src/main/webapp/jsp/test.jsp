<%@ page import="javax.servlet.jsp.jstl.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<BODY>
<%
    System.out.println( "Evaluating date now" );
    java.util.Date date = new java.util.Date();
%>

Hello! The time is now <%= date %> ${key}
<c:forEach items="${privilegeEntityList}" var="privilegeEntity">
    <h1>${privilegeEntity.privilegeName}</h1>
</c:forEach>
</BODY>
</HTML>
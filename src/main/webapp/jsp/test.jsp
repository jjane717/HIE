<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<HTML>
<BODY>
<%
    System.out.println( "Evaluating date now" );
    java.util.Date date = new java.util.Date();
%>

Hello! The time is now <%= date %>
</BODY>
</HTML>
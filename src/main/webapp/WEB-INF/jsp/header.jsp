<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="header">
    <div id="title">
        <div id="title-image"><img src="../images/heart.png" width="100px" height="100px"/></div>
        <div id="title-sub">
            <p id="title-one"><span>YJ</span> Health Insurance Exchange Markets</p>
            <p id="title-two">Find Your Appropriate Health Insurance Here.</p>
            <p id="title-three">Please Provide Correct Information.</p>
        </div>
    </div>
    <div id="title-right">
        <p id="title-four">Hello,
            <jsp:useBean id="user" type="org.yijun.hie.persistence.entity.UserAccountEntity" scope="session"/>
            <jsp:getProperty name="user" property="userName"/>
        </p>
        <p id="title-five">
            <a href="<c:url value="http://localhost:8080/logout" />">Logout</a>
        </p>
    </div>

</div>

<style>

</style>
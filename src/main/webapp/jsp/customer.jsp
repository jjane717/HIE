<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>System Management</title>
    <link href="../css/css.css" rel="stylesheet">
    <link href="../css/css2.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="../js/js.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<h1 id="h"></h1>
<div id="wrap">
    <div id="headerholder"></div>
    <div id="container">
        <table id="product">
            <c:forEach items="">

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
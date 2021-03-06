<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<script>
    $(document).ready(function(){
        $("#headerholder").load("userLogin");

        $("#customer-topmenu ul li").hover(function(){
            $(this).removeClass("holdlightblue");
            $(this).addClass("darkorange");
        },function(){
            $(this).removeClass("darkorange");
            if(!$(this).hasClass("clickdarkorange")){
                $(this).addClass("holdlightblue");
            }
        });

        $("#customer-topmenu ul li").click(function(){
            $("#customer-topmenu ul li").removeClass("clickdarkorange");
            $("#customer-topmenu ul li").addClass("holdlightblue");
            $(this).removeClass("holdlightblue");
            $(this).addClass("clickdarkorange");

            var urll = $(this).attr("source");
            $("#customer-container").load(urll);
        });

        $("#customer-topmenu ul li:first-child").removeClass("holdlightblue");
        $("#customer-topmenu ul li:first-child").addClass("clickdarkorange");
        var urll = $("#customer-topmenu ul li:first-child").attr("source");
        $("#customer-container").load(urll);
    });
</script>
<body>
<h1 id="h"></h1>
<div id="wrap">
    <div id="headerholder">
        <%--<jsp:include page="header.jsp"/>--%>
    </div>
    <div id="container">
        <div id="customer-topmenu">
            <ul id="privilege">
                <c:forEach items="${privileges}" var="privilege">
                    <li class="holdlightblue" source="${privilege.privilegeFile}">
                        <label>${privilege.privilegeName}</label>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="customer-container">
            <div style="text-align: center; margin-top:150px">
                <h1>Manage System</h1>
            </div>
        </div>
        <div id="test"></div>
    </div>
</div>
</body>
</html>
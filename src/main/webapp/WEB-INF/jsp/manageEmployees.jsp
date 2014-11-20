<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(document).ready(function(){
        $("#enterpriseTable tr").hover(function(){
            $(this).addClass("one");
        },function(){
            $(this).removeClass("one");
        });
    });

</script>

<div>
    <div id="create">
        <div id="filter">
            <span>Search By Employee ID: </span><br/>
            <input type="search" id="searchEmployeeID" onchange="searchEmployee()"/>
        </div>
        <div class="submit_button hidden" onclick="changePrivilege()">
            <label>Make Change</label>
        </div>
    </div>
    <div width="570px">
        <div id="chooseOrder">
            <form id="employeeInfo">
                <table id="enterpriseTable" cellspacing="0">
                    <tr>
                        <td colspan="5" class="tableTitle">Choose User Role</td>
                    </tr>
                    <tr>
                        <th class="details"></th>
                        <th>User Name</th>
                        <th>Employee ID</th>
                    </tr>
                    <c:forEach items="${userRoles}" var="user">
                        <tr class="main ${user.roleEntity.idEmployee}">
                            <td><input type="radio" name="selectEmployee" value="${user.roleEntity.idRole}" onchange="chooseEmployee()"></td>
                            <td>${user.userName}</td>
                            <td>${user.roleEntity.idEmployee}</td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="choosePayment-container">
    <div id="paymentDiv">
        <form id="paymentInfo">
            <input type="text" class="hidden" id="idRole" name="idRole"/>
            <table id="paymentTable" cellspacing="0">
                <tr>
                    <td colspan="2" class="tableTitle">Choose Privilege</td>
                </tr>
                <c:forEach items="${available}" var="all">
                    <tr>
                        <td>
                            <input type="checkbox" value="${all.idPrivilege}" class="selectPrivilege" name="selectedPrivilege" id="${all.privilegeFile}" disabled="disabled"/>
                        </td>
                        <td>${all.privilegeName}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>

<style>
    .submit_button{
        float: right;
        margin-top: 15px;
        margin-right: 30px;
    }

    .submit_button label{
        cursor: pointer;
    }

    .details{
        width: 50px;
        cursor: pointer;
    }

    .tableTitle{
        font-size: 22px;
        color: crimson;
        font-weight: bolder;
        text-align: center;
    }

    #cardTable td{
        /*margin-top: 20px;*/
        padding-top: 10px;
        text-align: center;
    }

    #chooseOrder{
        overflow:scroll;
        width: 350px;
        float:left;
    }

    #create{
        border-bottom: 2px solid red;
        height:60px;
        margin:30px 30px 0px 30px;
    }

    #filter{
        float:left;
    }

    #create span{
        font-size: 18px;
        font-weight: bolder;
        color: #90111a;
        margin-bottom: 5px;
    }

    #enterpriseTable{
        margin-top: 20px;
        width:330px;
        margin-left: 10px;
    }

    #enterpriseTable th{
        font-size: 18px;
        broder-right: 1px solid lightgray;
        padding-top: 10px;
    }

    #enterpriseTable tr{
        margin-top: 10px;
        padding: 20px;
        font-size: 14px;
        text-align: center;
    }
    #enterpriseTable td{
        height: 50px;
        width: auto;
        border-bottom: 1px solid lightgray;
    }

    #choosePayment-container{
        /*border: 3px solid crimson;*/
        border-radius: 5px;
        float: left;
        margin-top:10px;
    }

    #paymentDiv{
        overflow:scroll;
        width:220px;
    }

    #paymentTable{
        margin-top: 10px;
        width:200px;
        margin-left: 10px;
    }

    #paymentTable th{
        font-size: 18px;
        broder-right: 1px solid lightgray;
    }

    #paymentTable tr{
        margin-top: 10px;
        padding: 20px;
        font-size: 14px;
        text-align: center;
    }
    #paymentTable td{
        height: 50px;
        width: auto;
        border-bottom: 1px solid lightgray;
    }



</style>
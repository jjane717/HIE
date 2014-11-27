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
<div id="create">
    <div id="filter">
        <span>Filter By Enterprise: </span><br/>
        <select name="enterpriseName" id="filterEnterprise" onchange="filterUserByEnterprise()">
            <option value="All">All</option>
            <c:forEach items="${enterprises}" var="enterprise">
                <option value="${enterprise.enterpriseName}">${enterprise.enterpriseName}</option>
            </c:forEach>
        </select>
    </div>
    <div id="account_save_button" class="submit_button" onclick="createUserAccount()">
        <label>Create New User Account</label>
    </div>
</div>

<div id="enterpriseInfo">
    <table id="enterpriseTable" cellspacing="0">
        <tr>
            <th>User Name</th>
            <th>Email</th>
            <th>Name</th>
            <th>Role</th>
            <th>Enterprise</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${userAccounts}" var="user">
            <tr id="userAccountInfo_${user.idUserAccount}" class="main ${user.enterpriseEntity.enterpriseName}">
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.roleEntity.roleName}</td>
                <td>${user.enterpriseEntity.enterpriseName}</td>
                <c:if test="${user.status == false}">
                    <td>
                        <input id="checkbox-${user.idUserAccount}" type="checkbox" onchange=changeUserStatus("${user.idUserAccount}")>
                    </td>
                </c:if>
                <c:if test="${user.status == true}">
                    <td>
                        <input id="checkbox-${user.idUserAccount}" type="checkbox" checked="true" class="checkStatus" onchange=changeUserStatus("${user.idUserAccount}")>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<style>
    .submit_button{
        float:left;
    }

    #create{
        margin: 30px;
        border-bottom: 2px solid red;
        height:50px;
    }

    #filter{
        float:left;
    }

    #create span{
        font-size: 18px;
        font-weight: bolder;
        color: #90111a;
    }

    #enterpriseInfo{
        margin: 20px;
    }
    #enterpriseTable{
        width:570px;
    }

    #enterpriseTable th{
        font-size: 18px;
        broder-right: 1px solid lightgray;
    }

    #enterpriseTable tr{
        margin-top: 10px;
        padding: 20px;
        font-size: 14px;
        text-align: center;
    }
    #enterpriseTable td{
        height: 50px;
        width: 100px;
        border-bottom: 1px solid lightgray;
    }

</style>

<div id="dialog-form" title="Create New User Account" class="hidden">
    <p class="validateTips hidden">All form fields are required.</p>

    <form id="userAccountInfoForm">
        <fieldset>
            <table>
                <tr>
                    <td><label for="userName">User Name: </label></td>
                    <td><input type="text" name="userName" id="userName" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="password">Password: </label></td>
                    <td><input type="text" name="password" id="password" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="idRole">Role Name: </label></td>
                    <td>
                        <select id="idRole" name="idRole" class="text ui-widget-content ui-corner-all roleOption">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="idEnterprise">Enterprise Name: </label></td>
                    <td>
                        <select id="idEnterprise" name="idEnterprise" class="text ui-widget-content ui-corner-all enterpriseOption">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="firstName">First Name: </label></td>
                    <td><input type="text" name="firstName" id="firstName" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="lastName">Last Name: </label></td>
                    <td><input type="text" name="lastName" id="lastName" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="dateOfBirth">Date Of Birth: </label></td>
                    <td><input type="text" name="dateOfBirth" id="dateOfBirth" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="age">Age: </label></td>
                    <td><input type="text" name="age" id="age" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="street">Street: </label></td>
                    <td><input type="text" name="street" id="street" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="city">City: </label></td>
                    <td><input type="text" name="city" id="city" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" name="state" id="state" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="zip">Zip:</label></td>
                    <td><input type="text" name="zip" id="zip" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label></td>
                    <td><input type="text" name="phone" id="phone" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
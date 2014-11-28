<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(document).ready(function(){
        $("#enterpriseTable tr").hover(function(){
            //$(".action").addClass("hidden");
            $(this).addClass("one");
            $(this).find(".action").removeClass("hidden");
        },function(){
            $(this).removeClass("one");
            $(this).find(".action").addClass("hidden");
        });
    });

</script>

<div>
    <div id="create">
        <div id="filter">
            <span>Enterprise Balance: </span>
            <span id="balance">$ ${enterprise.enterpriseBalance}</span>
        </div>
    </div>
    <form id="productForm">
        <input type="text" name="idEnterprise" id="idEnterprise" value="${enterprise.idEnterprise}" class="hidden"/>
        <span>Transfer Money to Insurance Enterprise: </span>
        <select id="enterpriseName" name="enterpriseName" width="150px" onchange="viewOrders()">
            <c:forEach items="${insurance}" var="insurance">
                <option value="${insurance.enterpriseName}">${insurance.enterpriseName}</option>
            </c:forEach>
        </select>
        <div style="float: left">
            <input type="text" name="amount" id="amount"/>
        </div>
        <div id="button">
            <div id="account_save_button" class="submit_button" onclick="transferMoney()">
                <label>Transfer</label>
            </div>
        </div>
    </form>
</div>
<div id="viewOrders">

</div>

<style>
    .submit_button label{
        cursor: pointer;
    }

    #button{
        float: left;
    }

    #amount{
        margin: 10px;
        margin-top: 20px;
    }

    #productForm span{
        font-size: 18px;
        font-weight: bolder;
    }

    #productForm{
        margin-left: 20px;
    }

    #viewOrders{
        border-top:2px solid red;
        margin-top: 50px;
    }

    #enterpriseTable input[type="text"]{
        width: 60px;
        text-align: center;
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
        width: auto;
        border-bottom: 1px solid lightgray;
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

</style>
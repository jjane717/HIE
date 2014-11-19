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
        <div class="submit_button " onclick="makePayment()">
            <label>Make Payment</label>
        </div>
        <div class="submit_button " onclick="showAllPayment()">
            <label>Show All Payment</label>
        </div>
    </div>
    <div id="chooseOrder">
        <form id="orderInfo">
            <table id="enterpriseTable" cellspacing="0">
                <tr>
                    <td colspan="5" class="tableTitle">Choose Order</td>
                </tr>
                <tr>
                    <th class="details"></th>
                    <th>Name</th>
                    <th>Duration</th>
                    <th>Type</th>
                    <th>Total Amount</th>
                </tr>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td><input type="radio" name="selectOrder" value="${order.idOrder}" onchange="chooseOrder()"></td>
                        <td>${order.enterpriseProductEntity.productEntity.offerName}</td>
                        <td>${order.duration}</td>
                        <td>${order.paymentType}</td>
                        <td>${order.totalAmount}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
    <div id="choosePayment-container">
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

    #chooseOrder{
        overflow:scroll;
        height:300px;
    }

    #create{
        border-bottom: 2px solid red;
        height:60px;
    }

    #create span{
        font-size: 18px;
        font-weight: bolder;
        color: #90111a;
    }

    #enterpriseTable{
        margin-top: 20px;
        width:500px;
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

</style>
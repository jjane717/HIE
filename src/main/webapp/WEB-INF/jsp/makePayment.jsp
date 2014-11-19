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
        <div class="submit_button hidden" onclick="makePayment()">
            <label>Make Payment</label>
        </div>
        <div class="submit_button hidden changeText" onclick="showAllPayment()">
            <label>Show All Payment</label>
        </div>
    </div>
    <div width="570px">
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
        <div id="preparePay" class="hidden">
            <form id="prepareForm">
                <table cellspacing="0" id="cardTable">
                    <tr>
                        <td class="tableTitle">Pay By:</td>
                    </tr>
                    <tr>
                        <td class="detailsTitle"><label for="cardNumber">Card Number:</label></td>
                    </tr>
                    <tr>
                        <td><input name="cardNumber" id="cardNumber" type="text" width="100px"/></td>
                    </tr>
                    <tr>
                        <td class="detailsTitle"><label for="holderName">Card Holder Name:</label></td>
                    </tr>
                    <tr>
                        <td><input name="holderName" id="holderName" type="text" width="100px"/></td>
                    </tr>
                    <tr>
                        <td class="detailsTitle"><label for="expirationDate">Expiration Date:</label></td>
                    </tr>
                    <tr>
                        <td><input name="expirationDate" id="expirationDate" type="text"/></td>
                    </tr>
                    <tr>
                        <td class="detailsTitle"><label for="securityCode">Security Code:</label></td>
                    </tr>
                    <tr>
                        <td><input name="securityCode" id="securityCode" type="text"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="choosePayment-container">
</div>

<style>

    .detailsTitle{
        color: #6d3353;
        font-weight: bolder;
        font-size: 16px;
    }

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

    #preparePay{
        float: right;
        width: 180px;
    }

    #cardTable{
        width: 180px;
    }

    #cardTable td{
        /*margin-top: 20px;*/
        padding-top: 10px;
        text-align: center;
    }

    #chooseOrder{
        overflow:scroll;
        height:300px;
        width: 400px;
        float:left;
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
        width:380px;
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
        float: left;
    }

</style>
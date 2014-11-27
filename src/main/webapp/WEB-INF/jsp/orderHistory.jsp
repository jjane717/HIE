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
    <form id="productForm">
        <p id="tips" class="hidden"></p>
        <table id="enterpriseTable" cellspacing="0">
            <tr>
                <td colspan="5" class="tableTitle">Order History</td>
            </tr>
            <tr>
                <th class="details"></th>
                <th>Product Name</th>
                <th>Duration</th>
                <th>Payment Type</th>
                <th>Total Amount</th>
            </tr>
            <c:if test="${orders.size()<1}">
                <tr>
                    <td colspan="5">
                        No Order Here. Try to Get One. Go to View Market.
                    </td>
                </tr>
            </c:if>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td id="original_${order.idOrder}" class="details up detailsTd" onclick=showProductDetails("${order.idOrder}")></td>
                    <td>${order.enterpriseProductEntity.productEntity.offerName}</td>
                    <td>${order.duration}</td>
                    <td>${order.paymentType}</td>
                    <td>${order.totalAmount}</td>
                </tr>
                <tr id="detail_${order.idOrder}" class="hidden detailsTr">
                    <td colspan="5">
                        <div class="detailsDiv">
                            <table class="detailsTable" cellspacing="0">
                                <tr>
                                    <td class="detailsTitle">Create Date: </td><td>${order.createDate}</td>
                                    <td class="detailsTitle">Insurance Company: </td><td>${order.enterpriseProductEntity.productEntity.insuranceEnterpriseName}</td>
                                </tr>
                                <tr>
                                    <td class="detailsTitle">CoPay: </td><td>${order.enterpriseProductEntity.productEntity.coPay}</td>
                                    <td class="detailsTitle">CoInsurance: </td><td>${order.enterpriseProductEntity.productEntity.coInsurance}</td>
                                </tr>
                                <tr>
                                    <td class="detailsTitle">Deductible: </td><td>${order.enterpriseProductEntity.productEntity.deductible}</td>
                                    <td class="detailsTitle">Offer Price: </td><td>${order.enterpriseProductEntity.productEntity.offerPrice}</td>
                                </tr>
                                <tr>
                                    <td class="detailsTitle">Description: </td><td colspan="3">${order.enterpriseProductEntity.productEntity.description}</td>
                                </tr>
                                <tr>
                                    <td class="detailsTitle">Next Payment Date: </td>
                                    <td colspan="3">
                                        <c:set var="flag" value="1"/>
                                        <c:forEach items="${order.paymentEntityList}" var="payment">
                                            <c:choose>
                                                <c:when test="${payment.isPay == false && flag == 1}">
                                                    <c:out value="${payment.dueDate}"/>
                                                    <c:set var="flag" value="0"/>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>

<style>
    .submit_button{
        float: right;
    }

    .submit_button label{
        cursor: pointer;
    }

    .details{
        width: 50px;
        cursor: pointer;
    }

    .detailsDiv{
        background-color: lightgoldenrodyellow;
    }

    .detailsTable{
        margin:0px 50px;
        width: 450px;
    }

    .detailsTitle{
        color: #6d3353;
        font-weight: bolder;
        font-size: 16px;
    }

    #enterpriseTable{
        margin-top: 30px;
        width:570px;
        margin-left:15px;
    }

    #enterpriseTable input[type="text"]{
        width: 60px;
        text-align: center;
    }

    #enterpriseTable th{
        font-size: 18px;
        broder-right: 1px solid lightgray;
        padding-top:15px;
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

    .tableTitle{
        font-size: 30px;
        color: crimson;
        font-weight: bolder;
        text-align: center;
        padding-bottom: 10px;
    }

</style>
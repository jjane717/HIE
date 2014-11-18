<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(document).ready(function(){
        $("#enterpriseTable tr").hover(function(){
            $(this).addClass("one");
            $(this).find("input[type='text']").removeClass("zero");
            $(this).find("input[type='text']").addClass("one");
        },function(){
            $(this).removeClass("one");
            $(this).find("input[type='text']").removeClass("one");
            $(this).find("input[type='text']").addClass("zero");
        });
    });

</script>

<div>
    <table id="enterpriseTable" cellspacing="0">
        <tr>
            <th class="details"></th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Insurance Enterprise</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td id="original_${product.productEntity.idProduct}" class="details up detailsTd" onclick=showProductDetails("${product.productEntity.idProduct}")></td>
                <td>${product.productEntity.offerName}</td>
                <td>
                    <input type="text" class="disabledtext zero" value="${product.productEntity.offerPrice}"/>

                </td>
                <td>${product.productEntity.insuranceEnterpriseName}</td>
                <td>
                    <c:if test="${product.status == true}">
                        <input id="${product.productEntity.idProduct}" class="checkStatus" type="checkbox" checked="checked" onclick=changeProductStatus("${product.idEnterpriseProduct}")>
                    </c:if>
                    <c:if test="${product.status == false}">
                        <input id="${product.productEntity.idProduct}" type="checkbox" onclick=changeProductStatus("${product.idEnterpriseProduct}")>
                    </c:if>
                </td>
            </tr>
            <tr id="detail_${product.productEntity.idProduct}" class="hidden detailsTr">
                <td colspan="5">
                    <div class="detailsDiv">
                    <table class="detailsTable" cellspacing="0">
                        <tr>
                            <td class="detailsTitle">CoPay:</td><td>${product.productEntity.coPay}</td>
                            <td class="detailsTitle">CoInsurance:</td><td>${product.productEntity.coInsurance}</td>
                        </tr>
                        <tr>
                            <td class="detailsTitle">Deductible:</td><td>${product.productEntity.deductible}</td>
                            <td class="detailsTitle">Offer Price</td><td>${product.productEntity.offerPrice}</td>
                        </tr>
                        <tr>
                            <td class="detailsTitle">Description:</td><td colspan="3">${product.productEntity.description}</td>
                        </tr>
                        <tr>
                            <td class="detailsTitle">Target Market:</td><td colspan="3">${product.productEntity.targetMarket}</td>
                        </tr>
                    </table>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<style>
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
        margin-top: 10px;
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
        width: auto;
        border-bottom: 1px solid lightgray;
    }

</style>
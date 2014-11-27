<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(document).ready(function(){
        $("#enterpriseTable tr").hover(function(){
            //$(".action").addClass("hidden");
            $(this).addClass("one");
            $(this).find("input[type='text']").removeClass("zero");
            $(this).find("input[type='text']").removeClass("disabledtext");
            $(this).find("input[type='text']").removeAttr("disabled");
            $(this).find("input[type='text']").addClass("one");
            $(this).find(".action").removeClass("hidden");
        },function(){
            $(this).removeClass("one");
            $(this).find("input[type='text']").removeClass("one");
            $(this).find("input[type='text']").addClass("disabledtext");
            $(this).find("input[type='text']").addClass("zero");
            $(this).find("input[type='text']").attr("disabled","disabled");
            $(this).find(".action").addClass("hidden");
        });
    });

</script>

<div>
    <form id="productForm">
        <p id="tips" class="hidden"></p>
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
                        <input id="price_${product.idEnterpriseProduct}" type="text" class="disabledtext zero" disabled="disabled" value="${product.totalPrice}"/>
                        <div class="action submit_button hidden" onclick="updatePrice(${product.idEnterpriseProduct})">
                            <label>Update</label>
                        </div>
                    </td>
                    <td>${product.productEntity.insuranceEnterpriseName}</td>
                    <td>
                        <c:if test="${product.status == true}">
                            <input id="checkbox-${product.idEnterpriseProduct}" class="checkStatus" type="checkbox" checked="checked" onclick=changeProductStatus("${product.idEnterpriseProduct}")>
                        </c:if>
                        <c:if test="${product.status == false}">
                            <input id="checkbox-${product.idEnterpriseProduct}" type="checkbox" onclick=changeProductStatus("${product.idEnterpriseProduct}")>
                        </c:if>
                    </td>
                </tr>
                <tr id="detail_${product.productEntity.idProduct}" class="hidden detailsTr">
                    <td colspan="5">
                        <div class="detailsDiv">
                        <table class="detailsTable" cellspacing="0">
                            <tr>
                                <td class="detailsTitle">CoPay: </td><td>${product.productEntity.coPay}</td>
                                <td class="detailsTitle">CoInsurance: </td><td>${product.productEntity.coInsurance}</td>
                            </tr>
                            <tr>
                                <td class="detailsTitle">Deductible: </td><td>${product.productEntity.deductible}</td>
                                <td class="detailsTitle">Offer Price: </td><td>${product.productEntity.offerPrice}</td>
                            </tr>
                            <tr>
                                <td class="detailsTitle">Description: </td><td colspan="3">${product.productEntity.description}</td>
                            </tr>
                            <tr>
                                <td class="detailsTitle">Target Market: </td><td colspan="3">${product.targetMarket}</td>
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
        margin-top: 10px;
        width:570px;
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

</style>
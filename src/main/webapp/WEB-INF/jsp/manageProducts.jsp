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
            <td id="original_${product.idProduct}" class="details up detailsTd" onclick=showProductDetails("${product.idProduct}")></td>
            <td>${product.offerName}</td>
            <td>${product.offerPrice}</td>
            <td></td>
            <td>
                <c:if test="${product.status == true}">
                    <input id="${product.idProduct}" class="checkStatus" type="checkbox" checked="checked" onclick=changeProductStatus("${product.idProduct}")>
                </c:if>
                <c:if test="${product.status == false}">
                    <input id="${product.idProduct}" type="checkbox" onclick=changeProductStatus("${product.idProduct}")>
                </c:if>

            </td>
            </tr>
            <tr id="detail_${product.idProduct}" class="hidden detailsTr">
                <td colspan="5">
                    <div class="detailsDiv">
                    <table class="detailsTable" cellspacing="0">
                        <tr>
                            <td class="detailsTitle">CoPay:</td><td>${product.coPay}</td>
                            <td class="detailsTitle">CoInsurance:</td><td>${product.coInsurance}</td>
                        </tr>
                        <tr>
                            <td class="detailsTitle">Deductible:</td><td>${product.deductible}</td>
                            <td class="detailsTitle">Offer Price</td><td>${product.offerPrice}</td>
                        </tr>
                        <tr>
                            <td class="detailsTitle">Description:</td><td colspan="3">${product.description}</td>
                        </tr>
                        <tr>
                            <td class="detailsTitle">Target Market:</td><td colspan="3">${product.targetMarket}</td>
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
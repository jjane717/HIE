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
            <span>Filter By Insurance: </span><br/>
            <select id="targetMarket" name="targetMarket" width="150px" onchange="filterProduct()">
                <option value="All">All</option>
                    <c:forEach items="${enterprises}" var="insurance">
                        <option value="${insurance.enterpriseName}">${insurance.enterpriseName}</option>
                    </c:forEach>
            </select>
        </div>
    </div>
    <form id="productForm">
        <p id="tips" class="hidden"></p>
        <table id="enterpriseTable" cellspacing="0">
            <tr>
                <th class="details"></th>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Insurance</th>
                <th>HIE</th>
                <th>Choose</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr class="${product.productEntity.insuranceEnterpriseName} main">
                    <td id="original_${product.idEnterpriseProduct}" class="details up detailsTd" onclick=showProductDetails("${product.idEnterpriseProduct}")></td>
                    <td>${product.productEntity.offerName}</td>
                    <td>${product.totalPrice}</td>
                    <td>${product.enterpriseEntity.enterpriseName}</td>
                    <td>${product.productEntity.insuranceEnterpriseName}</td>
                    <td>
                        <div class="action submit_button hidden" onclick="chooseProduct(${product.idEnterpriseProduct})">
                            <label>Choose</label>
                        </div>
                    </td>
                </tr>
                <tr id="detail_${product.idEnterpriseProduct}" class="hidden detailsTr">
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

    #create{
        margin: 10px;
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
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
    <form id="chooseOffer">
    <div id="create">
        <div id="filter">
            <span>Filter By Target Market: </span><br/>
            <select id="targetMarket" name="targetMarket" width="150px" onchange="filterProduct()">
                <option value="All">All</option>
                <option value="AdultMarket">Adult Market</option>
                <option value="SeniorMarket">Senior Market</option>
                <option value="FamilyMarket">Family Market</option>
                <option value="SmallBusinessFamilyMarket">Small Business Family Market</option>
                <option value="SmallBusinessIndividualMarket">Small Business Individual Market</option>
                <option value="LowIncomeMarket">Low Income Market</option>
                <option value="LowIncomeFamilyMarket">Low Income Family Market</option>
                <option value="LowIncomeSmallBusinessFamilyMarket">Low Income Small Business Family Market</option>
                <option value="LowIncomeSmallBusinessIndividualMarket">Low Income Small Business Individual Market</option>
            </select>
        </div>
        <div id="account_save_button" class="submit_button" onclick="chooseOffer()">
            <label>Choose Offer</label>
        </div>
    </div>

    <table id="enterpriseTable" cellspacing="0">
        <tr>
            <th></th>
            <th class="details"></th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Target Market</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr class="${product.productEntity.targetMarket} main">
                <td><input type="radio" name="selectOffer" value="${product.productEntity.idProduct}"></td>
                <td id="original_${product.productEntity.idProduct}" class="details up detailsTd" onclick=showProductDetails("${product.productEntity.idProduct}")></td>
                <td>${product.productEntity.offerName}</td>
                <td>${product.productEntity.offerPrice}</td>
                <td>${product.productEntity.targetMarket}</td>
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
    </form>
</div>

<style>

    .submit_button{
        float:left;
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

    .detailsTitle{
        color: #6d3353;
        font-weight: bolder;
        font-size: 16px;
    }

    #enterpriseTable{
        margin-top: 10px;
        width:570px;
        margin-left: 10px;
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
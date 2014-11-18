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
        <div id="account_save_button" class="submit_button" onclick="createProduct()">
            <label>Create New Product</label>
        </div>
    </div>
    <table id="enterpriseTable" cellspacing="0">
        <tr>
            <th class="details"></th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td id="original_${product.productEntity.idProduct}" class="details up detailsTd" onclick=showProductDetails("${product.productEntity.idProduct}")></td>
                <td>${product.productEntity.offerName}</td>
                <td>${product.productEntity.offerPrice}</td>
                <td>${product.productEntity.description}</td>
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

<div id="dialog-form" title="Product" class="hidden">
    <p class="validateTips hidden">All form fields are required.</p>

    <form id="enterpriseInfoForm">
        <fieldset>
            <table>
                <tr>
                    <td><label for="offerName">Offer Name: </label></td>
                    <td><input type="text" name="offerName" id="offerName" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="coPay">CoPay: </label></td>
                    <td><input type="text" name="coPay" id="coPay" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="deductible">Deductible: </label></td>
                    <td><input type="text" name="deductible" id="deductible" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="coInsurance">CoInsurance: </label></td>
                    <td><input type="text" name="coInsurance" id="coInsurance" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="offerPrice">Offer Price:</label></td>
                    <td><input type="text" name="offerPrice" id="offerPrice" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="description">Description:</label></td>
                    <td><input type="text" name="description" id="description" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="targetMarket">Target Market: </label></td>
                    <td>
                        <select id="targetMarket" name="targetMarket" class="text ui-widget-content ui-corner-all" width="150px">
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
                    </td>
                </tr>
            </table>
        </fieldset>
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
        border-bottom: 2px solid red;
        height:50px;
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
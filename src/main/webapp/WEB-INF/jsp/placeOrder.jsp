<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(document).ready(function(){
        $("#duration").change(function(){
            var i = $(this).val();
            var price = $("#productPrice").attr("price");
            $("#totalAmount").val(i*price);
        });
    });

</script>

<div>
    <form id="chooseOrder">
        <div id="create">
            <div id="account_back_button" class="submit_button" onclick="backViewMarket()">
                <label>Back</label>
            </div>
            <div id="account_save_button" class="submit_button" onclick="placeOrder()">
                <label>Place Product</label>
            </div>
        </div>

        <table id="enterpriseTable" cellspacing="0">
            <tr>
                <td colspan="4" class="orderTitle">Product Information</td>
            </tr>
            <tr>
                <td class="detailsTitle">Product Name: </td><td>${choose.productEntity.offerName}</td>
                <td class="detailsTitle">Product Price: </td><td id="productPrice" price="${choose.totalPrice}">${choose.totalPrice}</td>
            </tr>
            <tr>
                <td class="detailsTitle">From Insurance: <td>${choose.productEntity.insuranceEnterpriseName}</td>
                <td class="detailsTitle">From HIE:</td><td>${choose.enterpriseEntity.enterpriseName}</td>
            </tr>
            <tr>
                <td colspan="4" class="orderTitle">Order Options</td>
            </tr>
            <tr>
                <td class="detailsTitle"><label for="duration">Duration: </label></td>
                <td><input type="number" name="duration" id="duration"/></td>
                <td colspan="2"><label class="side">[Input numbers of month]</label></td>
            </tr>
            <tr>
                <td class="detailsTitle"><label for="paymentType">Payment Type: </label></td>
                <td>
                    <select id="paymentType" name="paymentType">
                        <option value="Monthly">Monthly</option>
                        <option value="Quarterly">Quarterly</option>
                        <option value="Annually">Annually</option>
                    </select>
                </td>
                <td class="detailsTitle"><label for="totalAmount">Total Amount: </label></td>
                <td><input type="text" name="totalAmount" id="totalAmount" class="disabledtext" disabled="disabled"/></td>
            </tr>
        </table>
    </form>
</div>

<style>

    .side{
        font-style: oblique;
        font-size: 12px;
    }

    .orderTitle{
        font-size: 20px;
        color: crimson;
        font-weight: bolder;
        text-align: center;
    }

    .submit_button{
        float:left;
    }

    #create{
        margin: 10px;
        border-bottom: 2px solid red;
        height:50px;
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
        margin-top: 10px;
    }

</style>
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
    <form id="chooseHIE">
        <div id="create">
            <div id="account_back_button" class="submit_button" onclick="backPlaceProducts()">
                <label>Back</label>
            </div>
            <div id="account_save_button" class="submit_button" onclick="placeProduct()">
                <label>Place Product</label>
            </div>
        </div>

        <table id="enterpriseTable" cellspacing="0">
            <tr>
                <th></th>
                <th class="details"></th>
                <th>Enterprise Name</th>
                <th>Enterprise Code</th>
            </tr>
            <c:forEach items="${hies}" var="hie">
                <tr>
                    <td><input type="radio" name="selectHIE" value="${hie.idEnterprise}"></td>
                    <td id="original_${hie.idEnterprise}" class="details up detailsTd" onclick=showProductDetails("${hie.idEnterprise}")></td>
                    <td>${hie.enterpriseName}</td>
                    <td>${hie.enterpriseCode}</td>
                </tr>
                <tr id="detail_${hie.idEnterprise}" class="hidden detailsTr">
                    <td colspan="5">
                        <div class="detailsDiv">
                            <table class="detailsTable" cellspacing="0">
                                <tr>
                                    <td class="detailsTitle">Street:</td><td>${hie.street}</td>
                                    <td class="detailsTitle">City:</td><td>${hie.city}</td>
                                </tr>
                                <tr>
                                    <td class="detailsTitle">State:</td><td>${hie.state}</td>
                                    <td class="detailsTitle">Zip-Code:</td><td>${hie.zip}</td>
                                </tr>
                                <tr>
                                    <td class="detailsTitle">Phone:</td><td>${hie.phone}</td>
                                    <td class="detailsTitle">Email:</td><td>${hie.email}</td>
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
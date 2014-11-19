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
    <div id="paymentDiv">
        <form id="paymentInfo">
            <table id="paymentTable" cellspacing="0">
                <tr>
                    <td colspan="5" class="tableTitle">Choose Payment</td>
                </tr>
                <c:forEach items="${payments}" var="payment">
                    <c:if test="${payment.isPay == false}">
                        <tr>
                            <td><input type="radio" name="selectPayment" value="${payment.idPayment}"></td>
                            <td class="detailsTitle">Due Date: </td><td>${payment.dueDate}</td>
                            <td class="detailsTitle">Amount: </td><td>${payment.amount}</td>
                        </tr>
                    </c:if>
                    <c:if test="${payment.isPay == true}">
                        <tr class="paid hidden">
                            <td></td>
                            <td class="detailsTitle">Pay Date: </td><td>${payment.dueDate}</td>
                            <td class="detailsTitle">Amount: </td><td>${payment.amount}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </form>

    </div>
</div>

<style>
    .detailsTitle{
        color: #6d3353;
        font-weight: bolder;
        font-size: 16px;
    }

    #paymentDiv{
        overflow:scroll;
    }

    #paymentTable{
        margin-top: 10px;
        width:570px;
        margin-left: 10px;
    }

    #paymentTable th{
        font-size: 18px;
        broder-right: 1px solid lightgray;
    }

    #paymentTable tr{
        margin-top: 10px;
        padding: 20px;
        font-size: 14px;
        text-align: center;
    }
    #paymentTable td{
        height: 50px;
        width: auto;
        border-bottom: 1px solid lightgray;
    }


</style>
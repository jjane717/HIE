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
<div id="paymentDiv">
    <form id="paymentInfo">
        <table id="paymentTable" cellspacing="0">
            <tr>
                <td colspan="2" class="tableTitle">Choose Privilege</td>
            </tr>
            <c:forEach items="${available}" var="all">
                <tr>
                    <td>
                        <c:forEach items="${privilegeList}" var="privilege">
                            <c:if test="${all.idPrivilege == privilege.idPrivilege}">
                                <input type="checkbox" value="${all.idPrivilege}" class="selectPrivilege" checked="checked"/>
                            </c:if>
                            <c:if test="${all.idPrivilege != privilege.idPrivilege}">
                                <input type="checkbox" value="${all.idPrivilege}" class="selectPrivilege"/>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${all.privilegeName}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>

<style>

    #paymentDiv{
        overflow:scroll;
        width:220px;
    }

    #paymentTable{
        margin-top: 10px;
        width:220px;
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
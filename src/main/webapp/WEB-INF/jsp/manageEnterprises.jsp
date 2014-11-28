<script>
    $(document).ready(function(){
        $.ajax({
            type: "Get",
            url : "http://localhost:8080/enterprise",
            cache:true,

            success: function(data){
                $.each(data,function(i){
                    if(!(data[i]["enterpriseName"] == "Admin")) {
                        $("#enterpriseTable").append("<tr id=\"enterpriseInfo-" + data[i]["idEnterprise"] + "\"><td>" + data[i]["enterpriseName"] + "</td><td>" + data[i]["enterpriseType"] + "</td><td>" + data[i]["enterpriseCode"] + "</td><td><div class=\"action\"><div class=\"submit_button edit\" onClick=editEnterprise(\"" + data[i]["idEnterprise"] + "\")>Edit</div><div class=\"submit_button delete hidden\" onClick=deleteEnterprise(\"" + data[i]["idEnterprise"] + "\")>Delete</div></div></td></tr>");

                        $("#enterpriseTable tr").hover(function(){
                            $(this).addClass("one");
                        },function(){
                            $(this).removeClass("one");
                        });

                    }
                });
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    });

</script>
<div id="create">
    <div id="account_save_button" class="submit_button" onclick="createEnterprise()">
        <label>Create New Enterprise</label>
    </div>
</div>

<div id="enterpriseInfo">
    <table id="enterpriseTable" cellspacing="0">
        <tr>
            <th>Enterprise Name</th>
            <th>Enterprise Type</th>
            <th>Enterprise Code</th>
            <th></th>
        </tr>
    </table>
</div>

<style>
    .action{
        width: 100px;
        float: left;
    }

    .submit_button{
        float:left;
    }

    .edit{
        margin-right: 3px;
    }

    #create{
        border-bottom: 2px solid red;
        height:50px;
    }

    #enterpriseInfo{
        margin: 20px;
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
        width: 100px;
        border-bottom: 1px solid lightgray;
    }

</style>

<div id="dialog-form2" title="Enterprise" class="hidden">
    <p class="validateTips hidden">All form fields are required.</p>

    <form id="enterpriseInfoForm">
        <input type="text" name="idEnterprise" id="idEnterprise" class="hidden">
        <fieldset>
            <table>
                <tr>
                    <td><label for="enterpriseName">Enterprise Name: </label></td>
                    <td><input type="text" name="enterpriseName" id="enterpriseName" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="enterpriseCode">Enterprise Code: </label></td>
                    <td><input type="text" name="enterpriseCode" id="enterpriseCode" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="enterpriseType">Enterprise Type: </label></td>
                    <td>
                        <select id="enterpriseType" name="enterpriseType" class="text ui-widget-content ui-corner-all">
                            <option value="HIE">HIE</option>
                            <option value="Insurance">Insurance</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="street1">Street: </label></td>
                    <td><input type="text" name="street" id="street1" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="city1">City: </label></td>
                    <td><input type="text" name="city" id="city1" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="state1">State:</label></td>
                    <td><input type="text" name="state" id="state1" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="zip1">Zip:</label></td>
                    <td><input type="text" name="zip" id="zip1" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="phone1">Phone:</label></td>
                    <td><input type="text" name="phone" id="phone1" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
                <tr>
                    <td><label for="email1">Email:</label></td>
                    <td><input type="text" name="email" id="email1" value="" class="text ui-widget-content ui-corner-all"></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
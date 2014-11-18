<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>System Management</title>
    <link href="../css/css.css" rel="stylesheet">
    <link href="../css/css2.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="../js/js.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
        $(document).ready(function(){
            $.ajax({
                type: "Get",
                url : "http://localhost:8080/enterprise",
                cache:true,

                success: function(data){
                    $.each(data,function(i){
                        if(!(data[i]["enterpriseName"] == "Admin")) {
                            $("#enterpriseTable").append("<tr id=\"enterpriseInfo-" + data[i]["idEnterprise"] + "\"><td>" + data[i]["enterpriseName"] + "</td><td>" + data[i]["enterpriseType"] + "</td><td>" + data[i]["enterpriseCode"] + "</td><td><div class=\"action\"><div class=\"submit_button edit\" onClick=editEnterprise(\"" + data[i]["idEnterprise"] + "\")>Edit</div><div class=\"submit_button delete\" onClick=deleteEnterprise(\"" + data[i]["idEnterprise"] + "\")>Delete</div></div></td></tr>");

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

            $.ajax({
                type: "GET",
                url : "http://localhost:8080/pp",
                data : "",
                cache:true,

                success: function(data){
                    //alert("OK" + data);

                    $.each($.parseJSON(data),function(key,val){

                        $("#privilege").append("<li class=\"holdlightblue\" source=\"" + val + ".html\"><label>" + key + "</label></li>");
                        $("#customer-topmenu ul li").hover(function(){
                            $(this).removeClass("holdlightblue");
                            $(this).addClass("darkorange");
                        },function(){
                            $(this).removeClass("darkorange");
                            if(!$(this).hasClass("clickdarkorange")){
                                $(this).addClass("holdlightblue");
                            }

                        });

                        $("#customer-topmenu ul li").click(function(){
                            $("#customer-topmenu ul li").removeClass("clickdarkorange");
                            $("#customer-topmenu ul li").addClass("holdlightblue");
                            $(this).removeClass("holdlightblue");
                            $(this).addClass("clickdarkorange");

                            var urll = $(this).attr("source");
                            $("#customer-container").load(urll);
                        });


                    });

                },
                error: function(error){
                    alert("NO"+ error);
                }
            });


        });
    </script>
</head>
<body>
<h1 id="h"></h1>
<div id="wrap">
    <div id="headerholder"></div>
    <div id="container">
        <div id="customer-topmenu">
            <ul id="privilege"></ul>
        </div>
        <div id="customer-container">
            <script src="../js/js.js"></script>
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

            <div id="dialog-form" title="Edit New Address" class="hidden">
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
                                <td><label for="street">Street: </label></td>
                                <td><input type="text" name="street" id="street" value="" class="text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td><label for="city">City: </label></td>
                                <td><input type="text" name="city" id="city" value="" class="text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td><label for="state">State:</label></td>
                                <td><input type="text" name="state" id="state" value="" class="text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td><label for="zip">Zip:</label></td>
                                <td><input type="text" name="zip" id="zip" value="" class="text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td><label for="phone">Phone:</label></td>
                                <td><input type="text" name="phone" id="phone" value="" class="text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td><label for="email">Email:</label></td>
                                <td><input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all"></td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div id="test"></div>
    </div>
</div>
</body>
</html>
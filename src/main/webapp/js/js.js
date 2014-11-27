/**
 * Created by liuyijun on 14-11-10.
 */
function accedit(){
    if($("#account_save_button").is(":hidden")){
        $("#acctips").show();
        $("#account_save_button").show();
        $("#account_cancel_button").show();
        $("#view_acc input").removeClass("disabledtext").removeAttr("disabled");
    }
}

function acccancel(){
    $.ajax({
        type: "Get",
        url : "http://localhost:8080/account",
        cache:true,

        success: function(data){
            $("#email").val(data["email"]);
            $("#userName").val(data["userName"]);
            $("#password").val(data["password"]);
            $("#firstName").val(data["firstName"]);
            $("#lastName").val(data["lastName"]);
            $("#dateOfBirth").val(data["dateOfBirth"]);
            $("#age").val(data["age"]);
            $("#street").val(data["street"]);
            $("#city").val(data["city"]);
            $("#state").val(data["state"]);
            $("#zip").val(data["zip"]);
            $("#phone").val(data["phone"]);
        },
        error: function(error){
            alert("NO"+ error);
        }
    });

    $("#acctips").hide();
    $("#account_save_button").hide();
    $("#account_cancel_button").hide();
    $("#view_acc input").addClass("disabledtext").attr("disabled","true");
}

function accsave(){
    $.ajax({
        type: "Post",
        url : "http://localhost:8080/userUpdate",
        data : $("#view_acc").serialize(),
        cache:true,

        success: function(data){
            alert("OK" + data);

        },
        error: function(error){
            alert("NO"+ error);
        }
    });
}


function createEnterprise(){
    var enterpriseName = $( "#enterpriseName" ),
        idEnterprise = $ ( "#idEnterprise"),
        enterpriseCode = $( "#enterpriseCode" ),
        enterpriseType = $( "#enterpriseType"),
        street = $( "#street" ),
        city= $("#city"),
        state=$("#state"),
        zip = $( "#zip" ),
        email = $( "#email" ),
        phone = $( "#phone" ),
        allFields = $( [] ).add( enterpriseName ).add( enterpriseCode).add(street).add(city).add(zip).add(state).add(email).add(phone),
        tips = $( ".validateTips" );
    allFields.val("");

    $( "#dialog-form" ).dialog({
        autoOpen: true,
        height: 380,
        width: 400,
        modal: true,
        buttons: {
            "Create": function() {
                var bValid = true;
                allFields.removeClass( "ui-state-error" );

                bValid = bValid && checkLength( enterpriseName, "Enterprise Name", 3, 16, tips );
                bValid = bValid && checkLength( enterpriseCode, "Enterprise Code", 3, 5, tips );
                bValid = bValid && checkLength( street, "Street", 5, 100, tips );
                bValid = bValid && checkLength( city, "City", 2, 20, tips );
                bValid = bValid && checkLength( zip, "City", 4, 10, tips );
                bValid = bValid && checkLength( state, "City", 2, 20, tips );
                bValid = bValid && checkLength( phone, "City", 8, 20, tips );
                bValid = bValid && checkLength( email, "email", 6, 80, tips );

                bValid = bValid && checkRegexp( enterpriseName, /^[a-z]([0-9a-z_])+$/i, "Enterprise Name may consist of a-z, 0-9, underscores, begin with a letter.",tips );
                bValid = bValid && checkRegexp( enterpriseCode, /^([0-9])+$/, "Enterprise Code field only allow : A-Z a-z 0-9" ,tips);
                bValid = bValid && checkRegexp( street, /^([0-9a-zA-Z])+$/, "Address Line field only allow : A-Z a-z 0-9." , tips);
                bValid = bValid && checkRegexp( city, /^([a-zA-Z])+$/, "City field only allow : A-Z a-z.", tips );
                bValid = bValid && checkRegexp( state, /^([a-zA-Z])+$/, "State field only allow : A-Z a-z.",tips );
                bValid = bValid && checkRegexp( zip, /^([0-9])+$/, "Zip field only allow :0-9." ,tips);
                bValid = bValid && checkRegexp( phone, /^([0-9])+$/, "Phone field only allow : 0-9.", tips );
                bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" ,tips);




                if ( bValid ) {
                    $.ajax({
                        type: "POST",
                        url : "http://localhost:8080/addEnterprise",
                        data : $("#enterpriseInfoForm").serialize(),
                        cache:true,

                        success: function(data){
                            alert("OK"+data);
                            $("#enterpriseTable").append("<tr id=\"enterpriseInfo-" + data["idEnterprise"] + "\"><td>" + data["enterpriseName"] + "</td><td>" + data["enterpriseType"] + "</td><td>" + data["enterpriseCode"] + "</td><td><div class=\"action\"><div class=\"submit_button edit\" onClick=editEnterprise(\"" + data["idEnterprise"] + "\")>Edit</div><div class=\"submit_button delete\" onClick=deleteEnterprise(\"" + data["idEnterprise"] + "\")>Delete</div></div></td></tr>");
                        },
                        error: function(error){
                            alert("NO"+ error);
                        }
                    });

                    $( this ).dialog( "close" );
                }

            },
            Cancel: function() {
                allFields.val( "" ).removeClass( "ui-state-error" );
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            allFields.val( "" ).removeClass( "ui-state-error" );
            $( this ).dialog( "close" );
        }
    });
}


function editEnterprise(id){
    var enterpriseName = $( "#enterpriseName" ),
        idEnterprise = $ ( "#idEnterprise"),
        enterpriseCode = $( "#enterpriseCode" ),
        enterpriseType = $( "#enterpriseType"),
        street = $( "#street" ),
        city= $("#city"),
        state=$("#state"),
        zip = $( "#zip" ),
        email = $( "#email" ),
        phone = $( "#phone" ),
        allFields = $( [] ).add( enterpriseName ).add( enterpriseCode).add(street).add(city).add(zip).add(state).add(email).add(phone),
        tips = $( ".validateTips" );

    $.ajax({
        type: "POST",
        url : "http://localhost:8080/enterprise",
        data : {"id":id},
        cache:true,

        success: function(data){
            idEnterprise.val(data["idEnterprise"]);
            enterpriseName.val(data["enterpriseName"]);
            enterpriseCode.val(data["enterpriseCode"]);
            enterpriseType.val(data["enterpriseType"]);
            street.val(data["street"]);
            city.val(data["city"]);
            state.val(data["state"]);
            zip.val(data["zip"]);
            email.val(data["email"]);
            phone.val(data["phone"]);
        },
        error: function(error){
            alert("NO"+ error);
        }
    });

    $( "#dialog-form" ).dialog({
        autoOpen: true,
        height: 380,
        width: 400,
        modal: true,
        buttons: {
            "Update": function() {
                var bValid = true;
                allFields.removeClass( "ui-state-error" );

                bValid = bValid && checkLength( enterpriseName, "Enterprise Name", 3, 16, tips );
                bValid = bValid && checkLength( enterpriseCode, "Enterprise Code", 3, 5, tips );
                bValid = bValid && checkLength( street, "Street", 5, 100, tips );
                bValid = bValid && checkLength( city, "City", 2, 20, tips );
                bValid = bValid && checkLength( zip, "City", 4, 10, tips );
                bValid = bValid && checkLength( state, "City", 2, 20, tips );
                bValid = bValid && checkLength( phone, "City", 8, 20, tips );
                bValid = bValid && checkLength( email, "email", 6, 80, tips );

                bValid = bValid && checkRegexp( enterpriseName, /^[a-z]([0-9a-z_])+$/i, "Enterprise Name may consist of a-z, 0-9, underscores, begin with a letter.",tips );
                bValid = bValid && checkRegexp( enterpriseCode, /^([0-9])+$/, "Enterprise Code field only allow : 0-9" ,tips);
                bValid = bValid && checkRegexp( street, /^([0-9a-zA-Z])+$/, "Address Line field only allow : A-Z a-z 0-9." , tips);
                bValid = bValid && checkRegexp( city, /^([a-zA-Z])+$/, "City field only allow : A-Z a-z.", tips );
                bValid = bValid && checkRegexp( state, /^([a-zA-Z])+$/, "State field only allow : A-Z a-z.",tips );
                bValid = bValid && checkRegexp( zip, /^([0-9])+$/, "Zip field only allow :0-9." ,tips);
                bValid = bValid && checkRegexp( phone, /^([0-9])+$/, "Phone field only allow : 0-9.", tips );
                bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" ,tips);




                if ( bValid ) {
                    $.ajax({
                        type: "POST",
                        url : "http://localhost:8080/updateEnterprise",
                        data : $("#enterpriseInfoForm").serialize(),
                        cache:true,

                        success: function(data){
                            alert("OK"+data);
                        },
                        error: function(error){
                            alert("NO"+ error);
                        }
                    });

                    $( this ).dialog( "close" );
                }

            },
            Cancel: function() {
                allFields.val( "" ).removeClass( "ui-state-error" );
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            allFields.val( "" ).removeClass( "ui-state-error" );
            $( this ).dialog( "close" );
        }
    });
}

function deleteEnterprise(id){
    var deleteID = "#enterpriseInfo-" + id;
    var deleteTr = $(deleteID);
    $.ajax({
        type: "POST",
        url : "http://localhost:8080/deleteEnterprise",
        data : {"id":id},
        cache:true,

        success: function(data){
            alert("OK" + data);
            deleteTr.remove();
        },
        error: function(error){
            alert("NO"+ error);
        }
    });
}

function changeUserStatus(id){
    var con = confirm("Do you want to change the status?");
    var idtr = "#userAccountInfo-"+id;
    var ptr = $(idtr);
    var checkBox = ptr.find("[type=\"checkbox\"]");

    if(con){
        if(checkBox.hasClass("checkStatus")){
            $.ajax({
                type: "POST",
                url : "http://localhost:8080/updateUserAccountStatus",
                data: {"status": "false", "id":id},
                cache:true,

                success: function(data){
                    alert("You have already disabled this user account.");
                },
                error: function(error){
                    alert("NO"+ error);
                }
            });
            checkBox.removeAttr("checked");
            checkBox.removeClass("checkStatus");

        }else{
            $.ajax({
                type: "POST",
                url : "http://localhost:8080/updateUserAccountStatus",
                data: {"status": "true", "id":id},
                cache:true,

                success: function(data){
                    alert("You have already enabled this user account.");
                },
                error: function(error){
                    alert("NO"+ error);
                }
            });
            checkBox.attr("checked", "true");
            checkBox.addClass("checkStatus");

        }
    }else{

    }
}

function createUserAccount(){
    var userName = $( "#userName" ),
        password = $( "#password" ),
        idEnterprise = $( "#idEnterprise"),
        idRole = $ ( "#idRole"),
        firstName = $( "#firstName" ),
        lastName = $( "#lastName" ),
        dateOfBirth = $ ( "#dateOfBirth"),
        age = $ ( "#age"),
        street = $( "#street" ),
        city= $("#city"),
        state=$("#state"),
        zip = $( "#zip" ),
        email = $( "#email" ),
        phone = $( "#phone" ),
        allFields = $( [] ).add( userName ).add( password).add( firstName).add( lastName).add(dateOfBirth).add(age).add(street).add(city).add(zip).add(state).add(email).add(phone),
        tips = $( ".validateTips" );
    idEnterprise.html("");
    idRole.html("");
    allFields.val("");

    $.ajax({
        type: "GET",
        url : "http://localhost:8080/role",
        cache:true,

        success: function(data){
            $.each(data,function(i){
                if(data[i]["roleName"]!="Customer" && data[i]["roleName"]!="Employee") {
                    $(".roleOption").append("<option value=\"" + data[i]["idRole"] + "\">" + data[i]["roleName"] + "</option>");
                }
            });

            $(".roleOption").append("<option value=\"employee\">Employee</option>")

            $(".roleOption").change(function(){

                var idRole = $(".roleOption").val();
                var roleType = "";
                $.ajax({
                    type: "POST",
                    url : "http://localhost:8080/idRole",
                    data:{"id": idRole},
                    cache:true,

                    success: function(data){
                        roleType = data["roleName"];
                        var enterpriseGroup = "";
                        if(roleType == "Admin"){
                            enterpriseGroup = "Admin";
                        }else if(roleType == "HieAdmin"){
                            enterpriseGroup = "HIE";
                        }else if(roleType == "InsuranceAdmin"){
                            enterpriseGroup = "Insurance";
                        }else{
                            enterpriseGroup = "Employee";
                        }

                        $.ajax({
                            type: "GET",
                            url : "http://localhost:8080/enterpriseGroup",
                            data:{"enterpriseType":enterpriseGroup},
                            cache:true,

                            success: function(data){
                                $(".enterpriseOption").html("");
                                $.each(data,function(i){
                                    $(".enterpriseOption").append("<option value=\"" + data[i]["idEnterprise"] + "\">" + data[i]["enterpriseName"] + "</option>");
                                });
                            },
                            error: function(error){
                                alert("NO"+ error);
                            }
                        });
                    },
                    error: function(error){
                        alert("NO"+ error);
                    }
                });
            });
        },
        error: function(error){
            alert("NO"+ error);
        }
    });

    $( "#dialog-form" ).dialog({
        autoOpen: true,
        height: 450,
        width: 400,
        modal: true,
        buttons: {
            "Create": function() {
                var bValid = true;
                allFields.removeClass( "ui-state-error" );

                //bValid = bValid && checkLength( userName, "User Name", 3, 16, tips );
                //bValid = bValid && checkLength( password, "Password", 3, 5, tips );
                //bValid = bValid && checkLength( age, "Age", 1, 4, tips );
                //bValid = bValid && checkLength( dateOfBirth, "Street", 5, 20, tips );
                //bValid = bValid && checkLength( street, "Street", 5, 100, tips );
                //bValid = bValid && checkLength( city, "City", 2, 20, tips );
                //bValid = bValid && checkLength( zip, "City", 4, 10, tips );
                //bValid = bValid && checkLength( state, "City", 2, 20, tips );
                //bValid = bValid && checkLength( phone, "City", 8, 20, tips );
                //bValid = bValid && checkLength( email, "email", 6, 80, tips );

                //bValid = bValid && checkRegexp( userName, /^[a-z]([0-9a-z_])+$/i, "User Name may consist of a-z, 0-9, underscores, begin with a letter.",tips );
                //bValid = bValid && checkRegexp( password, /^([0-9])+$/, "Password field only allow : A-Z a-z 0-9" ,tips);
                //bValid = bValid && checkRegexp( age, /^([0-9])+$/, "Age field only allow :0-9." ,tips);
                //bValid = bValid && checkRegexp( dateOfBirth, /^([0-9])+$/, "Date of Birth field only allow :0-9, eg:'19900101' means 1990/01/01" ,tips);
                //bValid = bValid && checkRegexp( street, /^([0-9a-zA-Z])+$/, "Address Line field only allow : A-Z a-z 0-9." , tips);
                //bValid = bValid && checkRegexp( city, /^([a-zA-Z])+$/, "City field only allow : A-Z a-z.", tips );
                //bValid = bValid && checkRegexp( state, /^([a-zA-Z])+$/, "State field only allow : A-Z a-z.",tips );
                //bValid = bValid && checkRegexp( zip, /^([0-9])+$/, "Zip field only allow :0-9." ,tips);
                //bValid = bValid && checkRegexp( phone, /^([0-9])+$/, "Phone field only allow : 0-9.", tips );
                //bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" ,tips);

                if ( bValid ) {
                    $.ajax({
                        type: "POST",
                        url : "http://localhost:8080/createUserAccount",
                        data : $("#userAccountInfoForm").serialize(),
                        cache:true,

                        success: function(data){
                            if(data["userName"] != null) {
                                //$("#enterpriseTable").append("<tr id=\"userAccountInfo-" + data["idUserAccount"] + "\"><td>" + data["userName"] + "</td><td>" + data["email"] + "</td><td>" + data["firstName"] + " " + data["lastName"] + "</td><td>" + data["roleEntity"]["roleName"] + "</td><td>" + data["enterpriseEntity"]["enterpriseName"] + "</td><td><input type=\"checkbox\" checked=\"true\" class=\"checkStatus\" onClick=changeUserStatus(\"" + data["idUserAccount"] + "\")></td></tr>");
                                alert("You have already add an User");
                                $("#customer-container").load("manageUserAccounts");
                            }else{
                                alert("Duplicated User.");
                            }
                        },
                        error: function(error){
                            alert("NO"+ error);
                        }
                    });
                }
                $( this ).dialog( "close" );
            },
            Cancel: function() {
                allFields.val( "" ).removeClass( "ui-state-error" );
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            allFields.val( "" ).removeClass( "ui-state-error" );
            $( this ).dialog( "close" );
        }
    });
}

function showProductDetails(id){
    var originalTdId = "#original_" + id;
    var detailsTrId = "#detail_" + id;
    var originalTd = $(originalTdId);
    var detailsTr = $(detailsTrId);

    if(originalTd.hasClass("up")){
        $(".detailsTd").removeClass("down");
        $(".detailsTd").addClass("up")
        originalTd.removeClass("up");
        originalTd.addClass("down");
        $(".detailsTr").addClass("hidden");
        detailsTr.removeClass("hidden");
    }else{
        originalTd.removeClass("down");
        originalTd.addClass("up");
        detailsTr.addClass("hidden");
    }
}

function changeProductStatus(id){
    var con = confirm("Do you want to change the status?");
    if(con){
        var idBox = "#"+id;
        var checkBox = $(idBox);
        if(checkBox.hasClass("checkStatus")){
            $.ajax({
                type: "POST",
                url : "http://localhost:8080/updateProductStatus",
                data: {"status": "false", "id":id},
                cache:true,

                success: function(data){
                    alert("You have already disabled this product.");
                },
                error: function(error){
                    alert("NO"+ error);
                }
            });
            checkBox.removeAttr("checked");
            checkBox.removeClass("checkStatus");
        }else{
            $.ajax({
                type: "POST",
                url : "http://localhost:8080/updateProductStatus",
                data: {"status": "true", "id":id},
                cache:true,

                success: function(data){
                    alert("You have already enabled this product.");
                },
                error: function(error){
                    alert("NO"+ error);
                }
            });
            checkBox.attr("checked", "checked");
            checkBox.addClass("checkStatus");
        }
    }else{

    }
}

function createProduct(){
    var offerName = $( "#offerName" ),
        coPay = $( "#coPay"),
        deductible = $( "#deductible" ),
        coInsurance= $("#coInsurance"),
        offerPrice=$("#offerPrice"),
        description = $( "#description" ),
        targetMarket = $( "#targetMarket" ),
        allFields = $( [] ).add( offerName ).add( coPay).add(deductible).add(coInsurance).add(offerPrice).add(description),
        tips = $( ".validateTips" );

    $( "#dialog-form" ).dialog({
        autoOpen: true,
        height: 380,
        width: 500,
        modal: true,
        buttons: {
            "Create": function() {
                var bValid = true;
                allFields.removeClass( "ui-state-error" );

                bValid = bValid && checkLength( offerName, "Offer Name", 3, 16, tips );
                bValid = bValid && checkLength( coPay, "CoPay", 1, 5, tips );
                bValid = bValid && checkLength( deductible, "Deductible", 1, 5, tips );
                bValid = bValid && checkLength( coInsurance, "CoInsurance", 1, 5, tips );
                bValid = bValid && checkLength( offerPrice, "Offer Price", 1, 5, tips );
                bValid = bValid && checkLength( description, "Description", 2, 20, tips );

                bValid = bValid && checkRegexp( offerName, /^[a-z]([0-9a-z_])+$/i, "Offer Name may consist of a-z, 0-9, underscores, begin with a letter.",tips );
                bValid = bValid && checkRegexp( coPay, /^([0-9.])+$/, "CoPay field only allow : 0-9" ,tips);
                bValid = bValid && checkRegexp( deductible, /^([0-9.])+$/, "Deductible field only allow : 0-9" ,tips);
                bValid = bValid && checkRegexp( coInsurance, /^([0-9.])+$/, "CoInsurance field only allow : 0-9" ,tips);
                bValid = bValid && checkRegexp( offerPrice, /^([0-9.])+$/, "Offer Price field only allow :0-9." ,tips);
                bValid = bValid && checkRegexp( description, /^[a-z]([0-9a-z_])+$/i, "Description may consist of a-z, 0-9, underscores, begin with a letter.",tips );




                if ( bValid ) {
                    $.ajax({
                        type: "POST",
                        url : "http://localhost:8080/createProduct",
                        data : $("#enterpriseInfoForm").serialize(),
                        cache:true,

                        success: function(data){
                            alert("You have already created this product");

                        },
                        error: function(error){
                            alert("NO"+ error);
                        }
                    });
                    $( this ).dialog( "close" );
                }
            },
            Cancel: function() {
                allFields.val( "" ).removeClass( "ui-state-error" );
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            allFields.val( "" ).removeClass( "ui-state-error" );
            $( this ).dialog( "close" );
        }
    });

}

function filterProduct(){
    $(".main").removeClass("hidden");
    $(".detailsTr").addClass("hidden");
    $(".detailsTd").removeClass("down");
    $(".detailsTd").addClass("up");

    var targetMarket = "." + $("#targetMarket").val();

    if(targetMarket != ".All"){
        $(".main").addClass("hidden");
        $(targetMarket).removeClass("hidden");
    }
}

function chooseOffer(){
    var radios = document.getElementById("chooseOffer").selectOffer;
    var id = null;
    if(radios.value == null) {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                id = radios[i].value;
            }
        }
    }else{
        id = radios.value;
    }
    if(id==null){
        alert("Please Choose One Offer.");
    }else{
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/chooseOffer",
            data : {"id":id},
            cache:true,

            success: function(data){
                $("#customer-container").load("chooseOffer");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }
}

function backPlaceProducts(){
    $("#customer-container").load("placeProducts");
}

function backViewMarket(){
    $("#customer-container").load("viewMarket");
}

function placeProduct(){
    var radios = document.getElementById("chooseHIE").selectHIE;
    var id = null;
    if(radios.value == null) {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                id = radios[i].value;
            }
        }
    }else{
        id = radios.value;
    }
    if(id==null){
        alert("Please Choose One Enterprise.");
    }else{
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/placeProducts",
            data : {"id":id},
            cache:true,

            success: function(data){
                alert("Succeed! You have placed a product.");
                $("#customer-container").load("placeProducts");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }
}


function chooseProduct(id){
    $.ajax({
        type: "POST",
        url : "http://localhost:8080/chooseProduct",
        data : {"id":id},
        cache:true,

        success: function(data){
            $("#customer-container").load("chooseProduct");
        },
        error: function(error){
            alert("NO"+ error);
        }
    });
}

function placeOrder(id){
    var totalAmount = $("#totalAmount").val();
    var paymentType = $("#paymentType").val();
    var duration = $("#duration").val();
    if(totalAmount>0){
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/placeOrder",
            data : {"totalAmount":totalAmount,"paymentType":paymentType,"duration":duration, "id":id},
            cache:true,

            success: function(data){
                alert("Succeed! You can view this order in order history.");
                $("#customer-container").load("viewMarket");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }else{
        alert("Please Choose Correct Option.");
    }
}

function updatePrice(id){
    var idInput = "#price_" + id;
    var price = $(idInput);
    var bValid = true;
    var tips = $("#tips");

    bValid = bValid && checkLength( price, "Total Price", 1, 5, tips );
    bValid = bValid && checkRegexp( price, /^([0-9.])+$/, "Total Price field only allow :0-9." ,tips);

    if(bValid){
        tips.hide();
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/updateProductPrice",
            data: {"totalPrice": price.val(), "id":id},
            cache:true,

            success: function(data){
                alert("You have already Changed the Price.");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }

}

function chooseOrder(){
    var radios = document.getElementById("orderInfo").selectOrder;
    var id = null;
    if(radios.value == null) {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                id = radios[i].value;
            }
        }
    }else{
        id = radios.value;
    }
    if(id==null){
        alert("Please Choose One Order.");
    }else{
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/choosePayment",
            data : {"id":id},
            cache:true,

            success: function(data){
                $("#choosePayment-container").load("choosePayment");
                $(".submit_button").removeClass("hidden");
                $("#preparePay").removeClass("hidden");
                $(".changeText").find("label").html("Show All Payment");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }
}

function makePayment(){
    var radios = document.getElementById("paymentInfo").selectPayment;
    var id = null;
    if(radios.value == null) {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                id = radios[i].value;
            }
        }
    }else{
        id = radios.value;
    }
    if(id==null){
        alert("Please Choose One Payment.");
    }else{
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/makePayment",
            data : {"id":id},
            cache:true,

            success: function(data){
                alert("You have already make payment.");
                $("#choosePayment-container").html("");
                $(".submit_button").addClass("hidden");
                $("#preparePay").addClass("hidden");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }
}

function showAllPayment(){
    if($(".paid").hasClass("hidden")){
        $(".paid").removeClass("hidden");
        $(".changeText").find("label").html("Hide Paid");
    }else{
        $(".paid").addClass("hidden");
        $(".changeText").find("label").html("Show All Payment");
    }
}

function searchEmployee(){
    var search = "."+ $("#searchEmployeeID").val();

    if(search == "."){
        $(".main").removeClass("hidden");
    }else{
        $(".main").addClass("hidden");
        $(search).removeClass("hidden");
    }
}

function chooseEmployee(){
    var radios = document.getElementById("employeeInfo").selectEmployee;
    var id = null;
    if(radios.value == null) {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                id = radios[i].value;
            }
        }
    }else{
        id = radios.value;
    }

    if(id==null){
        alert("Please Choose One Employee.");
    }else{
        $("#idRole").val(id);
        $(".selectPrivilege").removeAttr("disabled");
        $(".selectPrivilege").removeAttr("checked");
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/choosePrivilege",
            data : {"id":id},
            cache:true,

            success: function(data){
                $.each(data,function(i){
                    var name = "#" + data[i]["privilegeFile"];
                    var privilege = $(name);
                    privilege.attr("checked","checked");
                });
                $(".submit_button").removeClass("hidden");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }
}

function changePrivilege(){
    var radios = document.getElementById("employeeInfo").selectEmployee;
    var id = null;
    if(radios.value == null) {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                id = radios[i].value;
            }
        }
    }else{
        id = radios.value;
    }

    if(id==null){
        alert("Choose Employee First.");
    }else{
        $.ajax({
            type: "POST",
            url : "http://localhost:8080/changePrivilege",
            data : $("#paymentInfo").serialize(),
            cache:true,

            success: function(data){
                alert(data);
                $("#customer-container").load("manageEmployees");
            },
            error: function(error){
                alert("NO"+ error);
            }
        });
    }
}

function updateTips( t,tips ) {
    tips.show()
        .html( t )
        .addClass( "ui-state-highlight" );
    setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
    }, 500 );
}

function checkLength( o, n, min, max, tips ) {
    if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
        min + " and " + max + "." ,tips);
        return false;
    } else {
        return true;
    }
}

function checkRegexp( o, regexp, n, tips ) {
    if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n ,tips );
        return false;
    } else {
        return true;
    }
}
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
                bValid = bValid && checkRegexp( enterpriseCode, /^([0-9])+$/, "Password field only allow : A-Z a-z 0-9" ,tips);
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
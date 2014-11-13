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
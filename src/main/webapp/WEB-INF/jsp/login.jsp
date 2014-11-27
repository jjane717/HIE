<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome to HIEs! Please Signin First!</title>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="http://thecodeplayer.com/uploads/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <!-- jQuery easing plugin -->
    <script src="http://thecodeplayer.com/uploads/js/jquery.easing.min.js" type="text/javascript"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>


</head>
<script>
    $(document).ready(function(){
//        $("#msform").show();
        $("#register").click(function(){
            $("#msform").removeClass("hidden");
        });

        //jQuery time
        var current_fs, next_fs, previous_fs; //fieldsets
        var left, opacity, scale; //fieldset properties which we will animate
        var animating; //flag to prevent quick multi-click glitches

        $(".cancel").click(function(){
            $("#msform").addClass("hidden");
        });

        $(".next").click(function(){
            if(animating) return false;
            animating = true;

            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            //activate next step on progressbar using the index of next_fs
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            //show the next fieldset
            next_fs.show();
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function(now, mx) {
                    //as the opacity of current_fs reduces to 0 - stored in "now"
                    //1. scale current_fs down to 80%
                    scale = 1 - (1 - now) * 0.2;
                    //2. bring next_fs from the right(50%)
                    left = (now * 50)+"%";
                    //3. increase opacity of next_fs to 1 as it moves in
                    opacity = 1 - now;
                    current_fs.css({'transform': 'scale('+scale+')'});
                    next_fs.css({'left': left, 'opacity': opacity});
                },
                duration: 800,
                complete: function(){
                    current_fs.hide();
                    animating = false;
                },
                //this comes from the custom easing plugin
                easing: 'easeInOutBack'
            });
        });

        $(".previous").click(function(){
            if(animating) return false;
            animating = true;

            current_fs = $(this).parent();
            previous_fs = $(this).parent().prev();

            //de-activate current step on progressbar
            $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

            //show the previous fieldset
            previous_fs.show();
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function(now, mx) {
                    //as the opacity of current_fs reduces to 0 - stored in "now"
                    //1. scale previous_fs from 80% to 100%
                    scale = 0.8 + (1 - now) * 0.2;
                    //2. take current_fs to the right(50%) - from 0%
                    left = ((1-now) * 50)+"%";
                    //3. increase opacity of previous_fs to 1 as it moves in
                    opacity = 1 - now;
                    current_fs.css({'left': left});
                    previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
                },
                duration: 800,
                complete: function(){
                    current_fs.hide();
                    animating = false;
                },
                //this comes from the custom easing plugin
                easing: 'easeInOutBack'
            });
        });

        $(".submit").click(function(){
            $.ajax({
                type: "POST",
                url : "http://localhost:8080/createUserAccount",
                data : $("#msform").serialize(),
                cache:true,

                success: function(data){
                    if(data["userName"] != null){
                        alert("You have been registered.");
                        $("#msform").addClass("hidden");
                    }else{
                        alert("Duplicated User.");
                    }

                },
                error: function(error){
                    alert("NO"+ error);
                }
            });

        });


    });
</script>
<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }
    .form-signin .checkbox {
        font-weight: normal;
    }
    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .form-signin .form-control:focus {
        z-index: 2;
    }
    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }

    .hidden{
        display: none;
    }

    #register:hover{
        text-decoration: underline;
        background-color: lightyellow;
    }


    #login-error{
        background-color: lightcoral;
        border-radius: 5px;
        font-size: 18px;
    }
    #login-error p{
        margin-left: 10px;
        color: #f5f5f5;
    }

</style>

<body>

<div class="container" onload="document.f.username.focus();">

    <form class="form-signin" role="form" name="f" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <c:if test="${error!=null}">
            <div id="login-error"><p>${error}</p></div>
        </c:if>
        <input name="username" type="text" id="inputEmail" class="form-control" placeholder="User Name" required autofocus>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <p id="register">Register Now!</p>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" name="submit" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->
</body>
</html>

<style>
    /*custom font*/
    @import url(http://fonts.googleapis.com/css?family=Montserrat);

    /*basic reset*/
    * {margin: 0; padding: 0;}
    /*form styles*/
    #msform {
        width: 400px;
        margin: -300px 0 0 450px;
        text-align: center;
        position: absolute;
    }
    #msform fieldset {
        background: white;
        border: 0 none;
        border-radius: 3px;
        box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.4);
        padding: 20px 30px;

        box-sizing: border-box;
        width: 80%;
        margin: 0 10%;

        /*stacking fieldsets above each other*/
        position: absolute;
    }
    /*Hide all except first fieldset*/
    #msform fieldset:not(:first-of-type) {
        display: none;
    }
    /*inputs*/
    #msform input, #msform textarea {
        padding: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
        margin-bottom: 10px;
        width: 100%;
        box-sizing: border-box;
        font-family: montserrat;
        color: #2C3E50;
        font-size: 13px;
    }
    /*buttons*/
    #msform .action-button {
        width: 100px;
        background: #27AE60;
        font-weight: bold;
        color: white;
        border: 0 none;
        border-radius: 1px;
        cursor: pointer;
        padding: 10px 5px;
        margin: 10px 5px;
    }
    #msform .action-button:hover, #msform .action-button:focus {
        box-shadow: 0 0 0 2px white, 0 0 0 3px #27AE60;
    }
    /*headings*/
    .fs-title {
        font-size: 15px;
        text-transform: uppercase;
        color: #2C3E50;
        margin-bottom: 10px;
    }
    .fs-subtitle {
        font-weight: normal;
        font-size: 13px;
        color: #666;
        margin-bottom: 20px;
    }
    /*progressbar*/
    #progressbar {
        margin-bottom: 30px;
        overflow: hidden;
        /*CSS counters to number the steps*/
        counter-reset: step;
    }
    #progressbar li {
        list-style-type: none;
        color: dodgerblue;
        text-transform: uppercase;
        font-size: 9px;
        font-weight: bolder;
        width: 33.33%;
        float: left;
        position: relative;
    }
    #progressbar li:before {
        content: counter(step);
        counter-increment: step;
        width: 20px;
        line-height: 20px;
        display: block;
        font-size: 10px;
        color: #333;
        background: white;
        border-radius: 3px;
        margin: 0 auto 5px auto;
    }
    /*progressbar connectors*/
    #progressbar li:after {
        content: '';
        width: 100%;
        height: 2px;
        background: white;
        position: absolute;
        left: -50%;
        top: 9px;
        z-index: -1; /*put it behind the numbers*/
    }
    #progressbar li:first-child:after {
        /*connector not needed before the first step*/
        content: none;
    }
    /*marking active/completed steps green*/
    /*The number of the step and the connector before it = green*/
    #progressbar li.active:before,  #progressbar li.active:after{
        background: #27AE60;
        color: white;
    }
</style>
    <form id="msform" class="hidden">
        <input type="button" name="cancel" class="cancel action-button" value="Cancel" />
        <!-- progressbar -->
        <ul id="progressbar">
            <li class="active">Account Setup</li>
            <li>Personal Information</li>
            <li>Personal Details</li>
        </ul>
        <span id="tips"></span>
        <!-- fieldsets -->
        <fieldset>
            <h2 class="fs-title">Create your account</h2>
            <h3 class="fs-subtitle">Set Up You Account</h3>
            <input type="text" name="idRole" id="idRole" class="hidden" value="1"/>
            <input type="text" name="idEnterprise" id="idEnterprise" class="hidden" value="3"/>
            <input type="text" name="email" id="email" placeholder="Email" />
            <input type="text" name="userName" id="userName" placeholder="User Name" />
            <input type="password" name="password" id="password" placeholder="Password" />
            <input type="button" name="next" class="next action-button" value="Next" />
        </fieldset>
        <fieldset>
            <h2 class="fs-title">Personal Information</h2>
            <h3 class="fs-subtitle">Choose Your Current Information</h3>
            <input type="text" name="firstName" id="fname" placeholder="First Name" />
            <input type="text" name="lastName" id="lname" placeholder="Last Name" />
            <input type="text" name="dateOfBirth" id="dateOfBirth" placeholder="Date of Birth"/>
            <input type="text" name="age" id="age" placeholder="Age" />
            <input type="text" name="street" id="street" placeholder="Street" />
            <input type="text" name="city" id="city" placeholder="City" />
            <input type="text" name="state" id="state" placeholder="State" />
            <input type="text" name="zip" id="zip" placeholder="Zip" />
            <input type="text" name="phone" id="phone" placeholder="phone" />
            <input type="button" name="previous" class="previous action-button" value="Previous" />
            <input type="button" name="next" class="next action-button" value="Next" />
        </fieldset>
        <fieldset>
            <h2 class="fs-title">Personal Details</h2>
            <h3 class="fs-subtitle">We will never sell it</h3>
            <label class="checkbox" for="isSmallBusiness">
                <input type="checkbox" name="isSmallBusiness" id="isSmallBusiness">Small Business
            </label>
            <label class="checkbox" for="isFamily">
                <input type="checkbox" name="isFamily" id="isFamily">Family Member
            </label>
            <label>
                <span>Income Status:</span>
                <select id="incomeStatus" name="incomeStatus">
                    <option value="none">Don't provide</option>
                    <option value="low">Less Than $5000 Annual</option>
                    <option value="above">Above $5000 Annual</option>
                </select>
            </label>
            <input type="button" name="previous" class="previous action-button" value="Previous" />
            <input type="button" name="submit" class="submit action-button" value="Submit" />
        </fieldset>
    </form>
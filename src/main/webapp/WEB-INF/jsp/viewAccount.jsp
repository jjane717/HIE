<script>
    $(document).ready(function(){
        $.ajax({
            type: "Get",
            url : "http://localhost:8080/account",
            cache:true,

            success: function(data){
//                $("#idUserAccount").val(data["idUserAccount"]);
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

    });
</script>
<div id="view_account">
    <form id="view_acc">
        <div class="mm">
            <div id="account_edit_button" class="submit_button" onclick="accedit()">
                <label>Edit</label>
            </div>
            <div id="account_save_button" class="submit_button hidden" onclick="accsave()">
                <label>Save</label>
            </div>
            <div id="account_cancel_button" class="submit_button hidden" onclick="acccancel()">
                <label>Cancel</label>
            </div>
        </div>
        <div class="mm">
            <h3>Login Information</h3>
            <%--<input type="text" name="idUserAccount" id="idUserAccount" placeholder="idUserAccount" class="disabledtext hidden" disabled="true"/>--%>
            <label for="email">Email: </label><input type="text" name="email" id="email" placeholder="Email" class="disabledtext" disabled="true"/><br/>
            <label for="userName">User Name: </label><input type="text" name="userName" id="userName" placeholder="User Name" class="disabledtext" disabled="true"/><br/>
            <label for="password">Password: </label><input type="password" name="password" id="password" placeholder="Password" class="disabledtext" disabled="true"/><br/>
        </div>
        <div class="mm">
            <h3>Personal Information</h3>
            <label for="firstName">Firs Name: </label><input type="text" name="firstName" id="firstName" placeholder="First Name" class="disabledtext" disabled="true"/><br/>
            <label for="lastName">Last Name: </label><input type="text" name="lastName" id="lastName" placeholder="Last Name" class="disabledtext" disabled="true"/><br/>
            <label for="dateOfBirth">Date Of Birth: </label><input type="text" name="dateOfBirth" id="dateOfBirth" placeholder="Date of Birth" class="disabledtext" disabled="true"/><br/>
            <label for="age">Age: </label><input type="text" name="age" id="age" placeholder="Age" class="disabledtext"/>
        </div>
        <div class="mm">
            <h3>Address Information</h3>
            <label for="street">Street: </label><input type="text" name="street" id="street" placeholder="Street" class="disabledtext" disabled="true"/><br/>
            <label for="city">City: </label><input type="text" name="city" id="city" placeholder="City" class="disabledtext" disabled="true"/><br/>
            <label for="state">State: </label><input type="text" name="state" id="state" placeholder="State" class="disabledtext" disabled="true"/><br/>
            <label for="zip">Zip-Code: </label><input type="text" name="zip" id="zip" placeholder="Zip" class="disabledtext" disabled="true" /><br/>
            <label for="phone">Phone: </label><input type="text" name="phone" id="phone" placeholder="phone" class="disabledtext" disabled="true"/><br/>
        </div>
    </form>
</div>

<style>
    #view_acc{
        margin:10px;
        padding: 10px;
        font-size:18px;
    }

    h3{
        margin: 20px;
    }

    label{
        margin: 10px;
        width: 80px;
    }

    input[type="text"],[type="password"]{
        margin:10px;
        padding:10px;
        border-radius: 5px;
        color:#000000;
        text-align: center;
        width: 300px;
    }

    .mm{
        float:left;
    }

</style>

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
                type: "GET",
                url : "http://localhost:8080/privileges",
                cache:true,

                success: function(data){
                    //alert("OK" + data);

                    $.each($.parseJSON(data),function(key,val){
                        $("#privilege").append("<li class=\"holdlightblue\" source=\"" + val + "\"><label>" + key + "</label></li>");
                    });

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
        </div>
        <div id="test"></div>
    </div>
</div>
</body>
</html>
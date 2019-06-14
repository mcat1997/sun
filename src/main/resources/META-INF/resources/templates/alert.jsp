<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>sun</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <h3>${sessionScope.alertStr}</h3>
                    <h3>${alertStr}</h3>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
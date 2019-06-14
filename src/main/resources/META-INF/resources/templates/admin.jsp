<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Sun在线问卷系统</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="/admin">Sun</a>
                </li>
                <li>
                    <a class="nav-link" href="/account">注销</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">欢迎你，管理员${sessionScope.account.accountName}</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="row" style="margin-top: 10%">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <button type="button" id = "accountInfo-button" class="btn btn-outline-primary btn-block">
                用户管理
            </button>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <button type="button" id="questionnaire-button" class="btn btn-outline-primary btn-block">
                问卷管理
            </button>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/admin.js"></script>
</html>
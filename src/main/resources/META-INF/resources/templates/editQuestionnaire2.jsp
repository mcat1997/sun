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
    <a id="questions" class="text-hide">${questions}</a>
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <p>${sessionScope.questionnaire.questionnaireName}</p>
                    <form id="questionnaire-form" class="form-group">

                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <button type="button" class="btn btn-outline-primary btn-block" href="javaScript:;" onclick="banItem()">
                封禁此问卷
            </button>
        </div>
        <div class="col-md-4"></div>
    </div>
    <a id="questionnaireId" class="text-hide">${questionnaire.id}</a>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/editQuestionnaire2.js"></script>
</html>
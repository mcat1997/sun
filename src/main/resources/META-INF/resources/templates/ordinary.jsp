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
                        <a class="nav-link active" href="/ordinary">Sun</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/account">注销</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">欢迎你，普通用户${sessionScope.account.accountName}</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row" style="margin-top: 10%">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#insertOneQuestionnaire">
                    创建问卷
                </button>
            </div>
            <div class="col-md-4"></div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button type="button" id="questionnaireInfo-button" class="btn btn-outline-primary btn-block">
                    我的问卷
                </button>
            </div>
            <div class="col-md-4"></div>
        </div>

        <div id="insertOneQuestionnaire" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <button class="close" data-dismiss="modal">
                            <span>&times;</span>
                        </button>
                    </div>
                    <div class="modal-title">
                        <h1 class="text-center">创建问卷</h1>
                    </div>
                    <div class="modal-body">
                        <form id="insertOneQuestionnaire-form" class="form-group" action="insertOneQuestionnaire" method="post">
                            <div class="form-group">
                                <label for="">问卷名</label>
                                <input name="questionnaireName" class="form-control" type="text"
                                       placeholder="问卷名">
                            </div>
                            <div class="text-right">
                                <button id="insertOneQuestionnaire-button" class="btn btn-primary" type="submit">提交</button>
                                <button class="btn btn-danger" data-dismiss="modal">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/ordinary.js"></script>
</html>
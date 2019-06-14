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
<a id="questionnaireId" class="text-hide">${questionnaireId}</a>
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
            <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#insertOneQuestion">
                添加问题
            </button>
        </div>
        <div class="col-md-4"></div>
    </div>

    <%--添加一个问题--%>
    <div id="insertOneQuestion" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="tabbable" id="tabs-293709">


                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" href="#tab1" data-toggle="tab">判断题</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#tab2" data-toggle="tab">单选题</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#tab3" data-toggle="tab">多选题</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#tab4" data-toggle="tab">主观题</a>
                            </li>
                        </ul>


                        <div class="tab-content">
                            <%--判断题--%>
                            <div class="tab-pane active" id="tab1">
                                <form id="trueOrFalse-form" class="form-group" action="insertOneQuestion" method="post">
                                    <div class="form-group">
                                        <input name="type" class="form-control text-hide" type="text" value="0">
                                    </div>
                                    <div class="form-group">
                                        <label>题目</label>
                                        <input name="questionTitle" id="trueOrFalse-questionTitle" class="form-control " type="text"
                                               placeholder="题目">
                                    </div>
                                    <div class="text-right">
                                        <button id="trueOrFalse-button" class="btn btn-primary" type="submit">添加</button>
                                    </div>
                                </form>
                            </div>
                            <%--单选题--%>
                            <div class="tab-pane" id="tab2">
                                <form id="singleSelection-form" class="form-group" action="insertOneQuestion" method="post">
                                    <div class="form-group">
                                        <input name="type" class="form-control text-hide" type="text" value="1">
                                    </div>
                                    <div class="form-group">
                                        <label>题目</label>
                                        <input name="questionTitle" id="singleSelection-questionTitle" class="form-control " type="text"
                                               placeholder="题目">
                                    </div>
                                    <div class="form-group">
                                        <label>答案样式</label>
                                        <input name="answerStyle" id="singleSelection-answerStyle" class="form-control " type="text"
                                               placeholder="答案样式（多个答案之间用';'分开）">
                                    </div>
                                    <div class="text-right">
                                        <button id="singleSelection-button" class="btn btn-primary" type="submit">添加</button>
                                    </div>
                                </form>
                            </div>
                            <%--多选题--%>
                            <div class="tab-pane" id="tab3">
                                <form id="multipleSelection-form" class="form-group" action="insertOneQuestion" method="post">
                                    <div class="form-group">
                                        <input name="type" class="form-control text-hide" type="text" value="2">
                                    </div>
                                    <div class="form-group">
                                        <label>题目</label>
                                        <input name="questionTitle" id="multipleSelection-questionTitle" class="form-control " type="text"
                                               placeholder="题目">
                                    </div>
                                    <div class="form-group">
                                        <label>答案样式</label>
                                        <input name="answerStyle" id="multipleSelection-answerStyle" class="form-control " type="text"
                                               placeholder="答案样式（多个答案之间用';'分开）">
                                    </div>
                                    <div class="text-right">
                                        <button id="multipleSelection-button" class="btn btn-primary" type="submit">添加</button>
                                    </div>
                                </form>
                            </div>
                            <%--主观题--%>
                            <div class="tab-pane" id="tab4">
                                <form id="subjective-form" class="form-group" action="insertOneQuestion" method="post">
                                    <div class="form-group">
                                        <input name="type" class="form-control text-hide" type="text" value="3">
                                    </div>
                                    <div class="form-group">
                                        <label>题目</label>
                                        <input name="questionTitle" id="subjective-questionTitle" class="form-control " type="text"
                                               placeholder="题目">
                                    </div>
                                    <div class="text-right">
                                        <button id="subjective-button" class="btn btn-primary" type="submit">添加</button>
                                    </div>
                                </form>
                            </div>


                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/editQuestionnaire.js"></script>
</html>
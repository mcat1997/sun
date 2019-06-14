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
                        <a class="nav-link active" href="/account">Sun</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row" style="margin-top: 10%">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#login">
                    登陆
                </button>
            </div>
            <div class="col-md-4"></div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#register">
                    注册
                </button>
            </div>
            <div class="col-md-4"></div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#changePwd">
                    修改密码
                </button>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
    <!-- 登录窗口 -->
    <div id="login" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">登录</h1>
                </div>
                <div class="modal-body">
                    <form id="login-form" class="form-group" action="/account/login" method="post">
                        <div class="form-group">
                            <label for="">用户名</label>
                            <input name="accountName" id="accountName-input" class="form-control " type="text"
                                   placeholder="用户名">
                        </div>
                        <div class="form-group">
                            <label for="">密码</label>
                            <input name="accountPwd" id="accountPwd-input" class="form-control" type="password"
                                   placeholder="密码">
                        </div>
                        <div class="text-right">
                            <button id="login-button" class="btn btn-primary" type="submit">登录</button>
                            <button class="btn btn-danger" data-dismiss="modal">取消</button>
                        </div>
                        <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register">还没有账号？点我注册</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- 注册窗口 -->
    <div id="register" class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">注册</h1>
                </div>
                <div class="modal-body">
                    <form id="register-form" class="form-group" action="account/register" method="post">
                        <div class="form-group">
                            <label for="">用户名</label>
                            <input name="accountName" class="form-control" type="text" placeholder="(必填)1-16位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">密码</label>
                            <input name="accountPwd" class="form-control" type="password" placeholder="(必填)1-16位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">再次输入密码</label>
                            <input name="accountPwdRepeat" class="form-control" type="password" placeholder="(必填)与上面相同">
                        </div>
                        <div class="form-group">
                            <label for="">安全码</label>
                            <input name="securityCode" class="form-control" type="password" placeholder="管理员注册填写">
                        </div>
                        <div class="text-right">
                            <button id="register-button" class="btn btn-primary" type="submit">提交</button>
                            <button class="btn btn-danger" data-dismiss="modal">取消</button>
                        </div>
                        <a href="" data-toggle="modal" data-dismiss="modal" data-target="#login">已有账号？点我登录</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%--修改密码窗口--%>
    <div id="changePwd" class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">密码修改</h1>
                </div>
                <div class="modal-body">
                    <form id="changePwd-form" class="form-group" action="account/changePwd" method="post">
                        <div class="form-group">
                            <label for="">用户名</label>
                            <input name="accountName" class="form-control" type="text" placeholder="(必填)1-16位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">原始密码</label>
                            <input name="oldPwd" class="form-control" type="password" placeholder="(必填)原始密码">
                        </div>
                        <div class="form-group">
                            <label for="">密码</label>
                            <input name="newPwd" class="form-control" type="password" placeholder="(必填)1-16位字母或数字">
                        </div>
                        <div class="form-group">
                            <label for="">再次输入密码</label>
                            <input name="repeatPwd" class="form-control" type="password" placeholder="(必填)与上面相同">
                        </div>
                        <div class="text-right">
                            <button id="changePwd-button" class="btn btn-primary" type="submit">提交</button>
                            <button class="btn btn-danger" data-dismiss="modal">取消</button>
                        </div>
                        <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register">还没有账号？点我注册</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/account.js"></script>
</html>
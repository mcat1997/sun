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
    <div class="col-md-12" style="margin-top: 10px">
        <table class="table">
            <thead>
                <tr>
                    <th>
                        #
                    </th>
                    <th>
                        用户名
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th>
                        更新时间
                    </th>
                    <th>
                        是否已被封禁
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tbody  id="accountInfos-tbody">
            </tbody>
        </table>
        <nav>
            <ul class="pagination">
                <li class="page-item">
                    <a id="prewPage" class="page-link" href="#">上一页</a>
                </li>
                <li class="page-item">
                    <a id="pageNum" class="page-link" href="#">${pageNum}</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#">...</a>
                </li>
                <li class="page-item">
                    <a id="pageCount" class="page-link" href="#">${pageCount}</a>
                </li>
                <li class="page-item">
                    <a id="nextPage" class="page-link" href="#">下一页</a>
                </li>
            </ul>
        </nav>
    </div>
    <a id="accounts" class="text-hide">${accounts}</a>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/accountInfos.js"></script>
</html>
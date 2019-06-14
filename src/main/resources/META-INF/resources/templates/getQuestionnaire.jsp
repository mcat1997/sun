<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${questionnaire.questionnaireName}</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<a id="isSuccess" class="text-hide">${isSuccess}</a>
<a id="alertStr" class="text-hide">${alertStr}</a>
<a id="questionnaireId" class="text-hide">${questionnaire.id}</a>
<a id="questions" class="text-hide">${questions}</a>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1>${questionnaire.questionnaireName}</h1>
                    <form id="questionnaire-form" class="form-group">

                    </form>
                    <button id="submit-button" class="btn btn-primary">提交</button>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/getQuestionnaire.js"></script>
</html>
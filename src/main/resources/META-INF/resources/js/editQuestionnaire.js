function flashform() {
    var questions = $.parseJSON($("#questions").text());
    questions.sort(function (a, b) {
        return a.serialNumber - b.serialNumber
    });
    var form = $("#questionnaire-form");
    form.empty();
    var text = "";
    var answerStyles;
    $.each(questions, function (j, question) {
        var answerResults = $.parseJSON(question.answerResult);
        text += "<lable>";
        if (question.type === 0) {
            text += question.serialNumber;
            text += ":";
            text += "(判断题)";
            text += question.questionTitle;
            text += "</lable><br>";
            text += "<input type='radio' name='trueOrFalse_";
            text += question.serialNumber;
            text += "' value='1'>正确<a style='color:red'>&nbsp;&nbsp;&nbsp;";
            text += answerResults[0];
            text += "</a><br>";
            text += "<input type='radio' name='trueOrFalse_";
            text += question.serialNumber;
            text += "' value='2'>错误<a style='color:red'>&nbsp;&nbsp;&nbsp;";
            text += answerResults[1];
            text += "</a><br>";
        } else if (question.type === 1) {
            text += question.serialNumber;
            text += ":";
            text += "(选择题)";
            text += question.questionTitle;
            text += "</lable><br>";
            answerStyles = $.parseJSON(question.answerStyle);
            $.each(answerStyles, function (i, answerStyle) {
                text += "<input type='radio' name='single_";
                text += question.serialNumber;
                text += "' value='";
                text += i;
                text += "'>";
                text += answerStyle;
                text += "<a style='color:red'>&nbsp;&nbsp;&nbsp;";
                text += answerResults[i];
                text += "</a><br>"
            });
        } else if (question.type === 2) {
            text += question.serialNumber;
            text += ":";
            text += "(多选题)";
            text += question.questionTitle;
            text += "</lable><br>";
            answerStyles = $.parseJSON(question.answerStyle);
            $.each(answerStyles, function (i, answerStyle) {
                text += "<input type='checkbox' name='multiple_";
                text += question.serialNumber;
                text += "' value='";
                text += i;
                text += "'>";
                text += answerStyle;
                text += "<a style='color:red'>&nbsp;&nbsp;&nbsp;";
                text += answerResults[i];
                text += "</a><br>"
            });
        } else if (question.type === 3) {
            text += question.serialNumber;
            text += ":";
            text += "(主观题)";
            text += question.questionTitle;
            text += "</lable><br>";
            text += "<button><a href='subjectAnswerInfos?questionId=";
            text += question.id;
            text += "'>点击查看</a></button>";
            text += "<br>";
        }
        text += "<br>";
    });
    form.append(text);
}

$(document).ready(function () {
    flashform();
    $('#trueOrFalse-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
            location.href = "/editQuestionnaire"
        }
    });
    $('#singleSelection-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
            location.href = "/editQuestionnaire"
        }
    });
    $('#multipleSelection-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
            location.href = "/editQuestionnaire"
        }
    });
    $('#subjective-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
            location.href = "/editQuestionnaire"
        }
    });
});
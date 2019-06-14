function flashForm() {
    var questions = $.parseJSON($("#questions").text());
    // var questions = $("#questions").text();
    questions.sort(function (a, b) {
        return a.serialNumber - b.serialNumber
    });
    var form = $("#questionnaire-form");
    form.empty();
    var text = "";
    var answerStyles;
    $.each(questions, function (i, question) {
        text += "<lable>";
        if (question.type === 0) {
            text += question.serialNumber;
            text += ":";
            text += "(判断题)";
            text += question.questionTitle;
            text += "</lable><br>";
            text += "<input type='radio' name='option_";
            text += question.id;
            text += "' value='1'>正确<br>";
            text += "<input type='radio' name='option_";
            text += question.id;
            text += "' value='2'>错误<br>";
        } else if (question.type === 1) {
            text += question.serialNumber;
            text += ":";
            text += "(选择题)";
            text += question.questionTitle;
            text += "</lable><br>";
            answerStyles = $.parseJSON(question.answerStyle);
            $.each(answerStyles, function (i, answerStyle) {
                text += "<input type='radio' name='option_";
                text += question.id;
                text += "' value='";
                text += (i + 1);
                text += "'>";
                text += answerStyle;
                text += "<br>"
            });
        } else if (question.type === 2) {
            text += question.serialNumber;
            text += ":";
            text += "(多选题)";
            text += question.questionTitle;
            text += "</lable><br>";
            answerStyles = $.parseJSON(question.answerStyle);
            $.each(answerStyles, function (i, answerStyle) {
                text += "<input type='checkbox' name='option_";
                text += question.id;
                text += "' value='";
                text += (i + 1);
                text += "'>";
                text += answerStyle;
                text += "<br>"
            });
        } else if (question.type === 3) {
            text += question.serialNumber;
            text += ":";
            text += "(主观题)";
            text += question.questionTitle;
            text += "</lable><br>";
            text += "<textarea name='option_";
            text += question.id;
            text += "'></textarea><br>";
        }
        text += "<br>";
    });
    form.append(text);
}

function packageData() {
    var answers = [];
    var questions = $.parseJSON($("#questions").text());
    var questionId;
    var name;
    var flag = true;
    $.each(questions, function (i, question) {
        if (question.type === 3) {
            questionId = question.id;
            name = "textarea[name='option_" + questionId + "']";
            var answerText = $(name).val();
            answers.push({"questionId": questionId,
                "answerText": answerText});
        } else {
            questionId = question.id;
            name = "input[name='option_" + questionId + "']:checked";
            var answerTexts = $(name);
            flag = false;
            $.each(answerTexts, function () {
                flag = true;
                answers.push({"questionId": questionId,
                    "answerText": $(this).val()});
            });
        }
        if (flag === false) {
            return false;
        }
    });
    if (flag === true) {
        return JSON.stringify(answers);
    } else {
        return "";
    }

}

$(document).ready(function () {
    if ($("#isSuccess").text()) {
        flashForm();
    } else {
        location.href = "did";
    }
    $("#submit-button").click(function () {
        var answersJson = packageData();
        var questionnaireId = parseInt($("#questionnaireId").text());
        if (answersJson === "") {
            alert("选择判断不能为空");
        } else {
            $.post("answerQuestionnaire",
                {
                    answersJson: answersJson,
                    questionnaireId: questionnaireId
                });
            location.href = "thanks";
        }
    });
});
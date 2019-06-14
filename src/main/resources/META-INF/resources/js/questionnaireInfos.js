$.windowbox = {
    fltbody: function () {
        var questionnairesJson = $("#questionnaires").text();
        var questionnaires = $.parseJSON(questionnairesJson);
        questionnaires = questionnaires.reverse();
        $("#questionnaires-tbody").empty();
        $.each(questionnaires, function (i, questionnaire) {
            var text = "<tr>";
            text += "<td><a href='javascript:;' onclick='editQuestionnaire(this.innerText)'>";
            text += questionnaire.id + "</td>";
            text += "<td>" + questionnaire.questionnaireName + "</td>";
            text += "<td>" + questionnaire.accountId + "</td>";
            text += "<td>" + questionnaire.questionNum + "</td>";
            text += "<td>" + questionnaire.answerNum + "</td>";
            text += "<td>" + questionnaire.createTime + "</td>";
            text += "<td>" + questionnaire.banded + "</td>";
            text += "<td><button href='javaScript:;' onclick='getUrl(";
            text += questionnaire.id + ")'>生成</button>" + "</td>";
            text += "</tr>";
            $("#questionnaireInfos-tbody").prepend(text)
        });
    }
};

function getUrl(data) {
    alert("localhost:8080/getQuestionnaire?questionnaireId=" + data);
}

function editQuestionnaire(questionnaireId) {
    location.href = "editQuestionnaire?questionnaireId=" + questionnaireId.toString();
}

$(document).ready(function () {
    $.windowbox.fltbody();
    $("#prewPage").click(function () {
        var pageNum = parseInt($("#pageNum").text()) - 1;
        if (pageNum < 1) {
            alert("已经是第一页了");
        } else {
            location.href = "questionnaireInfos?pageNum=" + pageNum.toString();
        }
    });

    $("#nextPage").click(function () {
        var pageNum = parseInt($("#pageNum").text()) + 1;
        var pageCount = parseInt($("#pageCount").text());
        if (pageNum > pageCount) {
            alert("已经是最后一页了");
        } else {
            location.href = "questionnaireInfos?pageNum=" + pageNum.toString();
        }
    });
});
$.windowbox = {
    fltbody: function () {
        var questionnairesJson = $("#questionnaires").text();
        var questionnaires = $.parseJSON(questionnairesJson);
        questionnaires = questionnaires.reverse();
        $("#questionnaires-tbody").empty();
        $.each(questionnaires, function (i, questionnaire) {
            var text = "<tr><td><a href='javascript:;' onclick='getQuestionnaire(this.innerText)'>" + questionnaire.id +
                "</a></td><td>" + questionnaire.questionnaireName +
                "</td><td>" + questionnaire.accountId +
                "</td><td>" + questionnaire.questionNum +
                "</td><td>" + questionnaire.answerNum +
                "</td><td>" + questionnaire.createTime +
                "</td><td>" + questionnaire.banded +
                "</td></tr>";
            $("#questionnaireInfos-tbody").prepend(text)
        });
    }
};

function getQuestionnaire(questionnaireId) {
    location.href = "editQuestionnaire2?questionnaireId=" + questionnaireId.toString();
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
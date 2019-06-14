$.windowbox = {
    fltbody: function () {
        var answersJson = $("#answers").text();
        var answers = $.parseJSON(answersJson);
        answers = answers.reverse();
        $("#answerInfos-tbody").empty();
        $.each(answers, function (i, answer) {
            var text = "<tr><td>" + answer.id +
                "</td><td>" + answer.answerText +
                "</td><td>" + answer.createTime +
                "</td></tr>";
            $("#answerInfos-tbody").prepend(text)
        });
    }
};

$(document).ready(function () {
    var questionId = $("#questionId").text();
    $.windowbox.fltbody();
    $("#prewPage").click(function () {
        var pageNum = parseInt($("#pageNum").text()) - 1;
        if (pageNum < 1) {
            alert("已经是第一页了");
        } else {
            location.href = "subjectAnswerInfos?pageNum=" + pageNum.toString() + "&questionId=" + questionId;
        }
    });

    $("#nextPage").click(function () {
        var pageNum = parseInt($("#pageNum").text()) + 1;
        var pageCount = parseInt($("#pageCount").text());
        if (pageNum > pageCount) {
            alert("已经是最后一页了");
        } else {
            location.href = "subjectAnswerInfos?pageNum=" + pageNum.toString() + "&questionId=" + questionId;
        }
    });
});
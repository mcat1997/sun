$(document).ready(function () {
    $('#insertOneQuestionnaire-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
            location.href = 'questionnaireInfos';
        }
    });
    $("#questionnaireInfo-button").click(function () {
        location.href = "questionnaireInfos"
    });
});
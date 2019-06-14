$.windowbox = {
    fltbody: function () {
        var accountsJson = $("#accounts").text();
        var accounts = $.parseJSON(accountsJson);
        accounts = accounts.reverse();
        $("#accountInfos-tbody").empty();
        $.each(accounts, function (i, account) {
            var text = "<tr><td>" + account.id;
            text += "</td><td>" + account.accountName;
            text += "</td><td>" + account.createTime;
            text += "</td><td>" + account.updateTime;
            // text += "</td><td>" + account.banded;
            if (account.banded == true) {
                text += "</td><td>已被封禁";
            } else {
                text += "</td><td><button href='javaScript:;' onclick='banItem2(";
                text += account.id;
                text += ")'>封禁</button>"
            }
            text += "</td></tr>";
            $("#accountInfos-tbody").prepend(text)
        });
    }
};

function banItem2(data) {
    location.href = "banItem?accountId=" + data;
}

$(document).ready(function () {
    $.windowbox.fltbody();
    $("#prewPage").click(function () {
        var pageNum = parseInt($("#pageNum").text()) - 1;
        if (pageNum < 1) {
            alert("已经是第一页了");
        } else {
            location.href = "accountInfos?pageNum=" + pageNum.toString();
        }
    });

    $("#nextPage").click(function () {
        var pageNum = parseInt($("#pageNum").text()) + 1;
        var pageCount = parseInt($("#pageCount").text());
        if (pageNum > pageCount) {
            alert("已经是最后一页了");
        } else {
            location.href = "accountInfos?pageNum=" + pageNum.toString();
        }
    });
});
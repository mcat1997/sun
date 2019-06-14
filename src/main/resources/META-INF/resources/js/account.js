$(document).ready(function () {
    $('#login-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
            if (data.account.role === 1) {
                location.href = '/ordinary';
            } else {
                location.href = '/admin';
            }

        }
    });
    $('#register-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
        }
    });
    $('#changePwd-form').ajaxForm(function (data) {
        if (data.success === false) {
            alert(data.alertStr);
        } else {
            alert(data.alertStr);
        }
    });
});

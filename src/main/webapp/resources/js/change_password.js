$(function() {
	$("#btnChangePwd").click(function() {
		var oldpwd = $("#password").val();
		var newpwd = $("#newPassword").val();
		var cnewpwd = $("#confirmPassword").val();
		if (newpwd != cnewpwd) {
			alert("Passwords do not match");
			return false;
		}
		else if (oldpwd == newpwd) {
			alert("New Password can not be same as current password");
			return false;
		}
		return true;
	});
});

$(document).ready(function() {
	console.log("ready!");
	$('#newPassword, #confirmPassword').on('keyup', function() {
		if ($('#newPassword').val() == $('#confirmPassword').val()) {
			$('#message').html('<i class="fa fa-check"></i>').css('color', 'lime');
		} else
			$('#message').html('<i class="fa fa-close"></i>').css('color', 'red');
	});
});
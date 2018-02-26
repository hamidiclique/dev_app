$(function() {
	$("#btnChangePwd").click(function() {
		var pwd = $("#newPassword").val();
		var cpwd = $("#confirmPassword").val();
		if (pwd != cpwd) {
			alert("Passwords do not match.");
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
$(function() {
	$("#btnAddNewUser").click(function() {
		var pwd = $("#password").val();
		var cpwd = $("#confirmpassword").val();
		if (pwd != cpwd) {
			alert("Passwords do not match.");
			return false;
		}
		return true;
	});
});

$(document).ready(function() {
	console.log("ready!");
	$('#password, #confirmpassword').on('keyup', function() {
		if ($('#password').val() == $('#confirmpassword').val()) {
			$('#message').html('<i class="fa fa-check"></i>').css('color', 'lime');
		} else
			$('#message').html('<i class="fa fa-close"></i>').css('color', 'red');
	});
});
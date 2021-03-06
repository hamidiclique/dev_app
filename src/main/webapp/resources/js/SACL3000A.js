$(document).ready(function() {
	//console.log("ready!");
	$('#password, #confirmpassword').on('keyup', function() {
		if ($('#password').val() == $('#confirmpassword').val()) {
			$('#message').html('<i class="fa fa-check"></i>').css('color', 'lime');
		} else
			$('#message').html('<i class="fa fa-close"></i>').css('color', 'red');
	});
	$("#btnAddNewUser").click(function() {
		var valid;
		var compasswd = parseInt(document.getElementById("checkPasswdValid").value);
		if (compasswd) {
			valid = fnValidatePassword();
		}
		else {
			valid = true;
		}		
		if (valid) {
			var pwd = $("#password").val();
			var cpwd = $("#confirmpassword").val();
			if (pwd != cpwd) {
				alert("Passwords do not match.");
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	});
});

function fnValidatePassword() {
	var minpass = document.getElementById("minpwdlen").value;
	var maxpass = document.getElementById("maxpwdlen").value;
	var pwd_element = document.getElementById("password");	
    // enter your regular expression to check for at least 8 characters here
    //var regexPasswordLength = /.{8,20}/; // test for at least min & max no. of paswd characters    
    // enter your regular expression to check for an uppercase letter here
    var regexPasswordContainsUpperCase = /[A-Z]/; //test for uppercase letter
    // enter your regular expression to check for a lowercase letter here
    var regexPasswordContainsLowerCase = /[a-z]/; //test for lowercase letter
    // enter your regular expression to check for a number here
    var regexPasswordContainsNumber = /\d/; //test for number 
    // enter your regular expression to check for a special character here
    var regexPasswordContainsSpecialChar = /\W/; //test for special character

    var unlock = true;
    if (pwd_element.value.length < minpass) {
        pwdHint.innerHTML = 'Password must be at least '+ minpass +' characters';
        pwdHint.style.display = "inline";
        unlock = false;
    } else if (pwd_element.value.length > maxpass) {
        pwdHint.innerHTML = 'Password can not exceed ' + maxpass + ' characters';
        pwdHint.style.display = "inline";
        unlock = false;
    } else if (!regexPasswordContainsUpperCase.test(pwd_element.value)) {
        pwdHint.innerHTML = 'Password must contain an uppercase character';
        pwdHint.style.display = "inline";
        unlock = false;
    } else if (!regexPasswordContainsLowerCase.test(pwd_element.value)) {
        pwdHint.innerHTML = 'Password must contain an lowercase character';
        pwdHint.style.display = "inline";
        unlock = false;
    } else if (!regexPasswordContainsNumber.test(pwd_element.value)) {
        pwdHint.innerHTML = 'Password must contain a number';
        pwdHint.style.display = "inline";
        unlock = false;
    } else if (!regexPasswordContainsSpecialChar.test(pwd_element.value)) {
        pwdHint.innerHTML = 'Password must contain a special character';
        pwdHint.style.display = "inline";
        unlock = false;
    } else {
    }
    return unlock;
}
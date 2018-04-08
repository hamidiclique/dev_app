var formPasswordForm = document.forms["passwordForm"];
var elemPW = document.getElementById("pwdvalidate");
var bPasswordPasses = false;

function fnValidatePassword(evt) {
    // enter your regular expression to check for at least 8 characters here
    var regexPasswordLength = /.{8,20}/; // test for at least 8 characters
    // enter your regular expression to check for an uppercase letter here
    var regexPasswordContainsUpperCase = /[A-Z]/; //test for uppercase letter
    // enter your regular expression to check for a lowercase letter here
    var regexPasswordContainsLowerCase = /[a-z]/; //test for lowercase letter
    // enter your regular expression to check for a number here
    var regexPasswordContainsNumber = /\d/; //test for number 
    // enter your regular expression to check for a special character here
    var regexPasswordContainsSpecialChar = /\W/; //test for special character

    bPasswordPasses = true;

    if (!regexPasswordLength.test(elemPW.value)) {
        pwdHint.innerHTML = 'Password must be at least 8 characters.';
        pwdHint.style.display = "inline";
        bPasswordPasses = false;
    }

    if (!regexPasswordContainsUpperCase.test(elemPW.value)) {
        pwdHint.innerHTML = 'Password must contain an uppercase character.';
        pwdHint.style.display = "inline";
        bPasswordPasses = false;
    }

    if (!regexPasswordContainsLowerCase.test(elemPW.value)) {
        pwdHint.innerHTML = 'Password must contain an lowercase character.';
        pwdHint.style.display = "inline";
        bPasswordPasses = false;
    }

    if (!regexPasswordContainsNumber.test(elemPW.value)) {
        pwdHint.innerHTML = 'Password must contain a number.';
        pwdHint.style.display = "inline";
        bPasswordPasses = false;
    }

    if (!regexPasswordContainsSpecialChar.test(elemPW.value)) {
        pwdHint.innerHTML = 'Password must contain a special character.';
        pwdHint.style.display = "inline";
        bPasswordPasses = false;
    }

    if (bPasswordPasses) {
        // looks goood
        pwdHint.innerHTML = 'Password passes all tests.';

    }
    evt.preventDefault();
}

formPasswordForm.addEventListener("submit", fnValidatePassword);
function selectAll() {
	var functions = document.getElementsByName('activeUserList');
	for (var i=0; i<functions.length; i++) {
		if (functions[i].type == 'checkbox')
			functions[i].checked = true;
	}
}			
function unselectAll() {
	var functions = document.getElementsByName('activeUserList');
	for (var i=0; i<functions.length; i++) {
		if (functions[i].type == 'checkbox')
			functions[i].checked = false;
	}
}
function countCheckboxes() {
	var functions = document.getElementsByName('activeUserList');
	var count = 0;
	for (var i=0; i<functions.length; i++) {
		if (functions[i].type == 'checkbox' && functions[i].checked === true) {
			count++;
		}
	}
	if (count > 0) {
		return true;
	}
	else {
		alert("Please select user(s) to logout forcefully");
		return false;
	}
}
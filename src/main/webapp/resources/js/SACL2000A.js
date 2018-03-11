function doUpdate() {
	if (checkLength(document.SACL2000A.roleDesc.value, 40))
		return false;

	if (!doCheckSelectBox(document.SACL2000A.lstBox2))
		return false;

	doSelectAll(document.SACL2000A.lstBox2);
	SACL2000A.submit();

}
function doCheckSelectBox(selObj) {
	if (selObj.options.length <= 0) {
		alert("You must add at least one Function Group");
		return false;
	} else
		return true;
}
function doSelectAll(selObj) {
	for (var x = 0; x < selObj.options.length; x++) {
		selObj.options[x].selected = true;
	}
}
function checkLength(roleDesc, max) {
	if(roleDesc.length > max) {
		alert("User Role Name can not longer than 40 (max) characters");
		return true;
	}
	else
		return false;
}

/*reference : https://jsfiddle.net/DTcHh/16420/?utm_source=website&utm_medium=embed&utm_campaign=DTcHh*/

$(document).ready(function() {
    $('#btnRight').click(function(e) {
        var selectedOpts = $('#lstBox1 option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    });
    
    $('#btnAllRight').click(function (e) {
        var selectedOpts = $('#lstBox1 option');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    });

    $('#btnLeft').click(function(e) {
        var selectedOpts = $('#lstBox2 option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    });
    
    $('#btnAllLeft').click(function (e) {
        var selectedOpts = $('#lstBox2 option');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    });
});
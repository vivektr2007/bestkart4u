var numval = "0123456789";

var alphaVal = "ABCDEFGHIJKLMNOPQRSTYUVWXYZabcdefghijklmnopqrstuvwxyz";

var alphaSpaceVal = " ABCDEFGHIJKLMNOPQRSTYUVWXYZabcdefghijklmnopqrstuvwxyz";
var alphaNumSpaceVal = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

function resValidateAmount(t, v, d) {

	$.trim(t.value);

	var flag = false;

	var w = "";
	// alert('enterd in validation');
	for (i = 0; i < t.value.length; i++) {

		x = t.value.charAt(i);

		if (x == "." && flag == false) {

			flag = true;

		} else if (x == "." && flag == true) {

			t.value = t.value.substring(0, (t.value.length - 1));

			return false;

		}

		else if ((t.value.length - (t.value.indexOf(".") + 1) > d)

				&& flag == true) {

			t.value = t.value.substring(0, (t.value.length - 1));

		}

		if (v.indexOf(x, 0) != -1) {

			w += x;

		}

	}

	t.value = w;

}

//validation for fields marked as required
$(document).ready(function() {
	$('[required]').blur(function() {

		validateRequired(this);
	});

});


function validateRequiredRadio(obj) {
	var check = true;
	if ($("#"+obj+":checked").length == 0){
		//alert("hiiii");
		check = false;
		/*$("#"+obj).next("div").remove();
		$("#"+obj).addClass("error");*/
		/*$("#"+obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));*/
	}
	else {
		/*if ($(obj).hasClass("error")) {
			$(obj).removeClass('error').addClass('success');
		} else {
			$(obj).addClass("success");
		}*/
		//$(obj).next("div").remove();

	}

	return check;
}

//mandatory field validation for fields of particular class
function validateRequired(obj) {
	var check = true;
	if (($(obj).is(":visible") || !$(obj).is(":disabled"))
			&& ($.trim($(obj).val()) == "" || $(obj).val() == 'select')) {

		check = false;
		$(obj).next("div").remove();
		$(obj).addClass("error");
		$(obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));

	} else {
		if ($(obj).hasClass("error")) {
			$(obj).removeClass('error').addClass('success');
		} else {
			$(obj).addClass("success");
		}
		$(obj).next("div").remove();

	}

	return check;
}

function validateNumber(obj) {
	var check = true;
	if(!$.isNumeric($(obj).val())){
		check = false;
		$(obj).next("div").remove();
		$(obj).addClass("error");
		$(obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field must be numeric. </div>"));

	} else {
		if ($(obj).hasClass("error")) {
			$(obj).removeClass('error').addClass('success');
		} else {
			$(obj).addClass("success");
		}
		$(obj).next("div").remove();

	}

	return check;
}

//mandatory field validation for fields of date and select
function validateDateRequired(obj) {
	var check = true;
	if ($(obj).is(":visible")
			&& ($.trim($(obj).val()) == "" || $(obj).val() == 'select')) {

		check = false;
		$(obj).next().next("div").remove();
		$(obj).addClass("error");
		$(obj)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> El campo es obligatorio </div>"));

	} else {
		if ($(obj).hasClass("error")) {
			$(obj).removeClass('error').addClass('success');
		} else {
			$(obj).addClass("success");
		}
		$(obj).next().next("div").remove();
	}

	return check;
}

function validateAutocomplete(obj) {
	var value = jQuery.trim($(obj).val());

	// if value is empty, do nothing
	if (value == "") {
		$(obj).val(value);
		return validateRequired(obj);
	}

	// get source of the autocomplete
	var list = $(obj).autocomplete("option", "source");
	var map;

	if (list != null) {
		// convert list to Map.
		map = listToMap(list);

		// check if value exists in Map as Key
		if (map[value] != null) {

			// set the value of element as key-value
			$(obj).val(map[value]);

			if ($(obj).hasClass("error")) {
				$(obj).removeClass('error').addClass('success');
			} else {
				$(obj).addClass("success");
			}
			$(obj).next("div").remove();
			return true;

		}

		// check if value exists in the list.
		if (jQuery.inArray(value, list) > -1) {
			if ($(obj).hasClass("error")) {
				$(obj).removeClass('error').addClass('success');
			} else {
				$(obj).addClass("success");
			}
			$(obj).next("div").remove();
			return true;
		}
	}
	// if value does not exists create the error Div
	createErrorDiv(obj);
	return false;
}

//maxlength validation for textarea
$(function() {
	$("textarea[maxlength]").bind('input propertychange', function() {
		var maxLength = $(this).attr('maxlength');
		if ($(this).val().length > maxLength) {
			$(this).val($(this).val().substring(0, maxLength));
		}
	});
});

$('.mandatoryAutocomplete').blur(function() {

	validateAutocomplete(this);

});

$('.mandatoryAutocompleteCiudadTomador').blur(
		function() {

			validateAutocomplete(this);
			if ($("#ciudadDesc").val() != '') {
				$("#ciudad").val(
						$.trim($("#ciudadDesc").val().substr(0,
								$("#ciudadDesc").val().indexOf("-"))));
			}

		});

function listToMap(list) {
	var map = new Object();
	var code;
	var itemValue;

	for ( var i = 0; i < list.length; i++) {
		itemValue = list[i];
		if (itemValue.indexOf("-") > -1) {
			code = $.trim(itemValue.substr(0, itemValue.indexOf("-")));

		} else {
			code = itemValue;
		}

		map[code] = itemValue;
	}
	return map;
}

function createErrorDiv(ohject) {

	var label = 'Opcion';

	if ($(ohject).parent().prev().hasClass('control-label')) {
		label = $(ohject).parent().prev().text().replace('*', '');
	}

	$(ohject).next("div").remove();
	$(ohject).addClass("error");
	$(ohject).after(
			$("<div id='errorDiv' class='error' style='color: #BD4247;'> "
					+ label + " no presentes en la lista </div>"));

}

$('.currency').blur(function() {

	/*
	 * if($.trim($(this).val()) == '') return ;
	 */

	var id = $(this).attr("id").replace("_formatted", "");
	$("#" + id).val($(this).asNumber({
		region : 'es-CO'
	}));

	$(this).val($("#" + id).val());

	$(this).formatCurrency({
		region : 'es-CO',
		roundToDecimalPlace : 0
	});

});

function validateCurrency(obj) {

	var check = true;
	var test = parseFloat($(obj).asNumber({
		region : 'es-CO'
	}));
	if (test > 0) {

		if ($(obj).hasClass("error")) {
			$(obj).removeClass('error').addClass('success');
		} else {
			$(obj).addClass("success");
		}
		$(obj).next("div").remove();

	} else {

		check = false;
		$(obj).next("div").remove();
		$(obj).addClass("error");
		$(obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Debe ser mayor que cero </div>"));

	}

	return check;
}

function minlength(obj, length) {

	var check = true;
	if (($(obj).val().length) < length) {
		check = false;
		$(obj).next("div").remove();
		$(obj).addClass("error");
		$(obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Debe ser al menos "
						+ length + " alfabetos </div>"));

	} else {

		if ($(obj).hasClass("error")) {
			$(obj).removeClass('error').addClass('success');
		} else {
			$(obj).addClass("success");
		}
		$(obj).next("div").remove();

	}
	return check;
}

//validation field to allow numbers only

jQuery('.numbersOnly').keyup(
		function(event) {
			// Allow: backspace, delete, tab, escape, and enter
			if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9
					|| event.keyCode == 27 || event.keyCode == 13 ||
					// Allow: Ctrl+A
					(event.keyCode == 65 && event.ctrlKey === true) ||
					// Allow: home, end, left, right, up, down
					(event.keyCode >= 35 && event.keyCode <= 40)) {
				return;
			}
			this.value = this.value.replace(/[^0-9]/g, '');
		});

//convert input field values to UPPERCASE letters
$('input:text').keyup(function() {
	if ($(this).hasClass("email")) {
		return;
	}
	$(this).css("text-transform", "uppercase");
	// this.value = this.value.toUpperCase();
});

//convert description field values to UPPERCASE letters
$('textarea').keyup(function() {
	// this.value = this.value.toUpperCase();
	$(this).css("text-transform", "uppercase");
});

$('input:text').blur(function() {
	if ($(this).hasClass("email")) {
		return;
	}
	this.value = this.value.toUpperCase();
});

//convert description field values to UPPERCASE letters
$('textarea').blur(function() {
	this.value = this.value.toUpperCase();
});

//validation for email
$('.email')
.blur(
		function() {
			var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!regex.test($.trim($(this).val()))
					&& $.trim($(this).val()) != "") {
				$(this).next("div").remove();
				$(this).addClass("error");
				$(this)
				.after(
						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Correo Inv\u00E1lido</div>"));
				return false;
			} else {
				if ($(this).hasClass("error")) {
					$(this).removeClass('error').addClass('success');
				} else {
					$(this).addClass("success");
				}
				$(this).next("div").remove();
				return true;
			}
		});
$(".datepicker").blur(
		function() {
			var id = $(this).attr('id');
			var currentTime = new Date();
			var year = currentTime.getFullYear();
			var date = $(this).val();
			var newDate = "";
			var validDatePattern = "";
			var validDatePattern1 = "";
			var validDatePattern2 = "";

			if (date.length == 4) {
				validDatePattern = date.match("\\d{2}\\d{2}");
				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ year;
			}

			if (date.length == 5) {
				validDatePattern = date.match("\\d{2}[\\/]\\d{2}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}");
				if (validDatePattern)
					newDate = date + "/" + year;
				if (validDatePattern1)
					newDate = date + "-" + year;
				if (validDatePattern2)
					newDate = date + "." + year;
			}

			if (date.length == 8) {
				validDatePattern = date.match("\\d{2}\\d{2}\\d{4}");

				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ date[4] + date[5] + date[6] + date[7];
			}

			if (date.length == 10) {

				validDatePattern = date.match("\\d{2}[\\/]\\d{2}[\\/]\\d{4}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}[\\-]\\d{4}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}[\\.]\\d{4}");
				newDate = date;
			}
			// // Detailed check for valid date ranges

			var monthfield = "";
			var dayfield = "";
			var yearfield = "";

			if (validDatePattern) {
				monthfield = newDate.split("/")[1];
				dayfield = newDate.split("/")[0];
				yearfield = newDate.split("/")[2];
			}
			if (validDatePattern1) {
				monthfield = newDate.split("-")[1];
				dayfield = newDate.split("-")[0];
				yearfield = newDate.split("-")[2];
			}
			if (validDatePattern2) {
				monthfield = newDate.split(".")[1];
				dayfield = newDate.split(".")[0];
				yearfield = newDate.split(".")[2];
			}

			newDate = dayfield + "/" + monthfield + "/" + yearfield;
			validateDate(id, monthfield, dayfield, yearfield, newDate);
		});

$(".datepickerSalud").blur(
		function() {
			var id = $(this).attr('id');
			var currentTime = new Date();
			var year = currentTime.getFullYear();
			var date = $(this).val();
			var newDate = "";
			var validDatePattern = "";
			var validDatePattern1 = "";
			var validDatePattern2 = "";

			if (date.length == 4) {
				validDatePattern = date.match("\\d{2}\\d{2}");
				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ year;
			}

			if (date.length == 5) {
				validDatePattern = date.match("\\d{2}[\\/]\\d{2}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}");
				if (validDatePattern)
					newDate = date + "/" + year;
				if (validDatePattern1)
					newDate = date + "-" + year;
				if (validDatePattern2)
					newDate = date + "." + year;
			}

			if (date.length == 8) {
				validDatePattern = date.match("\\d{2}\\d{2}\\d{4}");

				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ date[4] + date[5] + date[6] + date[7];
			}

			if (date.length == 10) {

				validDatePattern = date.match("\\d{2}[\\/]\\d{2}[\\/]\\d{4}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}[\\-]\\d{4}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}[\\.]\\d{4}");
				newDate = date;
			}
			// // Detailed check for valid date ranges

			var monthfield = "";
			var dayfield = "";
			var yearfield = "";

			if (validDatePattern) {
				monthfield = newDate.split("/")[1];
				dayfield = newDate.split("/")[0];
				yearfield = newDate.split("/")[2];
			}
			if (validDatePattern1) {
				monthfield = newDate.split("-")[1];
				dayfield = newDate.split("-")[0];
				yearfield = newDate.split("-")[2];
			}
			if (validDatePattern2) {
				monthfield = newDate.split(".")[1];
				dayfield = newDate.split(".")[0];
				yearfield = newDate.split(".")[2];
			}

			newDate = dayfield + "/" + monthfield + "/" + yearfield;
			validateDateSalud(id, monthfield, dayfield, yearfield, newDate);
		});

function validateDateSalud(id, monthfield, dayfield, yearfield, newDate) {
	var dayobj = new Date(yearfield, monthfield - 1, dayfield);
	var currentTime = new Date();
	// alert("Date is"+$("#" + id).val());

	if ($.trim($("#" + id).val()) == "") {
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> El campo obligatorio</div>"));

		return;
	}

	if ((dayobj.getMonth() + 1 != monthfield) || (dayobj.getDate() != dayfield)
			|| (dayobj.getFullYear() != yearfield)) {

		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no Valida </div>"));

	} else if (currentTime.getFullYear() < yearfield) {
		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> La fecha no puede ser mayor que la fecha actual.</div>"));
	} else if (currentTime.getFullYear() == yearfield
			&& currentTime.getMonth() + 1 < monthfield) {
		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> La fecha no puede ser mayor que la fecha actual.</div>"));
	} else if (currentTime.getFullYear() == yearfield
			&& currentTime.getMonth() + 1 == monthfield
			&& currentTime.getDate() < dayfield) {
		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> La fecha no puede ser mayor que la fecha actual.</div>"));
	} else if (isDateBeforeYrs(newDate, 60)) {

		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Persona mayor de 60 anos, no peude incluir se en poliza de salud. </div>"));

	} else {
		document.getElementById(id).value = newDate;
		if ($("#" + id).hasClass("error")) {
			$("#" + id).removeClass('error').addClass('success');
		} else {
			$("#" + id).addClass("success");
		}
		$("#" + id).next().next("div").remove();

		$('#edad').val(calculateAge(newDate));

		return true;

	}

}

$(".datepicker1").blur(
		function() {
			var id = $(this).attr('id');
			var currentTime = new Date();
			var year = currentTime.getFullYear();
			var date = $(this).val();
			var newDate = "";
			var validDatePattern = "";
			var validDatePattern1 = "";
			var validDatePattern2 = "";

			if (date.length == 4) {
				validDatePattern = date.match("\\d{2}\\d{2}");
				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ year;
			}

			if (date.length == 5) {
				validDatePattern = date.match("\\d{2}[\\/]\\d{2}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}");
				if (validDatePattern)
					newDate = date + "/" + year;
				if (validDatePattern1)
					newDate = date + "-" + year;
				if (validDatePattern2)
					newDate = date + "." + year;
			}

			if (date.length == 8) {
				validDatePattern = date.match("\\d{2}\\d{2}\\d{4}");

				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ date[4] + date[5] + date[6] + date[7];
			}

			if (date.length == 10) {

				validDatePattern = date.match("\\d{2}[\\/]\\d{2}[\\/]\\d{4}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}[\\-]\\d{4}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}[\\.]\\d{4}");
				newDate = date;
			}
			// // Detailed check for valid date ranges

			var monthfield = "";
			var dayfield = "";
			var yearfield = "";

			if (validDatePattern) {
				monthfield = newDate.split("/")[1];
				dayfield = newDate.split("/")[0];
				yearfield = newDate.split("/")[2];
			}
			if (validDatePattern1) {
				monthfield = newDate.split("-")[1];
				dayfield = newDate.split("-")[0];
				yearfield = newDate.split("-")[2];
			}
			if (validDatePattern2) {
				monthfield = newDate.split(".")[1];
				dayfield = newDate.split(".")[0];
				yearfield = newDate.split(".")[2];
			}

			newDate = dayfield + "/" + monthfield + "/" + yearfield;
			validateDate(id, monthfield, dayfield, yearfield, newDate);
		});

function validateDate(id, monthfield, dayfield, yearfield, newDate) {
	var dayobj = new Date(yearfield, monthfield - 1, dayfield);
	var currentTime = new Date();
	// alert("Date is"+$("#" + id).val());
	if ($.trim($("#" + id).val()) == "") {
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> El campo obligatorio</div>"));
		return;
	}

	if (($("#" + id).val() != " ") && (dayobj.getMonth() + 1 != monthfield)
			|| (dayobj.getDate() != dayfield)
			|| (dayobj.getFullYear() != yearfield)) {

		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no Valida </div>"));

	} else if (currentTime.getFullYear() < yearfield) {
		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> La fecha no puede ser mayor que la fecha actual.</div>"));
	} else if (currentTime.getFullYear() == yearfield
			&& currentTime.getMonth() + 1 < monthfield) {
		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> La fecha no puede ser mayor que la fecha actual.</div>"));
	} else if (currentTime.getFullYear() == yearfield
			&& currentTime.getMonth() + 1 == monthfield
			&& currentTime.getDate() < dayfield) {
		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> La fecha no puede ser mayor que la fecha actual.</div>"));
	} else {
		document.getElementById(id).value = newDate;
		if ($("#" + id).hasClass("error")) {
			$("#" + id).removeClass('error').addClass('success');
		} else {
			$("#" + id).addClass("success");
		}
		$("#" + id).next().next("div").remove();

		return true;

	}

}

$(".driverDOB").blur(
		function() {
			var id = $(this).attr('id');
			var currentTime = new Date();
			var year = currentTime.getFullYear();
			var date = $(this).val();
			var newDate = "";
			var validDatePattern = "";
			var validDatePattern1 = "";
			var validDatePattern2 = "";

			if (date.length == 4) {
				validDatePattern = date.match("\\d{2}\\d{2}");
				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ year;
			}

			if (date.length == 5) {
				validDatePattern = date.match("\\d{2}[\\/]\\d{2}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}");
				if (validDatePattern)
					newDate = date + "/" + year;
				if (validDatePattern1)
					newDate = date + "-" + year;
				if (validDatePattern2)
					newDate = date + "." + year;
			}

			if (date.length == 8) {
				validDatePattern = date.match("\\d{2}\\d{2}\\d{4}");

				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ date[4] + date[5] + date[6] + date[7];
			}

			if (date.length == 10) {

				validDatePattern = date.match("\\d{2}[\\/]\\d{2}[\\/]\\d{4}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}[\\-]\\d{4}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}[\\.]\\d{4}");
				newDate = date;
			}
			// // Detailed check for valid date ranges

			var monthfield = "";
			var dayfield = "";
			var yearfield = "";

			if (validDatePattern) {
				monthfield = newDate.split("/")[1];
				dayfield = newDate.split("/")[0];
				yearfield = newDate.split("/")[2];
			}
			if (validDatePattern1) {
				monthfield = newDate.split("-")[1];
				dayfield = newDate.split("-")[0];
				yearfield = newDate.split("-")[2];
			}
			if (validDatePattern2) {
				monthfield = newDate.split(".")[1];
				dayfield = newDate.split(".")[0];
				yearfield = newDate.split(".")[2];
			}

			newDate = dayfield + "/" + monthfield + "/" + yearfield;

			validateDrivingDate(id, monthfield, dayfield, yearfield, newDate);
		});

function validateDrivingDate(id, monthfield, dayfield, yearfield, newDate) {
	var dayobj = new Date(yearfield, monthfield - 1, dayfield);

	if ($.trim($("#" + id).val()) == "") {
		if ($("#uso").val() == '31') {
			$("#" + id).next().next("div").remove();
			$("#" + id).addClass("error");
			$("#" + id)
			.next()
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> El campo obligatorio</div>"));
		}
		return;
	}

	if ((dayobj.getMonth() + 1 != monthfield) || (dayobj.getDate() != dayfield)
			|| (dayobj.getFullYear() != yearfield)) {

		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no Valida </div>"));

	} else if (!isDateBeforeOrEqualYrs(newDate, 16)) {

		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no valida o edad inferior a 16, no se permite </div>"));

	} else if (isDateBeforeYrs(newDate, 85)) {

		$("#" + id).val("");
		$("#" + id).next().next("div").remove();
		$("#" + id).addClass("error");
		$("#" + id)
		.next()
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no valida o edad mayor a 85, no se permite </div>"));

	} else {
		document.getElementById(id).value = newDate;
		if ($("#" + id).hasClass("error")) {
			$("#" + id).removeClass('error').addClass('success');
		} else {
			$("#" + id).addClass("success");
		}
		$("#" + id).next().next("div").remove();

		return true;

	}

}
var idVida;
$(".datepickerVida").blur(
		function() {
			var id = $(this).attr('id');
			idVida=id;
			// date field is readonly then not to call blur on this field

			var currentTime = new Date();
			var year = currentTime.getFullYear();
			var date = $(this).val();
			var newDate = "";
			var validDatePattern = "";
			var validDatePattern1 = "";
			var validDatePattern2 = "";

			if (date.length == 4) {
				validDatePattern = date.match("\\d{2}\\d{2}");
				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ year;
			}

			if (date.length == 5) {
				validDatePattern = date.match("\\d{2}[\\/]\\d{2}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}");
				if (validDatePattern)
					newDate = date + "/" + year;
				if (validDatePattern1)
					newDate = date + "-" + year;
				if (validDatePattern2)
					newDate = date + "." + year;
			}

			if (date.length == 8) {
				validDatePattern = date.match("\\d{2}\\d{2}\\d{4}");

				newDate = date[0] + date[1] + "/" + date[2] + date[3] + "/"
				+ date[4] + date[5] + date[6] + date[7];
			}

			if (date.length == 10) {

				validDatePattern = date.match("\\d{2}[\\/]\\d{2}[\\/]\\d{4}");
				validDatePattern1 = date.match("\\d{2}[\\-]\\d{2}[\\-]\\d{4}");
				validDatePattern2 = date.match("\\d{2}[\\.]\\d{2}[\\.]\\d{4}");
				newDate = date;
			}
			// // Detailed check for valid date ranges

			var monthfield = "";
			var dayfield = "";
			var yearfield = "";

			if (validDatePattern) {
				monthfield = newDate.split("/")[1];
				dayfield = newDate.split("/")[0];
				yearfield = newDate.split("/")[2];
			}
			if (validDatePattern1) {
				monthfield = newDate.split("-")[1];
				dayfield = newDate.split("-")[0];
				yearfield = newDate.split("-")[2];
			}
			if (validDatePattern2) {
				monthfield = newDate.split(".")[1];
				dayfield = newDate.split(".")[0];
				yearfield = newDate.split(".")[2];
			}

			newDate = dayfield + "/" + monthfield + "/" + yearfield;
			validateVidaDate(id, monthfield, dayfield, yearfield, newDate,date);
		});

function validateVidaDate(id, monthfield, dayfield, yearfield, newDate,date) {
	var dayobj = new Date(yearfield, monthfield - 1, dayfield);
	$('#edadWarning').hide();
	if ($.trim($("#" + id).val()) == "") {
		if ($("#" + id).is(":visible")) {
			$("#" + id).next().next("div").remove();
			$("#" + id).addClass("error");
			$("#" + id)
			.next()
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> El campo obligatorio</div>"));
			$("#" + id).focus();
		} else {
			$("#ncmntoFechaWD").next("div").remove();
			$("#ncmntoFechaWD").addClass("error");
			$("#ncmntoFechaWD")
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> El campo obligatorio</div>"));
			$("#ncmntoFechaWD").focus();
		}
		$('#edadWarning').hide();
		$("#edad").val("");
		return;
	}

	if ((dayobj.getMonth() + 1 != monthfield) || (dayobj.getDate() != dayfield)
			|| (dayobj.getFullYear() != yearfield)) {
		if ($("#" + id).is(":visible")) {
			$("#" + id).val("");
			$("#" + id).next().next("div").remove();
			$("#" + id).addClass("error");
			$("#" + id)
			.next()
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no Valida </div>"));
			$("#" + id).focus();
		} else {
			$("#ncmntoFechaWD").val("");
			$("#ncmntoFechaWD").next("div").remove();
			$("#ncmntoFechaWD").addClass("error");
			$("#ncmntoFechaWD")
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no Valida </div>"));
			$("#ncmntoFechaWD").focus();
		}
		$("#edad").val("");

	} else if (!isDateBeforeOrEqualYrs(newDate, 18)) {
		if ($("#" + id).is(":visible")) {
			// $("#" + id).val("");
			$("#" + id).next().next("div").remove();
			$("#" + id).addClass("error");
			$("#" + id)
			.next()
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no valida o edad inferior a 18, no se permite </div>"));
			$("#" + id).focus();
		} else {
			// $("#ncmntoFechaWD").val("");
			$("#ncmntoFechaWD").next("div").remove();
			$("#ncmntoFechaWD").addClass("error");
			$("#ncmntoFechaWD")
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no valida o edad inferior a 18, no se permite </div>"));
			$("#ncmntoFechaWD").focus();
		}

		$('#edadWarning').hide();
		// $("#edad").val("");

	} else if (isDateBeforeYrs(newDate, 66)) {
		if ($("#" + id).is(":visible")) {
			// $("#" + id).val("");
			$("#" + id).next().next("div").remove();
			$("#" + id).addClass("error");
			$("#" + id)
			.next()
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no valida o edad mayor a 65, no se permite </div>"));
			$("#" + id).focus();
		} else {
			// $("#ncmntoFechaWD").val("");
			$("#ncmntoFechaWD").next("div").remove();
			$("#ncmntoFechaWD").addClass("error");
			$("#ncmntoFechaWD")
			.after(
					$("<div id='errorDiv' class='error' style='color: #BD4247;'> Fecha no valida o edad mayor a 65, no se permite  </div>"));
			$("#ncmntoFechaWD").focus();
		}

		$('#edadWarning').hide();
		// $("#edad").val("");
	} else {
		if (date.length == 10) 
			$("#" + id).focus(newDate) ;
		else
			$("#" + id).val(newDate) ;
		var map = new Object();
		map['yearfield'] = yearfield;
		map['monthfield'] = monthfield;
		map['dayfield'] = dayfield;
		// create json map for received argument map
		var jsonmap = JSON.stringify(map);

		// create dwr method arguments map
		var postParam = $.param({
			param0 : objectEval(jsonmap)
		});

		// callbak function map
		var mapforcallbackfunction = {
				"success" : vidaAgeSuccess
		};

		var getVidaAgeUrl = contextPth+ '/dwr/jsonp/CommonDwr/getVidaAge/';

		// call dwr with url , argument map , error span and call back functions map
		getDwrData(getVidaAgeUrl, postParam," ", mapforcallbackfunction);
	}

}

var vidaAgeSuccess = function(data) {
	$("#edad").val(data);
	$("#edad").attr("readOnly", true);
	$('#edadWarning').show();
	if ($("#" + idVida).hasClass("error")) {
		$("#" + idVida).removeClass('error').addClass('success');
	} else {
		$("#" + idVida).addClass("success");
	}
	$("#" + idVida).next().next("div").remove();

	if ($("#edad").hasClass("error")) {
		$("#edad").removeClass('error').addClass('success');
	} else {
		$("#edad").addClass("success");
	}
	$("#edad").next("div").remove();
	if (validationEdad()) {
		return true;
	}
	return false;
};

$(".driverDOB").datepicker({
	showOn : "button",
	buttonImage : "images/icons/calendar.gif",
	buttonImageOnly : true,
	changeMonth : true,
	changeYear : true,
	showButtonPanel : true,
	yearRange : "-85:-16",
	dateFormat : "dd/mm/yy",
	maxDate : "-16Y", // Will only allow the selection of dates more than 16
	// years ago, useful if you need to restrict this
	minDate : "-85Y",
	onSelect : function() {
		$(".driverDOB").blur();
	}
});

$(".datepicker").datepicker({
	showOn : "button",
	buttonImage : "images/icons/calendar.gif",
	buttonImageOnly : true,
	changeMonth : true,
	changeYear : true,
	showButtonPanel : true,
	yearRange : "-100:+0",
	dateFormat : "dd/mm/yy",
	onSelect : function() {
		$(".datepicker").blur();
	}
});

$(".datepickerSalud").datepicker({
	showOn : "button",
	buttonImage : "images/icons/calendar.gif",
	buttonImageOnly : true,
	changeMonth : true,
	changeYear : true,
	showButtonPanel : true,
	yearRange : "-60:+0",
	dateFormat : "dd/mm/yy",
	minDate : "-60Y",
	onSelect : function() {
		$(".datepickerSalud").blur();
	}
});

$(".datepicker1").datepicker({
	showOn : "button",
	buttonImage : "images/icons/calendar.gif",
	buttonImageOnly : true,
	changeMonth : true,
	changeYear : true,
	showButtonPanel : true,
	yearRange : "-100:+0",
	dateFormat : "dd/mm/yy",
	onSelect : function() {
		$(".datepicker1").blur();
	}
});

$(".datepickerVida").datepicker({
	showOn : "button",
	buttonImage : "images/icons/calendar.gif",
	buttonImageOnly : true,
	changeMonth : true,
	changeYear : true,
	showButtonPanel : true,
	yearRange : "-65:-18",
	dateFormat : "dd/mm/yy",
	maxDate : "-18Y", // Will only allow the selection of dates more than 16
	// years ago, useful if you need to restrict this
	minDate : "-65Y",
	onSelect : function() {
		$(".datepickerVida").blur();
		validationEdad();
	}
});

$("#celular").blur(function() {
	if ($("#celular").hasClass("requiredSubmit")) {
		if (!validateRequired(this)) {
			return false;
		}
	}
	var phone_number = $("#celular").val();

	// if (phone_number != '' && !phone_number.match(/\d{10}/)) {
	// $("#celular").next("div").remove();
	// $("#celular").addClass("error");
	// $("#celular")
	// .after(
	// $("<div id='errorDiv' class='error' style='color: #BD4247;'>Telefono
	// celular debe ser de 10 digitos </div>"));
	// } else {
	// $("#celular").next("div").remove();
	// }

});

$("#celularFormated")
.blur(
		function() {
			if ($("#celularFormated").hasClass("requiredSubmit")) {
				if (!validateRequired(this)) {
					return false;
				}
			}
			var phone_number = $("#celularFormated").val();
			$("#celular").val(phone_number);
			if (phone_number != '' && !phone_number.match(/\d{10}/)) {
				$("#celularFormated").next("div").remove();
				$("#celularFormated").addClass("error");
				$("#celularFormated")
				.after(
						$("<div id='errorDiv' class='error' style='color: #BD4247;'>Telefono celular debe ser de 10 digitos </div>"));
			} else {
				$("#celularFormated").next("div").remove();
				$("#celular").val($("#celularFormated").val());
			}

		});

function resValidateDocNum(t) {

	if ($.trim(t.value) == 0) {
		t.value = "";
		return false;
	} else {
		return true;
	}
}

//function to compare if first date is less than second date
function isDateBefore(d1, d2) {
	if ($.trim(d1) == '' || $.trim(d2) == '') {
		return;
	}

	date1 = createtDate(d1);
	date2 = createtDate(d2);

	if (date1 < date2) {
		// alert(d1+' is less than '+d2);
		return true;
	} else {
		// alert(d1+' is greater than '+d2);
		return false;
	}
}

//function to check if the date is before specified years for this
//pass the date in format format dd/mm/yyyy, dd-mm-yyyy, dd.mm.yyyy and number
//of years
function isDateBeforeYrs(d1, yrs) {

	var currentTime = new Date();

	var backDtStr = currentTime.getDate() + '-' + (currentTime.getMonth() + 1)
	+ '-' + (currentTime.getFullYear() - yrs);

	var backDate = createtDate(backDtStr);// yyyy, mm, dd

	// alert('backDate '+backDate);

	var date1 = createtDate(d1);// yyyy, mm, dd
	// alert('my dte '+date1);
	if (date1 < backDate) {
		// alert('date is '+yrs+' years before');
		return true;
	} else {
		// alert('date is within '+yrs+' years ');
		return false;
	}

}

//function to check if the date is before or equal specified years for this
//pass the date in format format dd/mm/yyyy, dd-mm-yyyy, dd.mm.yyyy and number
//of years
function isDateBeforeOrEqualYrs(d1, yrs) {

	var currentTime = new Date();

	var backDtStr = currentTime.getDate() + '-' + (currentTime.getMonth() + 1)
	+ '-' + (currentTime.getFullYear() - yrs);

	var backDate = createtDate(backDtStr);// yyyy, mm, dd

	// alert('backDate '+backDate);

	var date1 = createtDate(d1);// yyyy, mm, dd
	// alert('my dte '+date1);
	if (date1 <= backDate) {
		// alert('date is '+yrs+' years before');
		return true;
	} else {
		// alert('date is within '+yrs+' years ');
		return false;
	}

}

//format dd/mm/yyyy, dd-mm-yyyy, dd.mm.yyyy
function createtDate(d) {
	var d1 = d.split(/\D+/); // splits on any character(s) between digits
	return new Date(d1[2], (d1[1] - 1), d1[0]); // // yyyy, mm, dd
}

//get current date in dd/MM/yyy format
function currentDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = "";
	mm = today.getMonth() + 1;// January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10)
		dd = '0' + dd;
	if (mm < 10)
		mm = '0' + mm;
	return today = dd + '/' + mm + '/' + yyyy;

}

function calculateAge(d) {

	var dob = createtDate(d);
	var currentDate = new Date();
	var currentYear = currentDate.getFullYear();
	var birthdayThisYear = new Date(currentYear, dob.getMonth(), dob.getDate());
	var age = currentYear - dob.getFullYear();

	if (birthdayThisYear > currentDate) {
		age--;
	}

	return age;
}

function formatCurency(obj) {
	$('#' + obj).formatCurrency({
		region : 'es-CO',
		roundToDecimalPlace : 0
	});
}



(function($) {
	$.fn.decimalOnly = function() {
		$(this)
		.keydown(
				function(event) {
					// Allow: backspace, delete, tab, escape, and enter
					if (event.keyCode == 46
							|| event.keyCode == 8
							|| event.keyCode == 9
							|| event.keyCode == 27
							|| event.keyCode == 13
							||
							// Allow: Ctrl+A
							(event.keyCode == 65 && event.ctrlKey === true)
							||
							// Allow: home, end, left, right
							(event.keyCode >= 35 && event.keyCode <= 39)) {
						// let it happen, don't do anything
						return;
					} else if (event.keyCode == 190
							|| event.keyCode == 110) { // period
						if ($(this).val().indexOf('.') !== -1) // period
							// already
							// exists
							event.preventDefault();
						else
							return;
					} else {
						// Ensure that it is a number and stop the
						// keypress
						if (event.shiftKey
								|| (event.keyCode < 48 || event.keyCode > 57)
								&& (event.keyCode < 96 || event.keyCode > 105)) {
							event.preventDefault();
						}
					}
				});
	}

	$.fn.numericOnly = function() {
		$(this)
		.keydown(
				function(event) {
					// Allow: backspace, delete, tab, escape, and enter
					if (event.keyCode == 46
							|| event.keyCode == 8
							|| event.keyCode == 9
							|| event.keyCode == 27
							|| event.keyCode == 13
							||
							// Allow: Ctrl+A
							(event.keyCode == 65 && event.ctrlKey === true)
							||
							// Allow: home, end, left, right
							(event.keyCode >= 35 && event.keyCode <= 39)) {
						// let it happen, don't do anything
						return;
					} else {
						// Ensure that it is a number and stop the
						// keypress
						if (event.shiftKey
								|| (event.keyCode < 48 || event.keyCode > 57)
								&& (event.keyCode < 96 || event.keyCode > 105)) {
							event.preventDefault();
						}
					}
				});
	}
})(jQuery);

function removeErrorDiv(field) {
	if ($("#" + field).hasClass("error")) {
		$("#" + field).removeClass('error').addClass('success');
	} else {
		$("#" + field).addClass("success");
	}
	$("#" + field).next("div").remove();
}

function validateDateFromCurrentDate(obj){
	var expireDateStr = $("#"+obj).val();
	var expireDateArr = expireDateStr.split("/");
	
	var expireDate = new Date(expireDateArr[2], parseInt(expireDateArr[1])-1, expireDateArr[0]);
	var currentDate = new Date();
	
	var todayDate = new Date(currentDate.getFullYear(),currentDate.getMonth(),currentDate.getDate());
	
	var miliInDay = (1000 * 3600 * 24);
	var check = true;/*
	var someDate = new Date();
	var numberOfDaysToAdd = 2;
	someDate.setDate(todayDate.getDate() + numberOfDaysToAdd);*/ 
	if (todayDate > expireDate) {

		check = false;
		$("#"+obj).next("div").remove();
		$("#"+obj).addClass("error");
		$("#"+obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Date can't be less than current date. </div>"));

	}else if(((expireDate - todayDate)/miliInDay) > 2){
		check = false;
		
		$("#"+obj).next("div").remove();
		$("#"+obj).addClass("error");
		$("#"+obj)
		.after(
				$("<div id='errorDiv' class='error' style='color: #BD4247;'> Delivery date can not be fall after 2 days. </div>"));
	}
	else {
		if ($("#"+obj).hasClass("error")) {
			$("#"+obj).removeClass('error').addClass('success');
		} else {
			$("#"+obj).addClass("success");
		}
		$("#"+obj).next("div").remove();
	}
	return check;
}

function validateTimeSlot(){
	var date = new Date();
	alert(date.getHours());
	alert(date.getMinutes());
	alert(date.getSeconds());
	
	return false;
}

$(".nombre").blur(function() {

	var name = $(this).val();
	name = name.replace(/^\s+/, "").replace(/\s+$/, "").replace(/\s+/, " ");
	if ($(this).hasClass("requiredSubmit")) {
		// show error
		if (!validateRequired(this)) {
			return false;
		}

	}

	if (name != "") {

		// valid: maybe put trimmed name back into form
		$(this).val(name);
	}

});

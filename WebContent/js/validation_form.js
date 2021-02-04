function validateForm(){
	var x=document.forms["contact_form_bestkart"]["eq_name"].value;
	if (x==null || x=="Full Name"){
		document.getElementById("eq_name").style.background="#f77065";
		document.getElementById("eq_name").style.border="1px solid #c51c0e";
		return false;
	}

	//var x=document.forms["contact_form_bestkart"]["eq_email"].value;

	
	var emailID = document.forms["contact_form_bestkart"]["eq_email"].value;
    atpos = emailID.indexOf("@");
    dotpos = emailID.lastIndexOf(".");
    if(atpos < 1 || ( dotpos - atpos < 2 )){
        document.getElementById("eq_email").style.background="#ed867d";
		document.getElementById("eq_email").style.border="1px solid #c51c0e";
        return false;
    }
	
	var x=document.forms["contact_form_bestkart"]["eq_contact"].value;
	if (x==null || x=="Contact Number"){
		document.getElementById("eq_contact").style.background="#ed867d";
		document.getElementById("eq_contact").style.border="1px solid #c51c0e";
		return false;
	}
	
	var x=document.forms["contact_form_bestkart"]["eq_subject"].value;
	if (x==null || x=="Subject"){
		document.getElementById("eq_subject").style.background="#ed867d";
		document.getElementById("eq_subject").style.border="1px solid #c51c0e";
		return false;
	}
	
	var x=document.forms["contact_form_bestkart"]["eq_query"].value;
	if (x==null || x=="Query"){
  		document.getElementById("eq_query").style.background="#ed867d";
		document.getElementById("eq_query").style.border="1px solid #c51c0e";
		return false;
  	}
//alert("dddd"+document.contact_form_bestkart);
	//document.contact_form_bestkart.submit();
	document.forms["contact_form_bestkart"].submit();
}

function isNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57)) {
			return false;
		}
		return true;
	}
	
	

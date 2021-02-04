function validateForm(){
	var x=document.forms["contact_form"]["name"].value;
	if (x==null || x=="Name"){
		document.getElementById("name").style.background="#f77065";
		document.getElementById("name").style.border="1px solid #c51c0e"
		return false;
	}

	var x=document.forms["contact_form"]["email"].value;

	
	if (x==null || x=="Email"){
  		document.getElementById("email").style.background="#ed867d";
		document.getElementById("email").style.border="1px solid #c51c0e"
		return false;
  	}
	
	var x=document.forms["contact_form"]["txtInput"].value;
	if (x==null || x=="Enter the letters here"){
  		document.getElementById("txtInput").style.background="#ed867d";
		document.getElementById("txtInput").style.border="1px solid #c51c0e"
		return false;
  	}
	
	var x=document.forms["contact_form"]["query"].value;
	if (x==null || x=="Message"){
  		document.getElementById("query").style.background="#ed867d";
		document.getElementById("query").style.border="1px solid #c51c0e"
		return false;
  	}

}


   //Created / Generates the captcha function
       
$(document).ready(function DrawCaptcha()
    {
        var a = Math.ceil(Math.random() * 10)+ '';
        var b = Math.ceil(Math.random() * 10)+ '';       
        var c = Math.ceil(Math.random() * 10)+ '';  
        var d = Math.ceil(Math.random() * 10)+ '';  
        var code = a + b + c + d;
        document.getElementById("txtCaptcha").value = code
    }
)
    // Validate the Entered input aganist the generated security code function   
    /*function ValidCaptcha(){
        var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
        var str2 = removeSpaces(document.getElementById('txtInput').value);
        if (str1 == str2) return true;        
        return false;
        
    }*/

    // Remove the spaces from the entered and generated code
    /*function removeSpaces(string)
    {
        return string.split(' ').join('');
    }*/
    
 
	function ValidateNum(input,event){
		var keyCode = event.which ? event.which : event.keyCode;
		if(parseInt(keyCode)>=48 && parseInt(keyCode)<=57){
			return true;
		}
		return false;
	}


<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<!--
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	
	,submitHandler: function(form) {
	alert("test1");
            form.submit();
        }
	
	-->
	<link rel="stylesheet" href="http://jqueryvalidation.org/files/demo/site-demos.css">
 
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
	<script>
// just for the demos, avoids form submit
jQuery.validator.setDefaults({
  debug: true,
  success: "valid"
});

$(document).ready(function () {

    $( "#myform" ).validate({
  rules: {
    zipFile: {
      required: true,
      extension: "zip"
    }
  },
  messages: {  // <-- you must declare messages inside of "messages" option
        zipFile:{
            required:"Please select file to upload",                  
            extension:"Please select only zip file to upload."
        }
    }
});

    $('#buttontest').click(function() {
	    if ($('#myform').valid()) {
            //$('#myform').submit();
			document.myform.submit();
        } else {
            //alert('Please select only ZIP file to upload.');
        }
    });

});
</script>
<%-- <script src="js/jquery.min.js" type="text/javascript"></script>
 --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Upload</title>

</head>
<body>

	<jsp:include page="adminLinks.jsp"></jsp:include>
	Upload images Zip File Form
	<br />


	<form action="ZipUploadTest.action" method="post"
		enctype="multipart/form-data" id="myform" name="myform">

		<table align="center" border="1">
			<tr>
				<td><input type="file" id="field" name="zipFile" /></td>
			</tr>
			<tr>
				<td><input type="button" id="buttontest" value="Upload File" /></td>
			</tr>
			<%
				List<String> msgList = (List<String>) request
						.getAttribute("tempString");
				if (msgList != null && msgList.size() > 0) {
					for (String msg : msgList) {
			%>
			<tr>
				<td><font color="red"><%=msg%></font>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</form>


</body>
</html>
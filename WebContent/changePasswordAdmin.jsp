<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="./js/validation.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function changePassword(){
		var failFlag = 0;
		$('[required]').each(function() {
			var pass = validateRequired(this);
			if (!pass) {
				failFlag++;
			}
 		});
		if(parseInt(failFlag) > 0){
			return;
		}
		var newPass = $("#newPassword").val();
		var cnewPass = $("#cnewPassword").val();	
		if(newPass != cnewPass){
			alert("New Password and Conform-new Password must be same.");
			return false;
		}
		else{
			document.UpdatePasswordForm.submit();
		}
	}

</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Change Password Page<br/>
<form action="UpdatePassword.action" name="UpdatePasswordForm" method="post">
<table border="1" align="center">
	<tr>
		<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
	</tr>
	<tr>
		<td>Old Password</td>
		<td><input type="password" name="oldPassword" id="oldPassword" required="required"/></td>
	</tr>
	<tr>
		<td>New Password</td>
		<td><input type="password" name="newPassword" id="newPassword" required="required"/></td>
	</tr>
	<tr>
		<td>Confirm New Password</td>
		<td><input type="password" name="cNewPassword" id="cnewPassword" required="required"/></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" onclick="changePassword()" value="Change Password"/></td>
	
	</tr>
	
</table>
</form>
</body>
</html>
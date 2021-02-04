<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%--     <%@taglib uri="/struts-tags" prefix="s" %>
 --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
	"http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<script src="js/jquery.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	/* function upload(i){
		
		var prodId = $("#productId"+i).val();
		
	} */
	
	function upload(i) {

		var prodId = $("#productId"+i).val();
		//var images = $("#images"+i).val();
		var images = document.getElementById("images"+i).files[0];
		var formdata = new FormData();

		formdata.append("prodId", prodId);
        formdata.append("images", images);

        var xhr = new XMLHttpRequest();       

        xhr.open("POST","/BestKart4u/FileUploader", true);

        xhr.send(formdata);

        xhr.onload = function(e) {

            if (this.status == 200) {

               alert(this.responseText);
			   document.location.reload();	
            }

        };                    

    }   
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>

Resolve images Page<br/>

<table border="1">
	<tr>
		<th>Product Name</th><th>Product Brand Name</th><th>Image Name</th><th>Add Image</th>
	</tr>
	<%
	
		int i = 0;
		ArrayList al = (ArrayList)request.getAttribute("prodDetail");
		if(al != null && al.size() > 0){
			Iterator itr = al.iterator();
			while(itr.hasNext()){
				i = i+1;
				String arr[] = (String[])itr.next();
	%>
	<tr>
		<input type="hidden" name="productId<%=i%>" id="productId<%=i%>" value="<%=arr[0] %>"/>
		<td><%=arr[1] %></td>
		<td><%=arr[2] %></td>
		<td><%=arr[3] %></td>
		<td><input type="file" name="images<%=i%>" id="images<%=i%>"/></td>
		<td><input type="button" value="upload" onclick="upload('<%=i%>')"/></td>
	</tr>
	<%
			}
		}
		else{
	%>
		<tr>
			<td><font color="red">No data found</font></td>
		</tr>
	<%
		}
	%>
</table>

</body>
</html>
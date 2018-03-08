<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#submit").click(function() {
			console.log('click');
			jQuery.ajax({
				url : 'uploadServlet',
				data : new FormData(document.getElementById("fileForm")),
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				method : 'POST',
				success : function(data) {
					console.log("success");
					console.log(data);
				},
				complete : function(data) {
					console.log("complete");
				},
				error : function(data) {
					console.log("error");
				}
			});
		});
		$("#reportSubmit").click(function() {
			console.log('click');
			jQuery.ajax({
				url : 'generateReport',
				data : new FormData(document.getElementById("reportForm")),
				cache : false,
				contentType : false,
				processData : false,
				method : 'POST',
				success : function(data) {
					console.log("success");
					console.log(data);
					$('h4').remove();
					if(data=='true')
						$("<h4>Report is not Tempered</h4>").insertAfter("#reportForm h2");
					else
						$("<h4 style='color: red;'>Report is Tempered</h4>").insertAfter("#reportForm h2");
				},
				complete : function(data) {
					console.log("complete");
				},
				error : function(data) {
					console.log("error");
				}
			});
		});
	});
</script>
<title>File Uploading Form</title>
</head>
<body>
	<div style="margin-top: 2%; border: 1px solid black;">
		<form action="uploadServlet" method="post" id="fileForm">
			<div style="margin-left: 25%;">
				<h2>File Upload</h2>
				<div>
					Select a file to upload: <input type="file" name="file" id="file"
						size="50" />
				</div>
				<br /> <br /> <input type="button" value="Upload File" id="submit" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="submit" value="Submit File" />
			</div>
		</form>
	</div>
	<div style="margin-top: 2%; border: 1px solid black;">
		<form action="generateReport" method="post"
			enctype="multipart/form-data" id="reportForm">
			<div style="margin-left: 25%;">
				<h2>Generate Patient Report</h2>
				<label>Select Patentiet Id: </label> <select style="margin-top: 1%;"
					name="patentId">
					<%
						List<String> patientId = (ArrayList<String>) request.getAttribute("patientIds");
						for (String id : patientId) {
							out.print("<option value='"+id+"'>"+id+"</option>" + id);
						}
					%>

					<%-- <c:forEach var="id" items="${patientIds}">
						<option value='<c:out value = "${id}"/>'><c:out
								value="${id}" /></option>
					</c:forEach> --%>
				</select> <br /> <br /> <input type="button" value="Generate Report"
					id="reportSubmit" />
			</div>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#submit").click(function() {
			console.log('click');
			jQuery.ajax({
			    url: 'UploadServlet',
			    data: new FormData(document.getElementById("fileForm")),
			    enctype: 'multipart/form-data',
			    cache: false,
			    contentType: false,
			    processData: false,
			    method: 'POST',
			    success: function(data){
			       console.log("success");
			       console.log(data);
			    },
			    complete: function(data){
			    	console.log("complete");
			    },
			    error: function(data){
			    	console.log("error");
			    }
			}); 
		});
	});
</script>
<title>File Uploading Form</title>
</head>

<body>
	<div style="margin-top: 15%; border: 1px solid black;">
		<form action="UploadServlet" method="post"
			enctype="multipart/form-data" id="fileForm">
			<div style="margin-left: 25%;">
				<h3>File Upload:</h3>
				<div>
					Select a file to upload: <input type="file" name="file" id="file" size="50" />
				</div>
				<input type="button" value="Upload File" id="submit" /><br/>
				<input type="submit" value="Submit File" />
			</div>
		</form>
	</div>
</body>
</html>
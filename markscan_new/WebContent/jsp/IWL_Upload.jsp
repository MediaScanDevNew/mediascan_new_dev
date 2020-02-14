<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="report_table/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
	<form action="http://localhost:8088/crawler/uploadCSV" enctype="multipart/form-data" method="post" class="mar-top-15">
	  <input type="hidden" id="user_id" name="user_id" value="<%=session.getAttribute("uid") %>">
	  <input type="file" id="myFile" name="file">
	  <input type="submit" >
	</form>
	</div>
</body>
</html>
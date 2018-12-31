<%@ page language="java" import ="java.util.*" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import= "onlinePreview.* "%>
<%@page import= "java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Preview</title>
</head>
<body>
<h1>find some sample files in source folder</h1>
<h2>and input your full file name (eg: testjava.docx)</h2>
<form action="transfer" method="POST" target="blank">
<input type="text" name ="fileName" value="test file">
<input type="submit" value="submit"/>
</form>
</body>
</html>
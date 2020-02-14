<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<s:head />
</head>
 
<s:actionerror />
 
<s:form action="uploadAction" method="POST" enctype="multipart/form-data">
   <s:file name="uploadFile" label="Choose File" size="40" />
   <s:submit value="Upload" name="submit" />
</s:form>
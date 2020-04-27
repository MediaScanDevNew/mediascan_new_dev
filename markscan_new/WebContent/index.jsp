

<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%
BufferedReader br = null;
String sCurrentLine;
File ff= new File(request.getRealPath("/properties/int.txt"));
br = new BufferedReader(new FileReader(ff));
int i=Integer.parseInt(br.readLine());
br.close();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
if (i==0)
{
%>
<meta http-equiv="Refresh" content="0;URL=home" />
 <% 
}
else
{
 %>
 <meta http-equiv="Refresh" content="0;URL=maintein" />
 <% 
}
 %> 
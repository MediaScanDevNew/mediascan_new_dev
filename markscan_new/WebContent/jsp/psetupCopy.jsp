<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<%@ page import="com.markscan.project.beans.Stored_project_setup1" %>
<%@ page import="com.markscan.project.beans.Stored_project_setup" %>
<%@ page import="com.markscan.project.beans.Markscan_machine" %>
<%@include file="header.jsp"%>
<%-- <s:set name="theme" value="'simple'" scope="page" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporting</title>
<style type="text/css">

/* for hiding the page banner */
.pagebanner {
	display: none;
}
/* for customizing page links */
.pagelinks {
	color: maroon;
	margin: 20px 0px 20px 170px;
}
/* for shifting all the Export options*/
.exportlinks {
	margin: 20px 0px 20px 30px;
}
/* For changing the spaces between export link */
.export {
	margin-left: 30px;
}
/* For Table css */
table {
	border: 1px solid #666;
	width: 60%;
	margin: 20px 0 20px 0px;
}

table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}
/* For odd and even row decoration */
tr.odd {
	background-color: #f4f4f4
}

tr.tableRowEven, tr.even {
	background-color: #CCCCCC
}
/* Css for table elements */
th, td {
	padding: 2px 4px 2px 4px;
	text-align: left;
	vertical-align: top;
}

thead tr {
	background-color: #999999;
}
/* For changing the background colour while sorting */
th.sorted {
	background-color: #CCCCCC;
}

th.sorted a, th.sortable a {
	background-position: right;
	display: block;
	width: 100%;
}

th a:hover {
	text-decoration: underline;
	color: black;
}

th a, th a:visited {
	color: black;
}

table.three {
	
}
</style>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() { // for client name 
		$('#projecttype').change(function(event) {
			var country = $("select#projecttype").val(); // country = define value of selected option
			//	alert(country);
			//alert("Testing0.........");
			$.getJSON('ajaxAction0', {
				ptype : country
			}, function(jsonResponse) {
				//$('#ajaxResponse').text(jsonResponse.dummyMsg);
				//alert("Testing01........."+jsonResponse);
				var select = $('#clientname');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					//alert("Key "+key+"   value "+value);
					//alert("Testing02.........");
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});

	$(document).ready(function() { // for tv show name 
		$('#clientname').change(function(event) {
			
			var country = $("select#clientname").val(); // country = define value of selected option
			var ptypee = $("select#projecttype").val();	
			$.getJSON('ajaxAction1', {
				ctype : country,
				ptype : ptypee
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#propertyName');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					//alert("Key "+key+"   value "+value);
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
			
		});
	});

	$(document).ready(function() { // for keyphrase
		$('#propertyName').change(function(event) {
			//var country = $("select#propertyName").val(); // country = define value of selected option

			
			var sel = document.getElementById("propertyName");
			var value = sel.options[sel.selectedIndex].value; // or sel.value
			var text = sel.options[sel.selectedIndex].text;
			alert(value + "...choose project Name...  " + text);
			document.getElementById('pvalue').value = text;

		});
	});
	// Static keyords code 

	
	$(document).ready(function() { // for tv show name 
		$('#keyword1').change(function(event) {
			//alert("Keywords selected...............")
			var keyword1 = $("select#keyword1").val();
			
			
			//var country = $("select#clientname").val(); // country = define value of selected option
			//	alert(country);
			var projecttype = $("select#projecttype").val();
		//	var pname = $("select#propertyName").val();
			
			var pname = document.getElementById("propertyName");
			var pname1 =pname.options[pname.selectedIndex].text;
			if(pname1=="Select Property")
				{
				    alert("Please select Property Name")
				}
			
			
			if((keyword1 =="Static Keyword") && (pname1!="Select Property"))
				{
				//alert(keyword1);
			
			$.getJSON('ajaxAction3', {
				//ktype : keyword1,
				ptype : projecttype
				//ctype : ptypee
			}, function(jsonResponse) {
				
				//$('#ajaxResponse').text(jsonResponse.dummyMsg);
				//alert("Reponse data............"+jsonResponse);
			//var select = $('#keyword1');
			var data1='';
			var data2 ='';
				//select.find('ul').remove();
				//alert("Reponse data............."+jsonResponse.stateMap1);
				//alert("Reponse data1............");
				var count =1;
				/* var optiondata='<option value=0>Select Pipe</option><option value=1>Google</option><option value=2>Yahoo</option><option value=3>Bing</option><option value=4>DuckDuckGo</option><option value=5>RussiaGo</option><option value=6>Google India</option>'
					+'<option value=7>Google Canada</option><option value=8>Google Com</option><option value=9>Google Spane</option><option value=10>Google Nepal</option><option value=11>Google Bangladesh</option><option value=12>Google UAE</option><option value=13>Google Australia</option><option value=14>Google japan</option>'
					+'<option value=15>Google USA</option><option value=16>Google Pakistan</option><option value=17>Google UK</option><option value=18>Google Netherlands</option>' */
				
					var optiondata='<option value=0>Select Pipe</option><option value=1>Google</option><option value=2>Yahoo</option><option value=3>Bing</option><option value=4>DuckDuckGo</option><option value=5>Go.mail.ru</option>'
							
				$.each(jsonResponse.stateMap1, function(key, value) {
					var keys1 =pname1+' '+value;
					data1 ='<tr><td><input type ="checkbox" id='+count+' size=50 name= '+count+' value= "'+keys1+ '" />'+keys1+'</td><td><select name="'+keys1+ '">'+optiondata+'</select></td></tr>';
					$('<ul>').appendTo("Harry GGN");
					$("#kywrds12").append(data1);
					count =count+1;

				
				});
				
				data2 ='<input type ="text" id= "count"  name= "count" value= "'+count+'" /><br>';
				$("#kywrds").append(data2);
			});
			
				} 
			
			if(keyword1 =="Dynamic Keyword")
			{
				var data1='';
				var data2 ='';
				var optiondata='<option value=0>Select Pipe</option><option value=1>Google</option><option value=2>Yahoo</option><option value=3>Bing</option><option value=4>DuckDuckGo</option><option value=5>Go.mail.ru</option>'
					var count =15;
					for(var i=1;i<=count;i++)
						{
						var pi="pip"+i;
						data1 ='<tr><td>'+i+':<input type ="text" id='+i+' size=60 name= '+i+'  /></td><td><select name="'+pi+'">'+optiondata+'</select></td></tr>';
						$("#kywrds12").append(data1);
					
				}
		         data2 ='<input type ="text" id="count"  name= "count" value='+count+' /><br>';
				$("#kywrds").append(data2);
					
			
			}
		});
			
	
	});
	
		
/*
	
	$(document).ready(function(){
		  $("#keyword1").select(function(){
		    $("#keywrds").appendTo("Singh <br>");
		  });
		});

*/
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/default.css" type="text/css">

<!-- <link rel="stylesheet" type="text/css" href="css/ddstyle.css"> -->
<style type="text/css">
.Zebra_DatePicker {
	left: 102px;
	margin-top: 106px;
}
</style>
</head>
<body>

  
  <!-- keywrdSetup keywrdStore -->
	<s:form method="post" name="psetup" action="keywrdStore" theme="simple" onsubmit="return validateForm();">
	
	
		<div style="margin-left: 170px;">

			<s:if test="uid=='null'">
				<meta http-equiv="Refresh" content="0;URL=sessionOut" />
			</s:if>


			<table>
				<tr>
					<td>Project Type</td>
					<td><s:select id="projecttype" name="projecttype"
							list="listData" listKey="id" listValue="name" headerKey="0"
							headerValue="Select Project Type" label="Select Project"
							cssStyle="width: 170px;" /></td>
				</tr>
				<tr>
					<td>Client Name</td>
					<td><s:select id="clientname" name="clientname"
							list="{'Select Client'}" label="Select Client"
							cssStyle="width: 170px;"  /></td>
				</tr>
				<tr>
					<td>Project Name</td>
					<td><s:select id="propertyName" name="propertyName"
							list="{'Select Property'}" label="Select Property"
							cssStyle="width: 170px;"  /></td>
				</tr>
				<tr>
					<td>keywords</td>
					<td><s:select id="keyword1" name="keyword1"
					list="{'Select Keyword','Static Keyword','Dynamic Keyword'}" label="Select Keyword"
							cssStyle="width: 170px;"
					>	</s:select></td>
				</tr>
				<tr>
					<td></td>
					<td><s:textfield name="oneField"></s:textfield></td>
				</tr>
			</table>
			<div id="kywrds">
			<h3> Keywords.........</h3><br>
			<table id ="kywrds12"><tr><th> Keywords</th><th>Select Pipe</tr></table>
			
			</div>
			
		</div>
		<s:hidden name="pvalue" id="pvalue">
		</s:hidden>
		<!--  
		<div style="margin-left: 35px; margin-right: 35px;">
			<fieldset>
				<legend>Content Filter</legend>
				<s:textfield name="one" label="Content Filter" placeholder="One"></s:textfield>
				<s:textfield name="two" label="Content Filter" placeholder="Two"></s:textfield>
				<s:textfield name="three" label="Content Filter" placeholder="Three"></s:textfield>
				<s:textfield name="four" label="Content Filter" placeholder="Four"></s:textfield>
				<s:textfield name="five" label="Content Filter" placeholder="Five"></s:textfield>
				<s:textfield name="six" label="Content Filter" placeholder="Six"></s:textfield>
				<s:textfield name="seven" label="Content Filter" placeholder="Seven"></s:textfield>
				<s:textfield name="Eight" label="Content Filter" placeholder="Eight"></s:textfield>

			</fieldset>
		</div>
-->
      
		<div>
			<s:submit value="create project setup!!"></s:submit>
		</div>

	</s:form>
<div>
<h3>
If you are using Google on country wise, and running query on regional language,  
</h3>
</div>
<div id ="ajaxResponse"></div>

<table border="1" align="center">
	<tr>
		<td><%
			if(request.getAttribute("manual_bot") != null) {
		%>
				<table style="width: 100%;">
					<tr>
						<td><b>Manual BOT Logs</b></td>
					</tr>
					<tr>
						<td>
							<table style="width: 100%;">
								<tr>
									<td><b>Project Name</b></td>
									<td><b>Completed Flag</b></td>
									<td><b>Google</b></td>
									<td><b>Yahoo</b></td>
									<td><b>Bing</b></td>
									<td><b>DuckDuckGo</b></td>
									<td><b>Go.mail.ru</b></td>
									<td><b>WhiteList</b></td>
									<td><b>GreyList</b></td>
									<td><b>BlackList</b></td>
								</tr>
								<%
									ArrayList<Stored_project_setup1> manual_bot = (ArrayList<Stored_project_setup1>) request
												.getAttribute("manual_bot");
										for (Stored_project_setup1 bn : manual_bot) {
								%>
								<tr>
									<td><%=bn.getKeyphrase()%></td>
									<td><%=bn.getCompletedVal()%></td>
									<td><%=bn.getGoogleVal()%></td>
									<td><%=bn.getYahooVal()%></td>
									<td><%=bn.getBingVal()%></td>
									<td><%=bn.getDuckduckgoVal()%></td>
									<td><%=bn.getRussiagoVal()%></td>
									<td><%=bn.getWhitelistVal()%></td>
									<td><%=bn.getGreylistVal()%></td>
									<td><%=bn.getBlacklistVal()%></td>

								</tr>
								<% }%>

							</table>
						</td>
					</tr>
				</table> <% }%>
			</td>
	</tr>
	
	
	<tr>
	    <td>
				<%
					if (request.getAttribute("automate_bot") != null) {
				%>
				<table style="width: 100%;">
					<tr>
						<td><b>Automated BOT Logs</b></td>
					</tr>
					<tr>
						<td>
							<table  style="width: 100%;">
								<tr>
									<td><b>Project Name</b></td>
									<td><b>Completed Flag</b></td>
									<td><b>Google</b></td>
									<td><b>Yahoo</b></td>
									<td><b>Bing</b></td>
									<td><b>DuckDuckGo</b></td>
									<td><b>Go.mail.ru</b></td>
								</tr>
								<%
									ArrayList<Stored_project_setup> automate_bot = (ArrayList<Stored_project_setup>) request
												.getAttribute("automate_bot");
										for (Stored_project_setup bn : automate_bot) {
								%>
								<tr>
									<td><%=bn.getKeyphrase()%></td>
									<td><%=bn.getCompletedVal()%></td>
									<td><%=bn.getGoogleVal()%></td>
									<td><%=bn.getYahooVal()%></td>
									<td><%=bn.getBingVal()%></td>
									<td><%=bn.getDuckduckgoVal()%></td>
									<td><%=bn.getRussiagoVal()%></td>

								</tr>
								<% }%>

							</table>
						</td>
					</tr>
				</table> <% }%>
 
	    </td>
	</tr>
	
	<tr>
	  <td>
				<%
					if (request.getAttribute("machine_list") != null) {
				%>
				<table style="width: 100%;">
					<tr>
						<td><b>BOT Machine INFO</b></td>
					</tr>
					<tr>
						<td>
							<table style="width: 100%;">
								<tr>
									<td><b>IP Address</b></td>
									<td><b>Description</b></td>
									<td><b>BOT Status</b></td>

								</tr>
								<%
									ArrayList<Markscan_machine> machine_list = (ArrayList<Markscan_machine>) request
												.getAttribute("machine_list");
										for (Markscan_machine bn : machine_list) {
								%>
								<tr>
									<td><%=bn.getIp_address()%></td>
									<td><%=bn.getBot_version()%></td>
									<td><%=bn.getStatus_display()%></td>

								</tr>
								<% }%>

							</table>
						</td>
					</tr>
				</table> <% }%>
			</td>
	</tr>
</table>

<script type="text/javascript">

function validateForm(){
	
	
	return true;
}

</script>


 
 
 
 

</body>
</html>
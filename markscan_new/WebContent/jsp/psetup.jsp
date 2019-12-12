<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

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
				alert(keyword1);
			
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
				var optiondata='<option value=0>Select Pipe</option><option value=1>Google</option><option value=2>Yahoo</option><option value=3>Bing</option><option value=4>Google India</option>'
				+'<option value=5>Google Canada</option><option value=6>Google Com</option><option value=7>Google Spane</option><option value=8>Google Nepal</option><option value=9>Google Bangladesh</option><option value=10>Google UAE</option><option value=11>Google Australia</option><option value=12>Google japan</option>'
				+'<option value=13>Google USA</option><option value=14>Google Pakistan</option><option value=15>Google UK</option><option value=16>Google Netherlands</option>'
				$.each(jsonResponse.stateMap1, function(key, value) {
					//alert("Reponse data123.............");
					var keys1 =pname1+' '+value;
					//alert(count)
					data1 ='<tr><td><input type ="checkbox" id='+count+' size=50 name= '+count+' value= "'+keys1+ '" />'+keys1+'</td><td><select name="'+keys1+ '">'+optiondata+'</select></td></tr>';
					//alert("Key: "+key+"   value: "+value);
					//var $text12 =$("#"+key);
					//$text12.html(("<input type='text' id ='"+key+"' name='"+value+"'value='"+value+"'/> <br>"))
					//$text12.html(("Harry GGN <br>"));
					//alert("Reponse data123............");
					//alert("Key: " +key+"   value: "+value);
				
				
				//$('<ul>').appendTo("Harry GGN");
					$("#kywrds12").append(data1);
					count =count+1;

				
				});
				//$("#kywrds").append(data1);
				
				
		         data2 ='<input type ="text" id= "count"  name= "count" value= "'+count+'" /><br>';
				$("#kywrds").append(data2);
			});
			
				} 
			
			if(keyword1 =="Dynamic Keyword")
			{
			alert(keyword1);
			 
				
				//$('#ajaxResponse').text(jsonResponse.dummyMsg);
				//alert("Reponse data............"+jsonResponse);
			//var select = $('#keyword1');
			var data1='';
			var data2 ='';
			//alert("Test*************");
				//select.find('ul').remove();
				//alert("Reponse data............."+jsonResponse.stateMap1);
				//alert("Reponse data1............");
				//var count =1;
				var optiondata='<option value=0>Select Pipe</option><option value=1>Google</option><option value=2>Yahoo</option><option value=3>Bing</option><option value=4>Google India</option>'
				+'<option value=5>Google Canada</option><option value=6>Google Com</option><option value=7>Google Spane</option><option value=8>Google Nepal</option><option value=9>Google Bangladesh</option><option value=10>Google UAE</option><option value=11>Google Australia</option><option value=12>Google japan</option>'
				+'<option value=13>Google USA</option><option value=14>Google Pakistan</option><option value=15>Google UK</option><option value=16>Google Netherlands</option>'
				//$.each(jsonResponse.stateMap1, function(key, value) {
					//alert("Test*************");
					var count =15;
					for(var i=1;i<=count;i++)
						{
					//alert("Reponse data123.............");
					//var keys1 =pname1+' '+value;
					//alert(count)
					var pi="pip"+i;
					data1 ='<tr><td>'+i+':<input type ="text" id='+i+' size=60 name= '+i+'  /></td><td><select name="'+pi+'">'+optiondata+'</select></td></tr>';
					//alert("Key: "+key+"   value: "+value);
					//var $text12 =$("#"+key);
					//$text12.html(("<input type='text' id ='"+key+"' name='"+value+"'value='"+value+"'/> <br>"))
					//$text12.html(("Harry GGN <br>"));
					//alert("Reponse data123............");
					//alert("Key: " +key+"   value: "+value);
				
				
				//$('<ul>').appendTo("Harry GGN");
					$("#kywrds12").append(data1);
					//count =count+1;

				
				
				//$("#kywrds").append(data1);
				
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
	<s:form method="post" name="psetup" action="keywrdStore" theme="simple">
	
	
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
</body>
</html>
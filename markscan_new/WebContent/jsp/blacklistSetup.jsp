
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@taglib prefix="display" uri="http://displaytag.sf.net"%> --%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporting</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/pjstyle.css">
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui-wt.js"></script>
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
	width: 80%;
	margin: 20px 0 20px 0px;
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
</style>

<script type="text/javascript">
	$(document).ready(function() { // for client name 
		$('#projecttype').change(function(event) {
			var country = $("select#projecttype").val(); // country = define value of selected option
			//	alert(country);
			$.getJSON('ajaxAction0', {
				ptype : country
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#clientname');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});

	$(document).ready(function() { // for tv show name 
		$('#clientname').change(function(event) {
			var country = $("select#clientname").val(); // country = define value of selected option
			//	alert(country);
			var ptypee = $("select#projecttype").val();
			//alert(ptype);
			$.getJSON('ajaxAction1', {
				ctype : country,
				ptype : ptypee
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#propertyName_id');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});
</script>


<link rel="stylesheet" href="css/default.css" type="text/css">

<script type="text/javascript">
	$(document).ready(function() {
		$('select#projecttype').change(function() {
			var selectedText = $(this).find('option:selected').text();
				//alert(selectedText);
			$('input#ptype_name').val(selectedText);
		});
	});

	$(document).ready(function() {
		$('select#clientname').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#clientname_name').val(selectedText);
		});
	});

	$(document).ready(function() {
		$('select#propertyName_id').change(function() {
			var selectedText = $(this).find('option:selected').val();
	//	alert(selectedText);
			$('input#propertyid').val(selectedText);
			selectedText = $(this).find('option:selected').text();
			$('input#propertyName').val(selectedText);
		});
	});

	

	
</script>



</head>
<body>


	<div style="margin-left: 170px;">

		<s:if test="uid==0">
			<meta http-equiv="Refresh" content="0;URL=sessionOut" />
		</s:if>
		<s:form method="post" action="queryFire">


			<s:select id="projecttype" name="projecttype" list="listData"
				listKey="id" listValue="name" headerKey="0"
				headerValue="Select Project Type" label="Select Project"
				cssStyle="width: 170px;" />

			<s:select id="clientname" name="clientname"
				list="#{'0':'Select Client'}" label="Select Client"
				cssStyle="width: 170px;" />
			<s:select id="propertyName_id" name="propertyName_id"
				list="#{'0':'Select Property'}" label="Select Property"
				cssStyle="width: 170px;" />

			
				<s:select id="machine_detail" name="machine_detail"
				list="listData_one" listKey="ip_address" listValue="name"
				headerKey="0" headerValue="Select Machine" label="Select Machine"
				cssStyle="width: 170px;" />

<s:hidden></s:hidden>

			<s:hidden id="ptype_name" name="ptype_name"></s:hidden>
			<s:hidden id="clientname_name" name="clientname_name"></s:hidden>
			<s:textfield id="propertyid" name="propertyid" label="Project ID" cssStyle="width:160px;"></s:textfield> 
		<s:textfield id="propertyName" name="propertyName" label="Property Name" cssStyle="width:400px;"></s:textfield>	
		<s:textfield id="propertyUrl" name="propertyUrl" label="Property URL" cssStyle="width:600px;"></s:textfield>	
			
			
			
			


			<s:submit value="go"></s:submit>

		</s:form>





	</div>





</body>
</html>

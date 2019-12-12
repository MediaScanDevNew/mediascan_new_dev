
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">


<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="js/DataTables_example/site-examples.css">
<link rel="stylesheet" type="text/css"
	href="js/DataTables_example/jquery.css">
<style type="text/css" class="init">
</style>

<script src="js/DataTables_example/ga.js" async=""
	type="text/javascript"></script>
<script type="text/javascript" src="js/DataTables_example/site.js">
	
</script>

<script type="text/javascript" language="javascript"
	src="js/DataTables_example/jquery-1.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="js/DataTables_example/jquery.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="js/DataTables_example/demo.js">
	
</script>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>

<SCRIPT TYPE="text/javascript">
	function popup(mylink, windowname) {
		if (!window.focus)
			return true;
		var href;
		if (typeof (mylink) == 'string')
			href = mylink;
		else
			href = mylink.href;
		window.open(href, windowname, 'width=500,height=350,scrollbars=yes');
		return false;
	}
</SCRIPT>
<style type="text/css">
.a {
	width: 150px;
	margin-left: 20px;
}

.b {
	width: 300px;
}
</style>
</head>
<body class="wide comments example">



	<s:form action="saveOrUpdateMail" theme="simple">
		<%-- <s:push value="c_master">  --%>

		<s:hidden name="id__c" value="%{id__c}" />
		<table>
			<tr>
				<td class="a">Domain</td>
				<td class="b"><s:textfield name="domain" label="Client Name"
						value='%{domain}' cssStyle="width: 275px" /></td>
				<td class="a">Email</td>
				<td class="b"><s:textfield name="email_address__c"
						label="Client Address" value='%{email_address__c}'
						cssStyle="width: 275px" /></td>
				<td class="a"></td>
				<td class="b"><s:submit /></td>

			</tr>
			<%-- <tr>
				<td class="a">Email Type</td>
				<td class="b"><s:textfield name="email_id"
						label="Client Name" value='%{email_id}' /></td>

				<td class="a"></td>
				<td class="b"><s:submit /></td>
			</tr> --%>
			<%-- </s:push>  --%>
		</table>
	</s:form>
	<br>
	
<s:textfield value="%{dummyMsg}" cssStyle="color: red" readonly="true"></s:textfield>



	<hr>
	<a name="top" id="top"></a>







	<table style="width: 100%;" aria-describedby="example_info" role="grid"
		id="example" class="display dataTable" cellspacing="0" width="100%">
		<thead>
			<tr role="row">
				<th aria-label="Name: activate to sort column descending"
					aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting_asc">Domain
					Type</th>
				<th aria-label="Position: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Email</th>
					
				<!-- <th aria-label="Office: activate to sort column ascending"
					style="width: 150px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Email
					Type</th> -->

				<th aria-label="Salary: activate to sort column ascending"
					style="width: 77px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Edit</th>
					
				<th aria-label="Salary: activate to sort column ascending"
					style="width: 77px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Delete</th> 
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th colspan="1" rowspan="1">Domain</th>
				<th colspan="1" rowspan="1">Email</th>
				<!-- <th colspan="1" rowspan="1">Email Type</th> -->
				<th colspan="1" rowspan="1">Edit</th>
				 <th colspan="1" rowspan="1">Delete</th> 
			</tr>
		</tfoot>
		<tbody>




			<s:iterator value="master_emailData">
				<tr class="odd" role="row">
					<%-- <td><input type="checkbox" name="id"
						value='<s:property value="id"/>'></td> --%>
					<%-- <td><s:a href="isValid.jsp?id=%{id}"
							onclick="return popup(this, 'notes')">
							<s:property value="crawle_url2" />
						</s:a></td> --%>

					<%-- <td><s:property value="id" /></td> --%>
					<td><s:property value="domain_name" /></td>
					<td><s:property value="email" /></td>

					<%-- <td><s:property value="email_type" /></td> --%>
					<td><s:url id="editmail" action="editmail">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{editmail}">Edit</s:a></td>
						
					<td><s:url id="deletemail" action="deletemail">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{deletemail}">Delete</s:a></td>	
					
					
					<!-- <td>delete</td> -->



				</tr>
			</s:iterator>


		</tbody>
	</table>








</body>
</html>
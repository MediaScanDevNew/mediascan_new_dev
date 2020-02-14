

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


	<hr>
	<a name="top" id="top"></a>

	<table style="width: 100%;" aria-describedby="example_info" role="grid"
		id="example" class="display dataTable" cellspacing="0" width="100%">
		<thead>
			<tr role="row">
				<th aria-label="Name: activate to sort column descending"
					aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting_asc">Id</th>
				<th aria-label="Position: activate to sort column ascending"
					style="width: 150px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">URL
					</th>
				<th aria-label="Office: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">PMQC Status</th>
				<th aria-label="Age: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">OpsQC Stats</th>
				<th aria-label="Start date: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Master Email Status</th>
				
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th colspan="1" rowspan="1">Id</th>
				<th colspan="1" rowspan="1">URL</th>
				<th colspan="1" rowspan="1">PMQC Status</th>
				<th colspan="1" rowspan="1">OpsQC Stats</th>
				<th colspan="1" rowspan="1">Master Email Status</th>
			</tr>
		</tfoot>
		<tbody>




			<s:iterator value="link_withDetail">
				<tr class="odd" role="row">
					

					<td><s:property value="id" /></td>
					<td><s:property value="crawle_url2" /></td>
					<td>
					<s:if test="%{qc_new==1}">
					<span>Performed</span>
					</s:if><s:else><span style="color:red;">Pending</span></s:else>
					</td>
					<td>
					<s:if test="%{w_list_perform==1}">
					<span>Performed</span>
					</s:if><s:else><span style="color:red;">Pending</span></s:else>
					</td>
<td>
					<s:if test="%{c_new==1}">
					<span>Performed</span>
					</s:if><s:else><span style="color:red;">Pending</span></s:else>
					</td>


				</tr>
			</s:iterator>


		</tbody>
	</table>


</body>
</html>
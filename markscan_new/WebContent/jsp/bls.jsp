<%-- 
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">
<head>
<title>Diplay data</title>

<link rel="stylesheet" href="css/mystyle.css" type="text/css"
	media="print, projection, screen" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<script type="text/javascript" src="js/table_script.js"></script>
<script type="text/javascript">
	$(function() {
		$("table").tablesorter({
			widthFixed : true,
			widgets : [ 'zebra' ]
		});
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

<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 50%;
	height: 50%;
	padding: 16px;
	border: 16px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

th.b, td.b {
	width: 250px;
	word-wrap: break-word;
}

th.a, td.a {
	width: 30px;
	word-wrap: break-word;
}

th.c, td.c {
	width: 80px;
	word-wrap: break-word;
}

th.d, td.d {
	width: 60px;
	word-wrap: break-word;
}

table {
	border-collapse: collapse;
	table-layout: fixed;
	width: 100%;
}

td, tr, th, table {
	border: thin solid;
}
</style>
<script>
	function headerCheckboxOnChange(isChecked) {

		for (i = 0; i < $("input[class='row_ck']").length; i++)
			$("input[class='row_ck']")[i].checked = isChecked;
	}
	function rowCheckboxOnChange(isChecked) {
		if (!isChecked)
			$("#headerCheckbox")[0].checked = false;

		else {
			var flag = true;
			for (i = 0; i < $("input[class='row_ck']").length; i++) {

				if ($("input[class='row_ck']")[i].checked) {

					continue;
				} else {
					flag = false;
					break;
				}
			}//for
			$("#headerCheckbox")[0].checked = flag;
		}
	}

	function statecheck(layer) {
		//alert(layer);
		var myLayer = document.getElementById(layer);
		//alert(myLayer);
		//myLayer.style.backgroundColor = "#bff0a1";
		if (myLayer.childNodes[0].checked === true) {
			myLayer.style.backgroundColor = "#bff0a1";
		} else {
			myLayer.style.backgroundColor = "#eee";
		}
		;

	}
</script>

</head>
<body>
	<h2>Add BlackList Domain</h2>
	<div style="margin-left: 20px;">

		<form action="saveBLS">
			<input type="text" id="ten" name="ten" style="width: 350px"><br>
			<input type="submit" value="Add New Domain"
				style="margin-left: 250px;">
		</form>
	</div>
	<div>
		<input type="text" value="<s:property value='nine' />"
			readonly="readonly" style="color: red; width: 350px;">

	</div>
	<hr>
	<div>



		<table cellspacing="1" class="tablesorter">
			<thead>

				<tr>

					<td class="a"><input type="checkbox" id="headerCheckbox"
						onchange="headerCheckboxOnChange(this.checked)" /></td>

					<th class="c">Domain</th>
					<th class="c">Created by</th>
					<th class="c">Created date</th>



					<th class="c"></th>
					<!-- <th>Edit</th> -->
				</tr>
			</thead>

			<tfoot>
				<tr>

					<td class="a"><input type="checkbox" id="headerCheckbox"
						onchange="headerCheckboxOnChange(this.checked)" /></td>

					<th class="c">Domain</th>
					<th class="c">Created by</th>
					<th class="c">Created date</th>



					<th class="c"></th>
					<!-- <th>Edit</th> -->
				</tr>
			</tfoot>





			<s:iterator value="crList">

				<tr id="row<s:property value="link" />"
					name="<s:property value="link" />">

					<td><input type="checkbox" name="id" class="row_ck"
						onchange="statecheck('<s:property value="link"/>')" id="id"
						value='<s:property value="link"/>'></td>

					<td id="project" class="c"><s:property value="domainName" /></td>
					<!-- project name -->
					<td id="name_row<s:property value="id" />" class="c"><s:property
							value="userName" /></td>
					<!-- DOMAIN name -->


					<td class="c" id="country_row<s:property value="id" />"><s:property
							value="date__c" /></td>

					<td>
						<input type="button" src="image/pencil.png" width="20"
							height="20" id="edit_button<s:property value="id" />"
							value="Edit" class="edit"
							onclick="edit_row(<s:property value="id" />)"> <input
							type="button" id="save_button<s:property value="id" />"
							value="Save" class="save" src="image/floppy.png" width="20"
							height="20" onclick="save_row(<s:property value="id" />)"
							style="display: none;"> 
							 <input type="button" value="Delete" class="delete"
						src="../image/delete.png" width="20" height="20"
						onclick="delete_bls(<s:property value="id" />)"
						style="display: block;">
					</td>

				</tr>

			</s:iterator>
			<tbody>

			</tbody>
		</table>
	</div>


</body>
</html>

 --%>


<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">


<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/mystyle.css" type="text/css"
	media="print, projection, screen" />
<link rel="stylesheet" type="text/css"
	href="js/DataTables_example/site-examples.css">
<link rel="stylesheet" type="text/css"
	href="js/DataTables_example/jquery.css">

<link rel="stylesheet" href="css/mystyle.css" type="text/css"
	media="print, projection, screen" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<script type="text/javascript" src="js/table_script.js"></script>
<script type="text/javascript">
	$(function() {
		$("table").tablesorter({
			widthFixed : true,
			widgets : [ 'zebra' ]
		});
	});
</script>

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
<script>
	function headerCheckboxOnChange(isChecked) {

		for (i = 0; i < $("input[class='row_ck']").length; i++)
			$("input[class='row_ck']")[i].checked = isChecked;
	}
	function rowCheckboxOnChange(isChecked) {
		if (!isChecked)
			$("#headerCheckbox")[0].checked = false;

		else {
			var flag = true;
			for (i = 0; i < $("input[class='row_ck']").length; i++) {

				if ($("input[class='row_ck']")[i].checked) {

					continue;
				} else {
					flag = false;
					break;
				}
			}//for
			$("#headerCheckbox")[0].checked = flag;
		}
	}

	function statecheck(layer) {
		//alert(layer);
		var myLayer = document.getElementById(layer);
		//alert(myLayer);
		//myLayer.style.backgroundColor = "#bff0a1";
		if (myLayer.childNodes[0].checked === true) {
			myLayer.style.backgroundColor = "#bff0a1";
		} else {
			myLayer.style.backgroundColor = "#eee";
		}
		;

	}
</script>
</head>
<body class="wide comments example">



	<h2>Add BlackList Domain</h2>
	<div style="margin-left: 20px;">

		<form action="saveBLS">
			<input type="text" id="ten" name="ten" style="width: 350px"><br>
			<input type="submit" value="Add New Domain"
				style="margin-left: 370px;">
		</form>
	</div>
	<div>
		<input type="text" value="<s:property value='nine' />"
			readonly="readonly" style="color: red; width: 350px;">

	</div>


	<hr>
	<a name="top" id="top"></a>







	<table style="width: 100%;" aria-describedby="example_info" role="grid"
		id="example" class="display dataTable" cellspacing="0" width="100%">
		<thead>
			<tr role="row">
				<th aria-label="Name: activate to sort column descending"
					aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting_asc">Domain</th>
				<th aria-label="Position: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Created
					by</th>
				<th aria-label="Office: activate to sort column ascending"
					style="width: 150px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Created
					date</th>
				<th aria-label="Salary: activate to sort column ascending"
					style="width: 77px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Edit</th>
			</tr>
		</thead>
		<tfoot>
			<tr>



				<th colspan="1" rowspan="1">Domain</th>
				<th colspan="1" rowspan="1">Created by</th>
				<th colspan="1" rowspan="1">Created date</th>
				<th colspan="1" rowspan="1">Edit</th>
			</tr>
		</tfoot>
		<tbody>



			<s:iterator value="crList">
				<tr class="odd" role="row">
					<%-- <td><input type="checkbox" name="id"
						value='<s:property value="id"/>'></td> --%>
					<%-- <td><s:a href="isValid.jsp?id=%{id}"
							onclick="return popup(this, 'notes')">
							<s:property value="crawle_url2" />
						</s:a></td> --%>

					<%-- <td><s:property value="id" /></td> --%>
					<td><s:property value="domainName" /></td>
					<td><s:property value="userName" /></td>
					<td><s:property value="date__c" /></td>

					<td><%-- <input type="button" value="Delete" class="delete"
						src="image/delete.png" width="20" height="20"
						onclick="delete_bls(<s:property value="link" />)"
						style="display: block;"> --%>
						
						<form action="delete_bls">
						<input type="hidden" value='<s:property value="link" />' id="four" name="four" >
						<input type="submit" value="Delete">
						</form>
						
						
						
						
						</td>



				</tr>
			</s:iterator>




			<%-- 		<s:iterator value="crList">

				<tr id="row<s:property value="link" />"
					name="<s:property value="link" />">

					<td><input type="checkbox" name="four" class="row_ck"
						onchange="statecheck('<s:property value="link"/>')" id="four"
						value='<s:property value="link"/>'></td>

					<td id="project" class="c"><s:property value="domainName" /></td>
					<!-- project name -->
					<td id="name_row<s:property value="id" />" class="c"><s:property
							value="userName" /></td>
					<!-- DOMAIN name -->


					<td class="c" id="country_row<s:property value="id" />"><s:property
							value="date__c" /></td>

					<td>
						<input type="button" src="image/pencil.png" width="20"
							height="20" id="edit_button<s:property value="id" />"
							value="Edit" class="edit"
							onclick="edit_row(<s:property value="id" />)"> <input
							type="button" id="save_button<s:property value="id" />"
							value="Save" class="save" src="image/floppy.png" width="20"
							height="20" onclick="save_row(<s:property value="id" />)"
							style="display: none;"> 
							 <input type="button" value="Delete" class="delete"
						src="../image/delete.png" width="20" height="20"
						onclick="delete_bls(<s:property value="link" />)"
						style="display: block;">
					</td>

				</tr>

			</s:iterator> --%>


		</tbody>
	</table>








</body>
</html>






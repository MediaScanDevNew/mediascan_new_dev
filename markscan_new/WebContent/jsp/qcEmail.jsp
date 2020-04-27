
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
	<div>
		<form action="updateEmailQC" name="form1" method="post">


			<table cellspacing="1" class="tablesorter">
				<thead>

					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="c">Project Name</th>
						<th class="c">Domain Name</th>
						<th class="c">Email-ID</th>

						<th class="b">URL</th>

						<th class="c"></th>
						<!-- <th>Edit</th> -->
					</tr>
				</thead>

				<tfoot>
					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="c">Project Name</th>
						<th class="c">Domain Name</th>
						<th class="c">Email-ID</th>

						<th class="b">URL</th>

						<th class="c"></th>
						<!-- <th>Edit</th> -->
					</tr>
				</tfoot>





				<s:iterator value="listData">

					<tr id="row<s:property value="id" />"
						name="<s:property value="id" />">

						<td><input type="checkbox" name="id" class="row_ck"
							onchange="statecheck('<s:property value="id"/>')" id="id"
							value='<s:property value="id"/>'></td>

						<td id="project" class="c"><s:property
								value="email_error_new" /></td>
						<!-- project name -->
						<td id="name_row<s:property value="id" />" class="c"><s:property
								value="domain_name" /></td>
						<!-- DOMAIN name -->


						<td class="c" id="country_row<s:property value="id" />"><s:property
								value="email" /></td>
						<!-- email -->

						<td class="b" id="age_row<s:property value="id" />"><s:property
								value="url" /></td>
						<!-- URL -->
						<td><input type="button" src="../image/pencil.png" width="20" height="20"
							id="edit_button<s:property value="id" />" value="Edit"
							 class="edit"
							onclick="edit_row(<s:property value="id" />)"> <input
							type="button" id="save_button<s:property value="id" />"
							value="Save" class="save" src="../image/floppy.png" width="20"
							height="20" onclick="save_row(<s:property value="id" />)"
							style="display: none;"> <input type="button"
							value="Invalid" class="delete" src="../image/delete.png" width="20"
							height="20" onclick="delete_row(<s:property value="id" />)"
							style="display: block;"></td>
							
					</tr>

				</s:iterator>





				<tbody>

				</tbody>
			</table>



			<!-- <button type="button" class="one">Submit invalid</button> -->
			<s:submit value="Submit QC"></s:submit>
		</form>
	</div>


</body>
</html>









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
<%int i=0;  %>
	<div>
		<form action="bl_dataQCprfrm" name="form1" method="post">


			<table cellspacing="1" class="tablesorter">
				<thead>

					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="b">Source Link</th>
						<th class="c">Source Domain</th>
						<th class="c">Project Name</th>
						<th class="c">Project ID</th>
						<th class="b">2nd Infringing Link</th>
						<th class="b">Infringing Link</th>
						<th class="c">Infringing Domain</th>

						<th class="c"></th>
						<!-- <th>Edit</th> -->
					</tr>
				</thead>

				<tfoot>
					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="b">Source Link</th>
						<th class="c">Source Domain</th>
						<th class="c">Project Name</th>
						<th class="c">Project ID</th>
						<th class="b">2nd Infringing Link</th>
						<th class="b">Infringing Link</th>
						<th class="c">Infringing Domain</th>


						<th class="c"></th>
					</tr>
				</tfoot>





				<s:iterator value="listData">

					<tr id="row<s:property value="id" />"
						name="<s:property value="id" />">
<%i=i+1; %>
						<td><input type="checkbox" name="id" class="row_ck"
							onchange="statecheck('<s:property value="id"/>')" id="id"
							value='<s:property value="id"/>'></td>


						<td id="name_row<%=i %>" class="c"><s:property
								value="source_link" /></td>
						<!-- source Link -->
						<td id="age_row<%=i %>" class="c"><s:property
								value="source_domain" /></td>
						<!-- Source DOMAIN name -->

						<td id="project" class="c"><s:property value="ipaddress" /></td>
						<!-- project name -->
						<td class="c" id="country_row<%=i %>"><s:property
								value="projectid" /></td>
						<!-- project id -->

						<td id="2Infi" class="c"><s:property value="infringing_link" /></td>
						<!-- 2nd infringing link -->
						<td id="Infi" class="c"><s:property
								value="infringing_link_by_date" /></td>
						<!-- Infringing link -->
						<td id="Infi_domain" class="c"><s:property value="domain" /></td>
						<!-- infringing domain -->

						<!-- URL -->
						<td><input type="button"  id="edit_button<%=i %>"
							value="Edit" class="edit"
							onclick="edit_row(<%=i %>)"> 
							<input type="button" id="save_button<%=i %>"
							value="Save" class="save" onclick="save_row_bl(<%=i %>)"> 
							<input type="button"
							value="Invalid" class="delete" 
							onclick="delete_row_bl(<%=i %>)"></td>

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








<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Master email</title>
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

<link rel="stylesheet" href="css/mystyle.css" type="text/css"
	media="print, projection, screen" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<script type="text/javascript">
	$(function() {
		$("table").tablesorter({
			widthFixed : true,
			widgets : [ 'zebra' ]
		});
	});
</script>






<%-- <sx:head /> --%>


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
<script type="text/javascript">
	$(document).ready(function() {
		$(".one").click(function() {
			var favorite = [];
			$.each($("input[name='id']:checked"), function() {

				favorite.push($(this).val());
			});
			var apple = favorite.join(",");
			//  alert(apple);

			$.getJSON('ajaxActionInv', {
				invlink : apple
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				/* var select = $('#clientname');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				}); */
			});

		});
	});
</script>


</head>
<body>
	<div style="margin-left: 170px;">

		<s:form method="post" action="masterEmailAndImport">

			<s:hidden id="projecttype" name="projecttype" value="%{projecttype}"
				label="projecttype">
			</s:hidden>
			<s:hidden id="clientname" name="clientname" value="%{clientname}"
				label="clientname">
			</s:hidden>
			<!-- 
	here apple use for storing id 
	apple= craule_url2.id 
	-->
			<s:hidden id="apple" name="apple" value="%{uniqprojectName}"
				label="clientname">
			</s:hidden>
			
			<s:hidden id="startdate" name="startdate" value="%{startdate}"
				label="clientname">
			</s:hidden>

		<%-- 	<s:property value="uniqprojectName" />  --%>
			<br>
			<%-- <table>
				<s:iterator value="propinfo">
					<tr>
						<td><input type="checkbox" name="id" class="row_ck"
							onchange="statecheck('<s:property value="id"/>')"
							id='<s:property value="id"/>' value='<s:property value="id"/>'></td>
						<td><s:property value="project_name" /></td>
					</tr>
				</s:iterator>
			</table> --%>


			<table cellspacing="1" class="tablesorter">
				<thead>
					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>


						<th class="c">Project Name</th>
					</tr>
				</thead>

				<tfoot>
					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="c">Project Name</th>
					</tr>
				</tfoot>

				<%-- <s:hidden name="dataforupdate" value="%{dataforupdate}"></s:hidden> --%>
				<s:iterator value="propinfo">
					<tr>
						<td><input type="checkbox" name="id" class="row_ck"
							onchange="statecheck('<s:property value="id"/>')"
							id='<s:property value="id"/>' value='<s:property value="id"/>'></td>


						<td class="c"><s:property value="project_name" /></td>
					</tr>
				</s:iterator>
				<tbody>

				</tbody>
			</table>





			<s:submit value="Master Email & Email import"></s:submit>
		</s:form>

	</div>
	<div><h3 style="color: red;">Use Mozilla FireFox vor best result !!!</h3></div>
</body>
</html>

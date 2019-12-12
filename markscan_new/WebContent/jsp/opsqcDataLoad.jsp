

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
<%-- <script type="text/javascript">
	$('#selectAll').click(
			function(e) {
				$(this).closest('table').find('td input:checkbox').prop(
						'checked', this.checked);
			});
</script> --%>

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
<script type="text/javascript">
$(document).ready(function() {
	$(".one").click(function() {
		var favorite = [];
		$.each($("input[name='id']:checked"), function() {

			favorite.push($(this).val());
		});
		if(favorite.length>0){
		var apple = favorite.join(",");
		var person = prompt("Please enter valid Reason for invalid links", "Enter Reason");
		//  alert(apple);

		$.getJSON('ajaxActionInv', {
			invlink : apple, reasn:'OPSQC: '+person
		}, function(jsonResponse) {
			alert(jsonResponse.dummyMsg)
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			/* var select = $('#clientname');
			select.find('option').remove();
			$.each(jsonResponse.stateMap, function(key, value) {
				$('<option>').val(key).text(value).appendTo(select);
			}); */
		});
		}
		else{
			alert("Nothing is Selected for OpsQC")
		}
	});
});
</script>
</head>
<body>
	<div>
		<form action="updateOPSQCtable" name="form1" method="post">

			<s:textfield id="totalrowCount" name="totalrowCount"
				value="%{totalrowCount}" label="Pending Records for QC"
				readonly="true">
			</s:textfield>
			<s:submit value="Submit QC"></s:submit>
			<s:hidden id="getRow" name="getRow" value="%{getRow}" label="getRow">
			</s:hidden>
			<s:hidden id="projecttype" name="projecttype" value="%{projecttype}"
				label="projecttype">
			</s:hidden>
			<s:hidden id="clientname" name="clientname" value="%{clientname}"
				label="clientname">
			</s:hidden>
			<s:hidden id="propertyName" name="propertyName"
				value="%{propertyName}" label="propertyName">
			</s:hidden>
			<s:hidden id="datatype" name="datatype" value="%{datatype}"
				label="datatype">
			</s:hidden>

			<s:hidden id="startdate" name="startdate" value="%{startdate}"
				label="startdate">
			</s:hidden>
			<s:hidden id="uniqprojectName" name="uniqprojectName"
				value="%{uniqprojectName}" label="uniqprojectName">
			</s:hidden>


			<s:hidden id="ptype_name" name="ptype_name" value="%{ptype_name}"></s:hidden>
			<s:hidden id="clientname_name" name="clientname_name"
				value="%{clientname_name}"></s:hidden>
			<s:hidden id="propertyName_name" name="propertyName_name"
				value="%{propertyName_name}"></s:hidden>
			<s:hidden id="datatype_name" name="datatype_name"
				value="%{datatype_name}"></s:hidden>



			<table cellspacing="1" class="tablesorter">
				<thead>
					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="b">Source Link</th>
						<th class="c">Domain</th>
						<th class="b">Infringing Link</th>

						<th class="d">User</th>
						<th class="c">Project Name</th>
						<th class="c">Created Date</th>
						<!-- <th>Edit</th> -->
					</tr>
				</thead>

				<tfoot>
					<tr>

						<td class="a"><input type="checkbox" id="headerCheckbox"
							onchange="headerCheckboxOnChange(this.checked)" /></td>

						<th class="b">Source Link</th>
						<th class="c">Domain</th>
						<th class="b">Infringing Link</th>

						<th class="d">User</th>
						<th class="c">Project Name</th>
						<th class="c">Created Date</th>
						<!-- <th>Edit</th> -->
					</tr>
				</tfoot>


				<s:iterator value="qcData">
					<tr>
						<td><input type="checkbox" name="id" class="row_ck"
							onchange="statecheck('<s:property value="id"/>')"
							id='<s:property value="id"/>' value='<s:property value="id"/>'></td>
						<s:if test="%{greylist==1}">
							<td class="b" style="background-color: orange;"><s:a
									href="isValid.jsp?id=%{id}"
									onclick="return popup(this, 'notes')">
									<s:property value="link" />
								</s:a> <s:property value="greylist" /></td>
							<td class="c" style="background-color: orange;"><s:property
									value="domainName" /></td>
						</s:if>
						<s:else>
							<td class="b"><s:a href="isValid.jsp?id=%{id}"
									onclick="return popup(this, 'notes')">
									<s:property value="link" />
								</s:a></td>
							<td class="c"><s:property value="domainName" /></td>
						</s:else>
						<%-- <td class="c"><s:property value="domainName" /></td> --%>
						<td class="b"><s:property value="link001" /></td>
						<td class="d"><s:property value="userName" /></td>

						<td class="c">
							<%-- <input type="checkbox" name="projectNamelist"
							id="myCheck" class="project_chk"
							value="<s:property value="projectName"/>" checked="checked" readonly="readonly"> --%>
							<s:property value="projectName" />
						</td>

						<%-- 	<td><s:property value="projectName" /></td> --%>
						<td class="c"><s:property value="date__c" /></td>
						<%-- <td class="c"><s:property value="google_dmca_new" /></td> --%>
					</tr>
				</s:iterator>
				<tbody>

				</tbody>
			</table>



			<button type="button" class="one" style=" margin-left: 200px;">Submit invalid</button>
			<s:submit value="Submit QC"></s:submit>
		</form>
	</div>
<input type="text" id="ajaxResponse" name="ajaxResponse">

</body>
</html>





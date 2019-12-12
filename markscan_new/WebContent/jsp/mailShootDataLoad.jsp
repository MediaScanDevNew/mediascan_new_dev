
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
$(document).ready(function () {

    $('#myform').validate({ // initialize the plugin
        
        
    });

});

</script>



<script type="text/javascript">
	$(function() {
		$("table").tablesorter({
			widthFixed : true,
			widgets : [ 'zebra' ]
		});
	});
</script>




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
	width: 25px;
	word-wrap: break-word;
}

th.c, td.c {
	width: 80px;
	word-wrap: break-word;
}

th.d, td.d {
	width: 40px;
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
</script>
<script type="text/javascript">
	var stringresult = '';
	var pj = '';
	function captureData() {
		//alert('hello add');

		pj = '';
		stringresult = '';
		$('input:checked').each(
				function() {
					$this = $(this);
					var one = $this.parent().siblings('td').eq(0).text();
					var two = $this.parent().siblings('td').eq(1).text();
					var three = $this.parent().siblings('td').eq(2).text();
					var four = $this.parent().siblings('td').eq(3).text();
					var five = $this.parent().siblings('td').eq(4).text();
					var six = $this.parent().siblings('td').eq(5).text();
					//var three = $this.parent().siblings('td').eq(2).text();

					pj = (one + '<td> ' + two + '<td> ' + three + '<td> '
							+ five + '<td> ' + six);

					//or just 
					stringresult += pj + '<pj>';
				});
		alert('This is the whole string: ' + stringresult);
		var email_id = $('#e_id').val();
		alert(email_id);
		var module_id = $('#m_id').val();
		alert(module_id);

		//window.location="${pageContext.request.contextPath}/emailShootWithDetails?email_id="+email_id+"&module_id="+module_id+"&stringresult="+stringresult;
		$.getJSON('emailShootWithDetails', {
			email_id : email_id,
			module_id : module_id,
			stringresult : stringresult
		}, function(jsonResponse) {
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			var select = $('#propertyName');
			select.find('option').remove();
			$.each(jsonResponse.stateMap, function(key, value) {
				$('<option>').val(value).text(value).appendTo(select);
			});
		});
		alert('complete!!')
	};
	
	
	
	
</script>




</head>
<body>
<%int i=1; %>
	<!-- <div> -->
	<!-- <form action="#" name="form1" method="post"> -->
	<s:form method="post" action="emailShootWithDetails" id="myform">


		<s:hidden name="module_id" id="m_id" value="%{module_id}">
		</s:hidden>
		<s:hidden name="email_id" id="e_id" value="%{email_id}">
		</s:hidden>

		<table cellspacing="1" class="tablesorter">

			<thead>
				<tr>

					<td class="a"><input type="checkbox" id="headerCheckbox"
						onchange="headerCheckboxOnChange(this.checked)" /></td>
					<!-- <th class="d">ID</th> -->
					<th class="c">Project Name [Project Id]</th>
					<th class="c">Channel Name</th>
					<th class="c">Email-ID</th>

					<th class="b">URL</th>

					<th class="c">Domain</th>
					<!-- <th>Edit</th> -->
				</tr>
			</thead>

			<tfoot>
				<tr>

					<td class="a"><input type="checkbox" id="headerCheckbox"
						onchange="headerCheckboxOnChange(this.checked)" /></td>
					<!-- <th class="d">ID</th> -->
					<th class="c">Project Name [Project Id]</th>
					<th class="c">Channel Name</th>
					<th class="c">Email-ID</th>

					<th class="b">URL</th>

					<th class="c">Domain</th>
					<!-- <th>Edit</th> -->
				</tr>
			</tfoot>
			<tbody>
				<s:iterator value="mailShootData">
					<tr>

						<td class="a"><input type="checkbox" name="stringresult"
							class="row_ck" id="id"
							value='<s:property value="id"/><pj><s:property value="notice_id"/>'><%=i%></td>


						<td id="project" class="c"><s:property value="project_name" />&nbsp;&nbsp;[<s:property
								value="project_id" />]</td>
						<!-- project name -->
						<td id="name_row" class="c"><s:property value="channel_name" /></td>
						<!-- DOMAIN name -->


						<td class="c" id="country_row"><s:property
								value="email_address" /></td>
						<!-- email -->

						<td class="b" id="age_row"><s:property value="url_group" /></td>
						<td class="c"><s:property value="domain" /></td>
					</tr>
<%i++; %>
				</s:iterator>
			<tbody>
		</table>

		<!-- </form> -->
		<!-- </div> -->
		<input type="submit" id="send" value="send">
	</s:form>

</body>
</html>




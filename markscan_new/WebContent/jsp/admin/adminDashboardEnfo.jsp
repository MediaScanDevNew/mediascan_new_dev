<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>




<!-- date picket start -->
<link rel="stylesheet" href="css/dp_bootstrap-iso.css" />
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/dp_bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="css/dp_bootstrap-datepicker3.css" />


<!-- date picker end -->

<style type="text/css">
.box {
	color: #fff;
	padding: 20px;
	display: none;
	margin-top: 20px;
	width: 321px;
}

.red {
	background: #ff0000;
}

.green {
	background: #228B22;
}

.blue {
	background: #0000ff;
}

label {
	margin-right: 15px;
}
</style>




<script type="text/javascript">
	$(document).ready(function() {
		$('input[type="radio"]').click(function() {
			var inputValue = $(this).attr("value");
			var targetBox = $("." + inputValue);
			$(".box").not(targetBox).hide();
			$(targetBox).show();
		});
	});
</script>



<style type="text/css">
#left_side {
	float: left;
	width: 26%;
	background: gray;
}

/* #center_s {
        margin: 0 auto;
        width: 2%;
        background-color: #ccc;
    }
 */
#right_side {
	float: right;
	width: 74%;
	background: lightgray;
}
</style>


<style type="text/css">
/* * {
    margin:0;
    padding:0;
} */
body {
	font-family: "HelveticaNeue-Light", "Helvetica Neue Light",
		"Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
	font-weight: 300;
}

/*Grey box centered*/
/* .box {

    width: 50%;
    height: 30%;
    background-color: #e0e0e0;
    padding:2%;
    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;
    border-radius:12px;
    margin: auto;
}
 */

/*Column 1*/
/*Button 1*/
.button1 {
	display: table;
	float: left;
	width: 20%;
	height: 5%;
	padding: 2%;
	background-color: #434343;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	text-align: center;
	border-bottom: solid black 3px;
}

.button1 p {
	font-size: 100%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	color: white;
}

.button1:hover {
	border-bottom: solid black 2px;
	margin-top: 1px;
}

.button1:active {
	position: relative;
	background-color: #292929;
	border-bottom: solid #434343 2px;
	top: 2px;
}

/*Button 2*/
.button2 {
	display: table;
	float: left;
	margin-top: 5%;
	width: 40%;
	height: auto;
	padding: 2%;
	background-color: #a5de37;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	text-align: center;
	border-bottom: solid #8dbb28 3px;
}

.button2 p {
	font-size: 100%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	color: white;
}

.button2:hover {
	position: relative;
	border-bottom: solid #8dbb28 3px;
	top: 1px;
}

.button2:active {
	position: relative;
	top: 2px;
	background-color: #8dbb28;
	border-bottom: solid #a5de37 3px;
	color: white;
}

/*Button 5*/
.button5 {
	display: table;
	float: left;
	margin-top: 5%;
	width: 20%;
	height: 5%;
	padding: 2%;
	background-color: grey;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	text-align: center;
	border-bottom: solid #8dbb28 3px;
}

.button5 p {
	font-size: 100%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	color: white;
}

.button5:hover {
	position: relative;
	border-bottom: solid #8dbb28 3px;
	top: 1px;
}

.button5:active {
	position: relative;
	top: 2px;
	background-color: #8dbb28;
	border-bottom: solid #a5de37 3px;
	color: white;
}

/*Button 3*/
.button3 {
	display: table;
	float: left;
	margin-top: 5%;
	width: 40%;
	height: auto;
	padding: 2%;
	background-color: #fd6631;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	text-align: center;
	border-bottom: solid #d34e24 3px;
}

.button3 p {
	font-size: 100%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	color: white;
}

.button3 p:active {
	color: white;
}

.button3:hover {
	position: relative;
	border-bottom: solid #d34e24 2px;
	top: 1px;
}

.button3:active {
	position: relative;
	top: 2px;
	background-color: #d34e24;
	border-bottom: solid #fd6631 2px;
}

/*column2*/

/*Button 4*/
.button4 {
	display: table;
	float: right;
	width: 20%;
	height: 5%;
	padding: 2%;
	background-color: #434343;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	text-align: center;
	border-bottom: solid black 3px;
}

.button4 p {
	font-size: 100%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	color: white;
}

.button4:hover {
	border-bottom: solid black 2px;
	margin-top: 1px;
}

.button4:active {
	position: relative;
	background-color: #292929;
	border-bottom: solid #434343 2px;
	top: 2px;
}

/* table */
table, td, th {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
	width: 100%;
}

td {
	height: 50px;
	vertical-align: bottom;
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
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$('select#usertype').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#user_name').val(selectedText);
		});
	});

	$(document).ready(function() {
		$('select#projecttype').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#client_type').val(selectedText);
		});
	});

	$(document).ready(function() {
		$('select#clientname').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#client_name').val(selectedText);
		});
	});
</script>


</head>

<body>

	<div id="root">
		<div id="left_side">
			LEFT


			<div class="bootstrap-iso">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-6 col-sm-6 col-xs-12">
							<form action="adminDashEnfoAction" class="form-horizontal"
								method="post">
								<div class="form-group ">

									<div style="width: 321px;">
										<label><input type="radio" name="colorRadio"
											value="red"> All User</label> <label><input
											type="radio" name="colorRadio" value="green"> User
											Wise</label> <label><input type="radio" name="colorRadio"
											value="blue"> Client Wise</label>
									</div>
									<div class="red box"></div>
									<div class="green box">

										<s:select id="usertype" name="usertype" list="usrData"
											listKey="id" listValue="name" headerKey="0"
											headerValue="Select User" label="Select User"
											cssStyle="width: 170px; background:black;" />

										<input type="hidden" name="user_name" id="user_name">

									</div>
									<div class="blue box">


										<s:select id="projecttype" name="projecttype" list="listData"
											listKey="id" listValue="name" headerKey="0"
											headerValue="Select Project Type" label="Select Project"
											cssStyle="width: 170px; background:black;" />
										<br>

										<s:select id="clientname" name="clientname"
											list="#{'0':'Select Client'}" label="Select Client"
											cssStyle="width: 170px; background:black;margin-left: 7px;" />

										<input type="hidden" name="client_type" id="client_type">
										<input type="hidden" name="client_name" id="client_name">


									</div>

									<br>
									<div class="col-sm-10">
										<div class="input-group">

											Start Date<input class=" " id="date" name="date"
												placeholder="YYYY-MM-DD" type="text"
												style="margin-left: 118px; margin-top: -30px;" /> <br>
											<br> End Date <input class=" " id="date1" name="date1"
												placeholder="YYYY-MM-DD" type="text"
												style="margin-left: 118px; margin-top: -30px;" />
										</div>
									</div>
								</div>



								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-2">

										<input class="btn btn-primary " name="submit" type="submit"
											value="Submit">

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>




			<script>
				$(document)
						.ready(
								function() {
									var date_input = $('input[name="date"]'); //our date input has the name "date"
									var container = $('.bootstrap-iso form').length > 0 ? $(
											'.bootstrap-iso form').parent()
											: "body";
									date_input.datepicker({
										format : 'yyyy-mm-dd',
										container : container,
										todayHighlight : true,
										autoclose : true,
									})
								})

				$(document)
						.ready(
								function() {
									var date_input = $('input[name="date1"]'); //our date input has the name "date"
									var container = $('.bootstrap-iso form').length > 0 ? $(
											'.bootstrap-iso form').parent()
											: "body";
									date_input.datepicker({
										format : 'yyyy-mm-dd',
										container : container,
										todayHighlight : true,
										autoclose : true,
									})
								})
			</script>


		</div>
		<div id="right_side">
			RIGHT



			<div style='clear: both;'>
				<div class=button2>
					<h3>PmQc Pending</h3>
					<table>
						<tr>
							<th>Pending Count</th>
							<th>Date</th>
							<th>Client</th>

						</tr>
						<s:iterator value="pmqcPending">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="created_on" /></td>
								<td><s:property value="browser_name" />(<s:property
										value="del_reason" />)</td>
							</tr>

						</s:iterator>
					</table>
				</div>
				<!-- <div class=button5>
                <p>  
                    Press me      cvbxjh  jf bhj gfdhjdfg f vigh s nfsbhbmnghjhdgn bhldfs
                </p>
            </div> -->

				<div class=button3>
					<h3>OpsQc Pending</h3>
					<table>
						<tr>
							<th>Pending Count</th>
							<th>Date</th>
							<th>Client</th>

						</tr>
						<s:iterator value="opsqcPending">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="created_on" /></td>
								<td><s:property value="browser_name" />(<s:property
										value="del_reason" />)</td>
							</tr>

						</s:iterator>
					</table>

				</div>
			</div>
			<div style='clear: both;'>
				<div class=button2>
					<h3>Master Email Pending</h3>
					<table>
						<tr>
							<th>Pending Count</th>
							<th>Date</th>
							<th>Client</th>

						</tr>
						<s:iterator value="masterEmailPending">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="created_on" /></td>
								<td><s:property value="browser_name" />(<s:property
										value="del_reason" />)</td>
							</tr>

						</s:iterator>
					</table>
				</div>

				<div class=button3>
					<h3>Google Tracker Pending</h3>
					<table>
						<tr>
							<th>Pending Count</th>
							<th>Date</th>
							<th>Client</th>

						</tr>
						<s:iterator value="googleTrackerPending">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="created_on" /></td>
								<td><s:property value="browser_name" />(<s:property
										value="del_reason" />)</td>
							</tr>

						</s:iterator>
					</table>
				</div>
			</div>
			<div style='clear: both;'>
				<div class=button2>
					<h3>
						Email Qc Pending :
						<s:property value="mailQCPending" />
					</h3>

				</div>

				<div class=button3>
					<h3>Mail Shoot Pending</h3>
					<table>
						<tr>
							<th>Pending Count</th>
							<th>Date</th>
							<th>Client</th>

						</tr>
						<s:iterator value="mailShootPending">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="created_on" /></td>
								<td><s:property value="edit_field" />(<s:property
										value="edit_date" />)</td>
							</tr>

						</s:iterator>
					</table>
				</div>
			</div>

		</div>
	</div>









	<!-- google chart ... -->
</body>

</html>

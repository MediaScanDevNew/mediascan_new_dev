<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Date', 'Source', 'Infringing', 'Youtube', 'Social Media' ],

				<s:iterator value="monthlyGraph" status="pj">[
						'<s:property  value="date"/>',
						<s:property  value="source"/>,

						<s:property  value="Infringing"/>,
						<s:property  value="youtube"/>,
						<s:property  value="social_media"/>],

				</s:iterator>

		]);

		var options = {
			title : 'Daily Performance',
			curveType : 'function',
			legend : {
				position : 'bottom'
			}
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('curve_chart'));

		chart.draw(data, options);
	}
</script>

<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);
	var ab = [];
	<s:iterator value="usrData" var="myBean">
	ab.push("'<s:property value="name"/>'");
	</s:iterator>
	var x = ab;
	//alert(x);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ x ],

				<s:iterator value="userGraph" status="pj">[
						'<s:property  value="date"/>',
						<s:property  value="source"/>,

						<s:property  value="Infringing"/>,
						<s:property  value="youtube"/>,
						<s:property  value="social_media"/>],

				</s:iterator>

		]);

		var options = {
			title : 'User Performance',
			curveType : 'function',
			legend : {
				position : 'bottom'
			}
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('curve_chart_usr'));

		chart.draw(data, options);
	}
</script>


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
							<form action="adminDashAction" class="form-horizontal"
								method="post">
								<div class="form-group ">

									<div style="width: 321px;">
										<label><input type="radio" name="colorRadio"
											value="red"> All User</label> <label><input
											type="radio" name="colorRadio" value="green"> User
											Wise</label> <label><input type="radio" name="colorRadio"
											value="blue"> Client Wise</label>
									</div>
									<!-- <div class="red box"></div> -->
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
			<div class="widget-content">
				<div id="curve_chart" style="width: auto; height: 400px"></div>


			</div>


			<div class="widget-content">
				<!-- <div id="curve_chart_usr" style="width: auto; height: 400px"></div> -->



			</div>
		</div>













		<!-- google chart ... -->
</body>

</html>

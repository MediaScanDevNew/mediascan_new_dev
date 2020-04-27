<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@include file="../header.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>DataTables | Gentelella</title>

<!-- Bootstrap -->
<link href="report_table/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="report_table/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="report_table/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="report_table/green.css" rel="stylesheet">
<!-- Datatables -->
<link href="report_table/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="report_table/buttons.bootstrap.min.css" rel="stylesheet">
<link href="report_table/fixedHeader.bootstrap.min.css" rel="stylesheet">
<link href="report_table/responsive.bootstrap.min.css" rel="stylesheet">
<link href="report_table/scroller.bootstrap.min.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="report_table/custom.min.css" rel="stylesheet">

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	/* 	google.charts.load('current', {
	 'packages' : [ 'corechart' ]
	 });
	 google.charts.setOnLoadCallback(drawChart);

	 function drawChart() {
	 var data = google.visualization.arrayToDataTable([
	 [ 'Date', 'Source', 'Infringing', 'Youtube', 'Social Media',
	 'Duplicate' ],

	 <s:iterator value="product_usrData" status="pj">[
	 '<s:property  value="curr_date"/>',
	 <s:property  value="source_link"/>,

	 <s:property  value="infringing_link"/>,
	 <s:property  value="youtube"/>,
	 <s:property  value="social_media"/>,
	 <s:property  value="duplicate"/>],

	 </s:iterator>

	 ]);

	 var options = {
	 title : '<s:property value="user_name"/>\'s Performance',
	 curveType : 'function',
	 legend : {
	 position : 'bottom'
	 }
	 };

	 var chart = new google.visualization.LineChart(document
	 .getElementById('curve_chart'));

	 chart.draw(data, options);
	 } */
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
	top: 15%;
	left: 15%;
	width: 70%;
	height: 50%;
	padding: 10px;
	border: 10px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>

<script>
	// When the user clicks on div, open the popup
	function myFunction() {
		var popup = document.getElementById("myPopup");
		var user_id = document.getElementById("user_id");
		alert(user_id);
		popup.classList.toggle("show");
	}
</script>

<style type="text/css">
table.a {
	border-collapse: collapse;
    width: 100%;
}

table.a,th.b, td.c {
	padding: 8px;
    text-align: left;
    border: 1px solid #ddd;
}
tr:hover{background-color:#f5f5f5}
</style>

</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">





			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">


					<div class="clearfix"></div>

					<!-- <div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">


								<div class="clearfix"></div>
							</div>
							<div class="x_content">

								<div id="curve_chart" style="width: auto; height: 400px"></div>
							</div>
						</div>
					</div> -->



					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">

							<div class="x_content">

								<table id="datatable-buttons"
									class="table table-striped table-bordered">
									<thead>
										<tr>

											<th>Date</th>
											<th>User</th>
											<th>Source Link</th>
											<th>Infringing Link</th>
											<th>Source Domain</th>
											<th>Infringing Domain</th>
											<th>Youtube</th>
											<th>Social Media</th>
											<th>White List</th>
											<th>Grey List</th>

											<th>Facebook</th>
											<th>Instagram</th>
											<th>Twitter</th>
											<th>VK</th>
											<th>Periscope/Pscp</th>
											<th>Client Name {Property Details}</th>


										</tr>
									</thead>

									<tbody>

										<s:iterator value="monthlyGraph">
											<tr class="odd" role="row">
												<td><s:property value="date" /></td>
												<td><s:property value="proj_name" /></td>
												<td><s:property value="source" /></td>
												<td><s:property value="Infringing" /></td>
												<td><s:property value="source_domain" /></td>
												<td><s:property value="infringing_domain" /></td>
												<td><s:property value="youtube" /></td>
												<td><s:property value="social_media" /></td>
												<td><s:property value="white_list" /></td>
												<td><s:property value="grey_list" /></td>

												<td><s:property value="facebook" /></td>
												<td><s:property value="instagram" /></td>
												<td><s:property value="twitter" /></td>
												<td><s:property value="vk" /></td>
												<td><s:property value="periscope" /></td>
												<%-- <td><s:property value="Client_name" /></td> --%>
<td>
													<table class="a">
														<tr>
															<th class="b"> Client Name </th>
															<th class="b"> Source Link </th>
															<th class="b"> Infringing Link </th>
															<th class="b"> Youtube </th>
															<th class="b"> Facebook </th>
															<th class="b"> Instagram </th>
															<th class="b"> Twitter </th>
															<th class="b"> VK </th>
															<th class="b"> Periscope/Pscp </th>

														</tr>
														<s:iterator value="inside">
															<tr>
																<td class="c"><s:property value="wid" /> ()</td>
																<td class="c"><s:property value="is_valid" /></td>
																<td class="c"><s:property value="status" /></td>
																<td class="c"><s:property value="user_id" /></td>
																<td class="c"><s:property value="project_id" /></td>
																<td class="c"><s:property value="type" /></td>
																<td class="c"><s:property value="verified" /></td>
																<td class="c"><s:property value="site_down" /></td>
																<td class="c"><s:property value="is_new" /></td>
															</tr>


														</s:iterator>
													</table>
												</td>


											</tr>
										</s:iterator>
										<script>
											// When the user clicks on div, open the popup
											function myFunction() {
												var popup = document
														.getElementById("myPopup");
												popup.classList.toggle("show");
											}
										</script>
									</tbody>
								</table>
							</div>
						</div>
					</div>



				</div>
			</div>
		</div>

	</div>


	<!-- jQuery -->
	<script src="report_table/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="report_table/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="report_table/fastclick.js"></script>
	<!-- NProgress -->
	<script src="report_table/nprogress.js"></script>
	<!-- iCheck -->
	<script src="report_table/icheck.min.js"></script>
	<!-- Datatables -->
	<script src="report_table/jquery.dataTables.min.js"></script>
	<script src="report_table/dataTables.bootstrap.min.js"></script>
	<script src="report_table/dataTables.buttons.min.js"></script>
	<script src="report_table/buttons.bootstrap.min.js"></script>
	<script src="report_table/buttons.flash.min.js"></script>
	<script src="report_table/buttons.html5.min.js"></script>
	<script src="report_table/buttons.print.min.js"></script>
	<script src="report_table/dataTables.fixedHeader.min.js"></script>
	<script src="report_table/dataTables.keyTable.min.js"></script>
	<script src="report_table/dataTables.responsive.min.js"></script>
	<script src="report_table/responsive.bootstrap.js"></script>
	<script src="report_table/dataTables.scroller.min.js"></script>
	<script src="report_table/jszip.min.js"></script>
	<script src="report_table/pdfmake.min.js"></script>
	<script src="report_table/vfs_fonts.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="report_table/custom.min.js"></script>

</body>
</html>
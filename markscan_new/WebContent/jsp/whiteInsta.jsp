
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>

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
<SCRIPT TYPE="text/javascript">
	function popup(mylink, windowname) {
		if (!window.focus)
			return true;
		var href;
		if (typeof (mylink) == 'string')
			href = mylink;
		else
			href = mylink.href;
		window.open(href, windowname, 'width=500,height=400,scrollbars=yes');
		return false;
	}
</SCRIPT>
</head>

<body class="nav-md">





	<div class="container body" style="min-height: 1028px;">
		<div class="main_container">





			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">


					<div class="clearfix"></div>



					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">

							<div class="x_content">

								<table id="datatable-buttons"
									class="table table-striped table-bordered">
									<thead>
										<tr>

											<th>Instagram Link</th>
											<th>Taken By ID</th>
											<th>Created By</th>
											<th>Created Date</th>

										</tr>
									</thead>


									<tbody>


										<s:iterator value="showData">
											<tr class="odd" role="row">

												<td><s:property value="domainName" /></td>
												<td><s:property value="channel_name" /></td>
												<td><s:property value="userName" /></td>
												<td class="sorting_1" id="pradeep"><s:property
														value="date__c" /></td>

											</tr>
										</s:iterator>

									</tbody>
								</table>
							</div>
						</div>
					</div>






				</div>
			</div>
		</div>
		<!-- /page content -->

		<!-- footer content -->

		<!-- /footer content -->
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






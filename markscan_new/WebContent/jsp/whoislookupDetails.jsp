
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

<title>WhoisLookup</title>

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


											<th>Domain Name</th>
											<th>Registry Domain ID</th>
											<th>Registrar WHOIS Server</th>
											<th>Registrar URL</th>
											<th>Updated Date</th>
											<th>Creation Date</th>
											<th>Registry Expiry Date</th>
											<th>Registrar Registration Expiration Date</th>
											<th>Registrar</th>
											<th>Registrar IANA ID</th>
											<th>Registrar Abuse Contact Email</th>
											<th>Registrar Abuse Contact Phone</th>
											<th>Reseller</th>
											<th>Domain Status</th>
											<th>Registry Registrant ID</th>
											<th>Registrant Name</th>
											<th>Registrant Organization</th>
											<th>Registrant Street</th>
											<th>Registrant City</th>
											<th>Registrant State/Province</th>
											<th>Registrant Postal Code</th>
											<th>Registrant Country</th>
											<th>Registrant Phone</th>
											<th>Registrant Phone Ext</th>
											<th>Registrant Fax</th>
											<th>Registrant Fax Ext</th>
											<th>Registrant Email</th>
											<th>Registry Admin ID</th>
											<th>Admin Name</th>
											<th>Admin Organization</th>
											<th>Admin Street</th>
											<th>Admin City</th>
											<th>Admin State/Province</th>
											<th>Admin Postal Code</th>
											<th>Admin Country</th>
											<th>Admin Phone</th>
											<th>Admin Phone Ext</th>
											<th>Admin Fax</th>
											<th>Admin Fax Ext</th>
											<th>Admin Email</th>
											<th>Registry Tech ID</th>
											<th>Tech Name</th>
											<th>Tech Organization</th>
											<th>Tech Street</th>
											<th>Tech City</th>
											<th>Tech State/Province</th>
											<th>Tech Postal Code</th>
											<th>Tech Country</th>
											<th>Tech Phone</th>
											<th>Tech Phone Ext</th>
											<th>Tech Fax</th>
											<th>Tech Fax Ext</th>
											<th>Tech Email</th>
											<th>Registry Billing ID</th>
											<th>Billing Name</th>
											<th>Billing Organization</th>
											<th>Billing Street</th>
											<th>Billing Street</th>
											<th>Billing City</th>
											<th>Billing State/Province</th>
											<th>Billing Postal Code</th>
											<th>Billing Country</th>
											<th>Billing Phone</th>
											<th>Billing Phone Ext</th>
											<th>Billing Fax</th>
											<th>Billing Fax Ext</th>
											<th>Billing Email</th>
											<th>Name Server</th>
											<th>DNSSEC</th>


										</tr>
									</thead>


									<tbody>


										<s:iterator value="lookupDetail">
											<tr class="odd" role="row">
												<td><s:property value="domain_name" /></td>
												<td><s:property value="registry_domain_id" /></td>
												<td><s:property value="registrar_whois_server" /></td>
												<td><s:property value="registrar_url" /></td>
												<td><s:property value="updated_date" /></td>
												<td><s:property value="creation_date" /></td>
												<td><s:property value="registry_expiry_date" /></td>
												<td><s:property
														value="registrar_registration_expiration_date" /></td>
												<td><s:property value="registrar" /></td>
												<td><s:property value="registrar_iana_id" /></td>
												<td><s:property value="registrar_abuse_contact_email" /></td>
												<td><s:property value="registrar_abuse_contact_phone" /></td>
												<td><s:property value="reseller" /></td>
												<td><s:property value="domain_status" /></td>
												<td><s:property value="registry_registrant_id" /></td>
												<td><s:property value="registrant_name" /></td>
												<td><s:property value="registrant_organization" /></td>
												<td><s:property value="registrant_street" /></td>
												<td><s:property value="registrant_city" /></td>
												<td><s:property value="registrant_state_province" /></td>
												<td><s:property value="registrant_postal_code" /></td>
												<td><s:property value="registrant_country" /></td>
												<td><s:property value="registrant_phone" /></td>
												<td><s:property value="registrant_phone_ext" /></td>
												<td><s:property value="registrant_fax" /></td>
												<td><s:property value="registrant_fax_ext" /></td>
												<td><s:property value="registrant_email" /></td>
												<td><s:property value="registry_admin_id" /></td>
												<td><s:property value="admin_name" /></td>
												<td><s:property value="admin_organization" /></td>
												<td><s:property value="admin_street" /></td>
												<td><s:property value="admin_city" /></td>
												<td><s:property value="admin_state_province" /></td>
												<td><s:property value="admin_postal_code" /></td>
												<td><s:property value="admin_country" /></td>
												<td><s:property value="admin_phone" /></td>
												<td><s:property value="admin_phone_ext" /></td>
												<td><s:property value="admin_fax" /></td>
												<td><s:property value="admin_fax_ext" /></td>
												<td><s:property value="admin_email" /></td>
												<td><s:property value="registry_tech_id" /></td>
												<td><s:property value="tech_name" /></td>
												<td><s:property value="tech_organization" /></td>
												<td><s:property value="tech_street" /></td>
												<td><s:property value="tech_city" /></td>
												<td><s:property value="tech_state_province" /></td>
												<td><s:property value="tech_postal_code" /></td>
												<td><s:property value="tech_country" /></td>
												<td><s:property value="tech_phone" /></td>
												<td><s:property value="tech_phone_ext" /></td>
												<td><s:property value="tech_fax" /></td>
												<td><s:property value="tech_fax_ext" /></td>
												<td><s:property value="tech_email" /></td>
												<td><s:property value="registry_billing_id" /></td>
												<td><s:property value="billing_name" /></td>
												<td><s:property value="billing_organization" /></td>
												<td><s:property value="billing_street" /></td>
												<td><s:property value="billing_street" /></td>
												<td><s:property value="billing_city" /></td>
												<td><s:property value="billing_state_province" /></td>
												<td><s:property value="billing_postal_code" /></td>
												<td><s:property value="billing_country" /></td>
												<td><s:property value="billing_phone" /></td>
												<td><s:property value="billing_phone_ext" /></td>
												<td><s:property value="billing_fax" /></td>
												<td><s:property value="billing_fax_ext" /></td>
												<td><s:property value="billing_email" /></td>
												<td><s:property value="name_server" /></td>
												<td><s:property value="dnssec" /></td>


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






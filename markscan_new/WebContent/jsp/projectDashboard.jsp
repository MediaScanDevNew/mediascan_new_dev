
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
		window.open(href, windowname, 'width=550,height=400,scrollbars=yes');
		return false;
	}
	
	function checkingClose(str){
		var result = confirm("Want to delete?");
		if (result) {
			document.forms[0].action="closeProject?method=closeProject&id="+str;
	        document.forms[0].submit();
		}
		
		
	}
</SCRIPT>
<style type="text/css">
.c{
    color: #73879C;
    font-family: "Helvetica Neue", Roboto, Arial, "Droid Sans", sans-serif;
    font-size: 15px;
    font-weight: 400;
    line-height: 1.471
}
.select-box{
	padding: 15px 20px;
}
.height-200{
	min-height:200px !important;
}
</style>
</head>

<body class="nav-md">

	<s:form action="getdashboardDtls" cssClass="form-group select-box" theme="simple">
		<%-- <s:push value="c_master">  --%>

		<s:hidden name="id" value="%{id}" />
		<label for="p_type" class="col-md-1 col-sm-1 col-form-label">Property Type:</label>
	    <div class="col-md-3 col-sm-3">
	      <s:select name="p_type" list="project_type_Data" cssClass="form-control"
						id="p_type" headerKey="" value="%{p_type}" headerValue="Select"
						label="Select Project Type" listKey="id" listValue="name" />
	    </div>
	    <label for="client_name" class="col-md-1 col-sm-1 col-form-label">Client Name:</label>
	    <div class="col-md-3 col-sm-3">
	      <s:select name="client_name" list="client_type_Data" cssClass="form-control"
						id="client_name" headerKey="" value="%{client_name}" headerValue="Select"
						label="Select Client" listKey="id" listValue="client_name" />
	    </div>
	    
	    
		<%-- <table border="1" style="width: 800px;margin-left: 30px;margin-top: 20px;">
			<tr>  
			    
				<td class="c">Project Type</td>
				<td class="c"><s:select name="p_type" list="project_type_Data"
						id="p_type" headerKey="" value="%{p_type}" headerValue="Select"
						label="Select Project Type" listKey="id" listValue="name" /></td>
				
				<td class="c">Client Name</td>	
				
                <td class="c"><s:select name="client_name" list="client_type_Data"
						id="client_name" headerKey="" value="%{client_name}%" headerValue="Select"
						label="Select Client" listKey="id" listValue="client_name" /></td>
			</tr>
		</table> --%>
	</s:form>
	


	<div class="container body" >
		<div class="main_container">





			<!-- page content -->
			<div class="right_col height-200" role="main" >
				<div class="">


					<div class="clearfix"></div>



					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">

							<div class="x_content">

								<table id="datatable-buttons"
									class="table table-striped table-bordered">
									<thead>
										<tr>



											<th>Project Type</th>
											<th>Client Name</th>
											<th>Project Name [Project Id]</th>
											<th>Channel Name</th>
											<th>LOA</th>
											<th>Official URL</th>
											<th>Language</th>
											 <th>Releasing Date</th>
											<th>Telecast Time</th>
											<th>Telecast Days</th>
											 <th>Projects</th>
											 <th>Current Value(weeks)</th>
											 <th>Archive Value(days)</th>
											<th>Edit</th>
											<th>Close</th>
										
											
										</tr>
									</thead>


									<tbody>
									


										<s:iterator value="infos">
											<tr class="odd" role="row">

												<td><s:property value="name" /></td>
												<td><s:property value="client_name" /></td>
												<td class="sorting_1" id="pradeep"><s:property
														value="project_name" /><br> [<s:property value="id" />]</td>
												<td><s:property value="channel_name" /></td>
												<%-- <td><s:property value="end_date" /></td> --%>
												<td><s:property value="file_attach_link" /></td>
												<td><s:property value="actual_hosted_site" /></td>
												 <td><s:property  value="language" /></td>
												<td><s:property  value="realeasingDate" /></td>
												<td><s:property value="ttime" /></td>
												<td><s:property  value="telecast_days" /></td>
												<td><s:property  value="property_category" /></td>
												<td><s:property  value="current_value" /></td>
												<td><s:property  value="archive_days" /></td>
												
												
												<%-- <td><s:property value="created_on" /></td> --%>
												<td>
												  <s:url id="editURL" action="getProjectDtlsIdWise">
													<s:param name="id" value="%{id}"></s:param>
												  </s:url> <s:a href="%{editURL}">Edit</s:a>
												</td>

												<td>
												  <s:if test="%{projecttype ==0}">
												      <s:a href="javascript:void(0);" onclick="checkingClose('%{id}')">Close</s:a>
												  </s:if>
												</td>


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
	<script src="report_table/custom.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){	
	    $("#p_type").change(function() {
		     this.form.submit();
		});
	    
	    
	    $("#client_name").change(function(e) {
	    	if($("#p_type").val()!=''){
	    		$('#client_nm').val($(this).val());
	    		this.form.submit();
	    	}else{
	    		alert("Select Project Type..");
	    	}
	    	
		});
	    
	   
	});    
	</script>

</body>
</html>






<%@taglib prefix="s" uri="/struts-tags"%>

<table style="width: 100%;" aria-describedby="example_info" role="grid"
		id="example" class="display dataTable" cellspacing="0" width="100%" border="1">
		<thead>
			<tr role="row">
				<th aria-label="Name: activate to sort column descending"
					aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting_asc">Project
					Type</th>
				<th aria-label="Position: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Email
					Module</th>
				<th aria-label="Office: activate to sort column ascending"
					style="width: 150px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Client
					Name [Client Id]</th>
					
				<th aria-label="Age: activate to sort column ascending"
					style="width: 180px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Client
					Address</th> 
					
			    <th aria-label="Start date: activate to sort column ascending"
					style="width: 50px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Project team email</th>
					
				<th aria-label="Salary: activate to sort column ascending"
					style="width: 77px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Created
					Date</th>
				<th aria-label="Salary: activate to sort column ascending"
					style="width: 77px;" colspan="1" rowspan="1"
					aria-controls="example" tabindex="0" class="sorting">Edit</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th colspan="1" rowspan="1">Project Type</th>
				<th colspan="1" rowspan="1">Email Module</th>
				<th colspan="1" rowspan="1">Project Name [Client Id]</th>
				<th colspan="1" rowspan="1">Client Address</th>
			   <th colspan="1" rowspan="1">Project team email</th> 
				<!-- <th colspan="1" rowspan="1">Actual Host Site</th> -->
				<th colspan="1" rowspan="1">Created Date</th>
				<th colspan="1" rowspan="1">Edit</th>
			</tr>
		</tfoot>
		<tbody >


			

			<s:iterator value="client_master_data">
				<tr class="odd" role="row">
					<td><s:property value="projectName" /></td>
					<td><s:property value="lnk_typ" /></td>
					<td class="sorting_1"><s:property value="clientName" /><br>
						[<s:property value="id" />]</td>
					<td><s:property value="domainName" /></td>
					<%--<td><s:property value="end_date" /></td>
					 <td><s:property value="note1" /></td>--%>
					<td><s:property value="note1" /></td> 
					<td><s:property value="date__c" /></td>
					<td><s:url id="editURL" action="editUser">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{editURL}">Edit</s:a></td>



				</tr>
			</s:iterator> 


		</tbody>
	</table>
<script src="js/DataTables_example/ga.js" async=""
	type="text/javascript"></script>
<script type="text/javascript" src="js/DataTables_example/site.js">
	
</script>

<script type="text/javascript" language="javascript"
	src="js/DataTables_example/jquery-1.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="js/DataTables_example/jquery.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="js/DataTables_example/demo.js">
	
</script>

<script type="text/javascript" class="init">
	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>
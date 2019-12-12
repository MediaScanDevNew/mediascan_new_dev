<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">


<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">


    <link href="report_table/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="js/DataTables_example/site-examples.css">
    <link rel="stylesheet" type="text/css"
          href="js/DataTables_example/jquery.css">
    <style type="text/css" class="init">
    </style>

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
    <link href="report_table/custom.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
    <script type="text/javascript" class="init">
        $(document).ready(function () {
            $('#example').DataTable();
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

        $(document).ready(function () { // for get details view
            $('#p_type').change(function (event) {
                var projectTypeVal = $("#p_type").val();
                //alert(projectTypeVal);
                $.ajax({
                    type: "GET",
                    url: "getDetailsTypeWise?ptype=" + projectTypeVal,
                    success: function (response) {
                        if (response != '') {
                            //alert(response);
                            $('#clientData').html(response);
                            //$('#example').DataTable();
                        }
                    },
                    error: function (response) {
                        console.log(response);
                    }
                });
            });

            $('#initCaps').click(function() {
		   if($('#initCaps').prop('checked')){
			   $('#client_name').removeClass('myclass');
			   $('#capsFlag').val('0');
			   $('#client_name').val().toLowerCase();
		    }else{
		    	$('#client_name').addClass('myclass');
		    	 $('#capsFlag').val('1');
		    }
		});
        });


    </SCRIPT>
    <style type="text/css">
        .a {
            width: 150px;
            margin-left: 20px;
        }

        .b {
            width: 300px;
        }

        .myclass {
            text-transform: capitalize;
        }
        #clientData{
        	overflow:auto;
        	height:370px ;
        }
    </style>
</head>
<body class="wide comments example">


<%-- <s:form action="saveOrUpdateUser" cssClass="form-horizontal mar-top-6 font-14" theme="simple">


    <s:hidden name="id" value="%{id}"/>

    <div class="form-group">
        <label for="p_type" class="col-sm-2 col-md-2 col-sm-offset-3 col-md-offset-3 control-label">Project Type :</label>
        <div class="col-sm-4 col-md-4">
          <s:select name="p_type" list="project_type_Data" id="p_type" cssClass="form-control"
                    headerKey="" value="%{p_type}"  headerValue="Select" label="Select Project Type"
                    listKey="id" listValue="name" />
        </div>
      </div>
      <div class="form-group">
        <label for="client_name" class="col-sm-2 col-md-2 col-sm-offset-3 col-md-offset-3 control-label">Client Name :</label>
        <div class="col-sm-4 col-md-4">
          <s:textfield name="client_name" id="client_name" cssClass="form-control"
                    label="Client Name"  value='%{client_name}' />
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-4 col-md-offset-4 col-sm-4 col-md-4">
          <div class="checkbox">
            <label>
              <s:checkbox name="checkMe" id="initCaps" label=""/> Testttt
            </label>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="email_mod" class="col-sm-2 col-md-2 col-sm-offset-3 col-md-offset-3 control-label">Email Module :</label>
        <div class="col-sm-4 col-md-4">
          <s:select name="e_module" list="module_Data" id="email_mod"
                    headerKey="0" value="%{e_module}" headerValue="Select" label="Select a Module" cssClass="form-control"
                    listKey="id" listValue="module" />
        </div>
      </div>
      <div class="form-group">
        <label for="client_address" class="col-sm-2 col-md-2 col-sm-offset-3 col-md-offset-3 control-label">Client Address :</label>
        <div class="col-sm-4 col-md-4">
          <s:textarea name="client_address" id="client_address"
                label="Client Address"
                value='%{client_address}' />
        </div>
      </div>
      <div class="form-group">
        <label for="client_email" class="col-sm-2 col-md-2 col-sm-offset-3 col-md-offset-3 control-label">Project team email :</label>
        <div class="col-sm-4 col-md-4">
          <s:textfield name="client_email" id="client_email" cssClass="form-control"
                    label="Client Email" value='%{client_email}'  />
        </div>
      </div>
      <div class="form-group">
          <s:submit cssClass="col-sm-offset-6 col-md-offset-6 btn btn-success"/>
      </div>
    <input type="hidden" name="capsFlag" id="capsFlag" value="1">
</s:form>
--%>
<div class="container-fluid">
    <s:form action="saveOrUpdateUser" cssClass="mar-top-6 font-14" theme="simple">
        <s:hidden name="id" value="%{id}"/>
        <div class="form-group row">
            <label for="p_type" class="col-sm-1 col-md-1 control-label">Project Type:</label>
            <div class="col-sm-3 col-md-3">
                <s:select name="p_type" list="project_type_Data" id="p_type" cssClass="form-control"
                          headerKey="" value="%{p_type}"  headerValue="Select" label="Select Project Type"
                          listKey="id" listValue="name" />
            </div>
            <label for="client_name" class="col-sm-1 col-md-1 control-label">Client Name:</label>
            <div class="col-sm-3 col-md-3">
                <s:textfield name="client_name" id="client_name" cssClass="form-control"
                             label="Client Name"  value='%{client_name}' />
            </div>

            <label for="email_mod" class="col-sm-1 col-md-1 control-label">Email Module :</label>
            <div class="col-sm-3 col-md-3">
                <s:select name="e_module" list="module_Data" id="email_mod"
                          headerKey="0" value="%{e_module}" headerValue="Select" label="Select a Module" cssClass="form-control"
                          listKey="id" listValue="module" />
            </div>
        </div>
        <div class="form-group row">

            <label for="client_address" class="col-sm-1 col-md-1 control-label">Client Address :</label>
            <div class="col-sm-3 col-md-3">
                <s:textarea name="client_address" id="client_address"
                            label="Client Address"
                            value='%{client_address}' cssClass="form-control" />
            </div>
            <label for="client_email" class="col-sm-1 col-md-1 control-label">Project team email :</label>
            <div class="col-sm-3 col-md-3">
                <s:textfield name="client_email" id="client_email" cssClass="form-control"
                             label="Client Email" value='%{client_email}'  />
            </div>
            <div class="col-sm-2 col-md-2">
                <div class="checkbox">
                    <label>
                        <s:checkbox name="checkMe" id="initCaps" label=""/> Initial Caps
                    </label>
                </div>
            </div>
            <s:submit cssClass="col-sm-1 col-md-1 btn btn-success border-0"/>
        </div>


        <input type="hidden" name="capsFlag" id="capsFlag" value="1">
    </s:form>

    <s:textfield value="%{msg}" cssStyle="color: red; width : 400px;" readonly="true"></s:textfield>
    <hr>
    <a name="top" id="top"></a>
    <div id="clientData">
        <table style="width: 100%;" aria-describedby="example_info" role="grid"
               id="example" class="display dataTable" cellspacing="0" width="100%" border="1">
            <thead>
            <tr role="row">
                <th aria-label="Name: activate to sort column descending"
                    aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting_asc">Project
                    Type
                </th>
                <th aria-label="Position: activate to sort column ascending"
                    style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Email
                    Module
                </th>
                <th aria-label="Office: activate to sort column ascending"
                    style="width: 150px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Client
                    Name [Client Id]
                </th>

                <th aria-label="Age: activate to sort column ascending"
                    style="width: 180px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Client
                    Address
                </th>

                <th aria-label="Start date: activate to sort column ascending"
                    style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Project team email
                </th>

                <th aria-label="Salary: activate to sort column ascending"
                    style="width: 77px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Created
                    Date
                </th>
                <th aria-label="Salary: activate to sort column ascending"
                    style="width: 77px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Edit
                </th>
            </tr>
            </thead>
            <!-- <tfoot>
            <tr>
                <th colspan="1" rowspan="1">Project Type</th>
                <th colspan="1" rowspan="1">Email Module</th>
                <th colspan="1" rowspan="1">Project Name [Client Id]</th>
                <th colspan="1" rowspan="1">Client Address</th>
                <th colspan="1" rowspan="1">Project team email</th>
                <th colspan="1" rowspan="1">Actual Host Site</th>
                <th colspan="1" rowspan="1">Created Date</th>
                <th colspan="1" rowspan="1">Edit</th>
            </tr>
            </tfoot> -->
            <tbody>


            <s:iterator value="client_master_data">
                <tr class="odd" role="row">
                    <td><s:property value="projectName"/></td>
                    <td><s:property value="lnk_typ"/></td>
                    <td class="sorting_1"><s:property value="clientName"/><br>
                        [<s:property value="id"/>]
                    </td>
                    <td><s:property value="domainName"/></td>
                    <td><s:property value="end_date"/></td>
                    <td><s:property value="note1"/></td>
                    <td><s:property value="note1"/></td>
                    <td><s:property value="date__c"/></td>
                    <td><s:url id="editURL" action="editUser">
                        <s:param name="id" value="%{id}"></s:param>
                    </s:url> <s:a href="%{editURL}">Edit</s:a></td>


                </tr>
            </s:iterator>


            </tbody>
        </table>
    </div>
</div>
<%--<div class="container body">--%>
<%--    <div class="main_container">--%>
<%--        <!-- page content -->--%>
<%--        <div class="right_col height-200" role="main">--%>
<%--            <div class="">--%>
<%--                <div class="clearfix"></div>--%>
<%--                <div class="col-md-12 col-sm-12 col-xs-12">--%>
<%--                    <div class="x_panel">--%>
<%--                        <div class="x_content">--%>
<%--                            <table id="datatable-buttons"--%>
<%--                                   class="table table-striped table-bordered">--%>
<%--                                <thead>--%>
<%--                                <tr>--%>
<%--                                    <th>Project Type</th>--%>
<%--                                    <th>Email Module</th>--%>
<%--                                    <th>Project Name [Client Id]</th>--%>
<%--                                    <th>Client Address</th>--%>
<%--                                    <th>Project team email</th>--%>
<%--                                    <th>Created Date</th>--%>
<%--                                    <th>Edit</th>--%>
<%--                                </tr>--%>
<%--                                </thead>--%>
<%--                                <tbody>--%>
<%--                                <%--%>
<%--                                %>--%>
<%--                                <s:iterator value="client_master_data">--%>
<%--                                    <tr class="odd" role="row">--%>
<%--                                        <td><s:property value="projectName"/></td>--%>
<%--                                        <td><s:property value="lnk_typ"/></td>--%>
<%--                                        <td class="sorting_1"><s:property value="clientName"/><br>--%>
<%--                                            [<s:property value="id"/>]--%>
<%--                                        </td>--%>
<%--                                        <td><s:property value="domainName"/></td>--%>
<%--                                        <td><s:property value="end_date"/></td>--%>
<%--                                        <td><s:property value="note1"/></td>--%>
<%--                                        <td><s:property value="note1"/></td>--%>
<%--                                        <td><s:property value="date__c"/></td>--%>
<%--                                        <td><s:url id="editURL" action="editUser">--%>
<%--                                            <s:param name="id" value="%{id}"></s:param>--%>
<%--                                        </s:url> <s:a href="%{editURL}">Edit</s:a></td>--%>
<%--                                    </tr>--%>
<%--                                </s:iterator>--%>
<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <!-- /page content -->--%>

<%--    <!-- footer content -->--%>

<%--    <!-- /footer content -->--%>
<%--</div>--%>
<%--</div>--%>
</body>
</html>

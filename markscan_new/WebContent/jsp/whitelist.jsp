<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp" %>

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
    <!-- <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/south-street/jquery-ui.min.css" rel="stylesheet" type="text/css" /> -->
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
    <link href="css/custom.css" rel="stylesheet">
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
                        <form class="form-group row" id="addWhiteList" onsubmit="addWhiteListUrl(this);">
                            <label for="clientId" class="col-md-1 col-sm-1 col-form-label">Select Client:</label>
                            <div class="col-md-2 col-sm-2">
                                <select class="form-control" id="clientId" name="clientId"
                                        onchange="onClientChangeWhiteList()">

                                </select>
                            </div>
                            <label for="typeId" class="col-md-1 col-sm-1 col-form-label">Type of Platform:</label>
                            <div class="col-md-3 col-sm-3">
                                <select class="form-control" id="typeId" name="platformTypeId"
                                        onchange="onPlatformChange()">
                                    <option value="0">Select Platform</option>
                                    <option value="dm">Daily Motion</option>
                                    <option value="fb">Facebook</option>
                                    <option value="insta">Instagram</option>
                                    <option value="in">Internet</option>
                                    <option value="pin">Pinterest</option>
                                    <option value=re>Reddit</option>
                                    <option value="ti">TikTok</option>
                                    <option value="tw">Twitter</option>
                                    <option value="vi">Vimeo</option>
                                    <option value="yt">You Tube</option>
                                </select>
                                <p class="example" id="example">
                                </p>
                            </div>
                            <label for="url" class="col-md-1 col-sm-1 col-form-label mar-top-6">Add URL :</label>
                            <div class="col-md-2 col-sm-2">
                                <input type="text" class="form-control" name="wlistData" id="url" placeholder="Add URL">
                            </div>
                            <button type="submit" class="btn btn-success">Submit</button>
                        </form>
                        
                        <% if(request.getAttribute("clientId") !=null){%>
                       		 <div style="color: red;font-style:italic;font-size: medium;"><%=(String)request.getAttribute("msg") %></div>
                        <%} %>
                        <div class="x_content">

                            <table id="wLists"
                                   class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th class="none">Id</th>
                                    <th>Domain</th>
                                    <th>Created By</th>
                                    <th>Created Date</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>


                                <%-- <tbody>
                                     <s:iterator value="showData">
                                        <tr class="odd" role="row">

                                            <td><s:property value="domainName" /></td>
                                            <td><s:property value="channel_name" /></td>
                                            <td class="sorting_1" id="pradeep"><s:property
                                                    value="date__c" /></td>

                                        </tr>
                                    </s:iterator>

                                </tbody> --%>
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
<div class="modal fade" tabindex="-1" role="dialog" id="editModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content border-0">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Edit Domain</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <!-- <div class="form-group">
                        <label for="clientName" class="col-sm-4 control-label">Client Name :</label>
                        <div class="col-sm-5">
                          <input type="text" class="form-control" id="clientName" value="" readonly>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="pType" class="col-sm-4 control-label">Platform Type :</label>
                        <div class="col-sm-5">
                          <input type="email" class="form-control" id="pType" value="" readonly>
                        </div>
                      </div> -->
                    <div class="form-group">
                        <label for="domain" class="col-sm-4 control-label">Domain Name :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="domain" value="">
                        </div>
                    </div>
                    <input type="hidden" class="form-control" id="rowId" value="">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default border-0" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary border-0" onclick="editWhite()">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" tabindex="-1" role="dialog" id="deleteConfirm">
    <div class="modal-dialog" role="document">
        <div class="modal-content border-0">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Delete Domain</h4>
            </div>
            <div class="modal-body">
                <p class="font-14 error">Are you sure you want to delete this item?</p>
                <input type="hidden" class="form-control" id="deleteId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default border-0" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger border-0" onclick="deleteWhiteList()">Delete</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- jQuery -->
<script src="report_table/jquery.min.js"></script>
<%-- <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
<script src="//cdn.jsdelivr.net/jquery.ui-contextmenu/1.7.0/jquery.ui-contextmenu.min.js"></script> --%>
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
<script src="js/custom/message.js"></script>
<script src="js/custom/custom.js"></script>



<% if(request.getAttribute("clientId") !=null){%>
    
    <script type="text/javascript">
      $(function() {
    	  $.getJSON('getAllClient', {
    		}, function(jsonResponse) {
    			var select = $('#clientId');
    			select.find('option').remove();
    			let cliArr=[];
    			$.each(jsonResponse.stateMap, function(key, value) {
    			cliArr.push({id:key,value:value});
    			// $('<option>').val(key).text(value).appendTo(select);
    			});
    			cliArr.shift();
    			cliArr.sort(function (x,y) {
    			return ((x.value == y.value) ? 0 : ((x.value > y.value) ? 1 : -1 ));
    			});
    			cliArr.unshift({id:"0",value:"Select Client"});
    			for(let i=0;i<cliArr.length;i++){
    			$('<option>').val(cliArr[i].id).text(cliArr[i].value).appendTo(select);
    			}
    			
    			$('#clientId').val('<%=(String)request.getAttribute("clientId")%>');
    		    $('#typeId').val('<%=(String)request.getAttribute("platformTypeId")%>');
    		    onPlatformChange();
    		});
     }); 
      
      
 </script>

<% }%>




</body>
</html>






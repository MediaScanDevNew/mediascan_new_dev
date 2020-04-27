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
          <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
          
<!-- Datatables -->
<link href="report_table/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="report_table/buttons.bootstrap.min.css" rel="stylesheet">
<link href="report_table/fixedHeader.bootstrap.min.css" rel="stylesheet">
<link href="report_table/responsive.bootstrap.min.css" rel="stylesheet">
<link href="report_table/scroller.bootstrap.min.css" rel="stylesheet">          
    <style type="text/css" class="init">
    </style>



    
    <link href="report_table/custom.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <script src="js/DataTables_example/ga.js" async=""
            type="text/javascript"></script>
    <script type="text/javascript" src="js/DataTables_example/site.js"></script>



    <script type="text/javascript" language="javascript"
            src="js/DataTables_example/demo.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>            
          
            
<script>


function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}
function removeError(elemId){
	var x = document.getElementById(elemId);
	 x.style.display = "none";
}
function validateform(){  
	console.log("-------------HIT validateform -------------------");
	var returnvalue =true;
var source=document.myform.source.value;  
var projecttype=document.myform.projecttype.value; 
var clientname=document.myform.clientname.value; 
var pinfo=document.myform.pinfo.value; 
var search=document.myform.search.value;
var dtime=document.myform.dtime.value;
var utime=document.myform.utime.value;
console.log("var:: "+clientname);

  
if (source==null || source==""){  
	printError("sourceErr", "Please select Source Type"); 
	returnvalue= false;  
}else{
	removeError("sourceErr");
} 
if (projecttype==null || projecttype==""){  
	printError("proErr", "Please select Project Type"); 
	returnvalue= false; 
}else{
	removeError("proErr");
} 
if (clientname==null || clientname==""){  
	printError("cnameErr", "Please select Client Name"); 
	returnvalue= false; 
}else{
	removeError("cnameErr");
} 

if (pinfo==null || pinfo==""){  
	printError("pinfoErr", "Please select Property Name"); 
	returnvalue= false; 
}else{
	removeError("pinfoErr");
} 
if (search==null || search==""){  
	printError("srchErr", "Please type search text"); 
	returnvalue= false; 
}else{
	removeError("srchErr");
} 

if ((dtime==null || dtime=="")&&(utime==null || utime=="")){  
	printError("filterErr", "Please select any one filter"); 
	returnvalue= false; 
}else{
	removeError("filterErr");
} 

return returnvalue;
}  
</script>  

    <style type="text/css">
    .welcome {
background-color:
#DDFFDD;
border: 1px solid
#009900;
width: 550px;
margin: 16px 0px 15px 20px;
padding: 10px 10px 10px 16px;
}
        .a {
            width: 150px;
            margin-left: 20px;
        }

        .b {
            width: 300px;
        }
        .select_class{
           width: 30px !important;
        }

        .myclass {
            text-transform: capitalize;
        }
        #clientData{
/*         	overflow:auto;
        	height:370px ; */
        }
        .black{
        color:black;
        }
        .mar-top-10{
        	margin-top:10px;
        }
        .filter{
			padding-left: 8px;
			margin-top: -17px;
			background:
			darkgray;
			width: 70px;
			font-weight: bold;
        }
        .videoLink:hover {
        	color : blue;
        }
    </style>
</head>
<body class="wide comments example">


<div class="container-fluid black" style="overflow-x: hidden;overflow-y: hidden;">
<div class="row"></div>
<div class="row"></div>
<div class="row"></div>
<div class="row"></div>
<div class="row"></div>
<div class="row"></div>
        <div class="col-lg-12 " style>
				<s:if test="hasActionMessages()">
				   <div class="welcome ">
				   <button type="button" class="close" aria-label="Close">
  <span aria-hidden="true">&times;</span>
</button>
				      <s:actionmessage/>
				   </div>
				</s:if>
        	
        </div>
    
     
        <div class="form-group row">

        <div class="col-lg-12">      
	<s:form name="myform" action="addkeyphrasewhitelist" cssClass="mar-top-6 font-14" theme="simple" onsubmit="return validateform()" method="post">

            
            <label for="p_type" class="col-sm-1 col-md-1 control-label">Project Type:</label>
            <div class="col-sm-2 col-md-2">
                <s:select id="projecttype" name="projecttype" list="listData"
				listKey="id" listValue="name" headerKey=""
				headerValue="Select Project Type" label="Select Project"
				cssStyle="width: 170px;" />
				<div class="error" id="proErr"></div>
            </div>     
            <label for="p_type" class="col-sm-1 col-md-1 control-label">keyphrase Name:</label>        
        <div class="col-sm-2 col-md-2">
          <input type="text" name="keyphrasename" >
        </div>
        
        
          <s:submit cssClass=" col-sm-2 col-md-2 btn btn-success border-0" style="margin-left: 120px;"/>
       

            </div>  <hr>     
                

	
    
    </s:form>

    
    <hr>
   
    <div id="clientData">
        <table style="width: 100%;" aria-describedby="example_info" role="grid"
               id="example" class="display dataTable" cellspacing="0" width="100%" border="1">
            <thead>
            <tr role="row">
                         
                <th aria-label="Name: activate to sort column descending"
                    aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting_asc">keyphrase
                </th>
                <th aria-label="Position: activate to sort column ascending"
                    style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Active
                </th>
                
                <th aria-label="Position: activate to sort column ascending"
                    style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Action
                </th>                

               

            </tr>
            </thead>

            <tbody>



            </tbody>
           
        </table>
      
    </div>
</div>



  <div class="modal fade" id="myModal" role="dialog" style ="">
    <div class="modal-dialog modal-lg">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close mclose" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Keyphrase</h4>
        </div>
        <div class="modal-body">
			<s:form  action="updatekeyphrase" cssClass="mar-top-6 font-14" theme="simple"  method="post">
			  <input type="hidden" id="wlid" name="wlid" >
			  <input id="keyphrasename" type="text" name="keyphrasename"> 
			  <input type="submit" name="submit" value="Edit">
			</s:form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default mclose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>


</body>
</html>



<script>
$(document).ready(function() {
	$(".welcome").fadeOut(7000);
	$(".close").click(function(){
		
		$(".welcome").fadeOut(1000);
	});
	
	$("#projecttype").change(function(e){
		
		var projecttype = $("#projecttype").val();
		console.log("-----projecttype::"+projecttype);
		$.ajax({
		    url : "getkeyphrase",
		    type: "POST",
		    data : {projecttype:projecttype},
		    success: function(data, textStatus, jqXHR)
		    {
		    	$(".keyphrasedata").html('');
				console.log("-----------DATA---------:: "+data.keywordlist);
		    	var value = data.keywordlist
 		        var result = '<table style="width: 100%;" aria-describedby="example_info" role="grid" id="example" class="display dataTable" cellspacing="0" width="100%" border="1"><thead><tr role="row">';
 	               
 		       result +='<th aria-label="Name: activate to sort column descending" aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1" aria-controls="example" tabindex="0" class="sorting_asc">keyphrase  </th>';
 		      result +='<th aria-label="Position: activate to sort column ascending" style="width: 50px;" colspan="1" rowspan="1" aria-controls="example" tabindex="0" class="sorting">Active </th>';
 		     result +='<th aria-label="Position: activate to sort column ascending" style="width: 50px;" colspan="1" rowspan="1" aria-controls="example" tabindex="0" class="sorting">Action </th></tr></thead><tbody>';

		        if(value.length>0){
		        	for(var i=0;i<value.length;i++){
		        		result += '<tr>';
		        		console.log(value[i]);
		        		result += '<input type="hidden" name="keyid" value='+value[i].id+'>';
		        		result += '<td class="keyphrasevalue'+value[i].id+'">'+value[i].keyphrase+'</td>';
		        		
		        		if(value[i].isactive==0){
		        			result += '<td><span class="active'+value[i].id+'"> <input type="submit" class="btn btn-primary activeinactive"   onclick="updateactive('+value[i].isactive+','+value[i].id+')"  name ="active" value="Active"></span></td>';
		        		}else if(value[i].isactive==1){
		        			result += '<td><span class="active'+value[i].id+'"> <input type="submit" class="btn btn-danger activeinactive"   onclick="updateactive('+value[i].isactive+','+value[i].id+')" name ="inactive" value="Inactive"></span></td>';
		        		}else{
		        			result += '<td><span class="active'+value[i].id+'"> <input type="submit" class="btn btn-primary" name ="undefine" value="Undefine"></span></td>';
		        		}
		        		// var kpval = encodeURIComponent(value[i].keyphrase).trim();
		        		result += '<td onclick="openmodal('+value[i].id+ ',\''+ value[i].keyphrase + '\')"><input type="submit" class="btn btn-primary" name ="edit" value="Edit"</td>';
		        		 result += "</tr>";
		        	}
		        }
		        result += '</tbody>';
		        $("#clientData").html(result); 
		       
		        $('#example').DataTable();
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		});
	});	
	
	
});	

function  updateactive(aval,id){
	console.log("XXXXXXXXXXXXXX---:: "+aval);
	console.log("YYYYYYYYYYYYYYYYYY---:: "+id);
	var newval;
	if(aval==1){
		newval=0;
	}else{
		newval=1;
	}
	$.ajax({
	    url : "updateactivewhitelist",
	    type: "POST",
	    data : {wlid:id,aivalue:aval},
	    success: function(data, textStatus, jqXHR)
	    {
	    	var result = '';
			console.log("-----------DATA---------:: "+data);
			if(aval==1){
				result += '<input type="submit" class="btn btn-primary activeinactive"   onclick="updateactive('+newval+','+id+')"  name ="active" value="Active">';
			}else{
				result += '<input type="submit" class="btn btn-danger activeinactive"   onclick="updateactive('+newval+','+id+')" name ="inactive" value="Inactive">';
			}
           console.log("----result---:: "+result);  
           console.log("#active"+id);
           var activeid = "active"+id;
		  $('.active'+id).html(result);	
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	 
	    }
	});
}

function openmodal(id, keyphrasevalue){ 
	  console.log("value:: "+id);
    // var keyphrasevalue = $(".keyphrasevalue"+id).text();
     console.log("keyphrasevalue:: "+keyphrasevalue);
	$("#keyphrasename").val(keyphrasevalue);
	$("#wlid").val(id);
	$("#myModal").modal("show");
}
	
</script>

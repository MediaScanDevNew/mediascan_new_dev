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
            
    <script type="text/javascript" class="init">
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>            
            
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
	<s:form name="myform" action="reportdownload" cssClass="mar-top-6 font-14" theme="simple" onsubmit="return validateform()" method="post">
            <label for="p_type" class="col-sm-1 col-md-1 control-label">Source Type:</label>
            <div class="col-sm-2 col-md-2">
                <select name="source"  >
                   <option value="">Select Source Type</option>
                   <option value="1">Youtube</option>
                   <option value="2">Facebook</option>
                </select>
                <div class="error" id="sourceErr"></div>
            </div>
            
            <label for="p_type" class="col-sm-1 col-md-1 control-label">Project Type:</label>
            <div class="col-sm-2 col-md-2">
                <s:select id="projecttype" name="projecttype" list="listData"
				listKey="id" listValue="name" headerKey=""
				headerValue="Select Project Type" label="Select Project"
				cssStyle="width: 170px;" />
				<div class="error" id="proErr"></div>
            </div>            
            <label for="client_name" class="col-sm-1 col-md-1 control-label">Client Name:</label>
            <div class="col-sm-2 col-md-2">
                <s:select id="clientname" name="clientname" list="clientData"
				listKey="id" listValue="client_name" headerKey=""
				headerValue="Select Client Name" label="Select Client Name"
				cssStyle="width: 170px;" />
				<div class="error" id="cnameErr"></div>
            </div>
            
            <label for="client_name" class="col-sm-1 col-md-1 control-label">Property:</label>
            <div class="col-sm-2 col-md-2 pinfo">
<%--                 <s:select id="pinfo" name="pinfo" list="proinfoData"
				listKey="id" listValue="project_name" headerKey=""
				headerValue="Select Property Name" label="Select Property Name"
				cssStyle="width: 170px;" /> --%>
				<select name="pinfo">
				 <option value="">Select Property </option>
				</select>
				<div class="error" id="pinfoErr"></div>
            </div>
            </div>  <hr>     
                
        <div class="row"></div>
        <div class="row"></div>
  
		         <div class="col-lg-12 ">
				<s:submit cssClass=" col-sm-2 col-md-2 btn btn-success border-0" style="margin: 20px 136px;float: right;width: auto;" value="Download CSV"/>
			</div>
		</div>
	
    
    </s:form>

    
    <hr>
   

</div>



</body>
</html>


<script>
	var movieIds = [];
$(document).ready(function() {
	$(".welcome").fadeOut(7000);
	$(".close").click(function(){
		
		$(".welcome").fadeOut(1000);
	});
	
	$("#clientname").change(function(e){
		var clientname = $("#clientname").val();
		var projecttype = $("#projecttype").val();
		console.log(clientname+"-----"+projecttype);
		$.ajax({
		    url : "getproperty",
		    type: "POST",
		    data : {clientname:clientname,projecttype:projecttype},
		    success: function(data, textStatus, jqXHR)
		    {
		        var value = data.proinfoData
		        var result = '<select name="pinfo"><option value="">Select Property</option>';
		        if(value.length>0){
		        	for(var i=0;i<value.length;i++){
		        		console.log(value[i].project_name);
		        		result += "<option value="+value[i].id+">"+value[i].project_name+"</option>";
		        	}
		        }
		        result += "</select>";
		        $(".pinfo").html(result);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		});
	});

	

    
    $(".mclose").click(function(e){
    	console.log("CCCCLLLOOOSSSEE");
    	$("#video").attr("src","");
    	$("#myModal").modal("toggle");
    });
  });
  
  function getIds(id) {
	  console.log("-------- ", id);
	  let flag = 0;
	  for(let i = 0; i < movieIds.length; i++) {
		  console.log(movieIds[i] + ' ' + id)
		  if(movieIds[i] == id) {
			  flag = 1;
			  movieIds.splice(i,1);
		  }
	  }
	  if (flag === 0) {
		  movieIds.push(id);
	  }
	  console.log('+++++++++++ ', movieIds);
  }




</script>

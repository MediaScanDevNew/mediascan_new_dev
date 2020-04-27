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
	<s:form name="myform" action="youtubehit" cssClass="mar-top-6 font-14" theme="simple" onsubmit="return validateform()" method="post">
            <label for="p_type" class="col-sm-1 col-md-1 control-label">Source Type:</label>
            <div class="col-sm-2 col-md-2">
                <select name="source" id="source" >
                   <option value="">Select Source Type</option>
                   <option value="1">Youtube</option>
                   <!-- <option value="2">Facebook</option> -->
                   <option value="3">Twitter</option>
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
                
        
            <div class="col-lg-12 font-14" style="margin: 40px auto;"> 
	            <div class="col-sm-6 filterdiv" style="display: block;
							position: relative;
							border: 1px solid black;background: darkgray;">
	            <h4 class="filter">Filter:</h4>
		            <div class="col-sm-6">
			            <label for="client_name" class="col-sm-2 col-md-2 control-label">Duration Time:</label>
			            <div class="col-sm-2 col-md-2" style="display: inline-block;">
			                 <select name="dtime">
			                   <option value="">Select Duration Time</option>
			                   <option value="durationGreaterThanTwenty"> > 20 minutes</option>
			                   <option value="durationLessThanfour"> < 4 minutes </option>
			                </select>
			                
			            </div>    
		            </div>
		            <div class="col-sm-6">
			            <label for="client_name" class="col-sm-2 col-md-2 control-label">Uploaded Time:</label>
			            <div class="col-sm-2 col-md-2 utime" style="display: inline-block;">
			                 <select name="utime">
			                   <option value="">Select Uploaded Time</option>
			                   <option value="lastHour">Last hour</option>
			                   <option value="today">Today</option>
			                   <option value="thisWeek">This week</option>
			                   <option value="thisMonth">This month</option>
			                   <option value="thisYear">This year</option>
			                </select>
			               
			            </div> 
		            </div>                            
	            <div class="error" id="filterErr"></div>
	        </div>
			<div class="col-sm-6">
		         <label for="client_name" class="col-sm-1 col-md-1 control-label">Keyword:</label>
		         <div class="col-sm-6 col-md-6">
		         <input type="text" name="search" placeholder="Type Keyword" size="40" style="border-radius: 12px;height: 56px;" > 
		         <div class="error" id="srchErr"></div>
		         </div>
		         
				<s:submit cssClass=" col-sm-2 col-md-2 btn btn-success border-0" style="margin-left: 120px;"/>
			</div>
		</div>
	
    
    </s:form>

    
    <hr>
   
    <div id="clientData">
        <table style="width: 100%;" aria-describedby="example_info" role="grid"
               id="example" class="display dataTable" cellspacing="0" width="100%" border="1">
            <thead>
            <tr role="row">
                <th  class="select_class" > Select </th>            
                <th aria-label="Name: activate to sort column descending"
                    aria-sort="ascending" style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting_asc">Thumb Nails
                </th>
                <th aria-label="Position: activate to sort column ascending"
                    style="width: 50px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Link Name
                </th>
                <th aria-label="Office: activate to sort column ascending"
                    style="width: 150px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Size
                </th>

                <th aria-label="Age: activate to sort column ascending"
                    style="width: 180px;" colspan="1" rowspan="1"
                    aria-controls="example" tabindex="0" class="sorting">Type 
                </th>
				<th  class="select_class">Discard
                </th>

            </tr>
            </thead>

            <tbody>

								<form action="updatecyoutuberom" method="POST">
										<s:iterator value="cyData">
											<tr class="odd" role="row" id="row<s:text name="id"/>">
												<td><input type="checkbox" data-idVal="<s:text name="id"/>" name="mrow" onclick="getIds(<s:text name="id"/>)" ></td>  
												<td><img width="100" src="<s:text name="thumbnail_url"/>" alt="Logo" /></td>
												<td onclick='openmodal("<s:text name="url_link"/>")'><p class ="videoLink" style="cursor:grab;"  ><s:text name="url_link"/></p></td>
            <td> <select id="size<s:text name="id"/>" name="size"><option value="">Select Size</option><option value="Half">Half</option><option value="Full">Full</option></select> </td>
            <td><select id="type<s:text name="id"/>" name="type"><option value="">Select Type</option><option value="Copyright">Copyright</option><option value="Derivation">Derivation</option><option value="Trademark">Trademark</option><option value="Slide show">Slide show</option></select></td>
            <td><a href="#" class="deleterow" data-id="<s:text name="id"/>" onclick="deleterowfunc(<s:text name="id"/>)" >
          <span class="glyphicon glyphicon-trash"></span>
        </a></td>
											</tr>
										</s:iterator>
										</form>

            </tbody>
           
        </table>
         <s:submit cssClass="mar-top-10 btn btn-success border-0" onclick="submitData()"/>
    </div>
</div>




<div class="container">
  <h2></h2>
 

 
  <div class="modal fade" id="myModal" role="dialog" style ="">
    <div class="modal-dialog modal-lg">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close mclose" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Play video</h4>
        </div>
        <div class="modal-body">
          <iframe  id ="video" style ="height:400px; width:100%;"src="">
  			
		</iframe>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default mclose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>

</body>
</html>


<script>
	var movieIds = [];
$(document).ready(function() {
	
	$("#source").change(function(e){
		console.log("okkkkk");
		var value = $(this).val();
		if(value!="1"){
			$(".filterdiv").fadeOut(1000);
		}else{
			$(".filterdiv").fadeIn(1000);
		} 
	});
	
	
	$(".welcome").fadeOut(7000);
	$(".close").click(function(){
		
		$(".welcome").fadeOut(1000);
	});
	
	$("#clientname").change(function(e){
		var clientname = $("#clientname").val();
		var projecttype = $("#projecttype").val();
		console.log(clientname+"-----"+projecttype);
		if(clientname==null || clientname==""){
			var result = '<select name="pinfo"><option value="">Select Property</option>';
	        result += "</select>";
	        result += '<div class="error" id="pinfoErr"></div>';
	        $(".pinfo").html(result);
		}else{
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
			        result += '<div class="error" id="pinfoErr"></div>';
			        $(".pinfo").html(result);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			 
			    }
			});			
		}

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
  function submitData() {
	  let flag = 0, reqArr = [];
	  for(let i = 0; i < movieIds.length; i++) {
		  const sizeValue = $("#size" + movieIds[i]).val();
		  const typeValue = $("#type" + movieIds[i]).val();
		  if (sizeValue === '') {
			  flag = 1;
		  }
		  if (sizeValue === '') {
			  flag = 1;
		  }
		  const data = {
				  "id": movieIds[i],
				  "size": sizeValue,
				  "type": typeValue
		  };
		  reqArr.push(data);
	  }
	  if (flag === 1) {
		  alert('Fill all data');
		  return false;
	  }
	  console.log("main request data ", reqArr);
		 $.ajax({
		    url : "updatecyrow",
		    type: "POST",
		    data : {movieIds:JSON.stringify(reqArr)},
		    success: function(data, textStatus, jqXHR)
		    {
		      console.log("data::"+data);
			  for(let i = 0; i < movieIds.length; i++) {
				  console.log("--------------: ",movieIds[i])
				  $("#row"+movieIds[i]).remove();	
			  }
			  reqArr=[];
			  movieIds=[];
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		});  
  }
  
  function deleterowfunc(cuid){
     
		console.log("deleterowdeleterow:: "+cuid);
		$.ajax({
		    url : "delcrawlrow",
		    type: "POST",
		    data : {cuid:cuid},
		    success: function(data, textStatus, jqXHR)
		    {
		      console.log("data::"+data);
		        $("#row"+cuid).remove();
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		}); 
  }
  
  function openmodal(videoUrl){ 
	  console.log("videoUrl:: "+videoUrl);
	  if (videoUrl.indexOf('youtube') > -1) {
		  let array = videoUrl.replace("watch?v=","embed/");
		  	$("#video").attr("src",array);
		  	$("#myModal").modal("show"); 
	  } else {
		  window.open(videoUrl, '_blank');
	  }
  }




</script>
            
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
/* 	var x = document.getElementById(elemId);
	 x.style.display = "none"; */
	document.getElementById(elemId).innerHTML = "";
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
console.log("source:: "+source);

  
if (source==null || source==""){  
	console.log("SOURCE NAIIIIII");
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

if ((dtime==null || dtime=="")&&(utime==null || utime=="") && (source=="1") ){  
	printError("filterErr", "Please select any one filter"); 
	returnvalue= false; 
}else{
	removeError("filterErr");
}  

return returnvalue;
}  
</script> 

function edit_row(no)
{
	
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
 var name=document.getElementById("name_row"+no);
 var country=document.getElementById("country_row"+no);
 var age=document.getElementById("age_row"+no);
	
 var name_data=name.innerHTML;
 var country_data=country.innerHTML;
 var age_data=age.innerHTML;
 
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
 country.innerHTML="<input type='text' id='country_text"+no+"' value='"+country_data+"'>";
 age.innerHTML="<input type='text' id='age_text"+no+"' value='"+age_data+"' style='width:500px;'>";
 //alert("edit_button"+no);
}

function save_row(no)
{
	//alert(no);
 var name_val=document.getElementById("name_text"+no).value;
 var country_val=document.getElementById("country_text"+no).value;
 var age_val=document.getElementById("age_text"+no).value;

 

 var country=no;
//	alert("country==="+country);
	$.getJSON('ajaxActionB', {
		invalidQuery : country,
		url : age_val,
		mail : country_val,
		domain : name_val
	}, function(jsonResponse) {
	 aa=	$('#ajaxResponse').text(jsonResponse.dummyMsg);
		var select = $('#clientname');
		select.find('option').remove();
		$.each(jsonResponse.stateMap, function(key, value) {
			$('<option>').val(key).text(value).appendTo(select);
		});
	});
 
 
 document.getElementById("name_row"+no).innerHTML=name_val;
 document.getElementById("country_row"+no).innerHTML=country_val;
 document.getElementById("age_row"+no).innerHTML=age_val;

 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
 
 
}

function save_row_bl(no)
{
	//alert(no);
 var name_val=document.getElementById("name_text"+no).value;
 var country_val=document.getElementById("country_text"+no).value;
 var age_val=document.getElementById("age_text"+no).value;

 

 var country=no;
//	alert("country==="+country);
	$.getJSON('ajaxActionB_bl', {
		invalidQuery : country,
		domain : age_val,
		proj_id : country_val,
		url : name_val
	}, function(jsonResponse) {
	 aa=	$('#ajaxResponse').text(jsonResponse.dummyMsg);
		var select = $('#clientname');
		select.find('option').remove();
		$.each(jsonResponse.stateMap, function(key, value) {
			$('<option>').val(key).text(value).appendTo(select);
		});
	});
 
 
 document.getElementById("name_row"+no).innerHTML=name_val;
 document.getElementById("country_row"+no).innerHTML=country_val;
 document.getElementById("age_row"+no).innerHTML=age_val;

 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
 
 
}


function delete_row(no)
{
	var aa;
	var country=no;
//	alert("country==="+country);
	$.getJSON('ajaxActionA', {
		invalidQuery : country
	}, function(jsonResponse) {
	 aa=	$('#ajaxResponse').text(jsonResponse.dummyMsg);
		var select = $('#clientname');
		select.find('option').remove();
		$.each(jsonResponse.stateMap, function(key, value) {
			$('<option>').val(key).text(value).appendTo(select);
		});
	});
	
 document.getElementById("row"+no+"").outerHTML="";
}

function delete_row_bl(no)
{
	var aa;
	var country=no;
//	alert("country==="+country);
	$.getJSON('ajaxActionA_bl', {
		invalidQuery : country
	}, function(jsonResponse) {
	 aa=	$('#ajaxResponse').text(jsonResponse.dummyMsg);
		var select = $('#clientname');
		select.find('option').remove();
		$.each(jsonResponse.stateMap, function(key, value) {
			$('<option>').val(key).text(value).appendTo(select);
		});
	});
	
 document.getElementById("row"+no+"").outerHTML="";
}




function add_row()
{
 var new_name=document.getElementById("new_name").value;
 var new_country=document.getElementById("new_country").value;
 var new_age=document.getElementById("new_age").value;
	
 var table=document.getElementById("data_table");
 var table_len=(table.rows.length)-1;
 var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'><td id='name_row"+table_len+"'>"+new_name+"</td><td id='country_row"+table_len+"'>"+new_country+"</td><td id='age_row"+table_len+"'>"+new_age+"</td><td><input type='button' id='edit_button"+table_len+"' value='Edit' class='edit' onclick='edit_row("+table_len+")'> <input type='button' id='save_button"+table_len+"' value='Save' class='save' onclick='save_row("+table_len+")'> <input type='button' value='Delete' class='delete' onclick='delete_row("+table_len+")'></td></tr>";

 document.getElementById("new_name").value="";
 document.getElementById("new_country").value="";
 document.getElementById("new_age").value="";
}
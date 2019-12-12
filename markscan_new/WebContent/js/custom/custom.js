$(document).ready(function() {
	/**
	 * Get All Client
	 */
	$.getJSON('getAllClient', {
	}, function(jsonResponse) {
		var select = $('#clientId');
		select.find('option').remove();
		$.each(jsonResponse.stateMap, function(key, value) {
			$('<option>').val(key).text(value).appendTo(select);
		});
	});
//	$(document).contextmenu({
//	    delegate: ".dataTable td",
//	    menu: [
//	      {title: "Delete", cmd: "delete"},
//	      {title: "Edit", cmd: "edit"}
//	    ],
//	    select: function(event, ui) {
//	        switch(ui.cmd){
//	            case "delete":
//	                $(ui.target).parent().remove();
//	                break;
//	            case "edit":
//	              $(ui.target).html(
//	                $('<input type="text"/>').val(
//	                  $(ui.target).text()
//	                ).bind( "keypress focusout",function (e) {
//	                  if (e.type=="keypress"?(e.keyCode ==13?true:false):true) {
//	                      $(this).parent().html(
//	                        $(this).val()
//	                      );
//	                    }
//	                  })
//	              );
//	                break;
//	        }
//	    },
//	    beforeOpen: function(event, ui) {
//	        var $menu = ui.menu,
//	            $target = ui.target
//	        ui.menu.zIndex(0);
//	    }
//	  });
});
let table;
let greyTable;
/**
 * On platform type changes,it shows what type of URL should be given.
 * @returns
 */
function onPlatformChange(){
	let val=$('#typeId').val();
	if(val === "in"){
		$('#example').html(INTERNET_EX);
	}else if(val === "fb"){
		$('#example').html(FACEBOOK_EX);
	}else if(val === "tw"){
		$('#example').html(TWITTER_EX);
	}else if(val === "insta"){
		$('#example').html(INSTA_EX);
	}else if(val === "yt"){
		$('#example').html(YOUTUBE_EX);
	}else{
		$('#example').html("");
	}
	onClientChangeWhiteList();
}
/**
 * Add Grey list URL client and platform wise
 * @returns
 */
function addGrayListUrl(form){
	let clientId=$('#clientId').val();
	let platformId=$('#typeId').val();
	let url =$('#url').val().trim();
	if(clientId === "0"){
		alert('Please select a Client ');
	}else if(platformId === "0"){
		alert('Please select Platform type');
	}else if(url === ""){
		alert('Please add a URL');
	}else{
		form.action="glist";
		form.method="post";
	}
}
/**
 * Add White list URL client and platform wise
 * @returns
 */
function addWhiteListUrl(form){
	let clientId=$('#clientId').val();
	let platformId=$('#typeId').val();
	let url =$('#url').val().trim();
	if(clientId === "0"){
		alert('Please select a Client ');
	}else if(platformId === "0"){
		alert('Please select Platform type');
	}else if(url === ""){
		alert('Please add a URL');
	}else{
		if(platformId === "in"){
			form.action="addwlist";
			form.method="post";
		}else if(platformId === "fb" || platformId === "tw"){
			form.action="addwlist2";
			form.method="post";
		}else if(platformId === "insta"){
			form.action="wInsta";
			form.method="post";
		}else if(platformId === "yt"){
			form.action="wYT";
			form.method="post";
		}
	}
}
/**
 * get whitelisted domain after client change
 * @returns
 */
function onClientChangeWhiteList(){
	let clientId=$('#clientId').val();
	let platformId=$('#typeId').val();
	if(platformId !== "0" && clientId !== "0"){
		let path;
		if(platformId === "in"){
			path ='getWhiteListClientWise';
		}
		else if(platformId === "fb" || platformId === "tw"){
			path ='getSocialMediaClientWise';
		}else if(platformId === "insta"){
			path ='getInstaClientWise';
		}else if(platformId === "yt"){
			path ='getYTClientWise';
		}
//		console.log('path:: '+path);
		 table = $('#wLists').DataTable({
			   ajax: {
			       url: path+"?clientId="+clientId,
			       dataSrc: 'showData'
			   },
			   columnDefs: [
				    { targets: [ 0 ],
				      className: "none"
				    }
				  ],
			   columns: [
				   { data: "id"}, 
			       { data: "domainName"}, 
			       { data: "channel_name"}, 
			       { data: "date__c" },
			       {
			            mRender: function (data, type, row) {
			                return '<a class="table-edit pointer" id='+row.id+' onclick="editList(this.id)">Edit</a>'
			            }
			        },
			        {
			            mRender: function (data, type, row) {
			                return '<a class="table-edit pointer" id='+row.id+' onclick="deleteConfirmation(this.id)">Delete</a>'
			            }
			        }
			   ],
			   "bDestroy": true
			});
		table.column(0).visible(true);
//		$.getJSON(path+"?clientId="+clientId, {
//		}, function(jsonResponse) {
//			console.log(JSON.stringify(jsonResponse))
//		});
	}
}
/**
 * get gray listed domain after client change
 * @returns
 */
function onClientChangeGrayList(){
	let clientId=$('#clientId').val();
	if( clientId !== "0"){
		greyTable=$('#gLists').DataTable({
			   ajax: {
			       url: "getGreylistClientWise?clientId="+clientId,
			       dataSrc: 'showData'
			   },
			   columnDefs: [
			    { targets: [ 0 ],
				      className: "none"
				    }
			  ],
			   columns: [
				   { data: "id"}, 
			       { data: "domainName"}, 
			       { data: "channel_name"}, 
			       { data: "date__c" },
			       {
			            mRender: function (data, type, row) {
			                return '<a class="table-edit pointer" id='+row.id+' onclick="editList(this.id)">Edit</a>'
			            }
			        },
			        {
			            mRender: function (data, type, row) {
			                return '<a class="table-edit pointer" id='+row.id+' onclick="deleteConfirmation(this.id)">Delete</a>'
			            }
			        }
			   ],
			   "bDestroy": true,
			});
	}
}
function uploadSourceFile(form){
	let clientId=$('#clientId').val();
	if(clientId === "0"){
		alert('Please select a Client ');
	}else{
		form.action="uploadAction";
	}
}
/**
 * Open modal for Edit white and grey listed domain
 * @param id 
 * @returns
 */
function editList(id){
	let domain =$("#"+id).parent().parent().find("td:eq(1)").text();
	$('#editModal').modal('show');
//	$('#clientName').val($('#clientId').val());
//	$('#pType').val($('#typeId').val());
	$('#rowId').val(id);
	$('#domain').val(domain);
}
/**
 * open a confirmation box for deletion
 * @param id
 * @returns
 */
function deleteConfirmation(id){
	$('#deleteConfirm').modal('show');
	$('#deleteId').val(id);
}
/**
 * Delete white listed domain
 * @param id
 * @returns
 */
function deleteWhiteList(){
	$('#deleteConfirm').modal('hide');
	const id =$('#deleteId').val();
	$.getJSON('deleteInternetWhiteList', {
		'id':id,platformType:$('#typeId').val()
	}, function(jsonResponse) {
		console.log(JSON.stringify(jsonResponse))
//		$("#"+id).parent().parent().remove();
		table
	    .row( $("#"+id).parent().parent() )
	    .remove()
	    .draw();
	});
}
/**
 * Delete grey listed domain
 * @param id
 * @returns
 */
function deleteGreyList(){
	$('#deleteConfirm').modal('hide');
	const id =$('#deleteId').val();
	$.getJSON('deleteGreyList', {
		'id':id
	}, function(jsonResponse) {
		console.log(JSON.stringify(jsonResponse))
//		$("#"+id).parent().parent().remove();
		greyTable
	    .row( $("#"+id).parent().parent() )
	    .remove()
	    .draw();
	});
}
function editWhite(){
	console.log($('#rowId').val(),$('#domain').val());
	const id=$('#rowId').val();
	const domain =$('#domain').val();
	if(domain.trim() ===""){
		alert('Domain field can not be blank');
	}else{
		$('#editModal').modal('hide');
		$.getJSON('editInternetWhiteList', {
			'id':id,domain_name:domain,platformType:$('#typeId').val()
		}, function(jsonResponse) {
			console.log(JSON.stringify(jsonResponse))
			$("#"+id).parent().parent().find("td:eq(1)").text(domain);
		});
	}
//	$.ajax({
//		  type: "POST",
//		  url: 'editInternetWhiteList',
//		  data: data,
//		  contentType: 'application/json',
//		  dataType: 'json',
//		  success: function(msg){
//			  
//		  },
//		});
}
function editGreyList(){
	console.log($('#rowId').val(),$('#domain').val());
	const id=$('#rowId').val();
	const domain =$('#domain').val();
	if(domain.trim() ===""){
		alert('Domain field can not be blank');
	}else{
		$('#editModal').modal('hide');
		$.getJSON('editGreyList', {
			'id':id,domain_name:domain
		}, function(jsonResponse) {
			console.log(JSON.stringify(jsonResponse))
			$("#"+id).parent().parent().find("td:eq(1)").text(domain);
		});
	}
}
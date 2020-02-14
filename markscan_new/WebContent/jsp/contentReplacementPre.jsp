<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@taglib prefix="display" uri="http://displaytag.sf.net"%> --%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<style>
#myImg {
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s;
}

#myImg:hover {
	opacity: 0.7;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
}

/* Caption of Modal Image */
#caption {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
	text-align: center;
	color: #ccc;
	padding: 10px 0;
	height: 150px;
}

/* Add Animation */
.modal-content, #caption {
	-webkit-animation-name: zoom;
	-webkit-animation-duration: 0.6s;
	animation-name: zoom;
	animation-duration: 0.6s;
}

@
-webkit-keyframes zoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes zoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}

/* The Close Button */
.close {
	position: absolute;
	top: 15px;
	right: 35px;
	color: #f1f1f1;
	font-size: 40px;
	font-weight: bold;
	transition: 0.3s;
}

.close:hover, .close:focus {
	color: #bbb;
	text-decoration: none;
	cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px) {
	.modal-content {
		width: 100%;
	}
}
</style>




<style type="text/css">
table.ap {
	border-collapse: collapse;
	width: 100%;
}

table.ap, td, th {
	border: 1px solid black;
}

th.a, td.a {
	width: 125px;
}

td.b, th.b {
	width: 100px;
}

td.c, th.c {
	width: 300px;
}

td.d, th.d {
	width: 30px;
}

td.m {
	color: navy;
	font-size: large;
	font-weight: bold;
	width: 100px;
}

.rr {
	background-color: threedlightshadow;
}

.rm {
	background-color: black;
}
</style>
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
<style type="text/css">
.pj {
	margin-left: 500px;
	margin-top: 10px;
}

.p {
	border: thin solid;
	margin-left: -300px;
	margin-top: 10px;
	background-color: gray;
}

.pp {
	width: 700px;
}
</style>



<style>
div.ppj {
	width: 100px;
	height: 40px;
	background-color: red;
	position: relative;
	-webkit-animation-name: example; /* Safari 4.0 - 8.0 */
	-webkit-animation-duration: 4s; /* Safari 4.0 - 8.0 */
	-webkit-animation-iteration-count: infinite; /* Safari 4.0 - 8.0 */
	animation-name: example;
	animation-duration: 4s;
	animation-iteration-count: infinite;
} /* Safari 4.0 - 8.0 */
@
-webkit-keyframes example { 0% {
	background-color: red;
	left: 0px;
	top: 0px;
}

25%{
background-color


:yellow


;
left


:


200
px
;top


:


0
px
;


}
100%{
background-color


:red


;
left


:


0
px
;top


:


0
px
;


}
} /* Standard syntax */
@
keyframes example { 0% {
	background-color: red;
	left: 0px;
	top: 0px;
}
25%{
background-color


:yellow


;
left


:


200
px
;top


:


0
px
;


}
100%{
background-color


:red


;
left


:


0
px
;top


:


0
px
;


}
}
</style>

</head>
<body>

	<form action="contentReplace" method="post" class="pj">
		<table class="p">
			<tr>
				<td class="m">
					<%-- <s:textfield name="apple" id="apple" cssStyle="width=80px;"></s:textfield> --%>
					<input type="text" name="apple" id="apple" class="pp">
				</td>
			</tr>
			<tr>
				<td class="m"><input type="submit" value="Search Data"
					class="pj"></td>
			</tr>
			<%-- <s:submit value="Search"></s:submit> --%>

		</table>
	</form>
	<br>
	<div>
		<table class="ap">
			<s:iterator value="ContentFilterData">
				<!-- <tr class="rr">
					<th class="a">Id</th>
					<th class="c">URL</th>
					<th class="b">Domain</th>
					<th class="c">Infringing Link</th>
					<th class="a">User Name</th>
					<th class="b">Client Name</th>
					<th class="b">Property Name</th>
					<th class="b">Created Date</th>
					<th class="d">Edit</th>
				</tr> -->






				<tr class="rr">
					<td class="m">Id</td>
					<td class="a"><s:property value="id" /></td>
					<td class="m">URL</td>
					<td class="c"><s:property value="link" /></td>
					<td class="m">Domain</td>
					<td class="b"><s:property value="domainName" /></td>
				</tr>
				<tr>
					<td class="m">User Name</td>
					<td class="a"><s:property value="userName" /></td>
					<td class="m">Infringing Link</td>
					<td class="c"><s:property value="link001" /></td>
					<td class="m">Client Name</td>
					<td class="b"><s:property value="clientName" /></td>
				</tr>
				<tr class="rr">
					<td class="m">Property Name</td>
					<td class="a"><s:property value="propertName" /></td>
					<td class="m">Created Date</td>
					<td class="c"><s:property value="date__c" /></td>
					<%-- <td class="d"><s:a
							href="jsp/editContentReplacement.jsp?id=%{note1}&name=%{userName}&client_name=%{clientName}&project_name=%{propertName}&domainName=%{domainName}&link=%{link}&created_on=%{date__c}&link001=%{link001}
						"
							onclick="return popup(this, 'notes')"> Edit</s:a></td> --%>
					<td class="m">Qc Mode</td>
					<td class="b">
						<%-- <s:property value="qc_mode" /> --%> <s:if
							test="%{qc_mode == 0}">Manual QC </s:if> <s:elseif
							test="%{qc_mode == 1}">System QC</s:elseif>
					</td>
				</tr>
				<tr>
					<td class="m">Link Status</td>

					<%-- <s:property value="is_valid" /> --%>
					<s:if test="%{is_valid == 1}">
						<td class="a"><div class="ppj">Invalid Link</div></td>
					</s:if>
					<s:else>
						<td></td>
					</s:else>

					<td class="m">White List</td>
					<%-- <td class="a"><s:property value="whitelist" /></td> --%>
					<s:if test="%{whitelist == 1}">
						<td class="a"><div class="ppj">WhiteList Detected</div></td>
					</s:if>
					<s:else>
						<td></td>
					</s:else>
					<td class="m">Grey List</td>
					<%-- <td class="a"><s:property value="greylist" /></td> --%>
					<s:if test="%{greylist == 1}">
						<td class="a"><div class="ppj">GreyList Detected</div></td>
					</s:if>
					<s:else>
						<td></td>
					</s:else>
				</tr>
				<s:if test="%{is_valid==0}">
					<!-- <tr>
						<td>Invalid Link...</td>
					</tr> -->
				</s:if>
				<s:else>
					<tr class="rr">
						<td class="m">Invalid Time</td>
						<td class="a"><s:property value="invalid_time" /></td>
						<td class="m">Reason</td>
						<td class="a"
							style="color: white; background-color: black; font-weight: bold;"><s:property
								value="reason" /></td>
						<td class="m">Invalid By</td>
						<td class="a"><s:property value="user_invalid" /></td>
					</tr>
				</s:else>
				<tr>
					<td class="m">Note 1</td>
					<td class="a"><s:property value="note1" /></td>
					<td class="m">Note 2</td>
					<td class="a"><s:property value="note2" /></td>
					<td class="m">ScreenShot Path</td>
					<%-- <td class="a"><a href="<s:property value='image_path' />"
						target="_blank"><s:property value="image_path" /></a></td>
 --%>

					<td><s:if
							test="%{fileExtension=='.png'||fileExtension=='.PNG'}">
							<img id="myImg" src="<s:property value='image_path' />"
								alt="<s:property value='image_path' />" width="50" height="30">
							<div id="myModal" class="modal">
								<span class="close">&times;</span> <img class="modal-content"
									id="img01">
								<div id="caption"></div>
							</div>
						</s:if> <s:else>
							<a href="<s:property value='image_path' />">Download File</a>
						</s:else> <%-- <img id="myImg" src="<s:property value='image_path' />"
						alt="<s:property value='image_path' />" width="50" height="30">
					<s:property value='image_path' />
					<!-- The Modal -->
					<div id="myModal" class="modal">
						<span class="close">&times;</span> <img class="modal-content"
							id="img01">
						<div id="caption"></div>
					</div> --%></td>
				</tr>

				<s:if test="%{whitelist==1 && is_valid == 1}">
				</s:if>
				<s:else>
					<tr class="rr">
						<td class="m">User PMQC</td>
						<td class="a"><s:property value="user_pmqc" /></td>
						<td class="m">PMQC Time</td>
						<td class="a"><s:property value="pmqc_time" /></td>
						<td class="m"></td>
						<td class="m"></td>
					</tr>
					<tr>
						<td class="m">User OPSQC</td>
						<td class="a"><s:property value="user_opsqc" /></td>
						<td class="m">OPSQC Time</td>
						<td class="a"><s:property value="opsqc_time" /></td>
						<td class="m"></td>
						<td class="m"></td>
					</tr>

					<s:if test="%{link_logger== 1}">
						<s:if test="%{me_perform=='pending'}">
							<tr>
								<td class="m" colspan="6">Master Email pending....</td>
							</tr>
						</s:if>
						<s:else>
							<tr class="rr">
								<td class="m">User MasterEmail</td>
								<td class="a"><s:property value="user_me" /></td>
								<td class="m">MasterEmail Time</td>
								<td class="a"><s:property value="me_time" /></td>
								<td class="m"></td>
								<td class="m"></td>
							</tr>
						</s:else>
					</s:if>
					<s:elseif test="%{link_logger== 0}">
						<s:if test="%{gt_perform=='pending'}">
							<tr>
								<td class="m" colspan="6">Google Tracker pending....</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td class="m">User GoogleTracker</td>
								<td class="a"><s:property value="user_gt" /></td>
								<td class="m">GoogleTracker Time</td>
								<td class="a"><s:property value="gt_time" /></td>
								<td class="m"></td>
								<td class="m"></td>
							</tr>
						</s:else>
					</s:elseif>
				</s:else>
				<tr class="rm">
					<td class="a" colspan="6" style="color: black;">pradeep joshi</td>

				</tr>
			</s:iterator>
		</table>


	</div>
	<hr>
	<div>
		<table class="ap">
			<s:iterator value="yt_Data">
				<tr class="rr">
					<td class="m"> Id</td>
					<td class="a"><s:property value="crawle_url2_id" /></td>
					<td class="m">Video ID</td>
					<td class="a"><s:property value="video_id" /></td>
					<td class="m">Channel Id</td>
					<td class="a"><s:property value="channelId" /></td>
				</tr>
				<tr>
					<td class="m">Channel Title</td>
					<td class="a"><s:property value="channelTitle" /></td>
					<td class="m">Duration</td>
					<td class="a"><s:property value="duration" /></td>
					<td class="m">Like Count</td>
					<td class="a"><s:property value="likeCount" /></td>
				</tr>
				<tr class="rr">
					<td class="m">View Count</td>
					<td class="a"><s:property value="viewCount" /></td>
					<td class="m">Published Date</td>
					<td class="a"><s:property value="publishedAt" /></td>
					<td class="m"></td>
					<td class="a"></td>
				</tr>
				<tr class="rm">
					<td class="a" colspan="6" style="color: black;">pradeep joshi</td>
				</tr>
			</s:iterator>

		</table>

	</div>
<div>
In duration filed<br> 
H: Hours, M: Minutes, S:Seconds
</div>

	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById('myImg');
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		img.onclick = function() {
			modal.style.display = "block";
			modalImg.src = this.src;
			captionText.innerHTML = this.alt;
		}

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}
	</script>



</body>
</html>

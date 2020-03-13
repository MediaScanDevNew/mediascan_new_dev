<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp" %>


<%-- <s:set name="theme" value="'simple'" scope="page" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- time picker csss -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

    <title>Reporting</title>
    <style type="text/css">

        /* for hiding the page banner */
        .pagebanner {
            display: none;
        }

        /* for customizing page links */
        .pagelinks {
            color: maroon;
            margin: 20px 0px 20px 170px;
        }

        /* for shifting all the Export options*/
        .exportlinks {
            margin: 20px 0px 20px 30px;
        }

        /* For changing the spaces between export link */
        .export {
            margin-left: 30px;
        }

        /* For Table css */
        table {
            border: 1px solid #666;
            width: 60%;
            margin: 20px 0 20px 0px;
        }

        /* For odd and even row decoration */
        tr.odd {
            background-color: #f4f4f4
        }

        tr.tableRowEven, tr.even {
            background-color: #CCCCCC
        }

        /* Css for table elements */
        th, td {
            padding: 2px 4px 2px 4px;
            text-align: left;
            vertical-align: top;
        }

        thead tr {
            background-color: #999999;
        }

        /* For changing the background colour while sorting */
        th.sorted {
            background-color: #CCCCCC;
        }

        th.sorted a, th.sortable a {
            background-position: right;
            display: block;
            width: 100%;
        }

        th a:hover {
            text-decoration: underline;
            color: black;
        }

        th a, th a:visited {
            color: black;
        }

        time {
            padding: 2px;
            color: #333;
            font-family: "trebuchet ms";
            font-size: 16px;
        }

        .myclass {
            text-transform: capitalize;
        }

        .weekDays-selector input {
            display: none !important;
        }

        .weekDays-selector input[type=checkbox] + label {
            display: inline-block;
            border-radius: 6px;
            background: #dddddd;
            height: 40px;
            width: 30px;
            margin-right: 3px;
            line-height: 40px;
            text-align: center;
            cursor: pointer;
        }

        .weekDays-selector input[type=checkbox]:checked + label {
            background: #2AD705;
            color: #ffffff;
        }
    </style>
    <script src="js/jquery-1.8.2.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/default.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/pjstyle.css">

    <script src="js/jquery-ui.js"></script>
    <script type="text/javascript">
        //https://codepen.io/steelwater/pen/BjeZQx
        $(document).ready(function () { // for client name
            $('#projecttype').change(function (event) {
                var country = $("select#projecttype").val(); // country = define value of selected option
                $.getJSON('ajaxAction0', {
                    ptype: country
                }, function (jsonResponse) {


                    $('#ajaxResponse').text(jsonResponse.dummyMsg);
                    var select = $('#clientname');
                    select.find('option').remove();
                    /* $.each(jsonResponse.stateMap, function(key, value) {
                        console.log('stateMap--->'+key+'---value--->'+value);
                        $('<option>').val(key).text(value).appendTo(select);
                    }); */
                    let cliArr = [];
                    $.each(jsonResponse.stateMap, function (key, value) {
                        cliArr.push({id: key, value: value});
                        // $('<option>').val(key).text(value).appendTo(select);
                    });
                    cliArr.shift();
                    cliArr.sort(function (x, y) {
                        return ((x.value == y.value) ? 0 : ((x.value > y.value) ? 1 : -1));
                    });
                    cliArr.unshift({id: "0", value: "Select Client"});
                    for (let i = 0; i < cliArr.length; i++) {
                        $('<option>').val(cliArr[i].id).text(cliArr[i].value).appendTo(select);
                    }


                });
            });
        });

        $(document).ready(function () { // for tv show name
            $('#clientname').change(function (event) {
                var country = $("select#clientname").val(); // country = define value of selected option
                //	alert(country);
                var ptypee = $("select#projecttype").val();
                //alert(ptype);
                $.getJSON('ajaxAction1', {
                    ctype: country,
                    ptype: ptypee
                }, function (jsonResponse) {
                    $('#ajaxResponse').text(jsonResponse.dummyMsg);
                    var select = $('#propertyName');
                    select.find('option').remove();
                    $.each(jsonResponse.stateMap, function (key, value) {
                        $('<option>').val(key).text(value).appendTo(select);
                    });
                });
            });

            $('#initCaps').click(function () {
                if ($('#initCaps').prop('checked')) {
                    $('#propertyName_name').removeClass('myclass');
                    $('#propertyName_name').val().toLowerCase();
                    $('#capsFlag').val('0');
                } else {
                    $('#propertyName_name').addClass('myclass');
                    $('#capsFlag').val('1');
                }
            });

            $('#initCaps1').click(function () {
                if ($('#initCaps1').prop('checked')) {
                    $('#channel_name').removeClass('myclass');
                    $('#channel_name').val().toLowerCase();
                    $('#capsFlag1').val('0');
                } else {
                    $('#channel_name').addClass('myclass');
                    $('#capsFlag1').val('1');
                }
            });


            $('#projecttype').change(function () {
                var pType = $(this).val();
                var languag = $('#language').val();
                if (pType == 4 || pType == 5) {
                    $('#tv_content_tbl').show();
                    $('#others_content_tbl').hide();
                    $('#others_content_tbl_dtls').hide();
                    $('#property_category').val('0')
                    $('input[name="archives"]').each(function () {
                        this.checked = false;
                    });
                    $('#current_value').val('');

                } else {
                    $('#others_content_tbl').show();
                    $('#tv_content_tbl').hide();
                    $('input[name="days"]').each(function () {
                        this.checked = false;
                    });
                    $('#channel_name').val('');
                    $('#telecastTime1').val('');
                }
            });


            $('#property_category').change(function () {
                var pCategory = $(this).val();
                $('#others_content_tbl_dtls').show();
                if (pCategory == 'Current') {
                    $('#current_td').show();
                    $('#archive_td').hide();
                    $('input[name="archives"]').each(function () {
                        this.checked = false;
                    });
                } else {
                    $('#archive_td').show();
                    $('#current_td').hide();
                    $('#current_value').val('');
                }
            });


        });
    </script>


    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>


    <!-- <link rel="stylesheet" type="text/css" href="css/ddstyle.css"> -->


</head>
<body>

<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<div style="margin-left: 170px;">


    <s:form method="post" action="createProject">


        <s:select id="projecttype" name="projecttype" list="listData"
                  listKey="id" listValue="name" headerKey="0"
                  headerValue="Select Project Type" label="Select Project Type"
                  cssStyle="width: 170px;" value="%{projecttype}"/>

        <s:select id="clientname" name="clientname" list="#{'0':'Select Client'}"
                  label="Select Client" cssStyle="width: 170px;" value="88"/>


        <%--
        <s:select id="Language" placeholder="Select Language" list="#{'0':'Select Language'}" label="Select Language" cssStyle="width: 170px;" > </s:select>
        --%>

        <s:textfield name="propertyName_name" id="propertyName_name" placeholder="Property Name" label="Property Name"
                     value="%{propertyName_name}" cssClass="myclass"> </s:textfield> &nbsp;
        <s:checkbox name="checkMe" id="initCaps" label="Initial Caps OFF"/>

        <s:textfield name="file_attach_link" id="file_attach_link" placeholder="LoA (Letter of Authorization)"
                     label="LoA (Letter of Authorization)" value="%{file_attach_link}"> </s:textfield>

        <s:textfield name="actual_hosted_site" id="actual_hosted_site" placeholder="Official URL" label="Official URL"
                     value="%{actual_hosted_site}"> </s:textfield>
        <!--
        <s:textfield name="language" placeholder="Language" label="Language" value=""> </s:textfield>
        -->
        <s:select id="language" name="language"
                  list="{'Select Language','Hindi','English','Tamil','Telegu','Malyalam','Kannada','Marathi','Bengali'}"
                  label="Select language"
                  cssStyle="width: 170px;" value="%{language}"
        > </s:select>

        <s:textfield label="Releasing/Telecast Date" name="realeasingDate" id="datepicker"
                     cssStyle="width: 170px;" value="%{realeasingDate}"></s:textfield>

		<s:textfield name="genre" id="genre" placeholder="Genre" label="Genre"
                     value="" cssClass="myclass" readonly = "true"> </s:textfield> &nbsp;
        <s:hidden name="genre_id" id="genre_id" value="" />
        <table style="height: 57px;display: none;" width="300" id="tv_content_tbl">
            <tbody>
            <tr>
                <td style="width: 25%;"><h4>TV Content Only</h4></td>
            </tr>
            <tr>
                <td style="width: 68px;">
                    <s:textfield style="width: 110px;" name="telecastTime" label="Telecast Time" id="telecastTime1"
                                 value="%{telecastTime}"/></td>
            </tr>
            <tr>
                <td style="width: 25%;">Telecast Days</td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" name="days" id="sun" value="sun">Sun</td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" name="days" id="mon" value="mon">Mon</td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" name="days" id="tue" value="tue">Tue</td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" name="days" id="wed" value="wed">Wed</td>
            </tr>
            <tr>
                <td></td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" id="thu" name="days" value="thu">Thu</td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" name="days" id="fri" value="fri">Fri</td>
                <td style="width: 110px;">&nbsp;<input type="checkbox" name="days" id="sat" value="sat">Sat</td>
                <td style="width: 110px;">&nbsp;</td>
            </tr>
            <tr>
                <td>
                    <s:textfield name="channel_name" id="channel_name" placeholder="Channel Name" label="Channel Name"
                                 value="%{channel_name}" cssClass="myclass"> </s:textfield>
                    <s:checkbox name="checkMe" id="initCaps1" label="Initial Caps OFF"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><s:submit value="Create Project"></s:submit></td>
            </tr>
            </tbody>
        </table>

        <table style="height: 57px; display: none;" width="300"
               id="others_content_tbl">
            <tbody>
            <tr>


                <s:select label="Property Category"
                          headerKey="0" headerValue="Select Category"
                          list="#{'Current':'Current', 'Archive':'Archive'}"
                          name="property_category" id="property_category"
                          value="%{property_category}"/>

            </tr>
            </tbody>
        </table>

        <table id="others_content_tbl_dtls">
            <tr>
                <td rowspan="2" align="center">

                    <table style="display: none;border: none;" id="current_td">
                        <tbody>
                        <tr>

                            <td>&nbsp; <s:textfield
                                    name="current_value" id="current_value"
                                    placeholder="Current Value" label="Current Value(weeks)"
                                    value="%{current_value}">
                            </s:textfield>

                            </td>

                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>

            <tr>
                <td rowspan="2" align="left">
                    <table style="display: none;border: none;" id="archive_td">
                        <tbody>
                        <tr>
                                <%-- <td>&nbsp; <s:textfield
                                        name="archive_value" id="archive_value"
                                        placeholder="Archive Value" label="Archive Value(days)"
                                        value="%{archive_value}">
                                    </s:textfield>

                                </td> --%>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Asun"
                                                                   value="sun">Sun
                            </td>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Amon"
                                                                   value="mon">Mon
                            </td>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Atue"
                                                                   value="tue">Tue
                            </td>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Awed"
                                                                   value="wed">Wed
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Athu"
                                                                   value="thu">Thu
                            </td>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Afri"
                                                                   value="fri">Fri
                            </td>
                            <td style="width: 110px;">&nbsp;<input type="checkbox" name="archives" id="Asat"
                                                                   value="sat">Sat
                            </td>
                            <td style="width: 110px;">&nbsp;</td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><s:submit value="Create Project"></s:submit></td>
            </tr>
            </tbody>
        </table>

        <!--
        <s:submit value="Create Project"></s:submit>
        -->

        <input type="hidden" name="capsFlag" id="capsFlag" value="1">
        <input type="hidden" name="capsFlag1" id="capsFlag1" value="1">
    </s:form>
    <!-- <p>Date: <input type="text" id="datepicker"></p> -->
    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
        $(function () {
            $("#datepicker1").datepicker();
        });
        $(function () {
            $("#datepicker2").datepicker();
        });

        $('#telecastTime1').timepicker({

            timeFormat: 'hh:mm a',
            interval: 30,
            minTime: '00:00',
            maxTime: '23:30',
            //defaultTime: '24',
            startTime: '00:00',
            dynamic: true,
            dropdown: true,
            scrollbar: true

        });


    </script>
</div>

<s:textfield value="%{msg}" cssStyle="color: red; width : 400px;" readonly="true"></s:textfield>

<script type="text/javascript">
    $(function () {
        var projecttype = $("select#projecttype").val(); // country = define value of selected option
		//console.log('projecttype: --- > '+projecttype);
        if (projecttype != '0') {
            $.getJSON('ajaxAction0', {
                ptype: projecttype
            }, function (jsonResponse) {
                $('#ajaxResponse').text(jsonResponse.dummyMsg);
                var select = $('#clientname');
                select.find('option').remove();
                /* $.each(jsonResponse.stateMap, function(key, value) {
                    $('<option>').val(key).text(value).appendTo(select);
                }); */
                let cliArr = [];
                $.each(jsonResponse.stateMap, function (key, value) {
                    cliArr.push({id: key, value: value});
                    // $('<option>').val(key).text(value).appendTo(select);
                });
                cliArr.shift();
                cliArr.sort(function (x, y) {
                    return ((x.value == y.value) ? 0 : ((x.value > y.value) ? 1 : -1));
                });
                cliArr.unshift({id: "0", value: "Select Client"});
                for (let i = 0; i < cliArr.length; i++) {
                    $('<option>').val(cliArr[i].id).text(cliArr[i].value).appendTo(select);
                }

                var client_val;
                client_val = "<%=request.getAttribute("clientname")%>";
                if (client_val != '0') {
                    $('#clientname').val(client_val);

                }

            });
        }// end if condition
    });
</script>


<%
    if (request.getAttribute("projecttype") != null) {
%>
<script type="text/javascript">
    let lang,pType,genreId;
    /**
     * Changed by Pentation on 18/1/20.
     * For English language ,Tv and Sports contents,
     */
    $("select#projecttype").change(function () {
         pType = $(this).val();
         console.log(pType);
        changeDisplay();
    });
    $('#language').change(function(){
        // alert($(this).val());
        lang=$(this).val();
        changeDisplay();
    });
    function changeDisplay() {
    	//console.log(lang)
    	genreId=0;
    	if(lang !== undefined && pType !== undefined && pType!== '0' && lang !== 'Select Language'){
	    	$.getJSON('getGenre', {
	            ptype: pType,
	            language:lang
	        }, function (jsonResponse) {
				console.log(jsonResponse)
				$.each(jsonResponse.stateMap, function (key, value) {
					//console.log(key,value)
					//genreId=key;
					$('#genre_id').val(key)
					$('#genre').val(value);
                });
				console.log(genreId)
				//$('#genre').val(jsonResponse.genre);
	        });
    	}
        if (pType == '4' || pType == '5') {
            if(lang === 'English'){
                $('#others_content_tbl').show();
                $('#others_content_tbl_dtls').show();
                $('#tv_content_tbl').hide();

                $('input[name="days"]').each(function () {
                    this.checked = false;
                });
                $('#channel_name').val('');
                $('#telecastTime1').val('');
            }else {
                $('#tv_content_tbl').show();
                $('#others_content_tbl').hide();
                $('#others_content_tbl_dtls').hide();

                $('input[name="archives"]').each(function () {
                    this.checked = false;
                });
                $('#current_value').val('');

                $('input[name="days"]').each(function () {
                    this.checked = false;
                });
            }
        } else {
            $('#others_content_tbl').show();
            $('#others_content_tbl_dtls').show();
            $('#tv_content_tbl').hide();

            $('input[name="days"]').each(function () {
                this.checked = false;
            });
            $('#channel_name').val('');
            $('#telecastTime1').val('');
        } /*else {

            $('#others_content_tbl').hide();
            $('#others_content_tbl_dtls').hide();
            $('#tv_content_tbl').hide();

            $('input[name="days"]').each(function () {
                this.checked = false;
            });
            $('#channel_name').val('');
            $('#telecastTime1').val('');
            $('input[name="archives"]').each(function () {
                this.checked = false;
            });
            $('#current_value').val('');

        }*/
    }
</script>
<%} %>


<%
    if (request.getAttribute("mJSONArray") != null) {

%>
<script type="text/javascript">
    var days_arr = <%=request.getAttribute("mJSONArray") %>;
    for (i = 0; i < days_arr.length; i++) {
        $('#' + days_arr[i]).attr('checked', 'checked');
    }


</script>
<%} %>


<%
    if (request.getAttribute("mJSONArray1") != null) {

%>
<script type="text/javascript">
    var days_arr = <%=request.getAttribute("mJSONArray1") %>;
    for (i = 0; i < days_arr.length; i++) {
        $('#A' + days_arr[i]).attr('checked', 'checked');
    }


</script>
<%} %>

<%
    if (request.getAttribute("property_category") != null) {

%>

<script type="text/javascript">
    var category = "<%=request.getAttribute("property_category") %>";
    if (category == 'Current') {
        $('#current_td').show();
        $('#archive_td').hide();
        $('input[name="archives"]').each(function () {
            this.checked = false;
        });

    } else if (category == 'Archive') {
        $('#archive_td').show();
        $('#current_td').hide();
        $('#current_value').val('');
    } else {
        $('#current_td').hide();
        $('#archive_td').hide();
        $('input[name="archives"]').each(function () {
            this.checked = false;
        });
        $('#current_value').val('');
    }


</script>

<%} %>
<script type="text/javascript">
    (function ($) {
        $.fn.inputFilter = function (inputFilter) {
            return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function () {
                if (inputFilter(this.value)) {
                    this.oldValue = this.value;
                    this.oldSelectionStart = this.selectionStart;
                    this.oldSelectionEnd = this.selectionEnd;
                } else if (this.hasOwnProperty("oldValue")) {
                    this.value = this.oldValue;
                    this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
                } else {
                    this.value = "";
                }
            });
        };
    }(jQuery));
    $("#current_value").inputFilter(function (value) {
        return /^-?\d*$/.test(value);
    });

</script>
</body>
</html>

 

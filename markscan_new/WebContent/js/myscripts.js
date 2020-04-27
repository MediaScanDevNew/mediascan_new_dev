
	/* machine................... */
	$(document).ready(function() {
		var country = $("select#machine").val();
		$.getJSON('ajaxAction0', {
			machineName : country
		}, function(jsonResponse) {
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			var select = $('#machine');
			select.find('option').remove();
			$.each(jsonResponse.machine, function(key, value) {
				$('<option>').val(key).text(value).appendTo(select);
			});
		});

	});

	/* ipadress..port............... */
	$(document).ready(function() {
		$('#machine').change(function(event) {
			var country = $("select#machine").val();
			$.getJSON('ajaxAction2', {
				machineName : country
			}, function(jsonResponse) {
				$('#ip').text(jsonResponse.ip);
				$('#port').text(jsonResponse.port);
			});
		});
	});

	/* domain............................... */

	$(document).ready(function() {
		var country = $("select#domain").val();
		$.getJSON('ajaxAction3', {
			machineName : country
		}, function(jsonResponse) {
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			var select = $('#domain');
			select.find('option').remove();
			$.each(jsonResponse.machine, function(key, value) {
				$('<option>').val(key).text(value).appendTo(select);
			});
		});

	});

	/* all tv show name................... */
	$(document).ready(function() {
		var country = $("select#domain").val();
		$.getJSON('ajaxAction4', {
			tvshowName : country
		}, function(jsonResponse) {
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			var select = $('#tvshow');
			select.find('option').remove();
			$.each(jsonResponse.stateMap, function(key, value) {
				$('<option>').val(key).text(value).appendTo(select);
			});
		});
	});

	/* tvshow name............................. */

	$(document).ready(function() {
		$('#tvshow').change(function(event) {
			var country = $("select#tvshow").val();
			var coun = $("select#domain").val();
			$.getJSON('ajaxAction5', {
				showName : country,
				subdomain : coun
			}, function(jsonResponse) {
				$('#show').text(jsonResponse.show);
			});
		});
	});

	/* client name................... */
	$(document).ready(function() {
		$('#ptype').change(function(event) {
			var country = $("select#ptype").val();
			$.getJSON('ajaxAction', {
				projectName : country
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#states');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});

	/* project name....................... */
	$(document).ready(function() {
		$('#states').change(function(event) {
			var states = $("select#states").val();
			$.getJSON('ajaxAction1', {
				clientName : states
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#project');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});

$(document).ready(function() {
	var gTableCaptionText = $("#contrast-solution caption").text();

	$.tablesorter.addWidget({

		id: 'addA11y',
		format: function(table) {

			// Now add the accessibility information
			jQuery('tr', table).each( function(e) {

				// Do the header first because sorted and unsorted columns have this class
				$('th.'+table.config.cssHeader, this).each( function(e) {
					$('span.offscreen', this).each( function(e) {
						$(this).remove();
					});

					$("#contrast-solution caption").text(gTableCaptionText); // modify
					$(this).removeAttr("aria-sort"); // remove any existing aria-sort attribute on this header
					var text = $('html').attr('lang') === 'fr' ? 'Non trié' : 'Sortable, Not Sorted';
					$(this).append( $('<span>').addClass('offscreen').text(text)); // add some offscreen text to the header
				});

				// Now do the ascending and descending
				$(this).find( 'th.'+table.config.cssAsc).each( function(e) {
					$('span.offscreen', this).each( function(e) {
						$(this).remove();
					});
					var headerText = $(this).text();
					$("#contrast-solution caption").text(gTableCaptionText + ': Sorted By ' + headerText + ' Descending ');

					$(this).attr("aria-sort", "descending");//give it aria-sort attribute with value of descending
					var text = $('html').attr('lang') === 'fr' ? 'Trié dans l\'ordre décroissant' : 'Sortable, Sorted Descending';
					$(this).append( $('<span>').addClass('offscreen').text(text));// add some offscreen text to the header
				});
				$('th.'+table.config.cssDesc, this).each( function(e) {
					$('span.offscreen', this).each( function(e) {
						$(this).remove();
					});
					var headerText = $(this).text();
					$("#contrast-solution caption").text(gTableCaptionText + ': Sorted By ' + headerText + ' Ascending ');

					$(this).attr("aria-sort", "ascending");//give it aria-sort attribute with value of ascending
					var text = $('html').attr('lang') === 'fr' ? 'Trié dans l\'ordre croissant' : 'Sortable, Sorted Ascending';
					$(this).append( $('<span>').addClass('offscreen').text(text));// add some offscreen text to the header
				});
			});
		}
	});

	/** 
	 * Check if url contains the parameters "ratioSort" & "distanceSort"
	 * to set the good sorting to the table
	 * and edit the url if necessary
	 */ 
	
	function getTableSort() {
		// Get the params in the url
		var urlParams = window.location.search;

		// Param "ratioSort" in the url
		if ( urlParams.indexOf('ratioSort') > -1 )
			// Depending on its value, return the corresponding sorting setting
			return urlParams.indexOf('ratioSort=asc') > -1  ? [[3, 0]] : [[3, 1]];

		// Param "distanceSort" in the url
		if ( urlParams.indexOf('distanceSort') > -1 )
			// Depending on its value, return the corresponding sorting setting
			return urlParams.indexOf('distanceSort=asc') > -1  ? [[4, 0]] : [[4, 1]];

		// If no parameter "ratioSort" or "distanceSort" is present in the parameter
		// then we add the "distanceSort" parameter in the url
		history.pushState('', '', urlParams + '&distanceSort=asc'); 
		// We sort the table by distance asc
		return [[4, 0]];
	}
	
	// make the good table
	$("#contrast-solution")
		.tablesorter({
			widgets: ['addA11y'],
			sortList: getTableSort()
		})
		.wrap('<div role="application">') // wrap it in a DIV with a role of application
		.attr("role", "grid") // add role of grid to the table itself
		.attr("aria-readonly", "true")  // mark the grid as readonly (because none of the data is editable
		.attr("aria-labelledby", "theCaption") // label the table with caption
		.bind( 'keypress keyup keydown', function(e) {
			var keyCode;
			var special = (e.ctrlKey || e.shiftKey || e.metaKey || e.altKey);
			if ( !e.keyCode && 'which' in e && e.which) keyCode = e.which;
			else keyCode = e.keyCode;
			// Whenever a header cell is active, pressing the spacebar or enter
			// will trigger the sort
			if ( keyCode == 13 || keyCode == 32) {
				if ( $(e.target).hasClass( 'header')) {
					$(e.target).trigger( 'click');
				}
			}
		});  // set the table to be labelled by its caption

	// add role of row for all trs
	$("#contrast-solution tr").attr("role", "row");

	// assign role of columnheader and row header
	// THs inside the THEAD get columnheaders
	// THs inside the TBODY get rowheaders
	// primarily because we know this is a simple, symmetrical table. YMMV
	// also add tabindex to each header (this is an assumption that they are all sortable
	$("#contrast-solution thead th").attr("role", "columnheader").attr("scope", "col").wrapInner('<div role="button"></div>');
	$("#contrast-solution tbody th").attr("role", "rowheader").attr("scope", "row");

	// add tabindex so tabindex only appears on the sortable headers
	// which is basicaly any TH *with* a class of 'header' (which gets put there by tablesorter)
	$("#contrast-solution th.header").attr("tabindex", "0");

	// assign role of gridcell on all data cells
	$("#contrast-solution tbody td").attr("role", "gridcell");

	$('#contrast-solution caption').attr({
		role: 'log',
		'aria-live': 'assertive',
		'aria-relevant': 'all',
		'aria-atomic': 'true'
	});

	// Set the good params in the url
	// When we click on one of the column headers in the results table
	$('#contrast-solution th').on('click', function() {
		var $this = $(this);
		// If it is the "ratio" or the "distance" header
		if ( $this.hasClass('col03') || $this.hasClass('col05') ) {
			// Delay of 200ms to be sure the attribute "aria-sort" has been updated 
			setTimeout(function() {
				var ariaSort = $this.attr('aria-sort'),
					sort = ariaSort == 'ascending' ? 'asc' : 'desc',
					paramName = $this.hasClass('col03') ? 'ratioSort' : 'distanceSort';
					
				// Update the url with the good "distance" param
				history.pushState('', '', getOriginalUrlParams() + '&' + paramName + '=' + sort); 
			}, 200);
		} else {
			history.pushState('', '', getOriginalUrlParams()); 
		}
	});

	/**
	 * Return the original paramaters in the url 
	 * (not the one adding with JS: ratioSort & distanceSort)
	 */ 
	function getOriginalUrlParams() {
		var urlParams = window.location.search;

		if ( urlParams.indexOf('ratioSort') > -1 ) 
			return urlParams.substring(0, urlParams.indexOf('&ratioSort'));
		else if ( urlParams.indexOf('distanceSort') > -1 )
			return urlParams.substring(0, urlParams.indexOf('&distanceSort'));

		return urlParams;
	}

});

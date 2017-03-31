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

	// Check if the sorting by distance is defined in the URL
	var sort = 0,
			urlParams = window.location.search.substring(1);

	if ( urlParams.indexOf('distance') > -1 && urlParams.split('distance=')[1] == 'desc') sort = 1;
	
	if ( urlParams.indexOf('distance') > -1)
		urlParams = urlParams.substring(0, urlParams.indexOf('&distance='));
	
	// update URL with the parameter "distance" when page is loaded
	var distanceSort = sort == 0 ? 'asc' : 'desc';
	history.pushState('', '', '?' + urlParams + '&distance=' + distanceSort); 	

	// make the good table
	$("#contrast-solution")
		.tablesorter({
			widgets: ['addA11y'],
			sortList: [[4, sort]]
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


  // Update the parameter "distance" in the URL 
  // when man click the "sort by distance" button
	$('#contrast-solution .col05 div').on('click', function() {
		// Get value of the parameter or set one
		var urlParams = window.location.search.substring(1),
				currentSort = urlParams.indexOf('distance') > -1 ? (urlParams.indexOf('asc') > -1 ? 'desc' : 'asc') : 'desc';

		// If url contains the string "&distance=[...]", we delete the string in order to update the url afterwards 
		if ( urlParams.indexOf('distance') > -1 )
			urlParams = urlParams.substring(0, urlParams.indexOf('&distance='));

		// Update the url with the good "distance" param
		history.pushState('', '', '?' + urlParams + '&distance=' + currentSort); 	
	});
});

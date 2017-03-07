$(function() {	
	$('#old-contrast, #contrast-solution thead').affix({
		offset: {
		    top: function() {
				var header = $('#header').height(),
				    setUpForm = $('#set-up-form').height(),
				    limit = header + setUpForm + 68;
				return (this.top = header + setUpForm + 68)
		  }
		}
	});

	$(window).on('resize scroll', function() {
		var header = $('#header').height(),
				bannerHeight = $('#old-contrast').height(),
		    setUpForm = $('#set-up-form').height(),
		    limit = header + setUpForm + 68;

		if ( 
			$(this).scrollTop() > limit && 
			$(this).width() >= 768
		) {
		   $('body').css('margin-top', '160px');
		 	 $('#contrast-solution thead').css('top', bannerHeight);
		} else {
		   $('body').css('margin-top', 'inherit');
		 	 $('#contrast-solution thead').css('top', 0);
		}
	});
});
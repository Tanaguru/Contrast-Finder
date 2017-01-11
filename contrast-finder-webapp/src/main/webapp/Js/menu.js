;(function($) {

	$(function() {

		var $menu = $('#menu'),
			$toggle = $('#toggle'),
			openedMenu = false


		$toggle.on('click', function() {

			openedMenu = !openedMenu

			$toggle.attr('aria-expanded', openedMenu)

			if (openedMenu)
				$menu.addClass('is-opened')
			else 
				$menu.removeClass('is-opened')
		})

	});

})(jQuery);
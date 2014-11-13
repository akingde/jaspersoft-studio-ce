
		$(document).ready(function(){
				
				$("#title").css({ opacity: 0});
				$("#title").removeClass("hidden");
				
				$(".widget").css({ opacity: 0});
				$(".widget").removeClass('hidden');
				
				$(".widget").css({ 'padding-top': ($(window).height()*2) + 'px'});
				$(".widget").animate({ opacity: 1, 'padding-top': '0px' }, 2000,'easeOutQuart');	
				$("#title").animate({ opacity: 1 }, 2000, function() { 
				
					$(".widget").addClass("postAnim");
					$(".widget").css({ 'transition': '0.2s ease-in-out'});
				});
			});
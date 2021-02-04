$(document).ready(function(){
	$('a#pull').click(function(){
    	$("ul.nav").slideToggle();
		$("a#pull").toggleClass('show_part');
    	return false;
    });
});
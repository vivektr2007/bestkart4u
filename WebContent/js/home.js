$(function() {
	$('#carouSlide_1').carouFredSel({
		//scroll: 1000,
		circular: false,
		infinite: false,
		auto	: false,
		prev 	: {	
			button	: '#carouSlide_1_previtem',
			key		: 'left'
		},
		next 	: { 
			button	: '#carouSlide_1_nextitem',
			key		: 'right'
		},
		pagination: '#carouSlide_1_pag'
	});

	$('#carouSlide_2').carouFredSel({
		//scroll	: 1000,
		circular: false,
		infinite: false,
		auto	: false,
		prev 	: {	
			button	: '#carouSlide_2_previtem',
			key		: 'left'
		},
		next 	: { 
			button	: '#carouSlide_2_nextitem',
			key		: 'right'
		},
		pagination: '#carouSlide_2_pag'
	});
	
	
	$('#carouSlide_3').carouFredSel({
		//scroll	: 1000,
		circular: false,
		infinite: false,
		auto	: false,
		prev 	: {	
			button	: '#carouSlide_3_previtem',
			key		: 'left'
		},
		next 	: { 
			button	: '#carouSlide_3_nextitem',
			key		: 'right'
		},
		pagination: '#carouSlide_3_pag'
	});
	$('#carouSlide_4').carouFredSel({
		//scroll	: 1000,
		circular: false,
		infinite: false,
		auto	: false,
		prev 	: {	
			button	: '#carouSlide_4_previtem',
			key		: 'left'
		},
		next 	: { 
			button	: '#carouSlide_4_nextitem',
			key		: 'right'
		},
		pagination: '#carouSlide_4_pag'
	});
	$('#carouSlide_5').carouFredSel({
		//scroll	: 1000,
		circular: false,
		infinite: false,
		auto	: false,
		prev 	: {	
			button	: '#carouSlide_5_previtem',
			key		: 'left'
		},
		next 	: { 
			button	: '#carouSlide_5_nextitem',
			key		: 'right'
		},
		pagination: '#carouSlide_5_pag'
	});
	$('#testimonials').carouFredSel({
		direction	: 'up',
		items		: {
			visible		: 1,
			start		: 'random'
		},
		height		: 250,
		align		: 'center',
		auto		: 5000
	});
});
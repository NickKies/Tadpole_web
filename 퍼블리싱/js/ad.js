
$(function(){
	var deviceWidth = $(window).outerWidth();
	var mode = 0; // 1= mobile , 2= tablet, 3= pc
	if(deviceWidth < 768) {
		mode = 1;
		var swiper = new Swiper('.swiper-containerAd', {
	        direction: 'horizontal', // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
	        slidesPerView: 1, // 한번에 보이는 슬라이드 갯수
	        navigation: {
	            nextEl: '.swiper-button-next',
	            prevEl: '.swiper-button-prev'
	        }
	    });
	} else if(deviceWidth >= 768 && deviceWidth < 1200 ) {
		mode = 2;
		var swiper = new Swiper('.swiper-containerAd', {
	        direction: 'horizontal', // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
	        slidesPerView: 3, // 한번에 보이는 슬라이드 갯수
	        navigation: {
	            nextEl: '.swiper-button-next',
	            prevEl: '.swiper-button-prev'
	        }
	    });
	} else if(deviceWidth >= 1200 ) {
		mode = 3;
		var swiper = new Swiper('.swiper-containerAd', {
	        direction: 'horizontal', // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
	        slidesPerView: 4, // 한번에 보이는 슬라이드 갯수
	        navigation: {
	            nextEl: '.swiper-button-next',
	            prevEl: '.swiper-button-prev'
	        }
	    });
	};

	$(window).resize(function(){
		var widthValue = $(window).outerWidth();
		if(widthValue < 768) {
			if(mode == 2 || mode == 3) {
				$(".listAd .conBox").removeClass("swiper-containerAd");
				$(".listAd .conBox").removeClass("swiper-container-horizontal");
				$(".listAd .conBox ul").removeClass("swiper-wrapper");
				$(".listAd .conBox ul > li").removeClass("swiper-slide");
				$(".listAd .conBox ul > li").removeClass("swiper-slide-active");
				$(".listAd .conBox ul > li").removeClass("swiper-slide-next");

				$(".listAd .conBox").addClass("swiper-containerAd");
				$(".listAd .conBox ul").addClass("swiper-wrapper");
				$(".listAd .conBox ul > li").addClass("swiper-slide");
				var swiper = new Swiper('.swiper-containerAd', {
			        direction: 'horizontal', // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
			        slidesPerView: 1, // 한번에 보이는 슬라이드 갯수
			        navigation: {
			            nextEl: '.swiper-button-next',
			            prevEl: '.swiper-button-prev'
			        }
			    });
			    mode = 1;
			}
			
			
		} else if(widthValue >= 768 && widthValue < 1200 ) {
			if(mode==1 || mode ==3) {
				$(".listAd .conBox").removeClass("swiper-containerAd");
				$(".listAd .conBox").removeClass("swiper-container-horizontal");
				$(".listAd .conBox ul").removeClass("swiper-wrapper");
				$(".listAd .conBox ul > li").removeClass("swiper-slide");
				$(".listAd .conBox ul > li").removeClass("swiper-slide-active");
				$(".listAd .conBox ul > li").removeClass("swiper-slide-next");

				$(".listAd .conBox").addClass("swiper-containerAd");
				$(".listAd .conBox ul").addClass("swiper-wrapper");
				$(".listAd .conBox ul > li").addClass("swiper-slide");
				var swiper = new Swiper('.swiper-containerAd', {
			        direction: 'horizontal', // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
			        slidesPerView: 3, // 한번에 보이는 슬라이드 갯수
			        navigation: {
			            nextEl: '.swiper-button-next',
			            prevEl: '.swiper-button-prev'
			        }
			    });
			    mode = 2;
			}
			
			
		} else if(widthValue >= 1200 ) {
			if(mode == 2 || mode == 1) {
				$(".listAd .conBox").removeClass("swiper-containerAd");
				$(".listAd .conBox").removeClass("swiper-container-horizontal");
				$(".listAd .conBox ul").removeClass("swiper-wrapper");
				$(".listAd .conBox ul > li").removeClass("swiper-slide");
				$(".listAd .conBox ul > li").removeClass("swiper-slide-active");
				$(".listAd .conBox ul > li").removeClass("swiper-slide-next");

				$(".listAd .conBox").addClass("swiper-containerAd");
				$(".listAd .conBox ul").addClass("swiper-wrapper");
				$(".listAd .conBox ul > li").addClass("swiper-slide");
				var swiper = new Swiper('.swiper-containerAd', {
			        direction: 'horizontal', // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
			        slidesPerView: 4, // 한번에 보이는 슬라이드 갯수
			        navigation: {
			            nextEl: '.swiper-button-next',
			            prevEl: '.swiper-button-prev'
			        }
			    });
			    mode = 3;
			}
		};	
	});
	
});
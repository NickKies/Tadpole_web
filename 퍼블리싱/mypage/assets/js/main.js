$.noConflict();

jQuery(document).ready(function($) {

	"use strict";

	[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {
		new SelectFx(el);
	} );

	jQuery('.selectpicker').selectpicker;


	$('#menuToggle').on('click', function(event) {
		$('body').toggleClass('open');
	});

	$('.search-trigger').on('click', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$('.search-trigger').parent('.header-left').addClass('open');
	});

	$('.search-close').on('click', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$('.search-trigger').parent('.header-left').removeClass('open');
	});

	// $('.user-area> a').on('click', function(event) {
	// 	event.preventDefault();
	// 	event.stopPropagation();
	// 	$('.user-menu').parent().removeClass('open');
	// 	$('.user-menu').parent().toggleClass('open');
	// });




	// Navi active
	$(".content .contentNavi > div").click(function(){
		$(".content .contentNavi > div").removeClass("active");
		$(this).addClass("active");
	});

	$(".content .card .category > div").click(function(){
		$(".content .card .category > div").removeClass("active");
		$(this).addClass("active");

		// 상품 대분류에 따른 상세분류
		var iNum = $(this).index();
		$(".content .card .cate_select select").removeClass("active");
		$(".content .card .cate_select select").eq(iNum).addClass("active");
	});

	


	// 대출대상 팝업 180820 배다빈 추가

	$(".showTarget").click(function(){
		$(".popupTarget").addClass("active");
		if($(window).width() < 768){
			$(".popupTarget").css("height",$(document).height());
		} else {
			$(".popupTarget").css("height",$(document).height());
		}
		

		if($(".popup").hasClass("estimate_new")){
			var topValue = $(this).offset();
			$(".popupTarget").css("height",$("html").height());
			
		}
	});

	$(".popup .popup_step dl dd > div").click(function(){
		$(this).toggleClass("active");
	});

	$(".popupTarget .submitBtn").click(function(){
		var textBox = "";
		for(var i=0;i<$(".popupTarget dl dd > div.active").length; i++){
			textBox += $(".popupTarget dl dd > div.active").eq(i).text();
			if(i !== ($(".popupTarget dl dd > div.active").length-1)){
				textBox += " , ";
			} else {
				$("#pro-expir").val(textBox);
				$(".popupTarget").removeClass("active");
			}
		}
		
	});

	$(".popup .closeBtn").click(function(){
		$(this).parents(".popup").removeClass("active");
	});

	$(window).resize(function(){
		if($(window).width() < 768){
			$(".popupTarget").css("height",$("html").height());
			$(".popupDocu").css("height",$("html").height());
			$(".popupHow").css("height",$("html").height());
		} else {
			$(".popupTarget").css("height","100%");
			$(".popupDocu").css("height",$(".popupDocu .wrap").height()+40);
			$(".popupHow").css("height","100%");
		}
	});
	
	// 제출서류 팝업 180820 배다빈 추가
	$(".showDocu").click(function(){
		$(".popupDocu").addClass("active");
		if($(window).width() < 768){
			$(".popupDocu").css("height",$("html").height());
		} else {
			$(".popupDocu").css("height",$(".popupDocu .wrap").height()+40);
			if($(".popupDocu .wrap").height() < $("html").height()){
				$(".popupDocu").css("height",$("html").height());
			}
		}

		if($(".popup").hasClass("estimate_new")){
			var topValue = $(this).offset();
			// $(".popupDocu .wrap").css("top",topValue.top-500);
			$(".popupDocu").css("height",$("html").height());
		}
		// $(".popupDocu").css("height",$(window).height());
	});

	$(".popupDocu .submitBtn").click(function(){
		var textBox = "";
		for(var i=0;i<$(".popupDocu dl dd > div.active").length; i++){
			textBox += $(".popupDocu dl dd > div.active").eq(i).text();
			if(i !== ($(".popupDocu dl dd > div.active").length-1)){
				textBox += " , ";
			} else {
				$("#pro-docu").val(textBox);
				$(".popupDocu").removeClass("active");
			}
		}
	});

	// 상환방식 팝업 180820 배다빈 추가
	$(".showHow").click(function(){
		$(".popupHow").addClass("active");
		var topValue = $(this).offset();
		$(".popupHow .wrap").css("top",topValue.top-200);

		if($(window).width() < 768){
			$(".popupHow").css("height",$("html").height());
			
		}
		
		// $(".popupHow").css("height",$(window).height());
	});

	$(".popupHow .popup_step dl dd > div").click(function(){
		$(".popupHow .popup_step dl dd > div").removeClass("active");
		$(this).addClass("active");
	});

	$(".popupHow .submitBtn").click(function(){
		$("#pro-how").val($(".popupHow dl dd > div.active").text());
		$(".popupHow").removeClass("active");
	});

	// 신청방식 팝업 180822 배다빈 추가
	$(".showJoin").click(function(){
		$(".popupJoin").addClass("active");
		var topValue = $(this).offset();
		$(".popupJoin .wrap").css("top",topValue.top-200);

		if($(window).width() < 768){
			$(".popupJoin").css("height",$("html").height());
			
		}
		
		// $(".popupJoin").css("height",$(window).height());
	});

	$(".popupJoin .submitBtn").click(function(){
		var textBox = "";
		for(var i=0;i<$(".popupJoin dl dd > div.active").length; i++){
			textBox += $(".popupJoin dl dd > div.active").eq(i).text();
			if(i !== ($(".popupJoin dl dd > div.active").length-1)){
				textBox += " , ";
			} else {
				$("#pro-join").val(textBox);
				$(".popupJoin").removeClass("active");
			}
		}

		// $("#pro-join").val($(".popupJoin dl dd > div.active").text());
		// $(".popupJoin").removeClass("active");
	});

	// 상세설명 추가하기 삭제하기 180820 배다빈 추가
	$(".content .details .pluscover").click(function(){
		var plusContent ='<div class="col-12 col-md-12 col-sm-12 mb-4"><div class="form-group"><div class="input-group"><input type="text" placeholder="제목" class="form-control"><div class="input-group-addon deleteBtn"></div></div></div><div class="form-group"><textarea name="textarea-input" rows="2" placeholder="내용" class="form-control"></textarea></div></div>';
		$(this).parents(".col-12").before(plusContent);
	});

	$(document).on("click",".content .details .deleteBtn",function(){
		$(this).parents(".col-12.col-md-12.col-sm-12").remove();
	});

	// My 후기관리 답글 달기
	$(".content .table .revComment").click(function(){
		var revContent = '<tr><td class="comment_new"> <textarea name="textarea-input" rows="2" placeholder="내용" class="form-control"></textarea><div class="btnBox"><button type="button" class="revCancel">취소</button><button type="button" class="revSubmit">등록</button></div></td></tr>';
		$(this).parents("tr").after(revContent);
	});

	$(document).on("click",".content .comment_new .revCancel",function(){
		$(this).parents("tr").remove();
	});

	
	// 대출한도 , 찍기 
	$(document).on("keyup", "input:text[numberOnly]", function() {

		$(this).number(true);

	});

	// 대출한도 금액 나타내기
	$(document).on("focusout", "input:text[numberOnly]", function() {

		var won = $("#pro-limit").val();
		var arrWon  = ["만원", "억", "조", "경", "해", "자", "양", "구", "간", "정"];
		var changeWon = "";
		var pattern = /(-?[0-9]+)([0-9]{4})/;
		    while(pattern.test(won)) {                   
		        won = won.replace(pattern,"$1,$2");
		    }
		    var arrCnt = won.split(",").length-1;
		    for(var ii=0; ii<won.split(",").length; ii++) {
		        if(arrWon[arrCnt] == undefined) {
		            alert("값의 수가 너무 큽니다.");
		            break;
		        }
		 		 var tmpwon=0;
		  		for(var i=0;i<won.split(",")[ii].length;i++){
		   			var num1 = won.split(",")[ii].substring(i,i+1);
		  			 tmpwon = tmpwon+Number(num1);
		  		}
		 		 if(tmpwon > 0){
		   		 changeWon += won.split(",")[ii]+arrWon[arrCnt]; //55억0000만0000원 이런 형태 방지 0000 다 짤라 버린다
		  		}
		        arrCnt--;
		    }
		 return $(".changeWon").text(changeWon);

	});


	if($(".content .card").hasClass("productValue")){
		// 상품만기일 달력 ui
		$.datepicker.setDefaults({
	        dateFormat: 'yy-mm-dd',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년'
	    });

	    $("#datepicker1").datepicker();
	}
	

    // 계약서류 업로드
    var fileTarget = $('.fileBox .upload-hidden'); 
    fileTarget.on('change', function(){ 
	    if(window.FileReader){ 
	   	 	var filename = $(this)[0].files[0].name; 
	   	 	} else { // old IE 
	   	 	var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	   	 	} // 추출한 파일명 삽입 
	   	$(this).siblings('.upload-name').val(filename); 
   	});

    // estimate_list.html swiper control
    var mobile = false;
    if($(window).width()<768){
    	mobile=true;
    }
    $(window).resize(function(){
    	var mobWidth = $(window).width();
    	if(mobWidth<768){
    		if(mobile==false){
    // 			$(".content .card .search .chartWrap").addClass("swiper-container3");
				// $(".content .card .search .chartWrap .autoChart").addClass("swiper-wrapper");
				// $(".content .card .search .chartWrap .autoChart > div").addClass("swiper-slide");

				mobile=true;
    		}
    		
    	} else {
    		if(mobile==true){
    // 			$(".content .card .search .chartWrap").removeClass("swiper-container3");
				// $(".content .card .search .chartWrap").removeClass("swiper-container-horizontal");
				// $(".content .card .search .chartWrap .autoChart").removeClass("swiper-wrapper");
				// $(".content .card .search .chartWrap .autoChart > div").removeClass("swiper-slide");
				// $(".content .card .search .chartWrap .autoChart > div").removeClass("swiper-slide-active");
				// $(".content .card .search .chartWrap .autoChart > div").removeClass("swiper-slide-next");
				// $(".content .card .search .chartWrap .autoChart > div").css("width","25%");
				// $(".content .card .search .chartWrap .autoChart").attr("style","");
				mobile=false;
    		}
    	}
    });
    
    // My 결제관리 총 결제금액 
    var limitV = 0;
    $(".content .card .pointBox .pnPrice ul li").click(function(){
    	var indexV = $(this).index();
		if(indexV == 0) {
			limitV += 50000;
		} else if(indexV == 1){
			limitV += 100000;
		} else if(indexV == 2){
			limitV += 1000000;
		} else if(indexV == 3){
			limitV += 10000000;
		};
		$("#pnValue").val(limitV);
		$("#pnValue").number(true);
		$("#piValue").val($("#pnValue").val());
		$("#piValue").number(true);
    });

    // My 결제관리 총 결제금액 초기화버튼
    $(".content .card .pointBox .pnPrice ul .reloadBtn").click(function(){
    	limitV = 0;
    	$("#pnValue").val(limitV);
    	$("#piValue").val($("#pnValue").val());
    });

    // card 접기
    $(".content .card .card-header .toggleBox").click(function(){
    	$(this).parents(".card").toggleClass("hold");
    });

    // listView_customer.html listTab 탭기능 
	$(".listView .listTab ul li").click(function(){
		$(".listView .listTab ul li").removeClass("active");
		$(this).addClass("active");
		var indexV = $(this).index();
		$(".listView .listTabBox section").removeClass("active");
		$(".listView .listTabBox section").eq(indexV).addClass("active");
	});

	// productView.html 180824 추가
	$(".proViewBottom .proNavi > ul > li").click(function(){
		$(".proViewBottom .proNavi > ul > li").removeClass("active");
		$(this).addClass("active");
		var navi = $(this).index();
		$(".proViewBottom .proContent .proView > section").removeClass('active');
		$(".proViewBottom .proContent .proView > section").eq(navi).addClass("active");
	});
});
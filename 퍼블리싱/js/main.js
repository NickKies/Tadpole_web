$(function(){

	// register.html 모바일모드(true) / pc,태블릿모드(false)
	var mobile = false;

	// register.html 모바일 시작시 화면 크기 맞춤 모바일모드
	var wt = $(window).outerWidth();
	if(wt < 768 ) {
		$(".stepWrap").css("min-height",($(".on").height())+40);
		$(".stepWrap").css("height",($(".on").height())+40);
		$(".register .stepBox > div").css("width",$(window).width());
		$(".join .stepBox > div").css("width",wt);
		$(".join .stepBox > div").css("min-width","320px");
		mobile=true;

		$(".searchMap .mapBox .mapArea").css("width","100%");
		$(".searchMap .mapBox .listBox").css("height",($(window).height()-50));

		$(".mainVisual").css("height","auto");

		
	} else if( wt >= 1200) {
		$(".stepWrap").css("height",($(".on").height())+90);
		// $(".mainVisual").css("height","1014px");
		$(".mainVisual").css("min-height","1014px");
	} else if( wt >= 768 && wt < 1200) {
		$(".stepWrap").css("height",($(".on").height())+90);
		$(".mainVisual").css("min-height","1400px");

	}

	 
	 // $(".agreePopup").css("height",$(document).height());
	 $(".productPopup").css("height",$(document).height());
	// index.html 메인 첫번째 화면 높이 맞추기

	


	// searchMap Layout
	$(".searchMap .mapBox").css("height",($(window).height()-$("header").height()-1));
	// $(".searchMap .mapBox .mapArea").css("width",($(window).width()-$(".mapBox .listBox").width()));

	$(window).resize(function(){

		// register.html 모바일 화면 크기 = 가입 폼 맞추기
		var mobWidth = $(window).outerWidth();

		// 모바일 모드
		if(mobWidth<768){

			$(".mainVisual").css("height","auto");
			$(".mainVisual").css("min-height","0");
			
			$(".stepWrap").css("min-height",($(".on").height())+40);
			$(".stepWrap").css("height",($(".on").height())+40);
			$(".register .stepBox > div").css("width",$(window).width());
			$(".join .stepBox > div").css("width",$(window).width());
			$(".join .stepBox > div").css("min-width","320px");

			// PC에서 모바일 모드로 변경시 진행중이던 스탭으로 이동
			var page = $(".on").index();
			// mStep = page*mobWidth;
			// $(".stepBox").css("transform","translateX(-"+mStep+"px)");

			if(mobile==false){
				// $(".registerHtml .stepWrap .step0").css("left","0");
				// $(".registerHtml .stepWrap .step0").css("margin-left","0");


				// listView.html 모바일 슬라이드 기능
				$(".listView .contentWrap").addClass("swiper-container");
				$(".listView .viewBox").addClass("swiper-wrapper");
				$(".listView .viewBox > div").addClass("swiper-slide");

				$(".search .chartWrap").addClass("swiper-container3");
				$(".search .chartWrap .autoChart").addClass("swiper-wrapper");
				$(".search .chartWrap .autoChart > div").addClass("swiper-slide");

				// index.html 메인페이지 review 슬라이드 추가 180828 추가
				$(".review .wrap .reviewBox").addClass("swiper-containerReview");
				$(".review .wrap .reviewBox ul").addClass("swiper-wrapper");
				$(".review .wrap .reviewBox ul > li").addClass("swiper-slide");

				mobile=true;
			}
			
			
			$(".popup").css("height",$(document).height());
			// $(".agreePopup").css("height",$(document).height());
			$(".productPopup").css("height",$(document).height());

			// searchMap Layout
			$(".searchMap .mapBox").css("height",($(window).height()-50));
			$(".searchMap .mapBox .mapArea").css("width",($(window).width()));
			$(".searchMap .mapBox .mapArea").css("height",($(window).height()-50));
			$(".searchMap .mapBox .listBox").css("height",($(window).height()-50));
			$(".searchMap .mapBox .listBox").css("right","-100%");
			

		} else {

		// pc,태블릿 모드

			// index.html 메인 첫번째 화면 높이 변경시 자동 맞추기
			if(mobWidth < 1200 ) {
				$(".mainVisual").css("min-height","1400px");
			}

			if ( mobWidth >= 1200) {
				$(".mainVisual").css("min-height","1014px");

				// $(".stepWrap .step0").css("width","555px");
				// $(".stepWrap .step0").css("left","0");
				// $(".stepWrap .step0").css("margin-left","0");
				// $(".registerHtml .step1,.registerHtml .step2,.registerHtml .step3,.registerHtml .step4").css("width","1000px");
			} 

			if (mobWidth < 1200 && mobWidth >= 768){
				// $(".registerHtml .step1,.registerHtml .step2,.registerHtml .step3,.registerHtml .step4").css("width",mobWidth)-20;
				$(".stepWrap .step0").css("width","555px");
				// $(".registerHtml .stepWrap .step0").css("left",$(window).width()/2);
				// $(".registerHtml .stepWrap .step0").css("margin-left","-275.5px");
			}

			$(".stepWrap").css("height",($(".on").height())+90);
			// $(".register .stepBox > div").css("width","555px");
			$(".join .stepBox > div").css("width","555px");

			// 모바일에서 PC 모드로 변경시 진행중이던 스탭으로 이동
			var page = $(".on").index();
			step = page*555;
			// $(".stepBox").css("transform","translateX(-"+step+"px)");

			if(mobile==true){

				$(".register .stepBox > div").css("width","555px");

				// listView.html 모바일 슬라이드 기능
				$(".listView .contentWrap").removeClass("swiper-container");
				$(".listView .contentWrap").removeClass("swiper-container-horizontal");
				$(".listView .viewBox").removeClass("swiper-wrapper");
				$(".listView .viewBox > div").removeClass("swiper-slide");
				$(".listView .viewBox > div").removeClass("swiper-slide-active");
				$(".listView .viewBox > div").removeClass("swiper-slide-next");
				$("div.viewBox > div").css("width","25%");
				$(".listView .viewBox").attr("style","");

				$(".search .chartWrap").removeClass("swiper-container3");
				$(".search .chartWrap").removeClass("swiper-container-horizontal");
				$(".search .chartWrap .autoChart").removeClass("swiper-wrapper");
				$(".search .chartWrap .autoChart > div").removeClass("swiper-slide");
				$(".search .chartWrap .autoChart > div").removeClass("swiper-slide-active");
				$(".search .chartWrap .autoChart > div").removeClass("swiper-slide-next");
				$(".search .chartWrap .autoChart > div").css("width","25%");
				$(".search .chartWrap .autoChart").attr("style","");

				// index.html review 슬라이드 추가 180828 배다빈추가
				$(".review .wrap .reviewBox").removeClass("swiper-containerReview");
				$(".review .wrap .reviewBox").removeClass("swiper-container-horizontal");
				$(".review .wrap .reviewBox ul").removeClass("swiper-wrapper");
				$(".review .wrap .reviewBox ul > li").removeClass("swiper-slide");
				$(".review .wrap .reviewBox ul > li").removeClass("swiper-slide-active");
				$(".review .wrap .reviewBox ul > li").removeClass("swiper-slide-next");
				$(".review .wrap .reviewBox ul > li").css("width","33.3333%");
				$(".review .wrap .reviewBox ul").attr("style","");

				mobile=false;
			}
			

			$(".searchMap .mapBox").css("height",($(window).height()-126));
			$(".searchMap .mapBox .mapArea").css("width",($(window).width()-$(".mapBox .listBox").width()));
			$(".searchMap .mapBox .mapArea").css("height","100%");
			$(".searchMap .mapBox .listBox").css("right","0");
			$(".searchMap .mapBox .listBox").css("height","100%");

			// register popup 서비스 약관 및 동의 위치

			$(".productPopup").css("height",$(document).height());

		}

		
	});

	


	// register.html 다음버튼 , 이전버튼
	var step = 0;
	var mStep = 0;

	// 다음버튼
	$(".nextStep").click(function(){
		$(this).parents(".step").removeClass("on");
		$(this).parents(".step").next().addClass("on");
		var page = $(".on").index();
		var sort = $("#mb_sort").val();

		if(page == 1) {
			if($("#agreeNo").val() != 1){
				alert("서비스 약관을 읽어보신후 동의해주셔야 다음단계로 진행됩니다.");
				return;
			}
			var step = step +1;
		}
		if(page == 2) {
			if(!$("#reg_mb_id").val()){
				alert("아이디를 입력해주세요.");
				$("#reg_mb_id").focus();
				return;
			} else {
				var msg = reg_mb_id_check();
				if (msg) {
					alert(msg);
					$("#reg_mb_id").focus();
					return false;
				}
			}
			if(!$("#reg_mb_password").val()){
				alert("비밀번호를 입력해주세요.");
				$("#reg_mb_password").focus();
				return;
			}
			if($("#reg_mb_password").val() != $("#reg_mb_password_re").val()){
				alert("비밀번호와 비밀번호확인이 다릅니다.");
				$("#reg_mb_password_re").focus();
				return;
			}

			if(!$("#reg_mb_hp").val()){
				alert("휴대폰번호를 입력해주세요.");
				$("#reg_mb_hp").focus();
				return;
			} else {
				var msg = reg_mb_hp_check();
				if (msg) {
					alert(msg);
					$("#reg_mb_hp").focus();
					return false;
				}
			}
			if(!$("#reg_mb_hp_check").val()){
				alert("인증번호를 입력해주세요.");
				$("#reg_mb_hp_check").focus();
				return;
			}
			if($("#mb_hp_result").val() != "Y"){
				alert("휴대폰 인증이 완료되지 않았습니다.");
				return;
			}
			
		}
		if(page == 3) {
			if(!$("#com_name").val()){
				alert("금융회사명을 입력해주세요.");
				$("#com_name").focus();
				return;
			}
			if(!$("#com_circles").val()){
				alert("금융권을 선택해주세요.");
				$("#com_circles").focus();
				return;
			}
			if(!$("#com_business").val()){
				alert("사업내용을 선택해주세요.");
				$("#com_business").focus();
				return;
			}
			if(sort == "대부업체") {
				if(!$("#com_tell").val()){
					alert("홍보용 전화번호를 입력해주세요.");
					$("#com_tell").focus();
					return;
				}
				if(!$("#mb_homepage").val()){
					alert("홈페이지를 입력해주세요.");
					$("#mb_homepage").focus();
					return;
				}
			} else {
				if(!$("#com_tell").val()){
					alert("금융회사 대표번호를 입력해주세요.");
					$("#com_tell").focus();
					return;
				}
				if(!$("#com_condate").val()){
					alert("계약일을 입력해주세요.");
					$("#com_condate").focus();
					return;
				}
			}
			if(!$("#com_zip").val()){
				alert("우편번호를 주소찾기를 이용해 입려해주세요.");
				$("#com_zip").focus();
				return;
			}
			if(!$("#com_addr1").val()){
				alert("기본주소를 주소찾기를 이용해 입려해주세요.");
				$("#com_addr1").focus();
				return;
			}
		}
		if(page == 4) {
			if(!$("#mb_name").val()){
				alert("성명을 입력해주세요.");
				$("#mb_name").focus();
				return;
			}
			if(!$("#mb_rnumber").val()){
				alert("등로번호를 입력해주세요.");
				$("#mb_rnumber").focus();
				return;
			}
			if(!$("#mb_company").val()){
				alert("금융기관을 입력해주세요.");
				$("#mb_company").focus();
				return;
			}
			if(!$("#mb_tel").val()){
				alert("등록된 전화번호를 입력해주세요.");
				$("#mb_tel").focus();
				return;
			}
			if(sort == "법인대출모집인") {
				if(!$("#mb_date").val()){
					alert("등록일을 입력해주세요.");
					$("#mb_date").focus();
					return;
				}
			}

			if(sort == "대부업체") {
				if(!$("#mb_img").val()){
					alert("등록증을 등록해주세요.");
					$("#mb_img").focus();
					return;
				}
			} else {
				if(!$("#mb_img").val()){
					alert("수료증을 등록해주세요.");
					$("#mb_img").focus();
					return;
				}
			}

		}
		if(page == 5) {
			if(!$("#com2_name").val()){
				alert("대표자명을 입력해주세요.");
				$("#com2_name").focus();
				return;
			}
			if(!$("#com2_tel").val()){
				alert("대표자 전화번호를 입력해주세요.");
				$("#com2_tel").focus();
				return;
			}
			if(!$("#com2_number").val()){
				alert("등록번호를 입력해주세요.");
				$("#com2_number").focus();
				return;
			}
			if(!$("#com2_institution").val()){
				alert("등록기관을 입력해주세요.");
				$("#com2_institution").focus();
				return;
			}
		}
		/*
		if(page == 6) {
			if(!$("#sns_kakao").val()){
				alert("카카오페이지를 입력해주세요.");
				$("#sns_kakao").focus();
				return;
			}
			if(!$("#sns_kastory").val()){
				alert("카카오스토리를 입력해주세요.");
				$("#sns_kastory").focus();
				return;
			}
			if(!$("#sns_facebook").val()){
				alert("페이스북을 입력해주세요.");
				$("#sns_facebook").focus();
				return;
			}
			if(!$("#sns_band").val()){
				alert("밴드를 입력해주세요.");
				$("#sns_band").focus();
				return;
			}
			if(!$("#sns_twitter").val()){
				alert("트위터를 입력해주세요.");
				$("#sns_twitter").focus();
				return;
			}
			if(!$("#sns_youtube").val()){
				alert("유튜브를 입력해주세요.");
				$("#sns_youtube").focus();
				return;
			}
			if(!$("#sns_blog").val()){
				alert("블로그를 입력해주세요.");
				$("#sns_blog").focus();
				return;
			}
			if(!$("#sns_instar").val()){
				alert("인스타그램을 입력해주세요.");
				$("#sns_instar").focus();
				return;
			}
		}
		*/

// pc 버전일 때
		if(mobile==false){

			// 옆으로 이동
			// step = page*555;
			// $(".stepBox").css("transform","translateX(-"+step+"px)");
			$(".step.on").stop().animate({ left : "0" },600);
			$(this).parents(".step").stop().animate({ left : "-100%" },600,function(){});

			// 넓이조절
			var mobWidthV = $(window).outerWidth();
			if(mobWidthV < 1200 && mobWidthV >= 768) {
				// $(".registerHtml .step1,.registerHtml .step2,.registerHtml .step3").css("width",mobWidthV-20);
			}

			// 스탭별로 높이 변경
			$(".stepWrap").css("height",($(".on").height())+90);

			var scrollV = $(".step.on .stepTitle").offset();
			$(window).scrollTop(scrollV.top-176);


		// 모바일 버전일 때
		} else if(mobile){

			// 옆으로 이동
			$(".step.on").stop().animate({ left : "0" },400);
			$(this).parents(".step").stop().animate({ left : "-100%" },400,function(){
				
			});
			// var mobWidth = $(window).width();
			// mStep = page*mobWidth;
			// $(".stepBox").css("transform","translateX(-"+mStep+"px)");

			// 스탭별로 높이 변경
			$(".stepWrap").css("min-height",($(".on").height())+40);
			$(".stepWrap").css("height",($(".on").height())+40);

			var scrollV = $(".step.on .stepTitle").offset();
			$(window).scrollTop(scrollV.top-76);
		}

		setTimeout(function(){
			$(".stepNavi_2 li").eq(page).addClass("active");
		},300);
		$(".register .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		$(".join1 .stepNavi_2 .activeBg .activeBar").css("width",(page*20)+"%");
		$(".join2 .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		$(".join3 .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");

		// $(".stepNavi li").removeClass("active");
		// $(".stepNavi li:eq("+page+")").addClass("active");
		// $(".register .stepNavi .activeBg").css("margin-left",(25*page)+"%");
		// $(".join1 .stepNavi .activeBg").css("margin-left",(20*page)+"%");
		// $(".join2 .stepNavi .activeBg").css("margin-left",(33.3333*page)+"%");
		// $(".join3 .stepNavi .activeBg").css("margin-left",(33.3333*page)+"%");

	});





	// 다음버튼
	$(".nextStep_r").click(function(){
		$(this).parents(".step").removeClass("on");
		$(this).parents(".step").next().addClass("on");
		var page = $(".on").index();
		var sort = $("#mb_sort").val();

		if(page == 1) {

			if(!$("#reg-cate").val()){
				alert("대출종류를 선택해주세요.");
				$("#reg-cate").focus();
				return;
			}

			if($("#regCate").val() == ""){
				alert("자금용도를 선택해주세요.");
				$("#regCate").focus();
				return;
			}

			if(!$("#agree_1").is(':checked')){
				alert("서비스이용약관에 동의해주세요.");
				$("#agree_1").focus();
				return;
			}

			if(!$("#agree_2").is(':checked')){
				alert("개인정보취급방침에 동의해주세요.");
				$("#agree_2").focus();
				return;
			}

			if(!$("#agree_3").is(':checked')){
				alert("윤리강연에 동의해주세요.");
				$("#agree_3").focus();
				return;
			}

		}
		if(page == 2) {

			if(!$("#reg_mb_hp").val()){
				alert("휴대폰번호를 입력해주세요.");
				$("#reg_mb_hp").focus();
				return;
			} else {
				var msg = reg_mb_hp_check();
				if (msg) {
					alert(msg);
					$("#reg_mb_hp").focus();
					return false;
				}
			}
			if(!$("#reg_mb_hp_check").val()){
				alert("인증번호를 입력해주세요.");
				$("#reg_mb_hp_check").focus();
				return;
			}
			if($("#mb_hp_result").val() != "Y"){
				alert("휴대폰 인증이 완료되지 않았습니다.");
				return;
			}

			if($("#lo_bdate").val() == ""){
				alert("생년월일을 선택해주세요.");
				$("#lo_bdate").focus();
				return;
			}

			if($("#lo_sex").val() == ""){
				alert("성별을 선택해주세요.");
				$("#lo_sex").focus();
				return;
			}

			if($("#lo_army").val() == ""){
				alert("병역여부를 선택해주세요.");
				$("#lo_army").focus();
				return;
			}

			if($("#lo_mery").val() == ""){
				alert("결혼여부를 선택해주세요.");
				$("#lo_mery").focus();
				return;
			}


			
		}
		if(page == 3) {

			if($("#lo_job").val() == ""){
				alert("직업을 선택해주세요.");
				$("#lo_job").focus();
				return;
			}
			if($("#lo_ymoney").val() == "0"){
				alert("연소득을 입력해주세요.");
				$("#lo_ymoney").focus();
				return;
			}
			if($("#reg-month").val() == ""){
				alert("근무기간을 입력해주세요.");
				$("#reg-month").focus();
				return;
			}
			if($("#lo_bank").val() == ""){
				alert("급여수령방식을 선택해주세요.");
				$("#lo_bank").focus();
				return;
			}

		}
		if(page == 4) {
			/*
			if($("#reg-regMonth").val() == ""){
				alert("대출신청기간을 선택해주세요.");
				$("#reg-regMonth").focus();
				return;
			}
			if($("#lo_money").val() == ""){
				alert("대출신청액을 입력해주세요.");
				$("#lo_money").focus();
				return;
			}
			*/
		}
		if(page == 5) {
		}
		/*
		if(page == 6) {
			if(!$("#sns_kakao").val()){
				alert("카카오페이지를 입력해주세요.");
				$("#sns_kakao").focus();
				return;
			}
			if(!$("#sns_kastory").val()){
				alert("카카오스토리를 입력해주세요.");
				$("#sns_kastory").focus();
				return;
			}
			if(!$("#sns_facebook").val()){
				alert("페이스북을 입력해주세요.");
				$("#sns_facebook").focus();
				return;
			}
			if(!$("#sns_band").val()){
				alert("밴드를 입력해주세요.");
				$("#sns_band").focus();
				return;
			}
			if(!$("#sns_twitter").val()){
				alert("트위터를 입력해주세요.");
				$("#sns_twitter").focus();
				return;
			}
			if(!$("#sns_youtube").val()){
				alert("유튜브를 입력해주세요.");
				$("#sns_youtube").focus();
				return;
			}
			if(!$("#sns_blog").val()){
				alert("블로그를 입력해주세요.");
				$("#sns_blog").focus();
				return;
			}
			if(!$("#sns_instar").val()){
				alert("인스타그램을 입력해주세요.");
				$("#sns_instar").focus();
				return;
			}
		}
		*/

// pc 버전일 때
		if(mobile==false){

			// 옆으로 이동
			// step = page*555;
			// $(".stepBox").css("transform","translateX(-"+step+"px)");
			$(".step.on").stop().animate({ left : "0" },600);
			$(this).parents(".step").stop().animate({ left : "-100%" },600,function(){});

			// 넓이조절
			var mobWidthV = $(window).outerWidth();
			if(mobWidthV < 1200 && mobWidthV >= 768) {
				// $(".registerHtml .step1,.registerHtml .step2,.registerHtml .step3").css("width",mobWidthV-20);
			}

			// 스탭별로 높이 변경
			$(".stepWrap").css("height",($(".on").height())+90);

			var scrollV = $(".step.on .stepTitle").offset();
			$(window).scrollTop(scrollV.top-176);


		// 모바일 버전일 때
		} else if(mobile){

			// 옆으로 이동
			$(".step.on").stop().animate({ left : "0" },400);
			$(this).parents(".step").stop().animate({ left : "-100%" },400,function(){
				
			});
			// var mobWidth = $(window).width();
			// mStep = page*mobWidth;
			// $(".stepBox").css("transform","translateX(-"+mStep+"px)");

			// 스탭별로 높이 변경
			$(".stepWrap").css("min-height",($(".on").height())+40);
			$(".stepWrap").css("height",($(".on").height())+40);

			var scrollV = $(".step.on .stepTitle").offset();
			$(window).scrollTop(scrollV.top-76);
		}

		setTimeout(function(){
			$(".stepNavi_2 li").eq(page).addClass("active");
		},300);
		$(".register .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		$(".join1 .stepNavi_2 .activeBg .activeBar").css("width",(page*20)+"%");
		$(".join2 .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		$(".join3 .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");

		// $(".stepNavi li").removeClass("active");
		// $(".stepNavi li:eq("+page+")").addClass("active");
		// $(".register .stepNavi .activeBg").css("margin-left",(25*page)+"%");
		// $(".join1 .stepNavi .activeBg").css("margin-left",(20*page)+"%");
		// $(".join2 .stepNavi .activeBg").css("margin-left",(33.3333*page)+"%");
		// $(".join3 .stepNavi .activeBg").css("margin-left",(33.3333*page)+"%");

	});


	// register.html gender 선택 버튼
	$(".register .row .gendBox > div").click(function(){
		$(".register .row .gendBox > div").removeClass("active");
		$(this).addClass("active");
	});



		// 이전버튼
	$(".prevStep").click(function(){

		$(this).parents(".step").removeClass("on");
		$(this).parents(".step").prev().addClass("on");
		var page = $(".on").index();
		$(".step.on .stepTitle").focus();

		// pc버전일 때
		if(mobile==false){

			// 옆으로 이동
			$(".step.on").stop().animate({ left : "0" },600);
			$(this).parents(".step").stop().animate({ left : "100%" },600,function(){
				
			});

			

			// 넓이조절
			var mobWidthV = $(window).outerWidth();
			if(mobWidthV < 1200 && mobWidthV >= 768) {
				$(".registerHtml .step1,.registerHtml .step2,.registerHtml .step3,.registerHtml .step4").css("width",mobWidthV-20);
				$(".registerHtml .stepWrap .step0.on").css("left",$(window).width()/2);
			    $(".registerHtml .stepWrap .step0.on").css("margin-left",($(window).width()-555)/2);
			}
			

			// step = page*555;
			// $(".stepBox").css("transform","translateX(-"+step+"px)");

			// 스탭별로 높이 변경
			$(".stepWrap").css("height",($(".on").height())+90);

		// 모바일 버전일 때	
		} else if(mobile){

			// 옆으로 이동
			$(".step.on").stop().animate({ left : "0" },400);
			$(this).parents(".step").stop().animate({ left : "100%" },400,function(){
				
			});

			// var mobWidth = $(window).width();
			// mStep = page*mobWidth;
			// $(".stepBox").css("transform","translateX(-"+mStep+"px)");

			// 스탭별로 높이 변경
			$(".stepWrap").css("height",($(".on").height())+40);
		}
		

		$(".stepNavi_2 li").eq(page+1).removeClass("active");
		$(".register .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		$(".join1 .stepNavi_2 .activeBg .activeBar").css("width",(page*20)+"%");
		$(".join2 .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		$(".join3 .stepNavi_2 .activeBg .activeBar").css("width",(page*25)+"%");
		// $(".stepNavi li").removeClass("active");
		// $(".stepNavi li:eq("+page+")").addClass("active");
		// $(".register .stepNavi .activeBg").css("margin-left",(25*page)+"%");
		// $(".join1 .stepNavi .activeBg").css("margin-left",(25*page)+"%");
		// $(".join2 .stepNavi .activeBg").css("margin-left",(33.3333*page)+"%");
		// $(".join3 .stepNavi .activeBg").css("margin-left",(33.3333*page)+"%");
	});

	// 스크롤 시 header 배경 변경
	$(window).scroll(function(){
		if($(window).scrollTop() > 10){
			$("header").addClass("active");
		} else if($("header").hasClass(':not(.mapHeader)')){
			$("header").removeClass("active");
		}
	});

	// 모바일 메뉴 작동
	$(".mobileMenu").click(function(){
		$("header nav").css("right","0");
	});

	$(".mobileXbtn").click(function(){
		$("header nav").css("right","-800px");
	});

	// searchMap 모바일 목록 작동
	$(".searchMap .listBtn").click(function(){
		$(this).siblings(".listBox").css("right","0");
	});

	$(".searchMap .backBtn button").click(function(){
		$(this).parents(".listBox").css("right","-100%");
	});

	// $(".searchMap .listBtn").click(function(){
	// 	$("header nav").css("right","-800px");
	// });

	// 모두 동의
	$("#agree_all").click(function(){
		if($("#agree_all").prop("checked")){
			$(".agreePopup input[type=checkbox]").prop("checked",true);
			$(".agreePopup .agreeBtn").addClass("active");
		} else {
			$(".agreePopup input[type=checkbox]").prop("checked",false);
			$(".agreePopup .agreeBtn").removeClass("active");
		}
	});

	// 체크박스 아이콘 클릭시 체크박스 체크표시
	$(".agreePopup label").click(function(){
		if($(this).children("input[type=checkbox]").prop("checked")){
			$(this).children(".agreeBtn").addClass("active");
		} else {
			$(this).children(".agreeBtn").removeClass("active");
		}
	});


	// register.html step4 add btn 
	$(document).on("click",".addBtn",function(){
		var insert = '<div class="infoWrap"><div class="infoArea"><div class="infoBox info1"><div class="w33"><select name="loa_category[]"><option>대출분류</option><option>신용대출</option><option>마이너스통장</option><option>주택담보대출</option><option>전세대출</option><option>중도금/이주비대출</option><option>자동차 할부/리스</option><option>카드할부금</option><option>기타대출</option></select></div><div class="w33 inputGroup"><input type="text" name="loa_money[]" placeholder="대출금액" class="w60 infoSum" numberOnly><div class="daw w40">만원</div></div><div class="inputGroup w33"><input type="number" name="loa_peri[]" class="w60" placeholder="대출기간"><div class="daw w40">개월</div></div></div><div class="infoBox info2"><div class="w33"><select name="loa_rep[]"><option>상환방법</option><option>원리금균등분할</option><option>원금균등분할</option><option>만기일시</option></select></div><div class="w33 inputGroup"><input type="text" name="loa_rete[]" placeholder="대출금리" class="w60" maxlength="6" onkeyup="autoSum(this);"><div class="daw w40">%</div></div><div class="inputGroup w33"><input type="text" name="loa_gury[]" class="w60" placeholder="거치기간"><div class="daw w40">개월</div></div></div></div><div class="deleteBtn"><span class="i_delete"></span></div><div class="addBtnBox"><button type="button" class="addBtn"></button></div></div>';
		$(".row .info").append(insert);
		
		var mobWidthV = $(window).outerWidth();
		if(mobWidthV > 768) {
			$(".stepWrap").css("height",($(".on").height())+90);
		} else {
			$(".stepWrap").css("height",($(".on").height())+40);
		}
		
	});

	// register.html step3 delete btn 
	$(document).on("click",".deleteBtn",function(){
		$(this).parents(".infoWrap").remove();
		$(".stepWrap").css("height",($(".on").height())+40);
	});

	// mobileMenu 
	$(".submenu > a").click(function(e){
		if(mobile) e.preventDefault();
		$(this).parents(".submenu").toggleClass("active");
	});

	$(".submenu > .gobtn").click(function(){
		$(this).parents(".submenu").toggleClass("active");
	});

	// list.html 해당 글로 이동
	$(".list .contentWrap .nowLoan td").click(function(){
		location.href = 'listView_customer.html';
		// 게시글 번호 값 $(this).children("td.no").text();
	});

	// list.html 해당 글 비밀번호 입력페이지로 이동
	$(".list .contentWrap .myloan tr").click(function(){
		location.href = 'listView_customer.html';
		// 게시글 번호 값 $(this).children("td.no").text();
	});

	// 현재 위치정보 확인
	$(".register .positionNo").click(function(){
		if($(this).hasClass("positionYes")){
			$(this).removeClass("positionYes");
		} else {
			$(this).addClass("positionYes");
			function getLocation() {
			  if (navigator.geolocation) { // GPS를 지원하면
			    navigator.geolocation.getCurrentPosition(
					function(position) {
						setPosition(position); // request.php
					}, 
					function(failure) {
						if(failure.message.indexOf("Only secure origins are allowed") == 0) {
							alert('Only secure origins starting with "https" are allowed by your browser.');
						} else if( failure.code == 2) {
							alert("Error: Position is unavailable!");
						} else {
							alert("ERROR(" + failure.code + "): " + failure.message);
						}
						$("button.positionNo.positionYes").removeClass("positionYes");						
					},
					{
						enableHighAccuracy: false,
						maximumAge: 0,
						timeout: Infinity
					}
				);
			  } else {
			    alert('GPS를 지원하지 않습니다');
			  }
			}
			getLocation();
		}
	});

	// register agree popup 
	$(".agreePopup .agreeForm").click(function(){
		$(this).next().toggleClass("view");
		if($(window).outerWidth() > 768 ){
			$(".stepWrap").css("height",($(".on").height())+90);
		} else { 
			$(".stepWrap").css("height",($(".on").height())+40);
		}
		
	});

	$(".agreePopup .cancel").click(function(){
		$(".agreePopup").removeClass("on");
	});

	$(".agreeNo").click(function(){
		$(".agreePopup").addClass("on");
		if($(this).parents("section").hasClass("join")){
			$(".agreePopup").css('height',$("html").height());
		}
		// register popup 서비스 약관 및 동의 위치
		if(mobile==false){
			$(".agreePopup .wrap").css("top",($(window).height())/2);
		} else {
			var topValue = $(this).offset();
			$(".agreePopup .wrap").css("top", topValue.top);
		}
		
	});

	$(".agreePopup .submit").click(function(){
		if($(".agreePopup input[type=checkbox]").prop("checked")){
			$(".agreeNo").addClass("agreeYes");
			$(".agreePopup").removeClass("on");
			$(".agreeYes").text("서비스 약관 및 동의 완료");
			$("#agreeNo").val("1");
		} else {
			alert("서비스 약관에 모두 동의하셔야 계속 진행할 수 있습니다.");
		}
	});

	// companyView product popup 
	$(".search .productBtn").click(function(){
		$(this).parents(".search").siblings(".productPopup").addClass("on");
	});

	$(".productPopup .closeBtn").click(function(){
		$(this).parents(".productPopup").removeClass("on");
	});

	// loanresultWrite.html 상품설명 추가 
	$(document).on("click",".proInfoAddBtn",function(){
		var insert = '<ul class="productInfoWrite newInfoWrite"><li><input type="text" placeholder="제목을 입력하세요."/></li><li><textarea placeholder="내용을 입력하세요." rows="10"></textarea></li></ul>';
		$(".loan50 .formbtn").before(insert);
	});

	// loanresultWrite.html 상품설명 삭제
	$(document).on("click",".proInfoDelBtn",function(){
		if($(".loan50 .formbtn").prev().hasClass("newInfoWrite")) {
			$(".loan50 .formbtn").prev().remove();
		}
	});

	// loanresultWrite.html 상품선택 radio 체크
	$(".loanBox table tr").click(function(){
		$(".loanresult .productSelect").removeClass("on");
		var i = $(this).index();
		$(("#ck"+i)).prop("checked",true);
		$("input[type=radio]:checked").siblings(".productSelect").addClass("on");
	});

	// company View product Popup Navi
	$(".productPopup .popupNavi > div").click(function(){
		$(".productPopup .popupNavi > div").removeClass("on");
		$(this).addClass("on");
	});	

	// loanresult.html 대출시 주의사항 focus
	$(".loanresult .noticeBtn").click(function(){
		var offset = $(".productNotice .noticeTitle").offset();
		$('html, body').stop().animate({scrollTop : (offset.top)-126}, 400);
	});
	
	// loanresult.html 상세정보 열고 닫기
	$(".loanresult .productInfo .moreBtn").click(function(){
		$(this).parents(".productInfo").toggleClass("on");
	});

	// list.html th부분 눌렀을때 지역별 , 대출분류, 성별 목록 
	$(".list table th").click(function(){
		$(this).siblings("th").children(".lowCate").removeClass("on");
		$(this).children(".lowCate").toggleClass("on");
	});

	// loanresultWrite.html 에서 대출대상 popup 
	$(".loan50 .targetPopup").click(function(){
		$("section.popup").addClass("popupOn");
	});
	$(".popup .titleBox").click(function(){
		$(this).siblings(".row_desc").toggleClass("active");
	});
	$(".popup .descBox").click(function(){		
		
		
		if($(this).parents(".popup").hasClass("target")){

			var text1 = "";
			for(var i=0; i< $(".target input:checked").length; i++){
				var text2 = $(".target input[type=checkbox]:checked").eq(i).parents("label").text();
				text1+= "<span>"+text2+"</span>";
			}
			$(".popup .targetResult").html(text1);

		} else if($(this).parents(".popup").hasClass("docu")) {

			var text3 = "";
			for(var i=0; i< $(".docu input:checked").length; i++){
				var text4 = $(".docu input[type=checkbox]:checked").eq(i).parents("label").text();
				text3 += "<span>"+text4+"</span>";
			}
			$(".popup .docuResult").html(text3);
		}

	});

	$(".popup .cancel").click(function(){
		$(this).parents(".popup").removeClass("popupOn");
	});

	$(".popup .submit").click(function(){
		if($(this).parents(".popup").hasClass("resultTarget")){
			$("#rsTarget").val($(".targetResult").text());
		} else if($(this).parents(".popup").hasClass("target")){
			$("#target").val($(".targetResult").text());
		} else if($(this).parents(".popup").hasClass("docu")) {
			$("#docu").val($(".docuResult").text());
		} 
		$(this).parents(".popup").removeClass("popupOn");
		
	});

	// register.html viewPopup 열기 닫기
	$(".register .popupInfo").click(function(){

		// viewpopup 위치
		if(mobile==true){
			var topValue = $(this).offset();
			$(".viewPopup .wrap").css("top", topValue.top);
		}

		$(".viewPopup").css("height",$(document).height());
		$(".viewPopup").css("display","block");
		$(".viewPopup").addClass("on");
	});

	$(".viewPopup .wrap .btnArea button").click(function(){
		$(this).parents(".viewPopup").css("display","none");
		$(this).parents(".viewPopup").removeClass("on");
	});

	// 대출대상 팝업 180820 배다빈 추가

	$(".showTarget").click(function(){
		$(".popupTarget").addClass("active");

		if($(this).parents(".stepWrap").hasClass("mainRegister")){
			if($(this).parents(".step").hasClass("step2")){
				$(".popupTarget .target_step2").removeClass("active");
				$(".popupTarget .target_step1").addClass("active");
				$(".popupTarget").css("height",$(".popupTarget .wrap").height()+40);
			} else if ($(this).parents(".step").hasClass("step3")){
				$(".popupTarget .target_step1").removeClass("active");
				$(".popupTarget .target_step2").addClass("active");
				$(".popupTarget").css("height",$("html").height());
			}
		} else if ($(this).parents(".register").hasClass("registerHtml")){
			if($(this).parents(".step").hasClass("step1")){
				$(".popupTarget .target_step2").removeClass("active");
				$(".popupTarget .target_step1").addClass("active");
				$(".popupTarget").css("height",$(".popupTarget .wrap").height()+40);
			} else if ($(this).parents(".step").hasClass("step2")){
				$(".popupTarget .target_step1").removeClass("active");
				$(".popupTarget .target_step2").addClass("active");
				$(".popupTarget").css("height",$("html").height());
			}
		}
		
		
		if($(window).width() < 768){
			$(".popupTarget").css("height",$("html").height());
			var topV = $(".popupTarget .popupTitle").offset();
			$(window).scrollTop(topV.top);

		}
		

	});

	$(".popup .popup_step dl dd > div").click(function(){
		if($(this).parents(".popup_step").hasClass("target_step1")){
			$(".popup .target_step1 dl dd > div").removeClass("active");
			$(this).addClass("active");
		} else {
			$(this).toggleClass("active");
		}
		
	});

	$(".popupTarget .submitBtn").click(function(){
		if($(".popup_step.active").hasClass("target_step2")){
			var textBox = "";
			for(var i=0;i<$(".popupTarget .target_step2 dl dd > div.active").length; i++){
				textBox += $(".popupTarget .target_step2 dl dd > div.active").eq(i).text();
				if(i !== ($(".popupTarget .target_step2 dl dd > div.active").length-1)){
					textBox += " , ";
				} else {
					$("#reg-credit").val(textBox);
					$(".popupTarget").removeClass("active");
				}
			}
		} else if($(".popup_step.active").hasClass("target_step1")){
			var textBox2 = "";
			textBox2 += $(".target_step1 dl dd > div.active").text();

			$("#reg-job").val(textBox2);
			$(".popupTarget").removeClass("active");
		}
		
		
	});

	$(".popup .closeBtn").click(function(){
		$(this).parents(".popup").removeClass("active");
	});

	$(window).resize(function(){
		if($(window).width() < 768){
			$(".popupTarget").css("height",$("html").height());
		} else {
			$(".popupTarget").css("height",$(".popupTarget .wrap").height()+40);
		}
	});

	// listView 수정요청에 의한 기능 추가 180822 배다빈 추가
	$(".autoMatch").css("width",(($(".autoMatch > div").length)*100)+"%");

	$(".listView .toggleBox").click(function(){
		$(this).parents(".contentWrap").toggleClass("lock");
	});

	$(".search .toggleBox").click(function(){
		$(this).parents(".contentWrap").toggleClass("lock");
	});

	// productView.html 180824 추가
	$(".proViewBottom .proNavi > ul > li").click(function(){
		$(".proViewBottom .proNavi > ul > li").removeClass("active");
		$(this).addClass("active");
		var navi = $(this).index();
		$(".proViewBottom .proContent .proView > section").removeClass('active');
		$(".proViewBottom .proContent .proView > section").eq(navi).addClass("active");
	});

	// searchMap.html 검색조건 레이아웃 추가 180829 배다빈 추가 

	// 검색 필터 열기 닫기
	$(".search .searchBox .filterBtn").click(function(){
		$(this).siblings(".scrollWrap").toggleClass("active");
		if($(this).siblings(".scrollWrap").hasClass("active")){
			$(this).text("검색필터 닫기");
		} else {
			$(this).text("검색필터 열기");
		}
		if($(this).parents("section").hasClass("searchMap")){
			// $(this).siblings(".scrollWrap").css("height",$(window).height()-300);
		}
		
	});

	// 검색 필터 대출분류
	$(".fStep1 .filterTab .fCate").click(function(){
		$(".fStep1 .filterTab .fCate").removeClass("active");
		$(this).addClass("active");
		var textV = $(this).children("div").text();
		
		var indexValue = $(this).index();
		if(indexValue == 3) {
			$(location).attr("href","register.html");
		} else {
			$(".fStep1 .filterSelect select").removeClass("active");
			$(".fStep1 .filterSelect select").eq(indexValue).addClass("active");
			var textV2 = $(".fStep1 .filterSelect select.active option:selected").text();
		}
		$(".fStep1 .fTitle b.desc").text(textV+" - "+textV2);
		
	});

	$(document).on("change", ".fStep1 .filterSelect select.active", function() {
		var indexValue = $(this).index();
		$(".fStep1 .filterTab .fCate").removeClass("active");
		$(".fStep1 .filterTab .fCate").eq(indexValue).addClass("active");

		var textV = $(".fStep1 .filterTab .fCate.active div").text();
		var textV2 = $(".fStep1 .filterSelect select.active option:selected").text();
		console.log(textV2);
		$(".fStep1 .fTitle b.desc").text(textV+" - "+textV2);
	});
	
	// 검색 필터 금융권
	$(".fStep2 .filterTab li div").click(function(){
		var textV = $(this).text();
		$(".fStep2 .fTitle b.desc").text(textV);
		$(".fStep2 .filterTab li div").removeClass("active");
		$(this).addClass("active");
	});

	// 검색 필터 한도
	var limitV = 0;
	$(".fStep3 .filterTab li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0) {
			limitV += 10;
		} else if(indexV == 1){
			limitV += 100;
		} else if(indexV == 2){
			limitV += 1000;
		} else if(indexV == 3){
			limitV += 10000;
		};
		$("#fLimit").val(limitV);
	});

	// 검색 필터  대출한도 , 찍기 
	$(document).on("change", "input:text[numberOnly]", function() {
		$(this).number(true);
	});

	$(".fStep3 .filterTab li").click(function(){
		$("input:text[numberOnly]").number(true);
		var won = $("input:text[numberOnly]").val();
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
		 return $(".fStep3 .fTitle .desc").text(changeWon);
	});

	// 검색 필터  대출한도 금액 나타내기
	$(document).on("change", "input:text[numberOnly]", function() {
		if($(this).parents(".info")){
			$(this).number(true);
			return;
		}
		
		var won = $("#fLimit").val();
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
		 return $(".fStep3 .fTitle .desc").text(changeWon);

	});

	// 검색 필터 금리
	$(".fStep4 .rangeBox").mouseup(function(){
		var ragV = $(".range-input").val();
		var ragV2 = ragV.split(",",2);
		$(".fStep4 .fTitle .desc").text(ragV2[0]+"%"+" ~ "+ragV2[1]+"%");
	});

	// 검색 필터 대출대상
	$(".fStep5 .filterTab li div").click(function(){
		$(".fStep5 .filterTab li div").removeClass("active");
		$(this).addClass("active");
		var textV = $(this).text();
		$(".fStep5 .fTitle .desc").text(textV);
	});

	// 검색 필터 상환방식
	$(".fStep6 .filterTab li div").click(function(){
		$(".fStep6 .filterTab li div").removeClass("active");
		$(this).addClass("active");
		var textV = $(this).text();
		$(".fStep6 .fTitle .desc").text(textV);
	});

	// 검색 필터 신청방식
	$(".fStep7 .filterTab li div").click(function(){
		$(".fStep7 .filterTab li div").removeClass("active");
		$(this).addClass("active");
		var textV = $(this).text();
		$(".fStep7 .fTitle .desc").text(textV);
	});

	// 검색 필터 신청방식
	$(".fStep8 .filterTab li div").click(function(){
		$(".fStep8 .filterTab li div").removeClass("active");
		$(this).addClass("active");
		var textV = $(this).html();
		$(".fStep8 .fTitle .desc").html(textV);
	});

	// register.html 추가 양식 상환방식 팝업 180830 배다빈 추가
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
		$("#reg-how").val($(".popupHow dl dd > div.active").text());
		$(".popupHow").removeClass("active");
	});

	// register.html 추가 양식 신청방식 팝업 180830 배다빈 추가
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
				$("#reg-join").val(textBox);
				$(".popupJoin").removeClass("active");
			}
		}

		// $("#pro-join").val($(".popupJoin dl dd > div.active").text());
		// $(".popupJoin").removeClass("active");
	});


	// register.html 새 레이아웃 기능 180903 배다빈 추가
	// RYU - 필수처리를 위해 변경 181027
	$(".register .step0 .regCate ul li").click(function(){
		var indexV = $(this).index();

		var item1 = ["주택담보대출", "전세자금대출", "월세자금대출", "부동산PF대출", "경매대출", "NPL대출", "기타"];
		var item2 = ["예적금담보대출", "보험계약대출", "귀금속", "시계", "자동차", "주식", "가상화폐", "명품", "기타"];
		var item3 = ["채무통합", "대출상환", "사업자금", "생활비용", "주거비용", "결혼비용", "의료비용", "장례비용", "여가비용", "학자금", "자동차구매", "기타"];
		var changeItem;


		if(indexV == 1) {
			var textV = $(this).children("div").text();
			$("#reg-cate").val(textV+"대출");
			changeItem = item1;
		} else if (indexV == 2) {
			var textV = $(this).children("div").text();
			$("#reg-cate").val(textV+"대출");
			changeItem = item2;
		} else {
			var textV = $(this).children("div").text();
			$("#reg-cate").val(textV);
			changeItem =  item3;
		}

		$('#regCate').empty();
		var option = '';
		option += '<option value="">'+textV+' 자금용도</option>';
		for (var i=0;i<changeItem.length;i++){
			option += '<option value="'+ changeItem[i] + '">' + changeItem[i] + '</option>';
		}
		$('#regCate').append(option);

	});
/*
	// register.html 연소득 버튼
	var priceV = 0;
	$(".register .reg-price .selectBtn ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0) {
			priceV += 10;
			$("#reg-price").val(priceV);
		} else if (indexV == 1) {
			priceV += 100;
			$("#reg-price").val(priceV);
		} else if (indexV == 2){
			priceV += 1000;
			$("#reg-price").val(priceV);
		} else {
			priceV += 10000;
			$("#reg-price").val(priceV);
		}
		$("#reg-price").number(true);

	});
		// register.html 연소득 초기화 버튼
	$(".register .reg-price .selectBtn ul .reloadBtn").click(function(){
		priceV = 0;
		$("#reg-price").val(priceV);
	});
*/
	// register.html 근무기간 버튼
	var monthV = 0;
	$(".register .reg-month .selectBtn ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0) {
			monthV += 6;
			$("#reg-month").val(monthV);
		} else if (indexV == 1) {
			monthV += 12;
			$("#reg-month").val(monthV);
		} else if (indexV == 2){
			monthV += 36;
			$("#reg-month").val(monthV);
		} else if (indexV == 3){
			monthV += 60;
			$("#reg-month").val(monthV);
		}
	});
		// register.html 근무기간 초기화 버튼
	$(".register .reg-month .selectBtn ul .reloadBtn").click(function(){
		monthV = 0;
		$("#reg-month").val(monthV);
	});

	// register.html 선호금융권 버튼
	$(".register .reg-fina .clickBox ul li").click(function(){
		$(".register .reg-fina .clickBox ul li").removeClass("active");
		$(this).addClass("active");
	});

	// register.html 선호상환방식 버튼
	$(".register .reg-rep .clickBox ul li").click(function(){
		$(".register .reg-rep .clickBox ul li").removeClass("active");
		$(this).addClass("active");
	});

	// register.html 선호신청방식 버튼
	$(".register .reg-req .clickBox ul li").click(function(){
		$(".register .reg-req .clickBox ul li").removeClass("active");
		$(this).addClass("active");
	});

	// register.html 직업 버튼
	$(".register .reg-job .clickBox ul li").click(function(){
		$(".register .reg-job .clickBox ul li").removeClass("active");
		$(this).addClass("active");
	});

	// register.html 급여수령방식 버튼
	$(".register .reg-rec .clickBox ul li").click(function(){
		$(".register .reg-rec .clickBox ul li").removeClass("active");
		$(this).addClass("active");
	});


/*
	// register.html 대출신청액 버튼
	var moneyV = 0;
	$(".register .reg-money .selectBtn ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0) {
			moneyV += 10;
			$("#reg-money").val(moneyV);
		} else if (indexV == 1) {
			moneyV += 100;
			$("#reg-money").val(moneyV);
		} else if (indexV == 2){
			moneyV += 1000;
			$("#reg-money").val(moneyV);
		} else {
			moneyV += 10000;
			$("#reg-money").val(moneyV);
		}
		$("#reg-money").number(true);
	});
		// register.html 대출신청액 초기화 버튼
	$(".register .reg-money .selectBtn ul .reloadBtn").click(function(){
		moneyV = 0;
		$("#reg-money").val(moneyV);
	});
*/


	// register.html 대출신청기간
	var regMonthV = 0;
	$(".register .reg-regMonth .selectBtn ul li").click(function(){
		console.log("hi");
		var indexV = $(this).index();
		if(indexV == 0) {
			regMonthV += 6;
			$("#reg-regMonth").val(regMonthV);
		} else if (indexV == 1) {
			regMonthV += 12;
			$("#reg-regMonth").val(regMonthV);
		} else if (indexV == 2){
			regMonthV += 36;
			$("#reg-regMonth").val(regMonthV);
		} else if (indexV == 3){
			regMonthV += 60;
			$("#reg-regMonth").val(regMonthV);
		};
	});
		// register.html 대출신청기간 초기화 버튼
	$(".register .reg-regMonth .selectBtn ul .reloadBtn").click(function(){
		regMonthV = 0;
		$("#reg-regMonth").val(regMonthV);
	});


	// 검색 필터 금리
	$(".register .row .rangeBox").mouseup(function(){
		var ragV = $(".range-input").val();
		var ragV2 = ragV.split(",",2);
		$(".register .row .rangeValue").text(ragV2[0]+"%"+" ~ "+ragV2[1]+"%");
		$("#lo_rate1").val(ragV2[0]);
		$("#lo_rate2").val(ragV2[1]);
	});


	function getLocation() {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(showPosition);
	    } else { 
	        x.innerHTML = "Geolocation is not supported by this browser.";
	    }
	}


	// listView_customer.html listTab 탭기능 
	$(".listView .listTab ul li").click(function(){
		$(".listView .listTab ul li").removeClass("active");
		$(this).addClass("active");
		var indexV = $(this).index();
		$(".listView .listTabBox section").removeClass("active");
		$(".listView .listTabBox section").eq(indexV).addClass("active");
	});

	// listView.html , listView_customer.html 상담예약 버튼
	$(".goReserve").click(function(){
		var offsetV = $(this).offset();
		$(".popupReserve").css("height",$(document).height());
		$(".popupReserve .wrap").css("top",offsetV.top);
		$(".popupReserve").addClass("active");
		$(".popupReserve .reserve1").focus();
	});

	$(".popupReserve .reserveXbtn").click(function(){
		$(".popupReserve").removeClass("active");
	});

	$(document).on("change", ".popupReserve #reserveAll", function() {
		if($(this).prop("checked")){
			$(".popupReserve .reserve1 .reserveFirst").prop("selected",true);
			$(".popupReserve .reserve2 .reserveLast").prop("selected",true);
		}
	});
/*
	// index.html , register.html regCate 기능
	$(".register .regCate ul li").click(function(){
		var indexV = $(this).index();
		$(".register .regCate select").removeClass("active");
		$(".register .regCate select").eq(indexV).addClass("active");
	});
*/
	// calcu.html 신규 추가 180913 배다빈 추가
	$(".calcuTab > div").click(function(){
		$(".calcuTab > div").removeClass('active');
		$(this).addClass("active");
		var indexV = $(this).index();
		$(".calcuTabBox > section").removeClass('active');
		$(".calcuTabBox > section").eq(indexV).addClass("active");
	});

	$(".ltvCate > div").click(function(){
		$(".ltvCate > div").removeClass('active');
		$(this).addClass("active");
		if($(this).index() == 1) {
			$(".ltvCate2").addClass("active");
			$(".ltvCate3").addClass("active");
		} else {
			$(".ltvCate2").removeClass("active");
			$(".ltvCate3").removeClass("active");
		}
	});

	$(".ltvCate2 > div").click(function(){
		$(".ltvCate2 > div").removeClass("active");
		$(this).addClass('active');
	});

	// calcu.html 대출계산기 기능 180914 배다빈 추가
	$(".calcu .ctCate ul li").click(function(){
		var indexV = $(this).index();
		$(".calcu .ctCate2 select").removeClass('active');
		$(".calcu .ctCate2 select").eq(indexV).addClass("active");
	});

	// calcu.html 대출계산기 첫번째 탭 대출 계산기 기능
	$(".calcu .clickBtn li").click(function(){
		$(this).siblings().removeClass("active");
		$(this).addClass('active');
	});

	var ct1_1 = 0;
	$(".calcu .ct1_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct1_1 += 10;
			$("#ct1_1").val(ct1_1);
		} else if ( indexV == 1) {
			ct1_1 += 100;
			$("#ct1_1").val(ct1_1);
		} else if ( indexV == 2) {
			ct1_1 += 1000;
			$("#ct1_1").val(ct1_1);
		} else if ( indexV == 3) {
			ct1_1 += 10000;
			$("#ct1_1").val(ct1_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct1_1 = 0;
			$("#ct1_1").val(ct1_1);
		}
		$("#ct1_1").number(true);
	});

	var ct1_2 = 0;
	$(".calcu .ct1_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct1_2 += 3;
			$("#ct1_2").val(ct1_2);
		} else if ( indexV == 1) {
			ct1_2 += 4;
			$("#ct1_2").val(ct1_2);
		} else if ( indexV == 2) {
			ct1_2 += 5;
			$("#ct1_2").val(ct1_2);
		} else if ( indexV == 3) {
			ct1_2 += 6;
			$("#ct1_2").val(ct1_2);
		}  else if ( indexV == 4) {
			ct1_2 += 7;
			$("#ct1_2").val(ct1_2);
		} else if ( indexV == 5) {
			ct1_2 += 8;
			$("#ct1_2").val(ct1_2);
		} else if ( indexV == 6) {
			ct1_2 += 10;
			$("#ct1_2").val(ct1_2);
		} else if ( indexV == 7) {
			ct1_2 += 20;
			$("#ct1_2").val(ct1_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct1_2 = 0;
			$("#ct1_2").val(ct1_2);
		}
		$("#ct1_2").number(true);
	});

	var ct1_3 = 0;
	$(".calcu .ct1_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct1_3 += 12;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 1) {
			ct1_3 += 24;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 2) {
			ct1_3 += 36;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 3) {
			ct1_3 += 48;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 4) {
			ct1_3 += 60;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 5) {
			ct1_3 += 120;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 6) {
			ct1_3 += 180;
			$("#ct1_3").val(ct1_3);
		} else if ( indexV == 7) {
			ct1_3 += 240;
			$("#ct1_3").val(ct1_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct1_3 = 0;
			$("#ct1_3").val(ct1_3);
		}
		$("#ct1_3").number(true);
	});

	var ct1_4 = 0;
	$(".calcu .ct1_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct1_4 += 0;
			$("#ct1_4").val(ct1_4);
		} else if ( indexV == 1) {
			ct1_4 += 12;
			$("#ct1_4").val(ct1_4);
		} else if ( indexV == 2) {
			ct1_4 += 24;
			$("#ct1_4").val(ct1_4);
		} else if ( indexV == 3) {
			ct1_4 += 36;
			$("#ct1_4").val(ct1_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct1_4 = 0;
			$("#ct1_4").val(ct1_4);
		}
		$("#ct1_4").number(true);
	});

	// calcu.html 대출계산기 두번째 탭 DSR 기능
	var ct2_1 = 0;
	$(".calcu .ct2_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct2_1 += 10;
			$("#ct2_1").val(ct2_1);
		} else if ( indexV == 1) {
			ct2_1 += 100;
			$("#ct2_1").val(ct2_1);
		} else if ( indexV == 2) {
			ct2_1 += 1000;
			$("#ct2_1").val(ct2_1);
		} else if ( indexV == 3) {
			ct2_1 += 10000;
			$("#ct2_1").val(ct2_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct2_1 = 0;
			$("#ct2_1").val(ct2_1);
		}
		$("#ct2_1").number(true);
	});

	var ct2_2 = 0;
	$(".calcu .ct2_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct2_2 += 10;
			$("#ct2_2").val(ct2_2);
		} else if ( indexV == 1) {
			ct2_2 += 100;
			$("#ct2_2").val(ct2_2);
		} else if ( indexV == 2) {
			ct2_2 += 1000;
			$("#ct2_2").val(ct2_2);
		} else if ( indexV == 3) {
			ct2_2 += 10000;
			$("#ct2_2").val(ct2_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct2_2 = 0;
			$("#ct2_2").val(ct2_2);
		}
		$("#ct2_2").number(true);
	});

	var ct2_3 = 0;
	$(".calcu .ct2_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct2_3 += 3;
			$("#ct2_3").val(ct2_3);
		} else if ( indexV == 1) {
			ct2_3 += 4;
			$("#ct2_3").val(ct2_3);
		} else if ( indexV == 2) {
			ct2_3 += 5;
			$("#ct2_3").val(ct2_3);
		} else if ( indexV == 3) {
			ct2_3 += 6;
			$("#ct2_3").val(ct2_3);
		}  else if ( indexV == 4) {
			ct2_3 += 7;
			$("#ct2_3").val(ct2_3);
		} else if ( indexV == 5) {
			ct2_3 += 8;
			$("#ct2_3").val(ct2_3);
		} else if ( indexV == 6) {
			ct2_3 += 10;
			$("#ct2_3").val(ct2_3);
		} else if ( indexV == 7) {
			ct2_3 += 20;
			$("#ct2_3").val(ct2_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct2_3 = 0;
			$("#ct2_3").val(ct2_3);
		}
		$("#ct2_3").number(true);
	});

	var ct2_4 = 0;
	$(".calcu .ct2_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct2_4 += 12;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 1) {
			ct2_4 += 24;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 2) {
			ct2_4 += 36;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 3) {
			ct2_4 += 48;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 4) {
			ct2_4 += 60;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 5) {
			ct2_4 += 120;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 6) {
			ct2_4 += 180;
			$("#ct2_4").val(ct2_4);
		} else if ( indexV == 7) {
			ct2_4 += 240;
			$("#ct2_4").val(ct2_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct2_4 = 0;
			$("#ct2_4").val(ct2_4);
		}
		$("#ct2_4").number(true);
	});

	var ct2_5 = 0;
	$(".calcu .ct2_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct2_5 += 0;
			$("#ct2_5").val(ct2_5);
		} else if ( indexV == 1) {
			ct2_5 += 12;
			$("#ct2_5").val(ct2_5);
		} else if ( indexV == 2) {
			ct2_5 += 24;
			$("#ct2_5").val(ct2_5);
		} else if ( indexV == 3) {
			ct2_5 += 36;
			$("#ct2_5").val(ct2_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct2_5 = 0;
			$("#ct2_5").val(ct2_5);
		}
		$("#ct2_5").number(true);
	});

	// calcu.html 대출계산기 세번째 탭 신DTI 기능
	var ct3_1 = 0;
	$(".calcu .ct3_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct3_1 += 10;
			$("#ct3_1").val(ct3_1);
		} else if ( indexV == 1) {
			ct3_1 += 100;
			$("#ct3_1").val(ct3_1);
		} else if ( indexV == 2) {
			ct3_1 += 1000;
			$("#ct3_1").val(ct3_1);
		} else if ( indexV == 3) {
			ct3_1 += 10000;
			$("#ct3_1").val(ct3_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct3_1 = 0;
			$("#ct3_1").val(ct3_1);
		}
		$("#ct3_1").number(true);
	});

	var ct3_2 = 0;
	$(".calcu .ct3_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct3_2 += 10;
			$("#ct3_2").val(ct3_2);
		} else if ( indexV == 1) {
			ct3_2 += 100;
			$("#ct3_2").val(ct3_2);
		} else if ( indexV == 2) {
			ct3_2 += 1000;
			$("#ct3_2").val(ct3_2);
		} else if ( indexV == 3) {
			ct3_2 += 10000;
			$("#ct3_2").val(ct3_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct3_2 = 0;
			$("#ct3_2").val(ct3_2);
		}
		$("#ct3_2").number(true);
	});

	var ct3_3 = 0;
	$(".calcu .ct3_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct3_3 += 3;
			$("#ct3_3").val(ct3_3);
		} else if ( indexV == 1) {
			ct3_3 += 4;
			$("#ct3_3").val(ct3_3);
		} else if ( indexV == 2) {
			ct3_3 += 5;
			$("#ct3_3").val(ct3_3);
		} else if ( indexV == 3) {
			ct3_3 += 6;
			$("#ct3_3").val(ct3_3);
		}  else if ( indexV == 4) {
			ct3_3 += 7;
			$("#ct3_3").val(ct3_3);
		} else if ( indexV == 5) {
			ct3_3 += 8;
			$("#ct3_3").val(ct3_3);
		} else if ( indexV == 6) {
			ct3_3 += 10;
			$("#ct3_3").val(ct3_3);
		} else if ( indexV == 7) {
			ct3_3 += 20;
			$("#ct3_3").val(ct3_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct3_3 = 0;
			$("#ct3_3").val(ct3_3);
		}
		$("#ct3_3").number(true);
	});

	var ct3_4 = 0;
	$(".calcu .ct3_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct3_4 += 12;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 1) {
			ct3_4 += 24;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 2) {
			ct3_4 += 36;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 3) {
			ct3_4 += 48;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 4) {
			ct3_4 += 60;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 5) {
			ct3_4 += 120;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 6) {
			ct3_4 += 180;
			$("#ct3_4").val(ct3_4);
		} else if ( indexV == 7) {
			ct3_4 += 240;
			$("#ct3_4").val(ct3_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct3_4 = 0;
			$("#ct3_4").val(ct3_4);
		}
		$("#ct3_4").number(true);
	});

	var ct3_5 = 0;
	$(".calcu .ct3_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct3_5 += 0;
			$("#ct3_5").val(ct3_5);
		} else if ( indexV == 1) {
			ct3_5 += 12;
			$("#ct3_5").val(ct3_5);
		} else if ( indexV == 2) {
			ct3_5 += 24;
			$("#ct3_5").val(ct3_5);
		} else if ( indexV == 3) {
			ct3_5 += 36;
			$("#ct3_5").val(ct3_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct3_5 = 0;
			$("#ct3_5").val(ct3_5);
		}
		$("#ct3_5").number(true);
	});

	// calcu.html 대출계산기 네번째 탭 LTV 기능
	var ct4_1 = 0;
	$(".calcu .ct4_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct4_1 += 10;
			$("#ct4_1").val(ct4_1);
		} else if ( indexV == 1) {
			ct4_1 += 100;
			$("#ct4_1").val(ct4_1);
		} else if ( indexV == 2) {
			ct4_1 += 1000;
			$("#ct4_1").val(ct4_1);
		} else if ( indexV == 3) {
			ct4_1 += 10000;
			$("#ct4_1").val(ct4_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct4_1 = 0;
			$("#ct4_1").val(ct4_1);
		}
		$("#ct4_1").number(true);
	});

	var ct4_2 = 0;
	$(".calcu .ct4_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct4_2 += 10;
			$("#ct4_2").val(ct4_2);
		} else if ( indexV == 1) {
			ct4_2 += 100;
			$("#ct4_2").val(ct4_2);
		} else if ( indexV == 2) {
			ct4_2 += 1000;
			$("#ct4_2").val(ct4_2);
		} else if ( indexV == 3) {
			ct4_2 += 10000;
			$("#ct4_2").val(ct4_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct4_2 = 0;
			$("#ct4_2").val(ct4_2);
		}
		$("#ct4_2").number(true);
	});

	var ct4_3 = 0;
	$(".calcu .ct4_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct4_3 += 10;
			$("#ct4_3").val(ct4_3);
		} else if ( indexV == 1) {
			ct4_3 += 100;
			$("#ct4_3").val(ct4_3);
		} else if ( indexV == 2) {
			ct4_3 += 1000;
			$("#ct4_3").val(ct4_3);
		} else if ( indexV == 3) {
			ct4_3 += 10000;
			$("#ct4_3").val(ct4_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct4_3 = 0;
			$("#ct4_3").val(ct4_3);
		}
		$("#ct4_3").number(true);
	});

	var ct4_4 = 0;
	$(".calcu .ct4_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct4_4 += 10;
			$("#ct4_4").val(ct4_4);
		} else if ( indexV == 1) {
			ct4_4 += 100;
			$("#ct4_4").val(ct4_4);
		} else if ( indexV == 2) {
			ct4_4 += 1000;
			$("#ct4_4").val(ct4_4);
		} else if ( indexV == 3) {
			ct4_4 += 10000;
			$("#ct4_4").val(ct4_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct4_4 = 0;
			$("#ct4_4").val(ct4_4);
		}
		$("#ct4_4").number(true);
	});

	var ct4_5 = 0;
	$(".calcu .ct4_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct4_5 += 0;
			$("#ct4_5").val(ct4_5);
		} else if ( indexV == 1) {
			ct4_5 += 1;
			$("#ct4_5").val(ct4_5);
		} else if ( indexV == 2) {
			ct4_5 += 2;
			$("#ct4_5").val(ct4_5);
		} else if ( indexV == 3) {
			ct4_5 += 3;
			$("#ct4_5").val(ct4_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct4_5 = 0;
			$("#ct4_5").val(ct4_5);
		}
		$("#ct4_5").number(true);
	});

	// calcu.html 대출계산기 다섯번째 탭 DSR 기능
	var ct5_1 = 0;
	$(".calcu .ct5_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct5_1 += 10;
			$("#ct5_1").val(ct5_1);
		} else if ( indexV == 1) {
			ct5_1 += 100;
			$("#ct5_1").val(ct5_1);
		} else if ( indexV == 2) {
			ct5_1 += 1000;
			$("#ct5_1").val(ct5_1);
		} else if ( indexV == 3) {
			ct5_1 += 10000;
			$("#ct5_1").val(ct5_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct5_1 = 0;
			$("#ct5_1").val(ct5_1);
		}
		$("#ct5_1").number(true);
	});

	var ct5_2 = 0;
	$(".calcu .ct5_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct5_2 += 10;
			$("#ct5_2").val(ct5_2);
		} else if ( indexV == 1) {
			ct5_2 += 100;
			$("#ct5_2").val(ct5_2);
		} else if ( indexV == 2) {
			ct5_2 += 1000;
			$("#ct5_2").val(ct5_2);
		} else if ( indexV == 3) {
			ct5_2 += 10000;
			$("#ct5_2").val(ct5_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct5_2 = 0;
			$("#ct5_2").val(ct5_2);
		}
		$("#ct5_2").number(true);
	});

	var ct5_3 = 0;
	$(".calcu .ct5_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct5_3 += 3;
			$("#ct5_3").val(ct5_3);
		} else if ( indexV == 1) {
			ct5_3 += 4;
			$("#ct5_3").val(ct5_3);
		} else if ( indexV == 2) {
			ct5_3 += 5;
			$("#ct5_3").val(ct5_3);
		} else if ( indexV == 3) {
			ct5_3 += 6;
			$("#ct5_3").val(ct5_3);
		}  else if ( indexV == 4) {
			ct5_3 += 7;
			$("#ct5_3").val(ct5_3);
		} else if ( indexV == 5) {
			ct5_3 += 8;
			$("#ct5_3").val(ct5_3);
		} else if ( indexV == 6) {
			ct5_3 += 10;
			$("#ct5_3").val(ct5_3);
		} else if ( indexV == 7) {
			ct5_3 += 20;
			$("#ct5_3").val(ct5_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct5_3 = 0;
			$("#ct5_3").val(ct5_3);
		}
		$("#ct5_3").number(true);
	});

	var ct5_4 = 0;
	$(".calcu .ct5_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct5_4 += 12;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 1) {
			ct5_4 += 24;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 2) {
			ct5_4 += 36;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 3) {
			ct5_4 += 48;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 4) {
			ct5_4 += 60;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 5) {
			ct5_4 += 120;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 6) {
			ct5_4 += 180;
			$("#ct5_4").val(ct5_4);
		} else if ( indexV == 7) {
			ct5_4 += 240;
			$("#ct5_4").val(ct5_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct5_4 = 0;
			$("#ct5_4").val(ct5_4);
		}
		$("#ct5_4").number(true);
	});

	var ct5_5 = 0;
	$(".calcu .ct5_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct5_5 += 0;
			$("#ct5_5").val(ct5_5);
		} else if ( indexV == 1) {
			ct5_5 += 12;
			$("#ct5_5").val(ct5_5);
		} else if ( indexV == 2) {
			ct5_5 += 24;
			$("#ct5_5").val(ct5_5);
		} else if ( indexV == 3) {
			ct5_5 += 36;
			$("#ct5_5").val(ct5_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct5_5 = 0;
			$("#ct5_5").val(ct5_5);
		}
		$("#ct5_5").number(true);
	});

	// calcu.html 대출계산기 여섯번째 탭 DSR 기능
	var ct6_1 = 0;
	$(".calcu .ct6_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct6_1 += 10;
			$("#ct6_1").val(ct6_1);
		} else if ( indexV == 1) {
			ct6_1 += 100;
			$("#ct6_1").val(ct6_1);
		} else if ( indexV == 2) {
			ct6_1 += 1000;
			$("#ct6_1").val(ct6_1);
		} else if ( indexV == 3) {
			ct6_1 += 10000;
			$("#ct6_1").val(ct6_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct6_1 = 0;
			$("#ct6_1").val(ct6_1);
		}
		$("#ct6_1").number(true);
	});

	var ct6_2 = 0;
	$(".calcu .ct6_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct6_2 += 10;
			$("#ct6_2").val(ct6_2);
		} else if ( indexV == 1) {
			ct6_2 += 100;
			$("#ct6_2").val(ct6_2);
		} else if ( indexV == 2) {
			ct6_2 += 1000;
			$("#ct6_2").val(ct6_2);
		} else if ( indexV == 3) {
			ct6_2 += 10000;
			$("#ct6_2").val(ct6_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct6_2 = 0;
			$("#ct6_2").val(ct6_2);
		}
		$("#ct6_2").number(true);
	});

	var ct6_3 = 0;
	$(".calcu .ct6_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct6_3 += 3;
			$("#ct6_3").val(ct6_3);
		} else if ( indexV == 1) {
			ct6_3 += 4;
			$("#ct6_3").val(ct6_3);
		} else if ( indexV == 2) {
			ct6_3 += 5;
			$("#ct6_3").val(ct6_3);
		} else if ( indexV == 3) {
			ct6_3 += 6;
			$("#ct6_3").val(ct6_3);
		}  else if ( indexV == 4) {
			ct6_3 += 7;
			$("#ct6_3").val(ct6_3);
		} else if ( indexV == 5) {
			ct6_3 += 8;
			$("#ct6_3").val(ct6_3);
		} else if ( indexV == 6) {
			ct6_3 += 10;
			$("#ct6_3").val(ct6_3);
		} else if ( indexV == 7) {
			ct6_3 += 20;
			$("#ct6_3").val(ct6_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct6_3 = 0;
			$("#ct6_3").val(ct6_3);
		}
		$("#ct6_3").number(true);
	});

	var ct6_4 = 0;
	$(".calcu .ct6_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct6_4 += 12;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 1) {
			ct6_4 += 24;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 2) {
			ct6_4 += 36;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 3) {
			ct6_4 += 48;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 4) {
			ct6_4 += 60;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 5) {
			ct6_4 += 120;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 6) {
			ct6_4 += 180;
			$("#ct6_4").val(ct6_4);
		} else if ( indexV == 7) {
			ct6_4 += 240;
			$("#ct6_4").val(ct6_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct6_4 = 0;
			$("#ct6_4").val(ct6_4);
		}
		$("#ct6_4").number(true);
	});

	var ct6_5 = 0;
	$(".calcu .ct6_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct6_5 += 0;
			$("#ct6_5").val(ct6_5);
		} else if ( indexV == 1) {
			ct6_5 += 12;
			$("#ct6_5").val(ct6_5);
		} else if ( indexV == 2) {
			ct6_5 += 24;
			$("#ct6_5").val(ct6_5);
		} else if ( indexV == 3) {
			ct6_5 += 36;
			$("#ct6_5").val(ct6_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct6_5 = 0;
			$("#ct6_5").val(ct6_5);
		}
		$("#ct6_5").number(true);
	});

	// calcu.html 대출계산기 일곱번째 탭 DSR 기능
	var ct7_1 = 0;
	$(".calcu .ct7_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct7_1 += 10;
			$("#ct7_1").val(ct7_1);
		} else if ( indexV == 1) {
			ct7_1 += 100;
			$("#ct7_1").val(ct7_1);
		} else if ( indexV == 2) {
			ct7_1 += 1000;
			$("#ct7_1").val(ct7_1);
		} else if ( indexV == 3) {
			ct7_1 += 10000;
			$("#ct7_1").val(ct7_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct7_1 = 0;
			$("#ct7_1").val(ct7_1);
		}
		$("#ct7_1").number(true);
	});

	var ct7_2 = 0;
	$(".calcu .ct7_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct7_2 += 10;
			$("#ct7_2").val(ct7_2);
		} else if ( indexV == 1) {
			ct7_2 += 100;
			$("#ct7_2").val(ct7_2);
		} else if ( indexV == 2) {
			ct7_2 += 1000;
			$("#ct7_2").val(ct7_2);
		} else if ( indexV == 3) {
			ct7_2 += 10000;
			$("#ct7_2").val(ct7_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct7_2 = 0;
			$("#ct7_2").val(ct7_2);
		}
		$("#ct7_2").number(true);
	});

	var ct7_3 = 0;
	$(".calcu .ct7_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct7_3 += 3;
			$("#ct7_3").val(ct7_3);
		} else if ( indexV == 1) {
			ct7_3 += 4;
			$("#ct7_3").val(ct7_3);
		} else if ( indexV == 2) {
			ct7_3 += 5;
			$("#ct7_3").val(ct7_3);
		} else if ( indexV == 3) {
			ct7_3 += 6;
			$("#ct7_3").val(ct7_3);
		}  else if ( indexV == 4) {
			ct7_3 += 7;
			$("#ct7_3").val(ct7_3);
		} else if ( indexV == 5) {
			ct7_3 += 8;
			$("#ct7_3").val(ct7_3);
		} else if ( indexV == 6) {
			ct7_3 += 10;
			$("#ct7_3").val(ct7_3);
		} else if ( indexV == 7) {
			ct7_3 += 20;
			$("#ct7_3").val(ct7_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct7_3 = 0;
			$("#ct7_3").val(ct7_3);
		}
		$("#ct7_3").number(true);
	});

	var ct7_4 = 0;
	$(".calcu .ct7_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct7_4 += 12;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 1) {
			ct7_4 += 24;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 2) {
			ct7_4 += 36;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 3) {
			ct7_4 += 48;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 4) {
			ct7_4 += 60;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 5) {
			ct7_4 += 120;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 6) {
			ct7_4 += 180;
			$("#ct7_4").val(ct7_4);
		} else if ( indexV == 7) {
			ct7_4 += 240;
			$("#ct7_4").val(ct7_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct7_4 = 0;
			$("#ct7_4").val(ct7_4);
		}
		$("#ct7_4").number(true);
	});

	var ct7_5 = 0;
	$(".calcu .ct7_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct7_5 += 0;
			$("#ct7_5").val(ct7_5);
		} else if ( indexV == 1) {
			ct7_5 += 12;
			$("#ct7_5").val(ct7_5);
		} else if ( indexV == 2) {
			ct7_5 += 24;
			$("#ct7_5").val(ct7_5);
		} else if ( indexV == 3) {
			ct7_5 += 36;
			$("#ct7_5").val(ct7_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct7_5 = 0;
			$("#ct7_5").val(ct7_5);
		}
		$("#ct7_5").number(true);
	});

	// calcu.html 대출계산기 여덟번째 탭 DSR 기능
	var ct8_1 = 0;
	$(".calcu .ct8_1 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct8_1 += 10;
			$("#ct8_1").val(ct8_1);
		} else if ( indexV == 1) {
			ct8_1 += 100;
			$("#ct8_1").val(ct8_1);
		} else if ( indexV == 2) {
			ct8_1 += 1000;
			$("#ct8_1").val(ct8_1);
		} else if ( indexV == 3) {
			ct8_1 += 10000;
			$("#ct8_1").val(ct8_1);
		}

		if($(this).hasClass("reloadBtn")){
			ct8_1 = 0;
			$("#ct8_1").val(ct8_1);
		}
		$("#ct8_1").number(true);
	});

	var ct8_2 = 0;
	$(".calcu .ct8_2 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct8_2 += 10;
			$("#ct8_2").val(ct8_2);
		} else if ( indexV == 1) {
			ct8_2 += 100;
			$("#ct8_2").val(ct8_2);
		} else if ( indexV == 2) {
			ct8_2 += 1000;
			$("#ct8_2").val(ct8_2);
		} else if ( indexV == 3) {
			ct8_2 += 10000;
			$("#ct8_2").val(ct8_2);
		}

		if($(this).hasClass("reloadBtn")){
			ct8_2 = 0;
			$("#ct8_2").val(ct8_2);
		}
		$("#ct8_2").number(true);
	});

	var ct8_3 = 0;
	$(".calcu .ct8_3 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct8_3 += 3;
			$("#ct8_3").val(ct8_3);
		} else if ( indexV == 1) {
			ct8_3 += 4;
			$("#ct8_3").val(ct8_3);
		} else if ( indexV == 2) {
			ct8_3 += 5;
			$("#ct8_3").val(ct8_3);
		} else if ( indexV == 3) {
			ct8_3 += 6;
			$("#ct8_3").val(ct8_3);
		}  else if ( indexV == 4) {
			ct8_3 += 7;
			$("#ct8_3").val(ct8_3);
		} else if ( indexV == 5) {
			ct8_3 += 8;
			$("#ct8_3").val(ct8_3);
		} else if ( indexV == 6) {
			ct8_3 += 10;
			$("#ct8_3").val(ct8_3);
		} else if ( indexV == 7) {
			ct8_3 += 20;
			$("#ct8_3").val(ct8_3);
		}

		if($(this).hasClass("reloadBtn")){
			ct8_3 = 0;
			$("#ct8_3").val(ct8_3);
		}
		$("#ct8_3").number(true);
	});

	var ct8_4 = 0;
	$(".calcu .ct8_4 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct8_4 += 12;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 1) {
			ct8_4 += 24;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 2) {
			ct8_4 += 36;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 3) {
			ct8_4 += 48;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 4) {
			ct8_4 += 60;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 5) {
			ct8_4 += 120;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 6) {
			ct8_4 += 180;
			$("#ct8_4").val(ct8_4);
		} else if ( indexV == 7) {
			ct8_4 += 240;
			$("#ct8_4").val(ct8_4);
		}

		if($(this).hasClass("reloadBtn")){
			ct8_4 = 0;
			$("#ct8_4").val(ct8_4);
		}
		$("#ct8_4").number(true);
	});

	var ct8_5 = 0;
	$(".calcu .ct8_5 ul li").click(function(){
		var indexV = $(this).index();
		if(indexV == 0 ){
			ct8_5 += 0;
			$("#ct8_5").val(ct8_5);
		} else if ( indexV == 1) {
			ct8_5 += 12;
			$("#ct8_5").val(ct8_5);
		} else if ( indexV == 2) {
			ct8_5 += 24;
			$("#ct8_5").val(ct8_5);
		} else if ( indexV == 3) {
			ct8_5 += 36;
			$("#ct8_5").val(ct8_5);
		}

		if($(this).hasClass("reloadBtn")){
			ct8_5 = 0;
			$("#ct8_5").val(ct8_5);
		}
		$("#ct8_5").number(true);
	});

	$(".calcuTabBox .ctProductTab > div").click(function(){
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
	});

	$(".calcuTabBox .dtiCate1 ul li").click(function(){
		var indexV = $(this).index();
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
		$(".calcuTabBox .dtiCate2 > ul").removeClass("active");
		$(".calcuTabBox .dtiCate2 > ul").eq(indexV).addClass('active');

	});

	// faq tab 기능 추가 180917 배다빈 추가
	$(".list .faqTab div").click(function(){
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
		var indexV = $(this).index();
		$(".faqBox > ul").removeClass("active");
		$(".faqBox > ul").eq(indexV).addClass("active");
	});

	// 번호입력시 자동 다음 input으로 이동 180918 배다빈 추가
	$(".reservePhone input").keyup(function(){
		if($(this).val().length == $(this).attr("maxlength")){
			if($(this).hasClass("rpNumber1")){
				$(".reservePhone .rpNumber2").focus();
			} else if ($(this).hasClass("rpNumber2")){
				$(".reservePhone .rpNumber3").focus();
			} else if ($(this).hasClass("rpNumber3")){
				$(".reservePhone .reserveSave").focus();
			}
		}
	});

	$(".mbPhone input").keyup(function(){
		if($(this).val().length == $(this).attr("maxlength")){
			if($(this).hasClass("mbNumber1")){
				$(".mbPhone .mbNumber2").focus();
			} else if ($(this).hasClass("mbNumber2")){
				$(".mbPhone .mbNumber3").focus();
			} else if ($(this).hasClass("mbNumber3")){
				$(".mbPhone .mbButton button").focus();
			}
		}
	});

	$(".pcPhone input").keyup(function(){
		if($(this).val().length == $(this).attr("maxlength")){
			if($(this).hasClass("pcNumber1")){
				$(".pcPhone .pcNumber2").focus();
			} else if ($(this).hasClass("pcNumber2")){
				$(".pcPhone .pcNumber3").focus();
			} else if ($(this).hasClass("pcNumber3")){
				$(".pcPhone button").focus();
			}
		}
	});

	// register input 다음항목 포커스 180919 배다빈 추가
	$(".register #reg-phone").keyup(function(){
		if($(this).val().length == $(this).attr("maxlength")){
			$(".chkPhone").focus();
		}
	});

	// 툴팁 모바일에서 닫기
	$(".register .row label .tip .tipBtn").click(function(){
		if($(window).width() < 768) {
			if($(this).children(".tipDesc").hasClass("hidden")){
				$(this).children(".tipDesc").removeClass("hidden");
				$(this).children(".tipDesc").addClass('active');
			} else if($(this).children(".tipDesc").hasClass('active')){
				$(this).children(".tipDesc").removeClass("active");
				$(this).children(".tipDesc").addClass('hidden');
			}
		}
	});
	// $(".register .row label .tip .tipBtn .tipDesc").click(function(){
	// 	if($(window).width() < 768 ) {
	// 		if($(this).hasClass("hidden")){
	// 			$(this).removeClass("hidden");
	// 			$(this).addClass('active');
	// 		} else if($(this).hasClass('active')){
	// 			$(this).removeClass("active");
	// 			$(this).addClass('hidden');
	// 		}
			
	// 	}
	// });

	// 기대출정보 금액 , 찍기
	$(".register .infoWrap input.infoSum").keyup(function(){
		$(this).number(true);
	});



	$(".register .autoDotted").keydown(function(){
		
	});

	
});	

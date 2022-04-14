
		$(document).ready(function(){
			loadMenu();
			loadSales();
		    return false;
		})
		.on('click','#selMenu li',function(){
		    let str1 = $('p:nth-child(1)',this).text();
		    let str2 = $('p:nth-child(2)',this).text();
		    let ar = str2.split('원');
		    $('#menuname').val(str1);
		    
		    // 수량 금액 값 change
		    $('#count').val(1);
		    $('#price').val(ar[0]);
	
		    $('#count').change(function(){
		        num = $('#count').val();
		        $('#price').val(ar[0] * num);
		    });
		})
		// 메뉴 목록 비우기
		.on('click', '#btnReset', function(){
		    $('#menuname, #count, #price').val('');
		})
	
		// 주문 목록 추가
		.on('click', '#btnAdd', function(){
 		    $('#selOrder').append('<li class="fixed"><p>'+$('#menuname').val()+'</p><p>x' + $('#count').val() +'</p><p>'+$('#price').val() + '원</p></li>');
		    // 총액 계산
		    old = Number($('#total').val());
		    $('#total').val(old + Number($('#price').val()));
		    // 메뉴목록 속 input 리셋
		    $('#btnReset').trigger('click');
		})
	
		// 판매내역 추가
		.on('click', '#btnDone', function(){
			let arMenu = [];
			let arQty = [];
			let arTotal = [];
			$.each($("#selOrder li p:nth-child(1)"),function() {
				arMenu.push($(this).text());
			});
			$.each($("#selOrder li p:nth-child(2)"),function() {
				let str = $(this).text();
				let arSplit = str.split('x');
				arQty.push(arSplit[1]);
			});
			$.each($("#selOrder li p:nth-child(3)"),function() {
				let str = $(this).text();
				let arSplit = str.split('원');
				arTotal.push(Number(arSplit[0]));
			});
			let i = 0;
			$.each($('#selOrder li'), function(){
				if($('#mobile').val() == ""){
					$('#mobile').val("미적립");
				}
				$.get('salesInsert', {mobile : $('#mobile').val(),
                			  	  	  name : arMenu[i],
                			  	      qty : arQty[i],
                			  	      total :arTotal[i]
				},'text');
	    		i++;
      		});
      		$.get('salesInsert',{}, function() {
				loadSales();
      			$('#mobile, #total').val("");
	    	}, 'text');
	   		return false;
		})
		
		// 주문취소
		.on('click', '#btnCancel', function(){
		    $('#total, #mobile').val('');
		    $('#selOrder').text('');
		})
		// 메뉴관리
		.on('click', '#btnMenu', function(){
		    $('#dlgMenu').dialog({
		        width : 830,
		        close : function(){
		        	$('#_menuname, #_price, #_menucode').val('');
		        }
		    });
		})
		.on('click', '#selMenu1 li', function(){
		    let str1 = $('p:nth-child(1)',this).text();
		    let str2 = $('p:nth-child(2)',this).text();
		    let str3 = $('p:nth-child(3)',this).text();
		    let arSplit = str3.split('원');
		    $('#_menucode').val(str1);
		    $('#_menuname').val(str2);
		    $('#_price').val(arSplit[0]);
		})
		.on('click', '#btnPlus', function() {
		    // 추가(생성) / 수정(변경)
		    // 메뉴명 가격 읽기
		    let menuname = $('#_menuname').val();
		    let price = $('#_price').val();
		    let menucode = $('#_menucode').val();
			let operation = '';
			let arMenu = [];
			let arPrice = [];
			let arCode = [];
			$.each($("#selMenu1 li p:nth-child(1)"),function() {
				arCode.push($(this).text());
			});
			$.each($("#selMenu1 li p:nth-child(2)"),function() {
				arMenu.push($(this).text());
			});
			$.each($("#selMenu1 li p:nth-child(3)"),function() {
				arPrice.push($(this).text());
			});
			if(menucode == ''){
				if(menuname != '' && price != ''){
					for(i = 0; i < $('#selMenu1 li').length; i++){
						if(arMenu[i] == menuname){
							break;
						}
					}
		      		if(i == $('#selMenu1 li').length){
		      			operation = 'menuInsert';
		      		} else {
		      			alert("메뉴 수정시 메뉴코드를 입력하세요.");
		      		}
				}
			} else {
				if(menuname != '' && price != ''){
					for(i = 0; i < $('#selMenu1 li').length; i++){
						if(arCode[i] == menucode){
							break;
						}
					}
		      		if(i != $('#selMenu1 li').length){
		      			operation = 'menuUpdate';
		      		} else {
		      			alert("메뉴 추가시 메뉴코드란을 비워주세요.");
		      		}
				} else if(menuname != '' || price != '') {
					alert("메뉴명, 가격을 모두 입력해주세요.");
				} else {
					operation = 'menuDelete';
				}
			}
      		$.get(operation, {name : menuname,
                			  price : price,
                			  code : menucode
      		}, function() {
      			$('#_menuname, #_price, #_menucode').val("");
      			loadMenu();
	    	}, 'text');
	   		return false;
	   	})
	   	.on('click', '#btnReset2', function(){	// 메뉴관리 비우기
//	   		$("#_menucode, #_menuname, #_price").attr("disabled", false);
		    $('#_menucode, #_menuname, #_price').val('');
		})
		.on('click', '#btnMenu2', function(){
			loadTotalMenu();
			loadTotalCust();
		    $('#dlgMenu2').dialog({
		        width : 830,
		        close : function(){
		        }
		    });
		})
		.on('click', '.input_box li p:nth-child(2)', function(){
			$(this).not('#menuname')
//			if($(this).children() != $("#menuname") || $(this).children() != $("#price")){
//				console.log('잘된다!');
//				$(this).toggleClass('on');
//			}
		});
		
// 		.on('change', '#_menucode, #_menuname, #_price', function(){
// 			if($('#_menuname').val() != '' || $('#_price').val() != ''){
// 				$('#_menucode').val('');
// 				$("#_menucode").attr("disabled", true);
// 			} else {
// 				$("#_menucode").attr("disabled", false);
// 			}
// 			if($('#_menucode').val() != ''){
// 				$('#_menuname, #_price').val('');
// 				$("#_menuname, #_price").attr("disabled", true);
// 			} else {
// 				$("#_menuname, #_price").attr("disabled", false);
// 			}
// 		});
		function loadMenu() {
	    	$('#selMenu, #selMenu1').empty();
	    	$.get('menuSelect', {}, function(txt) {
	        	if (txt == "") return false;
	        	let rec = txt.split(';');
	        	for (i = 0; i < rec.length; i++) {
 		        	let field = rec[i].split(',');
	        		let html = '<li class="fixed"><p>' + field[1] + '</p><p>' + field[2] + '원</p></li>';
	        		let html2 = '<li class="fixed"><p>'+ field[0] + '</p><p>' + field[1] + '</p><p>' + field[2] + '원</p></li>';
	        		$('#selMenu').append(html);
	        		$('#selMenu1').append(html2);
	    		}
			}, 'text');
		}
		function loadSales() {
			$('#selSales, #selOrder').empty();
			$.get('salesSelect', {}, function(txt){
	        	if (txt == "") return false;
	        	let rec = txt.split(';');
	        	for (i = 0; i < rec.length; i++) {
 		        	let field = rec[i].split(',');
	        		let html = '<li class="fixed"><p>' + field[1] + '</p><p>' + field[2] + '</p><p>x'+ field[3] + '</p><p>' + field[4] + '원</p></li>';
	        		$('#selSales').append(html);
	    		}
			}, 'text');
		}
		function loadTotalMenu(){
			$('#total_menu').empty();
			$.get('totalMenu', {}, function(txt){
	        	if (txt == "") return false;
	        	let rec = txt.split(';');
	        	for (i = 0; i < rec.length; i++) {
 		        	let field = rec[i].split(',');
	        		let html = '<li class="fixed"><p>' + field[0] + '</p><p>x' + field[1] + '</p><p>'+ field[2] + '원</p></li>';
	        		$('#total_menu').append(html);
	    		}
			}, 'text');
		}
		function loadTotalCust(){
			$('#total_cust').empty();
			$.get('totalCust', {}, function(txt){
	        	if (txt == "") return false;
	        	let rec = txt.split(';');
	        	for (i = 0; i < rec.length; i++) {
 		        	let field = rec[i].split(',');
	        		let html = '<li class="fixed"><p>' + field[0] + '</p><p>' + field[1] + '원</p></li>';
	        		$('#total_cust').append(html);
	    		}
			}, 'text');
		}
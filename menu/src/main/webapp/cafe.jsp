<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>＊＊＊카  페＊＊＊</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
    <script src="main.js"></script>
    <link rel="icon" href="/menu/favicon.ico">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="wrapper fixed">
		<section class="fixed">
			<h2>메뉴목록</h2>
			<div>
				<ul id="selMenu" class="list_box fixed">
					<!--<li> 메뉴목록 위치 </li>-->
				</ul>
			</div>
			<div>
				<ul class="input_box fixed">
					<li><p>메 뉴 명<p><input type="text" id="menuname" placeholder="자동입력란" readonly></p></li>
					<li><p>수 &nbsp; &nbsp; 량<p><input type="number" id="count" min="1"></p></li>
					<li><p>금 &nbsp; &nbsp; 액<p class="kmoney on"><input type="text" id="price" placeholder="자동입력란" readonly></p></li>
				</ul>
			</div>
			<button id="btnReset" class="btn_bg"><span>취소</span></button>
			<button id="btnAdd" class="btn_bg"><span>메뉴추가</span></button>
			<div class="btnMenu">
        		<button id="btnMenu">메뉴관리</button>
    		</div>
		</section>
		<section class="fixed">
			<h2>주문목록</h2>
			<div>
				<ul id="selOrder" class="list_box fixed">
					<!--<li> 주문목록 위치 </li>-->
				</ul>
			</div>
			<div>
				<ul class="input_box fixed">
					<li class="fixed"><p>고객번호<p><input type="tel" id="mobile" size="13" placeholder="적립시 입력해주세요"></p></li>
					<li class="fixed"><p>총 &nbsp; &nbsp; 액<p class="kmoney"><input type="text" id="total" placeholder="자동입력란" readonly></p></li>
				</ul>
			</div>
			<button id=btnCancel class="btn_bg"><span>주문취소</span></button>
			<button id=btnDone class="btn_bg"><span>주문완료</span></button>
		</section>
		<section class="fixed">
			<h2>판매내역</h2>
			<div>
				<ul id="selSales" class="list_box fixed">
					<!--<li> 판매내역 위치 </li>-->
				</ul>
			</div>
			<div class="btnMenu">
				<button id="btnMenu2">summary</button>
    		</div>
		</section>
	</div>
    <div id="dlgMenu" style="display:none" title="메뉴관리">
    	<section class="fixed">
			<div>
				<ul id="selMenu1" class="list_box fixed">
					<!--<li> 메뉴관리 위치 </li>-->
				</ul>
			</div>
    		<div>
    			<div class="menu_info">
<!-- 	    		   	<h3>메뉴관리 가이드</h3> -->
	    			<dl>
	    				<dt>메뉴 추가</dt><dd>메뉴코드를 제외한 <strong>메뉴명, 가격</strong>을 입력하세요.</dd>
	    				<dt>메뉴 수정</dt><dd><strong>메뉴코드, 메뉴명, 가격</strong>을 입력하세요.<span>※메뉴코드는 변경이 불가능합니다.</span></dd>
	    				<dt>메뉴 삭제</dt><dd><strong>메뉴코드</strong>만 입력하세요.</dd>
	    			</dl>
	    		</div>
	    		<ul class="input_box fixed">
    				<li title="메뉴코드 입력시 메뉴가 삭제 됩니다."><p>메뉴코드</p><p><input type="number" id="_menucode"></p></li>
    				<li><p>메 뉴 명</p><p><input type="text" id="_menuname"></p></li>
    				<li><p>가 &nbsp; &nbsp; 격</p><p class="kmoney"><input type="number" id="_price" min="500" step="500"></p></li>
    			</ul>
    		</div>
    		<button id="btnPlus" class="btn_bg"><span>작성완료</span></button>
    		<button id="btnReset2" class="btn_bg"><span>비우기</span></button>
    	</section>
    </div>
    <div id="dlgMenu2" style="display:none" title="summary">
    	<section class="fixed">
			<div>
			    <h2>메뉴별 매출금액<span>(가나다순)</span></h2>
				<ul id="total_menu" class="list_box fixed">
					<!--<li> 메뉴별 매출금액 리스트 위치 </li>-->
				</ul>
			</div>
			<div>
				<h2>고객별 매출금액<span>(매출액순)</span></h2>
				<ul id="total_cust" class="list_box fixed">
					<!--<li> 고객별 매출금액 리스트 위치 </li>-->
				</ul>
			</div>
    	</section>
    </div>
</body>
</html>
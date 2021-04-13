<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- 4월 8일 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/notice_list.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

	/*예약 db에 저장하기*/
function roomBookingCheck() {
	alert("db저장 체크중");
	alert('roomNum: '+$("#roomNum").val());
	
		$.ajax({
			url:"/study/roomBookingCheck",
			type:"post",
			traditional:true,
			data:{
				"rb_time":$("#timeChoice").val(),
				"sr_id":$("#roomNum").val(),
				"rb_date":$("#date").val(),
				"lm_id": <%=session.getAttribute("session_id") %>
			},
			success : function(data) {
					alert(data.rs);
			},
			error : function() {
				alert("예약이 실패 하였습니다. 다른 스터디룸/예약시간/날짜로 다시 시도해 주세요. 오류가 지속되면 관리자에게 문의 부탁드립니다.");
			},
		});
}//roomBookingCheck


	/*시간비교 후 비어있는 시간 예약하기*/
function dateCheck() {
	alert("날짜체크중");
	alert('룸 번호: '+$("#roomNum").val());
	var sessionUId = "<%=session.getAttribute("session_flag") %>"

	if(sessionUId ==null || sessionUId != "success" ){
		alert('로그인 후 이용 가능합니다. 로그인 해주세요.');
	}else {
	
		$.ajax({
			url:"/study/dateCheck",
			type:"post",
			traditional:true,
			data:{
				"rb_date":$("#date").val(),"sr_id":$("#roomNum").val()
			},
			success : function(data) {
				console.log(data.rs[0]);
				
	 				var dis_check="";
					var dis_msg="";
					var innerStr = "";
					innerStr = '<select name="timeChoice" id="timeChoice">';
		  				for(var i= 9;i<= 18;i++){ 
		  					innerStr += '<option value='+i+''; 
		 					for(var j=0;j<data.rs.length;j++){
		 						//alert(data.rs[j]+":::"+i);
		 						 if(i==data.rs[j]){
		  						dis_check =' disabled '; 
		  						dis_msg='  [예약불가] ';
								break;
								 }
		 					} 
		 					innerStr += dis_check; 
		 					innerStr += '> ' + i + '시' + dis_msg + '</option>';
		 					dis_check=""; 
		 					dis_msg="";
						}
	  				innerStr += '</select>';
	  				
	  				innerStr += '<a href="#" class="#" ';
// 	  				innerStr += 'onclick="roomBookingCheck('+ $("#timeChoice").val() +',\''+roomNum+'\')">';
	  				innerStr += 'onclick="roomBookingCheck()">';
	  				innerStr += '예약하기</a>';
	  				
					$('#timeSelect').html(innerStr);
				
			},
			error : function() {
				alert("예약실패");
			},
			async:false
		});
  }//session확인	
}//dateCheck
</script>
</head>
<body>
	<header>
		<ul>
			<li>회원가입</li>
			<span>|</span>
			<li>로그인</li>
			<span>|</span>
			<li>고객행복센터</li>
			<span>|</span>
			<li>배송지역검색</li>
			<span>|</span>
			<li>기프트카드 등록</li>
		</ul>
	</header>

	<nav>
		<a href="/main">
			<div class="logo"></div>
		</a>

		<div id="search">
			<div class="search"></div>
			<br> <span>메뉴찾기</span>
		</div>

		<div id="cart">
			<div class="cart"></div>
			<br> <span>장바구니</span>
		</div>

		<div class="nav-menu">
			<ul>
				<li>COOKIT소개</li>
				<li>COOKIT 메뉴</li>
				<li>리뷰</li>
				<li>이벤트</li>
				<a href="/study/studyRoom"><li><span>스터디룸</span></li></a>
			</ul>
			
		</div>
	</nav>

	<section>
		<h1>스터디룸 예약</h1>
		<a href="/study/mypageRoomList"><li><span>나의 스터디룸 예약현황 </span></li></a>
		<div class="wrapper"></div>

		<form action="./study/studyRoom" method="post">
		<table>
			<colgroup>
				<col width="20px">
				<col width="10%">
				<col width="28px">
				<col width="20px">
			</colgroup>
			<tr>
				
				<th>위치</th>
				<th>룸 이름</th>
				<th>당일 예약 현황</th>
				<th>다른 날 예약하기</th>
			</tr>
			<tr>
				<td><span class="table-notice">1층</span></td>
				<td><img alt="" src="../images/best01.png"></td>
				<td>
 					09:00 - 10:00 (예약중)<br> 
					10:00 - 11:00 (예약중)<br>
					11:00 - 12:00 (예약중)<br>
					12:00 - 13:00 ( 예약가능 )<br>
					13:00 - 14:00 (예약중)<br>
					14:00 - 15:00 (예약중)<br>
					15:00 - 16:00 (예약중)<br>
					16:00 - 17:00 (예약중)<br>
					17:00 - 18:00 (예약중)<br>
					18:00 - 19:00 (예약중)
				</td>
				<td>
					<input type="date" id="date" name="date"><br>
					
					<input type="hidden" id="roomNum" value="1">
					
					<div id="timeSelect" ></div>
					
					<input type="button" onclick="dateCheck()"  value="날짜선택하기">
				</td>
			</tr>
			</table>
			</form>
			<table>
			<colgroup>
				<col width="20px">
				<col width="10%">
				<col width="28px">
				<col width="20px">
			</colgroup>
			<tr>
				<td><span class="table-notice">1층</span></td>
				<td><img alt="" src="../images/best02.png"></td>
				<td>오늘날짜 출력</td>
				<td></td>
			</tr>
			</table>
			<table>
			<colgroup>
				<col width="20px">
				<col width="10%">
				<col width="28px">
				<col width="20px">
			</colgroup>
			<tr>
				<td><span class="table-notice">2층</span></td>
				<td><img alt="" src="../images/best03.png"></td>
				<td>오늘날짜 출력</td>
				<td></td>
			</tr>
			</table>
			<table>
			<colgroup>
				<col width="20px">
				<col width="10%">
				<col width="28px">
				<col width="20px">
			</colgroup>
			<tr>
				<td><span class="table-notice">2층</span></td>
				<td><img alt="" src="../images/best04.png"></td>
				<td>오늘날짜 출력</td>
				<td></th>
			</tr>
			</table>
			<table>
			<colgroup>
				<col width="20px">
				<col width="10%">
				<col width="28px">
				<col width="20px">
			</colgroup>
			<tr>
				<td><span class="table-notice">2층</span></td>
				<td><img alt="" src="../images/best05.png"></td>
				<td>오늘날짜 출력</td>
				<td></td>
			</tr>
		</table>
			

		<footer>
			<div class="wrapper">
				<div class="footer-left">
					<div class="footer-logo"></div>
					<div class="copyright">© COOKIT ALL RIGHTS RESERVED</div>
				</div>

				<div class="footer-center">
					<ul class="footer-nav">
						<li class="first-list">이용약관</li>
						<li class="green">개인정보처리 방침</li>
						<li>법적고지</li>
						<li>사업자정보 확인</li>
					</ul>

					<ul class="footer-info">
						<li class="first-list">씨제이제일제당(주)</li>
						<li>대표이사 : 손경식,강신호,신현재</li>
						<li>사업자등록번호 : 104-86-09535</li>
						<li class="first-list">주소 : 서울 중구 동호로 330 CJ제일제당 센터 (우) 04560</li>
						<li>통신판매업신고 중구 제 07767호</li>
						<li class="first-list">개인정보보호책임자 : 조영민</li>
						<li>이메일 : cjon@cj.net</li>
						<li>호스팅제공자 : CJ올리브네트웍스㈜</li>
					</ul>

					<div id="check">
						고객님은 안전거래를 위해 현금등으로 결제시 LG U+ 전자 결제의 매매보호(에스크로) 서비스를 이용하실 수 있습니다.
						<span class="check">가입 사실 확인</span>
					</div>
				</div>

				<div class="footer-right">
					<div id="shortcut">
						<span>CJ그룹계열사 바로가기</span>
						<div class="shortcut"></div>
					</div>

					<div class="call">고객행복센터 1668-1920</div>
					<div class="inquery">1:1 문의</div>
				</div>

			</div>
		</footer>
</body>
</html>
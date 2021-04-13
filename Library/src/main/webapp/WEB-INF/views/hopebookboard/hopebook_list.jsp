<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/notice_list.css">
</head>
<body>
  <header>
    <ul>
      <li>회원가입</li> <span>|</span>
      <li>로그인</li> <span>|</span>
      <li>고객행복센터</li> <span>|</span>
      <li>배송지역검색</li> <span>|</span>
      <li>기프트카드 등록</li>
    </ul>
  </header>

  <nav>
    <div class="logo"></div>

    <div id="search">
      <div class="search"></div><br>
      <span>메뉴찾기</span>
    </div>

    <div id="cart">
      <div class="cart"></div><br>
      <span>장바구니</span>
    </div>

    <div class="nav-menu">
      <ul>
        <li>COOKIT소개</li>
        <li>COOKIT 메뉴</li>
        <li>리뷰</li>
        <li>이벤트</li>
        <li>MY쿡킷</li>
      </ul>  
    </div>
  </nav>

  <section>
    <h1>NOTICE</h1>
    <div class="wrapper">
     <form action="./hopebook_list" name="search" method="post">
		<select name="category" id="category">
			<option value="all"${map.category eq 'all'? 'selected':'' } >전체</option>
			<option value="title" ${map.category eq 'title'? 'selected':'' }>제목</option>
			<option value="content" ${map.category eq 'content'? 'selected':'' }>내용</option>
		</select>

        <div class="title">
          <input type="text" size="16">
        </div>
  
        <button type="submit">
        	<i class="fas fa-search"></i>
        </button>
      </form>
    </div>

    <table>
      <colgroup>
        	<col width="12%">
			<col width="44%">
			<col width="17%">
			<col width="17%">
			<col width="10%">
      </colgroup>
      <tr>
        <th>No.</th>
        <th>제목</th>
        <th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
      </tr>

			<!-- 내용부분 -->
			<c:forEach var="dto" items="${map.list }">
		      	<tr>

			        <td>${dto.hb_Seq }</td>
			        <td class="table-title">
<%-- 			        <c:forEach begin="1" end="${dto.bindent }"> --%>
<!-- 					<img src="images/rr.png"> -->
<%-- 					</c:forEach>  --%>
			        <a href="./content_view?category=${map.category}&search=${map.search}&hb_Seq=${dto.hb_Seq }&page=${map.page}">${dto.hb_Title }</a>
			        </td>
			        <td>${dto.hb_User }</td>
					<td>${dto.hb_Date }</td>
					<td>${dto.hb_Hit }</td>
			      </tr>
			</c:forEach>
   		</table>

   			 <!-- 페이지번호 넣기-->
			<ul class="page-num">
			<a href="./hopebook_list?category=${map.category}&search=${map.search}&page=1"><li class="first"></li></a>
			<c:choose>
				<c:when test="${map.page <=1 }">
					<li class="prev"></li>
				</c:when>
				<c:otherwise>
					<a href="./hopebook_list?category=${map.category}&search=${map.search}&page=${map.page-1 }"><li class="prev"></li></a>
				</c:otherwise>
			</c:choose>

			<!--번호반복 -->
			<c:forEach var="nowpage" begin="${map.startpage }" end="${map.endpage }">
				<c:choose>
					<c:when test="${map.page==nowpage }">
						<li class="num"><div>${nowpage }</div></li>
					</c:when>
					<c:otherwise>
						<a href="./hopebook_list?category=${map.category}&search=${map.search}&page=${nowpage }"><li class="num"><div>${nowpage }</div></li></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!--번호반복 -->

			<c:choose>
				<c:when test="${map.page >= map.maxpage }">
					<li class="next"></li>
				</c:when>
				<c:otherwise>
					<a href="./hopebook_list?category=${map.category}&search=${map.search}&page=${map.page+1 }"><li class="next"></li></a>
				</c:otherwise>
			</c:choose>


			<a href="./hopebook_list?category=${map.category}&search=${map.search}&page=${map.maxpage }"><li class="last"></li></a>
		</ul>

		<a href="./write_view"><div class="write">쓰기</div></a>
	</section>

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
  
        <div id="check">고객님은 안전거래를 위해 현금등으로 결제시 LG U+ 전자 결제의 매매보호(에스크로) 서비스를 이용하실 수 있습니다. <span class="check">가입 사실 확인</span></div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>인덱스</title>
	</head>
	<body>
		<c:choose>
			<c:when test="${session_flag==null || session_flag=='' }">
				<h2><a href="login">로그인</a></h2>
			</c:when>
			<c:otherwise>
				<h2><a href="logout">로그아웃</a></h2>
				<h2><a href="memberList">회원정보보기</a></h2>
			
			</c:otherwise>
		</c:choose>
		
	</body>
</html>
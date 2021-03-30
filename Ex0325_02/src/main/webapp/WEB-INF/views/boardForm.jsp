<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글쓰기</title>
	</head>
	<body>
		<form action="write" method="post" name="form1">
			
			<input type="hidden" name="page" value="1">
			<input type="hidden" name="category" value="all">
			<input type="hidden" name="search" value="갓">
			
			<label>번호</label>
			<input type="text" name="bId"><br>
			<label>이름</label>
			<input type="text" name="bName"><br>
			<label>제목</label>
			<input type="text" name="bTitle"><br>
			<label>내용</label>
			<textarea name="bContent" cals="50" rows="10"></textarea><br>
			<label>날짜</label>
			<input type="date" name="bDate"><br>
			<input type="submit" value="전송">
		</form>
	</body>
</html>
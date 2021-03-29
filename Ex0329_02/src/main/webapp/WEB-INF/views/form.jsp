<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>form</h2>
		<form action ="/form_ok" name="form" method ="post" enctype="multipart/form-data">
			<label>아이디</label>
			<input type="text" name="id"><br>
			<label>이름</label>
			<input type="text" name="name"><br>
			<label>파일첨부</label>
			<input type="file" name="file"><br>
			<input type="submit" value="전송" ><br>
			
			<img alt="" src="upload/1.png">
		</form>
	</body>
</html>
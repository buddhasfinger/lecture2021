<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>룸 예약시간</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function serviceCheck() {
		alert("예약진행중 [serviceCheck 진입]");
		$.ajax({
			url : "/serviceCheck",
			type : "get",
			data : {
			//"id":$("#id").val(),"pw":$("#pw").val()
			},
			contentType : "application/json",
			success : function(data) {
				var html="";
				<%
				
				int[] rs = {10,11,13,17};
				
				String dis_check="";
				String dis_msg="";
				
				for(int i=9;i<=18;i++){ %><option value=i<%
					for(int j=0;j<rs.length;j++){
						 if(i==rs[j]){
						    dis_check ="disabled"; dis_msg="  [예약불가]";
						    break;
						  }
						}%> <%=dis_check%>
						><%=i%>시<%=dis_msg%></option>
						<%dis_check="";dis_msg="";}%>
				
				
				
				$('#time').html(html);
				
				
				
				
				alert("예약 성공");
				if (data.serviceCheck == 1) {
					alert(data.message);
					location.href = "../main";
				} else {
					alert(data.message);
				}
			},
			error : function() {
				alert("예약실패");
			}
		});
	}
	function goBack() {
		alert("스터디룸을 다시 선택합니다.");
		location.href = "./studyRoom";
	}
</script>

<style>
body {
	position: relative;
	height: 100%;
	background: #f5f6f7;
}

#a {
	font-size: 30px;
}
</style>
</head>
<body>
	<h2>룸 예약시간</h2>

	<form action="/study/roomBooking" method="post">
		<h3></h3>
		<a id="a">스터디룸</a> <select name=job>
			<option value="" selected>선택하세요.
			<option name="룸 선택" id="룸 선택" value="룸 01">룸 1
			<option name="룸 선택" id="룸 선택" value="룸 02">룸 2
			<option name="룸 선택" id="룸 선택" value="룸 03">룸 3
			<option name="룸 선택" id="룸 선택" value="룸 04">룸 4
			<option name="룸 선택" id="룸 선택" value="룸 05">룸 5
		</select><br> 
		<a id="a">날짜선택</a> 
		<input type="date" name="date" id="date"><br>
		
		
		 <a id="a">시간선택</a> 
		 <select name=time id="time" >
			<option value="" selected>선택하세요.
			<%
			
			int[] rs = {10,11,13,17};
			
			String dis_check="";
			String dis_msg="";
			
			for(int i=9;i<=18;i++){ %><option value=i<%
				for(int j=0;j<rs.length;j++){
					 if(i==rs[j]){
					    dis_check ="disabled"; dis_msg="  [예약불가]";
					    break;
					  }
					}%> <%=dis_check%>
					><%=i%>시<%=dis_msg%></option>
					<%dis_check="";dis_msg="";}%>
			
			
			
			
			
		</select><br> 
		<input type="submit"  class="" value="예약하기">
<!-- 		<input type="button" onclick="serviceCheck()" class="" value="예약하기"> -->
		<input type="button" onclick="goBack()" class="" value="스터디룸 다시선택">
	</form>

</body>
</html>
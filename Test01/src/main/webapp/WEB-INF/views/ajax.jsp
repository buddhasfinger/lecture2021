<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
    	function testAjax(){
    		alert('로그인 체크 합니다');
    		$.ajax({
				url:"/testAjax",
				type:"get",
				data:{
					//"date":$("#date").val(), "location":$("#location").val(); 이거안됨
					date:$("#date").val(),location:$("#location").val() //보내는 데이터
				},
				contentType:"application/json",
				dataType:"json",
				success:function(data){  //받는 데이터
					//$("#dataDiv").html(data);	
					alert(data);
					var arr = data.response.body.items.item;
			        alert(arr);
			        var html;
			        if(arr.length>=1){
			        for(var i in arr){
			            html += '<tr>';
			            html += '<td>'+arr[i].tm+'</td>';
			            html += '<td>'+arr[i].thema+'</td>';
			            html += '<td>'+arr[i].courseId+'</td>';
			            html += '<td>'+arr[i].courseAreaId+'</td>';
			            html += '<td>'+arr[i].courseAreaName+'</td>';
			            html += '<td>'+arr[i].courseName+'</td>';
			            html += '<td>'+arr[i].sky+'</td>';
			            html += '<td>'+arr[i].rhm+'</td>';
			            //....
			            html += '</tr>';
			            }
			        }
// 			        else{
// 			        html="<tr><td>데이터 없음</td></tr>";
// 			        }
			        $('#data').html(html);
			        },

				error:function(){
					alert("에러");
				}
			});
			
    	}
    </script>
		<style>
			table,th,td {border: 1px solid black;}
		</style>
		
	</head>
	<body>
		<h2>ajax테스트</h2>
		<form>
			<label>날짜(예시:2021010110)</label>
			<input type="text" id="date" name="date"><br>
			<label>관광지명</label>
			<select name="location" id="location"> //주제선택
				<option value="봉화" >봉화</option>
				<option value="영양" >영양</option>
				<option value="보은" >보은</option>
			</select>
			
			<input type="button" onclick="testAjax()" value="검색">
			</form>
		<table >
			<thead>
				<tr> 
				 	<td>예보시각 </td>
				 	<td>테마 </td>
				 	<td>코스ID </td>
				 	<td>코스지역 아이디 </td>
				 	<td>코스지역 이름 </td>
				 	<td>코스이름 </td>
				 	<td>하늘상태 </td>
				 	<td>습도 </td>
				 </tr>
			</thead>
		
			<tbody id="data">
			
			</tbody>
		</table>
		
	</body>
</html>
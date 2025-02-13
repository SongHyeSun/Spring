<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function getDeptName(pDeptno) {
		console.log(pDeptno);
		//alert("pDeptno-> "+pDeptno);
		// 행동강령 : Ajax로 부서번호 보내고 부서명 받음
		$.ajax(
				{
					url:"<%=request.getContextPath()%>/getDeptName",
					//parameter 명칭(반드시 DTO에 정의된 명칭): parameter 값
					//여러개의 값을 넘겨주기 위해서, {deptno : pDeptno, dname : pDname}
					data:{deptno : pDeptno},
					dataType:'text',
					// function에 들어가는 deptName의 dataType이라는 뜻 -> text
					success:function(deptName){
						//		명칭은 뭘 써도 상관 없지만, 아래에 일관되게 같은 parameter 명이어야 한다.
						//alert("success ajax Data-> "+deptName);
						$('#deptName').val(deptName);	/* input Tag */
						$('#msg').html(deptName);		/* span id Tag */
					}
				}
		);
	}
	
	function getDept(pDeptno) {
		alert("pDeptno-> "+pDeptno);
		// sample/sendVO2
		// id : RestDept 결과 값 넣어주기
		
		$.ajax(
				{
					 url:"sample/sendVO2",
					 data:{deptno : pDeptno},
					 dataType:'json',
					 success:function(sampleVo){
						 //json은 객체명.dto명 -> 값을 불러와준다.
						 resultStr = sampleVo.firstName + " " + sampleVo.lastName + " " + sampleVo.mno;
						 alert("ajax getDept resultStr-> "+resultStr);
						 $('#RestDept').val(resultStr);		// input tag
					 }
				}
		);
	}
	
	function empWriteBtn() {
		var empno = $('#empno').val();
		
		//var sendData = JSON.stringify($('#empTrans').serialize());
		//alert('sendData-->'+sendData);
		
		//	 empTrans id를 전부 serializeArray로 배열값으로 넣어주겠다는 의미
		var sendData = $('#empTrans').serializeArray();
		
		//		JSON Data Conversion
		//sendData3가 아래 jsonParse에서 받아온 obj (객체로 넘어온 값)
		sendData3 = jsonParse(sendData);
		alert('sendData3-->'+sendData3);
		console.log('sendData3-->'+sendData3);
		
 		$.ajax({
			url: 'empSerializeWrite',
			type: 'POST',
			contentType: 'application/json;charset=UTF-8',
			//sendData3를 json 형태의 string data로 만들어서 보내주겠다는 의미
			//이렇게 되면, 상대방은 parsing만 해주면 data를 편하게 쓸 수 있다.
			data: JSON.stringify(sendData3),
			dataType:'json',
			success: function(response) {
				if(response.writeResult > 0) {
					alert("성공");
				}
			}
		}); 
	}
	
	function jsonParse(sendData2) {
		obj = {};
		if (sendData2) {
			
			jQuery.each(sendData2, function() {
				//name과 value를 obj 객체로 setting을 해준 후
				obj[this.name] = this.value;
				alert('this.name->' + this.name, ', this.value->' + this.value);
			});
		}
			return obj;
	}
</script>
</head>
<body>
	<h2>회원 정보</h2>
	<table>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>업무</th>
			<th>부서</th>
			<th>근무지</th>
		</tr>
		<c:forEach var="emp" items="${listEmp}">
			<tr>
				<td>${emp.empno }</td>
				<td>${emp.ename }</td>
				<td>${emp.job }</td>
				<td>${emp.deptno} 
				    <input type="button" id="btn_idCheck" value="부서명" onmouseover="getDeptName(${emp.deptno })">
				</td>
				<td>${empDept.loc }</td>
			</tr>
		</c:forEach>
	</table><p>

	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>
    
    RestController sendVO2: <input type="text" id="RestDept"    readonly="readonly"><p>
	RestController sendVO2: sendVO2<input type="button" id="btn_Dept"  value="부서명"  
	                                      onclick="getDept(10)"><p>
	                                      
	<h2>Serialize Test</h2>
    <form  name="empTrans"   id="empTrans">
    		<input type="hidden"  id="empno"  name="empno"    value="1234">
    		<input type="hidden"  id="ename"  name="ename"    value="시리얼">
    		<input type="hidden"  id="sal"    name="sal"      value="1000">
        <input type="button"  value="Ajax Serialize 확인" onclick="empWriteBtn()">
     </form>
</body>
</html>
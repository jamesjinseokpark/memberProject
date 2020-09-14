<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/header.jsp" />
<h1>업데이트 결과</h1>
<B>${member.mname }님 정상적으로 변경 되었습니다.</B>
<a href="list">리스트로 이동 5초 후.</a>
${name}
<script>
	setTimeout( function() { window.location='list';}, 5000);

</script>


<jsp:include page="./include/footer.jsp" />
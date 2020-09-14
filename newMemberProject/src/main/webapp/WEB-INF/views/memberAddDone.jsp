<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/header.jsp" />
<h1>회원 등록결과 </h1><br>
<b>정상적으로 등록되었습니다.</b><br>
<a href="list">5초 후 리스트로 이동합니다.</a>

<script>

setTimeout (function() {window.location='list';} , 5000);

</script>



<jsp:include page="./include/footer.jsp" />

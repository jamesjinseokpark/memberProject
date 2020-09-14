<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/header.jsp" />
<h1>회원탈퇴 </h1>
<form action='delete' method='post'>
<B>${mno }님 탈퇴를 희망하십니까?</B>

<input type="hidden" name="mno" value="${mno}">

<input type='submit' value="탈퇴">
<input type='reset' value="취소">
<input type='button' onclick="location.href='list'" value="리스트">



</form>


<jsp:include page="./include/footer.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/header.jsp" />

<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<h1>회원 정보 수정 </h1>
<form action='update' method='post'>
번호 : <input type='text' name='mno' value='${member.mno}' readonly><br>
이름: <input type='text' name='mname' value='${member.mname}'><br>
이메일: <input type='text' name='email' value='${member.email}'><br>
암호: <input type='password' name='pwd' value='${member.pwd}'><br>
가입일:<fmt:formatDate value='${member.cre_date}' pattern="yyyy-MM-dd" /><br>
<input type='submit' value='수정'>
<input type='reset' value='취소'>
<input type='button' onclick='location.href="list"' value='리스트로'>
<input type='button' onclick='location.href="delete?mno=${member.mno}"' value='회원탈퇴'>

</form>
<jsp:include page="./include/footer.jsp" />
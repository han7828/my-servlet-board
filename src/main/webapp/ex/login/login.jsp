<%--
  Created by IntelliJ IDEA.
  User: ast07
  Date: 2024-01-16
  Time: 오전 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String id = (String) session.getAttribute("id");%>
<html>
<head>
    <title>로그인 폼</title>
</head>
<body>
    <% if ( id != null ) { %>
         <h2>접속이 완료되었습니다. <%=id%>님 어서오세요.</h2>
         <a href="/ex/logout">로그아웃</a>
    <% } else { %>
    <form method="post" action="/ex/login">
        <label for="id">아이디 : </label>
        <input type="text" name="id" id="id"><br>
        <label for="pw">비밀번호 : </label>
        <input type="text" name="pw" id="pw"><br>
        <input type="submit" value="로그인" class="submit-btn btn btn-secondary btn-block">
    </form>
    <div class="notified">${requestScope.loginFailed ? "로그인이 실패하였습니다. 아이디 혹은 비밀번호를 정확하게 입력해주세요." : ""}</div>
    <% } %>
</body>
<script>
    setTimeout(() => {
        document.querySelector(".notification").hidden = true;
    }, 2000);
</script>
</html>

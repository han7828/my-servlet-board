<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String[] members = (String[]) session.getAttribute("members");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <a class="logo" href="/board/list">HOME</a>
    <nav>
        <ul class="nav-items">
            <% if ( members != null ) { %>
            <li><%=members[3]%>님 어서오세요.</li>
            <% } %>
            <li><a href="/board/list">게시글목록</a></li>
            <% if ( members != null ) { %>
            <li><a href="/member/logout" onclick="return confirm('로그아웃 하시겠습니까?')" >로그아웃</a></li>
            <li><a href="/member/rForm">회원정보수정</a></li>
            <% } else { %>
            <li><a href="/member/lForm">로그인</a></li>
            <li><a href="/member/jForm">회원가입</a></li>
            <% } %>
        </ul>
    </nav>
</header>
</body>
</html>

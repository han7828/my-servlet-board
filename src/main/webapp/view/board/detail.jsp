<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="com.kitri.myservletboard.data.Comment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String[] members = (String[]) session.getAttribute("members");%>
<%Board board = (Board) request.getAttribute("board");%>
<%ArrayList<Comment> comment = (ArrayList<Comment>) request.getAttribute("comment");%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="게시판 상세" />
</jsp:include>
<body class="sb-nav-fixed">
<jsp:include page="/view/common/header.jsp"/>
<main class="mt-5 pt-5">
    <div class="container-fluid px-4 ">
        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>게시판 상세</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: 500px;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small>조회수 : ${board.getViewCount()}</small></div>
                    &nbsp
                    <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small>댓글수 : ${board.getCommentCount()}</small></div>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 border border-dark rounded-pill">#${board.getId()}</div>
                    </div>
                </div>
                <div class="p-2 border-bottom">
                    <b>${board.getTitle()}</b>
                </div>
                <div class="p-2 border-bottom">
                    <b>작성자 :</b> ${board.getWriter()}
                </div>
                <div class="p-2 border-bottom">
                    <b>등록일시 :</b> ${board.getCreatedAt()}
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-100 form-control bg-white" id="content" name="content"
                              disabled>${board.getContent()}</textarea>
                </div>
                <div class="d-flex flex-row-reverse mb-3 mr-3">
                    &nbsp
                    &nbsp
                    <% if ( members != null ) { %>
                        <% if ( board.getMemberId().equals(Long.parseLong(members[0]))) { %>
                    <a href="/board/delete?id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')"><small>삭제하기</small></a>
                    &nbsp
                    <a href="/board/updateForm?id=${board.getId()}" class="btn btn-secondary btn-sm"><small>수정하기</small></a>
                    <% } %>
                    <% } %>
                    &nbsp
                    <a href="/board/list" class="btn btn-secondary btn-sm"><small>목록으로</small></a>
                    &nbsp
                </div>
            </div>
        </div>
    </div>
    <%for ( int i = 0 ; i < comment.size() ; i++ ) {%>
    <form action="/board/deleteComment" method="post">
    <div class="container-fluid px-4 ">
        <div class="card mb-4 w-50 mx-auto">
            <div class="d-flex flex-column" style="height: auto;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <input hidden="hidden" type="text" name="board_id" value="<%=board.getId()%>">
                    <input hidden="hidden" name="id" value="<%=comment.get(i).getId()%>">
                    <% if ( members != null ) { %>
                        <% if ( comment.get(i).getMember_id().equals(Long.parseLong(members[0]))) { %>
                    <div class="comment_btn">
                        &nbsp
                    <a href="" type="submit" class="btn btn-outline-secondary" onclick="return confirm('댓글 수정하시겠습니까?')">수정하기</a>
                    <button type="submit" class="btn btn-outline-secondary" onclick="return confirm('댓글 삭제하시겠습니까?')">삭제하기</button>
                        &nbsp
                    </div>
                        <% } %>
                    <% } %>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small><b><%=comment.get(i).getCommenter()%></b></small></div>
                        &nbsp
                        <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small><%=comment.get(i).getCreated_at()%></small></div>
                    </div>
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-auto form-control bg-white" id="comment1" name="content" maxlength="200" disabled><%=comment.get(i).getContent()%></textarea>
                </div>
<%--                <div class="d-flex flex-row-reverse mb-3 mr-3">--%>
<%--                    <% if ( members != null ) { %>--%>
<%--                        <% if ( comment.get(i).getMember_id().equals(Long.parseLong(members[0]))) { %>--%>
<%--                    <a href="/board/" class="btn btn-secondary" id="createComment" onclick="return confirm('댓글을 등록하시겠습니까?')"><small>등록</small></a>--%>
<%--                        <% } %>--%>
<%--                    <% } %>--%>
<%--                </div>--%>
            </div>
        </div>
    </div>
    </form>
    <% } %>
    <% if ( members != null ) { %>
    <form action="/board/createComment" method="post">
        <div class="container-fluid px-4 ">
            <div class="card mb-4 w-50 mx-auto">
                <div class="d-flex flex-column" style="height: auto;">
                    <input hidden="hidden" type="text" name="board_id" value="<%=board.getId()%>">
                    <input hidden="hidden" type="text" name="member_id" value="<%=members[0]%>">
                    <div>
                        <h2 class="mt-3" style="text-align: center;"><b>댓글 쓰기</b></h2>
                        <hr class="mb-0">
                    </div>
                    <div class="m-3 h-75">
                        <textarea class="h-auto form-control bg-white" id="commentContent" name="commentContent" maxlength="200"></textarea>
                    </div>
                    <div class="d-flex flex-row-reverse mb-3 mr-3">
                        <button class="btn btn-secondary" type="submit" id="createComment" onclick="return confirm('댓글을 등록하시겠습니까?')"><small>등록</small></button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <% } %>
</main>
</body>
<style>
    .pd {
        padding-left: 5px;
        padding-right: 5px;
    }

</style>
<script>
    setTimeout(() => {
        document.querySelector(".notification").hidden = true;
    }, 2000);
</script>
</html>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page import="com.kitri.myservletboard.data.SearchData" %>
<%@ page import="com.kitri.myservletboard.data.Organize" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Pagination pagination = (Pagination) request.getAttribute("pagination"); %>
<% SearchData searchData = (SearchData) request.getAttribute("searchData"); %>
<% Organize organize = (Organize) request.getAttribute("organize"); %>
<%String[] members = (String[]) session.getAttribute("members");%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="게시판 목록" />
</jsp:include>
<body>
  <jsp:include page="/view/common/header.jsp"/>
  <div>
    <h2 style="text-align: center; margin-top: 100px;"><b>게시판 목록</b></h2>
  </div>
  <div class="pageOption">
    <form class="d-flex" novalidate action="/board/list" method="get">
      <input hidden="hidden" name="searchField" value="<%=searchData.getSearchField()%>">
      <input hidden="hidden" name="searchText" value="<%=searchData.getSearchText()%>">
      <input hidden="hidden" name="searchTime" value="<%=searchData.getSearchTime()%>">
      <select name="organize" class="form-select" id="organize1" onchange="this.form.submit()">
        <option value="newest" <% if (organize.getOrganize().equals("newest")) {%>szelected<%}%> >최신 순</option>
        <option value="oldest" <% if (organize.getOrganize().equals("oldest")) {%>selected<%}%> >과거 순</option>
        <option value="views" <% if (organize.getOrganize().equals("views")) {%>selected<%}%> >조회수 순</option>
        <option value="accuracy" <% if (organize.getOrganize().equals("accuracy")) {%>selected<%}%> >정확도 순</option>
      </select>
      <select name="recordsPerPage" class="form-select" id="recordsPerPage1" onchange="this.form.submit()">
        <option value="10" <% if (pagination.getRecordsPerPage() == 10) {%>selected<%}%> >10개씩</option>
        <option value="20" <% if (pagination.getRecordsPerPage() == 20) {%>selected<%}%> >20개씩</option>
        <option value="30" <% if (pagination.getRecordsPerPage() == 30) {%>selected<%}%> >30개씩</option>
        <option value="40" <% if (pagination.getRecordsPerPage() == 40) {%>selected<%}%> >40개씩</option>
        <option value="50" <% if (pagination.getRecordsPerPage() == 50) {%>selected<%}%> >50개씩</option>
      </select>
    </form>
  </div>
  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
        <% ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
           for (int i = 0 ; i < boards.size() ; i++) { %>
          <tr>
            <th scope="row"><%= boards.get(i).getId() %></th>
            <td><a href="/board/detail?id=<%=boards.get(i).getId()%>"><%= boards.get(i).getTitle() %></a></td>
            <td><%= boards.get(i).getWriter() %></td>
            <td><%= boards.get(i).getCreatedAt() %></td>
            <td><%= boards.get(i).getViewCount() %></td>
            <td><%= boards.get(i).getCommentCount() %></td>
          </tr>
        <% } %>
        </tbody>
      </table>
      <div>
        <% if ( members != null ) { %>
        <a href="/board/createForm" role="button" class="btn btn-secondary my-2 my-sm-0">글쓰기</a>
        <% } %>
      </div>
      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">
          <% if (pagination.isHasPrev()) { %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen() - 1%>&searchField=<%=searchData.getSearchField()%>&searchText=<%=searchData.getSearchText()%>&searchTime=<%=searchData.getSearchTime()%>&recordsPerPage=<%=pagination.getRecordsPerPage()%>&organize=<%=organize.getOrganize()%>" tabindex="1" aria-disabled="true"><<</a>
          </li>
          <% } else { %>
          <li class="page-item disabled">
            <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen() - 1%>&searchField=<%=searchData.getSearchField()%>&searchText=<%=searchData.getSearchText()%>&searchTime=<%=searchData.getSearchTime()%>&recordsPerPage=<%=pagination.getRecordsPerPage()%>&organize=<%=organize.getOrganize()%>" tabindex="1" aria-disabled="true"><<</a>
          </li>
          <% }%>

          <%
            for(int i = pagination.getStartPageOnScreen(); i <= pagination.getEndPageOnScreen(); i++) {
              if(pagination.getPage() == i ) {
          %>
              <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%>&searchField=<%=searchData.getSearchField()%>&searchText=<%=searchData.getSearchText()%>&searchTime=<%=searchData.getSearchTime()%>&recordsPerPage=<%=pagination.getRecordsPerPage()%>&organize=<%=organize.getOrganize()%>"><%=i%></a></li>
            <% } else { %>
              <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%>&searchField=<%=searchData.getSearchField()%>&searchText=<%=searchData.getSearchText()%>&searchTime=<%=searchData.getSearchTime()%>&recordsPerPage=<%=pagination.getRecordsPerPage()%>&organize=<%=organize.getOrganize()%>"><%=i%></a></li>
            <% } %>
          <% } %>

          <% if (pagination.isHasNext()) { %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen() + 1%>&searchField=<%=searchData.getSearchField()%>&searchText=<%=searchData.getSearchText()%>&searchTime=<%=searchData.getSearchTime()%>&recordsPerPage=<%=pagination.getRecordsPerPage()%>&organize=<%=organize.getOrganize()%>" tabindex="1" aria-disabled="true">>></a>
          </li>
          <% } else { %>
          <li class="page-item disabled">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen() + 1%>&searchField=<%=searchData.getSearchField()%>&searchText=<%=searchData.getSearchText()%>&searchTime=<%=searchData.getSearchTime()%>&recordsPerPage=<%=pagination.getRecordsPerPage()%>&organize=<%=organize.getOrganize()%>" tabindex="1" aria-disabled="true">>></a>
          </li>
          <% }%>
        </ul>
      </nav>
    </div>
      <div class="searchBox">
        <form class="d-flex" novalidate action="/board/list" method="post">
          <select name="searchTime" class="form-select" id="exampleSelect1">
            <option value="allday" <% if (searchData.getSearchTime().equals("allday")) {%>selected<%}%> >전체 기간</option>
            <option value="1day" <% if (searchData.getSearchTime().equals("1day")) {%>selected<%}%> >1일</option>
            <option value="1week" <% if (searchData.getSearchTime().equals("1week")) {%>selected<%}%> >1주</option>
            <option value="1month" <% if (searchData.getSearchTime().equals("1month")) {%>selected<%}%> >1개월</option>
            <option value="6month" <% if (searchData.getSearchTime().equals("6month")) {%>selected<%}%> >6개월</option>
            <option value="1year" <% if (searchData.getSearchTime().equals("1year")) {%>selected<%}%> >1년</option>
          </select>
          <select name="searchField" class="form-select" id="exampleSelect2">
            <option value="title" <% if (searchData.getSearchField().equals("title")) {%>selected<%}%> >제목</option>
            <option value="writer" <% if (searchData.getSearchField().equals("writer")) {%>selected<%}%> >작성자</option>
          </select>
          <input name="searchText" class="form-control me-sm-2" type="search" placeholder="<%=searchData.getSearchText()%>" onfocus="this.placeholder='Search'" onblur="<%=searchData.getSearchText()%>">
          <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </div>
  </div>
  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous">
  setTimeout(() => {
    document.querySelector(".notification").hidden = true;
  }, 2000);
</script>
</html>
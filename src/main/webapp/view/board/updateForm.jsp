<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>1
<%String[] members = (String[]) session.getAttribute("members");%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="게시판 수정" />
</jsp:include>
<body>
<jsp:include page="/view/common/header.jsp"/>
    <div class="container">
        <div class="input-form-backgroud row">
            <div class="input-form col-md-12 mx-auto">
                <h4 class="mb-3"><b>게시물 수정</b></h4>
                <hr>
                <br>
                <form class="validation-form" novalidate action="/board/update" method="post">
                    <input name="id" type="text" value="${board.getId()}" hidden>
                    <input hidden="hidden" name="memberId" value="<%=members[0]%>">
                    <div class="mb-3">
                        <label for="title">제목</label>
                        <input name="title" type="text" class="form-control" id="title" placeholder="" value="${board.getTitle()}" required>
                        <div class="invalid-feedback">
                            제목을 입력해주세요.
                        </div>
                    </div>
                    <div class="row">
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용</label>
                        <textarea name="content" class="form-control" id="content" cols="30" rows="5" placeholder="" value="">${board.getContent()}</textarea>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-secondary btn-block" type="submit" onclick="return confirm('게시물을 수정하시겠습니까?')">게시물 수정하기</button>
                        </div>
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-secondary btn-block" type="submit">취소</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="p-2">
        <div class="footer">
            <footer>
                <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
            </footer>
        </div>
    </div>
    </div>
    <script>
        window.addEventListener('load', () => {
            const forms = document.getElementsByClassName('validation-form');

            Array.prototype.filter.call(forms, (form) => {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
        }, false);

        setTimeout(() => {
            document.querySelector(".notification").hidden = true;
        }, 2000);
    </script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3b
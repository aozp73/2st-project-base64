<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
<div style="height: 100px;"></div>
            <div class="container py-3 bg-white">
            <div class="p-3">
                <hr>
                <h2 style="text-align: center;">나의 공고 목록</h2>
                <hr>
            </div>

            <!-- 카드 시작 -->
            <div class="p-3">
                <div class="row gx-3">

                    <c:forEach items="${myBoardList}" var="board">
                        <input id="boardId" type="hidden" value="${board.id}">
                        <div class="col-md-3 py-2">
                            <a href="/board/${board.id}" class="no_under_line_link">
                                <div id="boardImage-${board.id}" class="card col-lg-12"
                                    onmouseenter="mouseEnterImages(this)" onmouseleave="mouseLeaveImages(this)">
                                    <div class="d-flex justify-content-center">
                                    <img class="card-img-top" style="height: 100px; max-width: 200px;" src="${board.profile == null ? " /images/defaultProfile.png" :
                                            board.profile}"
                                        alt="Card image">
                                        </div>
                                    <div class="card-body">
                                        <div class="my-text-ellipsis">
                                            <h5>${board.companyName}</h5>
                                        </div>
                                        <div class="my-text-ellipsis">
                                            ${board.title}
                                        </div>
                                        <div class="my-text-ellipsis">
                                            채용
                                        </div>
                                    </div>
                            </a>
                            <div class="card-footer d-flex justify-content-between">
                                <div>(D-1)</div>
                                <div>
                                    <button type="button" class="btn btn-primary"
                                        onclick="location.href='/board/updateForm/${board.id}'">수정</button>
                                    <button type="button" class="btn btn-secondary">삭제</button>
                                </div>
                            </div>
                        </div>
                </div>
                </c:forEach>



            </div>
            <div class="d-flex justify-content-center mt-5"
                style="display: grid; grid-template-columns: 1fr 8fr 1fr; margin-bottom: 60px;">

                <div class="col-md-4"></div>

                <div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">4</a></li>
                            <li class="page-item"><a class="page-link" href="#">5</a></li>
                            <li class="page-item"><a class="page-link" href="#">6</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </nav>
                </div>

                <div class="col-md-4 d-flex justify-content-end mb-3">
                    <a class="btn btn-secondary" href="/board/saveForm" style="position: relative; bottom: 40px;">공고
                        등록</a>
                </div>

            </div>

        </div>
        </div>
        </div>

        <!-- 페이징 -->


        <script>
            function mouseEnterImages(e) {
                // console.log(e.getAttribute('id'))
                let id = e.getAttribute('id');
                $("#" + id).addClass("border border-primary");
            }
            function mouseLeaveImages(e) {
                let id = e.getAttribute('id');
                $("#" + id).removeClass("border border-primary");
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>

            <%-- /company/{companyId}/board --%>
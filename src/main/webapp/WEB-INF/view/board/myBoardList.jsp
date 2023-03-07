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
                <div id="myBoardCheck" class="row gx-3">

                    <c:forEach items="${myBoardList}" var="board">
                        <div id="targetBoard-${board.id}" class="col-md-3 py-2">
                            <input id="boardId" type="hidden" value="${board.id}">
                            <a href="/board/${board.id}" class="no_under_line_link">
                                <div id="boardImage-${board.id}" class="card col-lg-12"
                                    onmouseenter="mouseEnterImages(this)" onmouseleave="mouseLeaveImages(this)">
                                    <div class="d-flex justify-content-center">
                                        <img class="card-img-top" style="height: 100px; max-width: 200px;"
                                            src="${board.profile == null ? " /images/defaultProfile.png" :
                                            board.profile}" alt="Card image">
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
                                <c:choose>
                                    <c:when test="${board.dday < 0}">
                                        <div style="color: crimson;">(지원 마감)</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div>(D-${board.dday == 0 ? 'Day' : board.dday})</div>
                                    </c:otherwise>
                                </c:choose>
                                <div>
                                    <button type="button" class="btn btn-primary"
                                        onclick="location.href='/board/updateForm/${board.id}'">수정</button>
                                    <button type="button" class="btn btn-secondary"
                                        onclick="deleteBoard(`${board.id}`)">삭제</button>
                                </div>
                            </div>
                        </div>
                </div>
                </c:forEach>



            </div>
            <div class="row d-flex justify-content-center mt-5" style="margin-bottom: 60px;">

                <div class="col-md-4"></div>

                <div class="col-md-4">
                    <%-- <nav aria-label="Page navigation example">
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
                        </nav> --%>
                </div>

                <div class="col-md-4 d-flex justify-content-end">
                    <a class="btn btn-secondary" href="/board/saveForm"
                        style="position: relative; bottom: 13px; right:20px;">공고
                        등록</a>
                </div>

            </div>

        </div>
        </div>
        </div>

        <!-- 페이징 -->


        <script>
            function deleteBoard(boardId) {
                let targetBoard = "targetBoard-" + boardId

                $.ajax({
                    type: "delete",
                    url: "/board/" + boardId,
                    dataType: "json"
                }).done((res) => {
                    // alert(res.msg);
                    $("#" + targetBoard).remove();
                    boardCheck()
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
            function boardCheck() {
                if ($("#myBoardCheck").children().length == 0) {
                    let el = `<div class="d-flex justify-content-center">
                                    <br>
                                    등록된 공고가 없습니다.
                                </div>
                                <br>
                                <br>
                                <br>`
                    $("#myBoardCheck").append(el);
                }
            }


            function mouseEnterImages(e) {
                // console.log(e.getAttribute('id'))
                let id = e.getAttribute('id');
                $("#" + id).addClass("border border-primary");
            }
            function mouseLeaveImages(e) {
                let id = e.getAttribute('id');
                $("#" + id).removeClass("border border-primary");
            }


            boardCheck()
        </script>

        <%@ include file="../layout/footer.jsp" %>

            <%-- /company/{companyId}/board --%>
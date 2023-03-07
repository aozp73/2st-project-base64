<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <input id="checkKeyword" type="hidden" name="" value="${pagingDto.keyword}">
        <div style="height: 100px;"></div>


        <div class="container my-3 py-3 px-3">
            <div class="p-3">
                <h2 style="text-align: center;">나의 스크랩 공고 목록</h2>
            </div>
            <%-- <c:if test="${principal.role == 'employee'}">
                <div class="d-flex justify-content-end mb-2">
                    <select id="selectBox" class="form-select" style="width: 123px;"
                        onchange="if(this.value) location.href=(this.value);">
                        <option value="/board/list">전체공고</option>
                        <option value="/board/list?keyword=lang">기술매칭</option>
                        <option value="#"> 인기순</option>
                        <option value="#"> 날짜순</option>
                    </select>
                </div>
                </c:if> --%>
                <!-- 카드 시작 -->
                <div class="p-3">
                    <div id="myScrapCheck" class="row gx-3">
                        <c:forEach items="${myScrapBoardList}" var="board">
                            <%-- <div id="cancleLove-${board.loveId}"> --%>
                                <div id="cancleLove-${board.loveId}" class="col-md-3 py-2">
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

                                          <%-- <div>(D-${board.dday == 0 ? 'Day' : board.dday})</div> --%>

                                        <c:if test="${principal.id != null and principal.role eq 'employee'}">
                                            <div><i id="heart-${board.id}"
                                                    class="fa-regular fa-heart my-xl my-cursor fa-lg ${board.css}"
                                                    value="${board.loveId}" onclick="heart(`${board.id}`)"></i></div>
                                        </c:if>
                                    </div>
                                </div>
                    </div>
                    <%-- </div> --%>
                        </c:forEach>

                </div>
        </div>

            </div>

            <script>
                scrapCheck()
                function scrapCheck() {
                    if ($("#myScrapCheck").children().length == 0) {
                        let el = `<div class="d-flex justify-content-center">
                                     <br>
                                     스크랩 공고가 없습니다.
                                 </div>
                                 <br>
                                 <br>
                                 <br>`
                        $("#myScrapCheck").append(el);
                    }
                }

                function heart(boardId) {
                    let id = ${ principal.id };
                    let targetHeart = "heart-" + boardId;
                    let heartId = $("#" + targetHeart).attr("value");
                    let board = { boardId: boardId }
                    // if ($("#"+targetHeart).hasClass("fa-solid")) {
                    if (heartId == 0) {

                        let deleteId;

                        $.ajax({
                            type: "post",
                            url: "/love",
                            data: JSON.stringify(board),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json"
                        }).done((res) => {
                            $("#" + targetHeart).addClass("fa-solid");
                            $("#" + targetHeart).removeClass("fa-regular");
                            $("#" + targetHeart).attr("value", res.data);
                        }).fail((err) => {
                            alert(err.responseJSON.msg);
                        });

                    } else {

                        $.ajax({
                            type: "delete",
                            url: "/love/" + heartId,
                            dataType: "json"
                        }).done((res) => {
                            $("#" + targetHeart).removeAttr("onclick")
                            $("#cancleLove-" + heartId).remove();
                            scrapCheck()
                        }).fail((err) => {
                            alert(err.responseJSON.msg);
                        });
                    }
                }






                function callPrev() {
                    // el표현식으로 값을 받지 못해 hidden에서 받아 옴
                    let keyword = $("#checkKeyword").val();
                    let currentPage = `${pagingDto.currentPage - 1}`
                    if (keyword) {
                        location.href = "/board/list?page=" + currentPage + "&keyword=" + keyword;
                    } else {
                        location.href = "/board/list?page=" + currentPage;
                    }
                }

                function callNext() {

                    let keyword = $("#checkKeyword").val();
                    let currentPage = `${pagingDto.currentPage + 1}`
                    if (keyword) {
                        location.href = "/board/list?page=" + currentPage + "&keyword=" + keyword;
                    } else {
                        location.href = "/board/list?page=" + currentPage;
                    }
                }

                function selectBoxCheck() {
                    let check = $("#checkKeyword").val()
                    if (check == "lang") {
                        $("#selectBox").val("/board/list?keyword=lang").prop("selected", true);
                    }
                }

                function mouseEnterImages(e) {
                    let id = e.getAttribute('id');
                    $("#" + id).addClass("border border-primary");
                }
                function mouseLeaveImages(e) {
                    let id = e.getAttribute('id');
                    $("#" + id).removeClass("border border-primary");
                }


                selectBoxCheck()
            </script>

            <%@ include file="../layout/footer.jsp" %>
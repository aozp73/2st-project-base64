<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <div style="height: 100px;"></div>
            <div class="container py-3 bg-white">
            <div class="border border-primary">
                <div class="my-boardDetail-header">
                    <div class="row">
                        <div class="col-md-6 mb-2">
                            <div class="my-4 ms-4 pt-1 ps-2">
                                <div class="d-flex align-items-start" style="font-size: 20px;">
                                    <div class="my-text-ellipsis">
                                        ${board.companyName}
                                    </div>
                                    <div class="ms-2"><i id="heart"
                                            class="fa-regular fa-heart my-xl my-cursor fa-sm"></i>
                                    </div>
                                </div>
                                <div class="my-text-ellipsis" style="font-size: 22px;">
                                    ${board.title}
                                </div>
                            </div>
                            <div class="my-boardDetail-header-sector1-grid">
                                <div>
                                    <div class="mb-4 ms-4 ps-2" style="color:#5C667B">
                                        <div>
                                            경력
                                        </div>
                                        <div>
                                            학력
                                        </div>
                                        <div>
                                            근무형태
                                        </div>
                                        <div>
                                            우대사항
                                        </div>
                                    </div>
                                </div>
                                <div style="width: 80%; color:#2D65F2;">
                                    <div class="my-text-ellipsis">
                                        ${board.careerString}
                                    </div>
                                    <div class="my-text-ellipsis">
                                        ${board.educationString}
                                    </div>
                                    <div class="my-text-ellipsis">
                                        ${board.jobTypeString}
                                    </div>
                                    <div class="my-text-ellipsis">
                                        ${board.favor}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                        </div>
                    </div>
                    <div class="pt-3 ps-1 my-borderDetail-color-left">
                        <div class="row d-flex justify-content-center mb-3 mt-3 pt-2"
                            style="height: 80px; flex: 1 1 auto;">
                            <div class="col-md-1"></div>
                            <div class="col-md-7">
                                <img class="card-img-top" style="max-width: 170px;" src="${board.profile == null ? "
                                    /images/defaultProfile.png" : board.profile}" alt="Card image">
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                        <div class="row ps-4">
                            <div class="col-md-5" style="color:#5C667B">
                                <div class="mb-2 my-text-ellipsis">회사이름
                                </div>
                                <div class="mb-2 my-text-ellipsis">회사규모
                                </div>
                                <div class="my-text-ellipsis">업종
                                </div>
                            </div>
                            <div class="col-md-6 ps-0">
                                <div class="mb-2 my-text-ellipsis">${board.companyName}</div>
                                <div class="mb-2 my-text-ellipsis">${board.companyScale}</div>
                                <div class="my-text-ellipsis">${board.companyField}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="container px-5 pt-5 pb-4 my-borderDetail-color-top">
                        <div>
                            <%-- <div class="d-flex justify-content-center mb-5">
                                <img class="img-fluid" style="height: 100%; width: auto;" src="" alt="Card image">
                        </div> --%>
                        <div>
                            ${board.content}
                            <br>
                        </div>
                    </div>
                    <c:if test="${principal.role eq 'employee'}" >
                    <div class="d-flex justify-content-center mt-5">
                        <div><button type="submit" class="btn btn-primary my-button-color-default"
                                data-bs-toggle="modal" data-bs-target="#portfolioModal1">지원하기</button></div>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
        </div>

        <!-- Portfolio Modal 1-->
        <div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" aria-labelledby="portfolioModal1"
            aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header border-0"><button class="btn-close" type="button" data-bs-dismiss="modal"
                            aria-label="Close"></button></div>
                    <div class="modal-body text-center pb-5">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-8">
                                    <!-- Portfolio Modal - Title-->
                                    <h2 class="portfolio-modal-title text-secondary text-uppercase mb-0">이력서 선택</h2>
                                    <!-- Icon Divider-->
                                    <div class="divider-custom">
                                        <div class="divider-custom-line"></div>
                                        <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                                        <div class="divider-custom-line"></div>
                                    </div>
                                    <!-- Portfolio Modal - Image-->
                                    <!-- Portfolio Modal - Text-->
                                    <table style="width: 100%;" class="mb-3">
                                        <table class="table table-hover">
                                            <thead class="my-bg-color-default">
                                                <tr class="text-center text-white">
                                                    <th style="width: 20%;">번호</th>
                                                    <th style="width: 70%;">이력서제목</th>
                                                    <th style="width: 10%;">선택</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- 반복문 -->
                                                <c:forEach items="${resumeList}" var="resume" varStatus="status">
                                                <tr>
                                                    <td class="text-center">${status.count}</td>
                                                    <td class="my-text-ellipsis">${resume.title}</td>
                                                    <td><button type="button"
                                                            class="badge bg-success my-border-color-default"
                                                            onclick="apply(${board.id}, ${resume.id})">지원</span>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </table>
                                    <button class="btn btn-primary" data-bs-dismiss="modal">
                                        <i class="fas fa-xmark fa-fw"></i>
                                        창닫기
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function apply(boardId, resumeId) {
                let data = {
                    boardId : boardId,
                    resumeId : resumeId,
                }
                $.ajax({
                    type: "post",
                    url: '/apply',
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                }).done((res) => {
                    alert(res.msg);
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>
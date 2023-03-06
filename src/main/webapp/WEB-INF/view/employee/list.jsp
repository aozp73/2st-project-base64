<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

      <%@ include file="../layout/header.jsp" %>
            <div style="height: 100px;"></div>
            <div class="container py-3 bg-white">
                  <div class="p-3">
                        <hr>
                        <h2 style="text-align: center;">인재 목록</h2>
                        <hr>
                  </div>

                  <c:choose>
                        <c:when test="${principal.role eq 'company' and recommendEmployeeList[0].id != null}">
                              <div class="border border-primary rounded-1 p-3 bg-primary">
                                    <h2 style="text-align: center; color: aliceblue;">구직자 추천</h2>
                                    <!-- 카드 들어갈 곳 -->
                                    <div class="row gx-3">
                                          <c:forEach items="${recommendEmployeeList}" var="recommendEmployee">
                                                <div class="col-md-3 py-2">
                                                      <div id="employee${recommendEmployee.id}"
                                                            onmouseenter="mouseEnterImages(this)"
                                                            onmouseleave="mouseLeaveImages(this)"
                                                            class="card col-lg-12">
                                                            <a href="/employee/${recommendEmployee.id}"
                                                                  class="no_under_line_link">
                                                                  <img class="card-img-top" style="height: 100px;"
                                                                        src="/images/profile.jfif" alt="Card image">
                                                                  <div class="card-body">
                                                                        <div class="my-text-ellipsis">
                                                                              <h5>${recommendEmployee.realName}</h5>
                                                                        </div>
                                                                        <div class="my-text-ellipsis">
                                                                              <c:choose>
                                                                                    <c:when
                                                                                          test="${recommendEmployee.career == 0}">
                                                                                          신입
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                          ${recommendEmployee.career} 년차
                                                                                    </c:otherwise>
                                                                              </c:choose>
                                                                        </div>
                                                                        <div class="my-text-ellipsis">
                                                                              ${recommendEmployee.title}
                                                                        </div>
                                                                  </div>
                                                            </a>
                                                            <div class="card-footer d-flex justify-content-between">
                                                                  <div>${recommendEmployee.createdAtToString}</div>
                                                                  <div><i id="heart"
                                                                              class="fa-regular fa-heart my-xl my-cursor fa-lg"></i>
                                                                  </div>
                                                            </div>
                                                      </div>
                                                </div>
                                          </c:forEach>
                                          <!-- 반복문 종료 -->
                                    </div>
                              </div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                  </c:choose>
                  <div class="p-3">
                        <div class="row">
                              <c:forEach items="${pagingDto.resumeListDtos}" var="employee">
                                    <div class="col-md-3 py-2">
                                          <div id="employee${employee.id}" onmouseenter="mouseEnterImages(this)"
                                                onmouseleave="mouseLeaveImages(this)" class="card col-lg-12">
                                                <a href="/employee/${employee.id}" class="no_under_line_link">
                                                      <img class="card-img-top" style="height: 100px;"
                                                            src="/images/profile.jfif" alt="Card image">
                                                      <div class="card-body">
                                                            <div class="my-text-ellipsis">
                                                            <c:choose>
                                                               <c:when test="${employee.realName eq null}">
                                                                  <h5>비공개</h5>
                                                               </c:when>
                                                            
                                                               <c:otherwise>
                                                                  <h5>${employee.realName}</h5>
                                                               </c:otherwise>
                                                            </c:choose>
                                                            </div>
                                                            <div class="my-text-ellipsis">
                                                                  <c:choose>
                                                                        <c:when test="${employee.career == 0}">
                                                                              신입
                                                                        </c:when>
                                                                        <c:when test="${employee.career eq null}">
                                                                              비공개
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                              ${employee.career} 년차
                                                                        </c:otherwise>
                                                                  </c:choose>
                                                            </div>
                                                            <div class="my-text-ellipsis">
                                                            <c:choose>
                                                                  <c:when test="${employee.title eq null}">
                                                                              비공개
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                  ${employee.title}
                                                                        </c:otherwise>
                                                                  </c:choose>
                                                            </div>
                                                      </div>
                                                </a>
                                                <div class="card-footer d-flex justify-content-between">
                                                      <div>${employee.createdAtToString}</div>
                                                      <div><i id="heart"
                                                                  class="fa-regular fa-heart my-xl my-cursor fa-lg"></i>
                                                      </div>
                                                </div>
                                          </div>
                                    </div>
                              </c:forEach>
                        </div>
                  </div>

                  <div class="d-flex justify-content-center">
                        <ul class="pagination">

                              <li class='page-item ${pagingDto.first ? "disabled" : ""}'><a class="page-link"
                                          href="javascript:void(0);" onclick="callPrev();">Prev</a></li>

                              <c:forEach var="num" begin="${pagingDto.startPageNum}" end="${pagingDto.lastPageNum}">

                                    <li class='page-item'><a class='page-link'
                                                href="/employee/list?page=${num-1}">${num}</a></li>
                              </c:forEach>

                              <li class='page-item ${pagingDto.last ? "disabled" : ""}'><a class="page-link"
                                          href="javascript:void(0);" onclick="callNext();">Next</a></li>

                        </ul>
                  </div>
            </div>
            <script>
                  function callPrev() {
                        let currentPage = `${pagingDto.currentPage - 1}`
                        location.href = "/employee/list?page=" + currentPage;
                  }

                  function callNext() {
                        let currentPage = `${pagingDto.currentPage + 1}`
                        location.href = "/employee/list?page=" + currentPage;
                  }


                  function mouseEnterImages(e) {
                        let id = e.getAttribute('id');
                        $("#" + id).addClass("border border-primary");
                  }

                  function mouseLeaveImages(e) {
                        let id = e.getAttribute('id');
                        $("#" + id).removeClass("border border-primary");
                  }
            </script>

            <%@ include file="../layout/footer.jsp" %>
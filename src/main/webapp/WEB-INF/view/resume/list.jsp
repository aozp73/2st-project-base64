<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

      <%@ include file="../layout/header.jsp" %>
      <div style="height: 100px;"></div>
            <div class="container mt-3">
                  <h2 style="text-align: center;">이력서 목록</h2>
                  <c:choose>
                        <c:when test="${resumeList != null}">
                              <table class="table table-striped">
                                    <thead>
                                          <tr>
                                                <th>제목</th>
                                                <th>내용 미리보기</th>
                                                <th>작성일</th>
                                          </tr>
                                    </thead>
                                    <tbody>
                                          <c:forEach items="${resumeList}" var="resume">
                                                <tr>
                                                      <td><a href="/resume/${resume.id}">${resume.title}</a></td>
                                                      <td>${resume.preview}</td>
                                                      <td>${resume.createdAtToString}</td>
                                                      <td><button type="button" class="badge bg-danger my-border-color-warning" onclick="deleteResume(${resume.id})">삭제</span></td>
                                                </tr>
                                          </c:forEach>
                                    </tbody>
                              </table>
                        </c:when>
                        <c:otherwise>
                              <div>
                                    <h2 style="text-align: center;">이력서가 없어요!</h2>
                              </div>
                        </c:otherwise>
                  </c:choose>
            </div>
            <div class="row p-3">
                  <div class="col-sm-4 b-3 p-3">
                  </div>
                  <div class="col-sm-4">
                        <button type="button" class="col-12 btn btn-outline-info"><a href="/resume/saveForm">새로운 이력서 작성</a></button>
                  </div>
                  <div class="col-sm-4 b-3 p-3">
                  </div>
            </div>

            <script>
        function deleteResume(id) {
            $.ajax({
                type: "delete",
                url: "/resume/"+id+"/delete"
            }).done(res => {
                alert(res.msg);
            location.reload();
            }).fail(err => {
                alert(err.JSON)
            });
        }
    </script>
            <%@ include file="../layout/footer.jsp" %>
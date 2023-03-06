<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <div style="height: 100px;"></div>
        <div class="container py-3 bg-white">
            <div class="mt-3">
                <div class="row">
                    <div class=" col-sm-4 p-3">
                        <div class="border border-primary rounded-1 m-3" style="height: 100%;">
                            <div class="col-12 p-3">
                                <div class="row">
                                    <div class="col-sm-5 m-3">
                                        <h3>
                                            <c:choose>
                                                <c:when test="${employee.realName == null}">
                                                    비공개
                                                </c:when>
                                                <c:otherwise>
                                                    ${employee.realName}
                                                </c:otherwise>
                                            </c:choose>
                                        </h3>
                                    </div>
                                    <div class="col-sm-5">
                                        <img src="${employee.profile == null ? " /images/newjeans.jpg" :
                                            employee.profile}" class="rounded-circle"
                                            style="max-width: 100%; height: auto;">
                                    </div>
                                </div>
                            </div>
                            <div class="col-10 m-3 p-3">
                                <h3 class="mt-3">인적사항</h3>
                                <hr class="bg-primary">
                                <ul class="nav nav-pills flex-column">
                                    <li class="nav-item">
                                        <div class="row">
                                            <div class="col-3 ps-3 pe-0">
                                                주소 :
                                            </div>
                                            <div class="col-9 ps-0">
                                                <c:choose>
                                                    <c:when test="${employee.address == null}">
                                                        비공개
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${employee.address}
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${employee.detailAddress == null}">
                                                        비공개
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${employee.detailAddress}
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </li>
                                    <br>
                                    <li class="nav-item">
                                        <div class="row">
                                            <div class="col-3 ps-3 pe-0">
                                                학력 :
                                            </div>
                                            <div class="col-9 ps-0">
                                                4년제 대학 졸업
                                            </div>
                                        </div>
                                    </li>

                                </ul>
                            </div>
                            <div class="col-10 m-3 p-3">
                                <h3 class="mt-3">연락처</h3>
                                <hr class="bg-primary">
                                <ul class="nav nav-pills flex-column">
                                    <li class="nav-item">
                                        <div class="row">
                                            <div class="col-3 ps-3 pe-0">
                                                Tel :
                                            </div>
                                            <div class="col-9">
                                                <c:choose>
                                                    <c:when test="${employee.tel == null}">
                                                        비공개
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${employee.tel}
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="nav-item">
                                        <div class="row">
                                            <div class="col-3 ps-3 pe-0">
                                                email :
                                            </div>
                                            <div class="col-9">
                                                <c:choose>
                                                    <c:when test="${employee.email == null}">
                                                        비공개
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${employee.email}
                                                    </c:otherwise>
                                                </c:choose>
                                                <!-- 이메일이 길어질경우 후에 간격 수정가능 -->
                                            </div>
                                        </div>
                                    </li>
                                </ul>

                            </div>
                            <div class="col-10 m-3 p-3">
                                <h3 class="mt-3">경력 및 기술</h3>
                                <hr class="bg-primary">
                                <ul class="nav nav-pills flex-column">
                                    <li class="nav-item">
                                        <div class="row">
                                            <div class="col-3 ps-3 pe-0">
                                                경력 :
                                            </div>
                                            <div class="col-9 ps-0">
                                                <c:choose>
                                                    <c:when test="${employee.career == 0}">
                                                        신입
                                                    </c:when>
                                                    <c:otherwise>
                                                        개발직종 ${employee.career} 년차
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="nav-item">
                                        <div class="row">
                                            <div class="col-3 ps-3 pe-0">
                                                기술 :
                                            </div>
                                            <div class="col-9 ps-0">
                                                <div>
                                                    <c:forEach items="${employeeTech}" var="employeeTech">
                                                        <div class="me-2">
                                                            <span class="badge bg-success">${employeeTech}</span>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                </ul>
                            </div>

                            <hr class="d-sm-none">
                        </div>
                    </div>
                    <div class="col-sm-8 p-3 ">
                        <div class="p-3 m-3 border border-primary rounded-1">
                            <h2 class="p-3 text-center"><b>자기 소개</b></h2>
                            <hr class="bg-primary">
                            <h5>
                                <c:choose>
                                    <c:when test="${employee.title == null}">
                                        비공개
                                    </c:when>
                                    <c:otherwise>
                                        ${employee.title}
                                    </c:otherwise>
                                </c:choose>
                            </h5>

                            <p>
                                <c:choose>
                                    <c:when test="${employee.content == null}">
                                        비공개
                                    </c:when>
                                    <c:otherwise>
                                        ${employee.content}
                                    </c:otherwise>
                                </c:choose>
                            </p>

                        </div>
                        <!-- <div class="p-3 m-3 border border-primary rounded-1">
                            <h2 class="p-3 text-center"><b>경력 사항</b></h2>
                            <hr class="bg-primary">
                            <h5>핵심 경력</h5>
                            <ul class="nav nav-pills flex-column">
                                <li class="nav-item">
                                    <div class="row">
                                        <div class="col-2 ps-3 pe-0">
                                            경력 :
                                        </div>
                                        <div class="col-9 ps-0">
                                            <c:choose>
                                                <c:when test="${employee.career == 0}">
                                                    신입
                                                </c:when>
                                                <c:otherwise>
                                                    개발직종 ${employee.career} 년차
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </li>
                                <h5>보유 기술</h5>
                                <div class="d-flex">
                                    <c:forEach items="${employeeTech}" var="employeeTech">
                                        <div class="me-2">
                                            <span class="badge bg-success">${employeeTech}</span>
                                        </div>
                                    </c:forEach>
                                </div>
                            </ul>
                        </div>
                        <c:choose>
                            <c:when test="${principal.role eq 'company'}">
                                <div class="row p-3">
                                    <div class="col-sm-6 b-3 p-3">
                                    </div>
                                    <div class="col-sm-2 b-3 p-3">
                                    </div>
                                    <div class="col-sm-2 ">
                                        <button type="button" class="col-12 btn btn-outline-info">채용</button>
                                    </div>
                                    <div class=" col-sm-2 ">
                                        <button type="button" class="col-12 btn btn-outline-danger">비채용</button>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div> -->
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../layout/footer.jsp" %>
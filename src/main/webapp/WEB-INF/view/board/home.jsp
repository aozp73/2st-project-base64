<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <header class="masthead bg-primary text-white text-center">
            <div class="container d-flex align-items-center flex-column">
                <!-- Masthead Heading-->
                <h1 class="masthead-heading text-uppercase mb-0">Start Your Career</h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Masthead Subheading-->
                <p class="masthead-subheading font-weight-light mb-0">빠르고 쉽게 구인 구직</p>
            </div>
        </header>
        <!-- About Section-->
        <section class="page-section bg-primary text-white mb-0" id="about">
            <div class="container">
                <!-- About Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-white">사이트 정보</h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- About Section Content-->
                <div class="row">
                    <div class="col-lg-4 ms-auto">
                        <p class="lead">프로그래머인데 일자리를 구하기 힘들다구요?<br> 여기 여러분들을 위한 일자리가 준비되어 있습니다!</p>
                    </div>
                    <div class="col-lg-4 me-auto">
                        <p class="lead">IT업계 사장님이신데 좋은 구직자를 구하기 <br>힘드신가요?<br> 여기 모든 프로그래머들이 있습니다!</p>
                    </div>
                </div>
                <!-- About Section Button-->
                <div class="text-center mt-4">
                    <a class="btn btn-xl btn-outline-light" href="/employee/joinForm">
                        <i class="fa-solid fa-user me-2"></i>
                        회원가입하기
                    </a>
                </div>
            </div>
        </section>
        <c:choose>
            <c:when test="${principal == null}">

                <!-- Contact Section-->
                <section class="page-section bg-white" id="login">
                    <div class="container">
                        <!-- Contact Section Heading-->
                        <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Login</h2>
                        <!-- Icon Divider-->
                        <div class="divider-custom">
                            <div class="divider-custom-line"></div>
                            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                            <div class="divider-custom-line"></div>
                        </div>
                        <!-- Contact Section Form-->
                        <div class="row justify-content-center">
                            <div class="col-lg-8 col-xl-7">
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- * * SB Forms Contact Form * *-->
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- This form is pre-integrated with SB Forms.-->
                                <!-- To make this form functional, sign up at-->
                                <!-- https://startbootstrap.com/solution/contact-forms-->
                                <!-- to get an API token!-->
                                <form action="/login" method="post">
                                    <!-- Name input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="username" type="text"
                                            placeholder="Enter your name..." />
                                        <label for="name">Username</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is
                                            required.</div>
                                    </div>
                                    <!-- Email address input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="password" type="password"
                                            placeholder="Enter password..." />
                                        <label for="email">Password</label>
                                    </div>
                                    <!-- Submit Button-->
                                    <button class="btn btn-primary btn-xl" id="submitButton"
                                        type="submit">Login</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </c:when>

            <c:otherwise>
            </c:otherwise>
        </c:choose>
        <%@ include file="../layout/footer.jsp" %>
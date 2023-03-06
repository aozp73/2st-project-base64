<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div style="height: 100px;"></div>

            <div class="container py-3 bg-white">
            <div class="justify-content-center d-flex py-3">
                <hr>
                <h2>회원가입</h2>
                <hr>
            </div>
            <div class="justify-content-center d-flex">
                <nav class="navbar navbar-expand-sm bg-light navbar-light" style="width: 50%;">
                    <div class="container-fluid">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link" href="/employee/joinForm">일반회원</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">기업회원</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="justify-content-center d-flex mb-3">
                <div class="my-border-color-default px-3 py-3" style="width: 50%; ">
                    <form method="post" action="/company/join" onsubmit="return valid()">
                        <div class="mb-3">
                            <label class="form-label">사업자 등록번호</label>
                            <input type="text" name="companyNumb" class="form-control"
                                placeholder="Company Registration Number">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">회사명</label>
                            <input type="text" name="companyName" class="form-control" placeholder="Company Name">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">회사주소</label>
                            <input type="text" name="address" class="form-control" placeholder="Company Address">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">상세주소</label>
                            <input type="text" name="detailAddress" class="form-control"
                                placeholder="Company Detail Address">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">아이디</label>
                            <input id="username" type="text" name="username" class="form-control" placeholder="Username"
                                onchange="checkUsername()">
                        </div>
                        <div id="usernameCheck"></div>
                        <div class="mb-3">
                            <label class="form-label">비밀번호</label>
                            <input type="password" name="password" id="password" class="form-control"
                                placeholder="Password" onchange="checkSamePassword()">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">비밀번호확인</label>
                            <input type="password" class="form-control" id="passwordCheck"
                                placeholder="Confirm Password" onchange="checkSamePassword()">
                        </div>
                        <div id="passwordCheckAlert">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">이메일</label>
                            <input type="email" name="email" class="form-control" placeholder="Email">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">이용 약관</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3">
Lorem ipsum dolor sit amet consectetur, adipisicing elit. Deleniti ea incidunt ratione voluptates enim. Voluptatem optio suscipit neque cumque omnis amet maiores possimus, recusandae laudantium pariatur eveniet nostrum dignissimos eligendi!
Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reprehenderit laudantium provident laborum ipsam impedit, dolorum sit itaque sunt mollitia aspernatur, assumenda perferendis excepturi amet, corporis dicta molestias nostrum? Esse, perspiciatis! 
                    </textarea>
                        </div>
                        <div class="mb-3 form-check d-flex justify-content-end">
                            <input type="checkbox" class="form-check-input">
                            <label class="form-check-label ms-2">동의합니다</label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">개인 정보 수집 및 이용 약관</label>
                            <textarea class="form-control" rows="3">
Lorem ipsum dolor sit amet consectetur, adipisicing elit. Deleniti ea incidunt ratione voluptates enim. Voluptatem optio suscipit neque cumque omnis amet maiores possimus, recusandae laudantium pariatur eveniet nostrum dignissimos eligendi!
Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reprehenderit laudantium provident laborum ipsam impedit, dolorum sit itaque sunt mollitia aspernatur, assumenda perferendis excepturi amet, corporis dicta molestias nostrum? Esse, perspiciatis! 
                        </textarea>
                        </div>
                        <div class="mb-3 form-check d-flex justify-content-end">
                            <input type="checkbox" class="form-check-input">
                            <label class="form-check-label ms-2">동의합니다</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary my-button-color-default">회원가입</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>

            // 아이디 중복확인 
            let checkUser = false;

            function checkUsername() {
                let username = $("#username").val();

                $.ajax({
                    type: "get",
                    url: "/usernameSameCheck?username=" + username,
                }).done((res) => {
                    if (res.data == true) {
                        $("#usernameCheck").empty();
                        let el = `<div class="alert alert-success" id="usernameCheck">
                                  <strong>`+ res.msg + `</strong>
                                  </div>`;
                        $("#usernameCheck").append(el);
                        checkUser = true;
                    } else {
                        $("#usernameCheck").empty();
                        let el = `<div class="alert alert-danger" id="usernameCheck">
                                  <strong>`+ res.msg + `</strong>
                                  </div>`;
                        $("#usernameCheck").append(el);
                        checkUser = false;
                    }
                }).fail((err) => {

                });
            }
            // ~ 아이디 중복확인  


            // 비밀번호 확인 체크
            let checkPassword = false;

            function valid() {
                if (checkUser == false) {
                    alert("유저네임을 확인해주세요")
                    return false;
                }

                if (checkPassword == false) {
                    alert("비밀번호 확인란을 확인해주세요");
                    return false;
                }

                return true;
            }

            function checkSamePassword() {
                let password = $("#password").val();
                let passwordCheck = $("#passwordCheck").val();
                if (password == passwordCheck) {
                    checkPassword = true;
                    $("#passwordCheckAlert").empty();
                    let el = `<div class="alert alert-success" id="passwordCheckAlert">
                              <strong>비밀번호 확인 완료!</strong>
                              </div>`;
                    $("#passwordCheckAlert").append(el);
                } else {
                    checkPassword = false;
                    $("#passwordCheckAlert").empty();
                    let el = `<div class="alert alert-danger">
                              <strong>비밀번호가 다릅니다!</strong>
                              </div>`;
                    $("#passwordCheckAlert").append(el);
                }
            }
            // ~ 비밀번호 확인 체크
        </script>

        <%@ include file="../layout/footer.jsp" %>
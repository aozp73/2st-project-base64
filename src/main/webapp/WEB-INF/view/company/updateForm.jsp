<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <div style="height: 100px;"></div>
        <div class="container py-3 bg-white">
            <div class="p-3">
                <hr>
                <h2 style="text-align: center;">회원 정보 수정</h2>
                <hr>
            </div>
            <div class="justify-content-center d-flex mb-3">
                <div class="border border-primary rounded-1 px-3 py-3" style="width: 50%; ">
                    <form method="post" action="/company/update" enctype="multipart/form-data"
                        onsubmit="return valid()">

                        <div class="row">

                            <div class="col-md-5">
                                <div class="mb-3">
                                    <label for="exampleInputCompanyName" class="form-label">회사명</label>
                                    <input type="text" name="companyName" class="form-control"
                                        value="${companyDto.companyName}" placeholder="Company Name">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">회사규모</label>
                                    <select class="form-select" name="companyScale">
                                        <option value="대기업">대기업</option>
                                        <option value="중견기업">중견기업</option>
                                        <option value="중소기업">중소기업</option>
                                        <option value="스타트업">스타트업</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">업종</label>
                                    <select class="form-select" name="companyField">
                                        <option value="웹개발">웹 개발</option>
                                        <option value="게임개발">게임 개발</option>
                                        <option value="SW개발">SW 개발</option>
                                        <option value="앱개발">앱 개발</option>
                                    </select>
                                </div>

                            </div>

                            <div class="col-md-7">
                                <div class="container pt-4 ps-4 mt-2">

                                    <!-- <form id="profileForm" method="post" action="/user/profileUpdate"
                                        enctype="multipart/form-data"> -->
                                    <div class="d-flex justify-content-center">
                                        <div class="form-group d-flex justify-content-center my-thumbnail-color-default "
                                            style="height: 150px; width: 150px; align-items: center">

                                            <img id="imagePreview" src="${principal.profile == null ? "
                                                /images/defaultProfile.png" : principal.profile}" alt="Current Photo"
                                                class="img-fluid" style="height:80px; ">
                                        </div>
                                    </div>

                                    <div class="form-group mt-4 d-flex justify-content-center">
                                        <input type="file" class="form-control" id="thumbnail" name="profile"
                                            onchange="chooseImage(this)" style="width: 250px; height: 35px; ">
                                    </div>

                                    <!-- <div class="d-flex justify-content-center mt-3">
                                            <button type="submit" class="btn btn-primary btn-sm">사진변경</button>
                                        </div> -->

                                    <!-- </form> -->
                                </div>
                            </div>

                        </div>


                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">변경 비밀번호</label>
                            <input type="password" name="password" id="password" class="form-control"
                                placeholder="Password" onchange="checkSamePassword()" style="width:100%">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">비밀번호확인</label>
                            <input type="password" class="form-control" id="passwordCheck"
                                placeholder="Confirm Password" onchange="checkSamePassword()" style="width:100%">
                        </div>
                        <div id="passwordCheckAlert">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputEmail" class="form-label">이메일</label>
                            <input type="email" name="email" class="form-control" value="${companyDto.email}"
                                placeholder="Email" style="width:100%">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputCompanyAddress" class="form-label">회사주소</label>
                            <input type="text" name="address" class="form-control" value="${companyDto.address}"
                                placeholder="Company Address">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputCompanyAddress" class="form-label">상세주소</label>
                            <input type="text" name="detailAddress" class="form-control"
                                value="${companyDto.detailAddress}" placeholder="Company Address">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputTelNumber" class="form-label">전화번호</label>
                            <input type="text" name="tel" class="form-control" value="${companyDto.tel}"
                                placeholder="하이픈(-) 없이 입력해주세요"
                                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary my-button-color-default">수정완료</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>



            let checkPassword = false;
            function valid() {
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
            function chooseImage(obj) {
                // console.log(obj);
                // console.log(obj.files);
                let f = obj.files[0];

                if (!f.type.match("image.*")) {
                    alert("이미지를 등록해야 합니다.");
                    return;
                }

                let reader = new FileReader();
                reader.readAsDataURL(f);

                reader.onload = function (e) {
                    console.log(e);
                    console.log(e.target.result);
                    $("#imagePreview").attr("src", e.target.result);
                }
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
<div style="height: 100px;"></div>
            <div class="container py-3 bg-white">
            <div class="p-3">
                <hr>
                <h2 style="text-align: center;">회원 정보 수정</h2>
                <hr>
                </div>
            <div class="justify-content-center d-flex">
                <div class="border border-primary rounded-1 px-3 py-3" style="width: 50%; ">
                    <form action="/employee/update/${employeeDto.id}" method="post" enctype="multipart/form-data" onsubmit="return valid()">
                    <input id="id" name="id" value="${employeeDto.id}" type="hidden">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="mb-3">
                                <label for="exampleInputPassword" class="form-label">변경 비밀번호</label>
                                <input type="password" name="password" id="password" class="form-control"
                                     placeholder="Password" onchange="checkSamePassword()">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">비밀번호확인</label>
                                <input type="password" class="form-control" id="passwordCheck" placeholder="Confirm Password"
                                    onchange="checkSamePassword()">
                            </div>
                            <div id="passwordCheckAlert">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">이메일</label>
                                <input name="email" id="email" type="email" class="form-control" placeholder="Email"
                                    value="${employeeDto.email}">
                            </div>
                        </div>
                        <div class="col-md-7">
                                <div class="d-flex justify-content-center">
                                    <div class="form-group d-flex justify-content-center my-thumbnail-color-default "
                                        style="height: 150px; width: 150px; align-items: center">

                                    <img id="imagePreview" src="${principal.profile == null ? "/images/defaultProfile.png" : principal.profile}" alt="Current Photo"
                                                    class="img-fluid" style="height:80px; ">
                                    </div>
                                </div>

                                <div class="form-group mt-4 d-flex justify-content-center">
                                    <input type="file" class="form-control" id="profile" name="profile"
                                                onchange="chooseImage(this)" style="width: 250px; height: 35px; ">
                                </div>
                        </div>
                    </div>


                        <div class="mb-3">
                            <label class="form-label">학력</label>
                            <select name="education" id="education" type="text" class="form-select">
                                <option value="비공개">비공개</option>
                                <option value="고졸">고졸</option>
                                <option value="2~3년제 대졸">2~3년제 대졸</option>
                                <option value="4년제 대졸">4년제 대졸</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">실명</label>
                            <input name="realName" id="realName" type="text" class="form-control" placeholder="realName"
                                value="${employeeDto.realName}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">주소</label>
                            <input name="address" id="address" type="text" class="form-control" placeholder="Address"
                                value="${employeeDto.address}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">상세주소</label>
                            <input name="detailAddress" id="detailAddress" type="text" class="form-control" placeholder="Detail Address"
                                value="${employeeDto.detailAddress}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">연락처</label>
                            <input name="tel" id="tel" type="text" class="form-control" placeholder="Tel" value="${employeeDto.tel}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">경력 연차</label>
                            <input name="career" id="career" type="number" class="form-control" placeholder="Career Year" min="0"
                                value="${employeeDto.career}">
                        </div>
                        <label class="form-label">관심분야</label>
                        <div class="mb-5" style="display: grid;
                                        grid-template-columns: 8fr 2fr;">
                            <div>
                                <div class="mb-3"></div>
                                <div class="mb-3">
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang1" value="1"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox1">Java</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang2" value="2"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox2">C#</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang3" value="3"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox1">Python</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang4" value="4"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox2">PHP</label>
                                    </div>
                                </div>
                                <div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang5" value="5"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox1">JS</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang6" value="6"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox2">Ruby</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang7" value="7"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox1" style="text-size-adjust: 12px;">Assembly</label>
                                    </div>
                                    <div class="form-check form-check-inline" style="width: 19%">
                                        <input class="form-check-input" type="checkbox" id="lang8" value="8"
                                                name="lang">
                                        <label class="form-check-label" for="inlineCheckbox2">SQL</label>
                                    </div>
                                </div>
                            </div>
                            <div>
                            <button type="button" class="badge bg-primary my-button-color-default" onclick="updateEmployeeTech()">관심 분야 변경</button>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button class="btn btn-primary my-button-color-default">정보 수정</button>
                        <a href="/resume/list">
                            <div class="btn btn-secondary ms-3">이력서 수정</div>
                        </a>
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
                </script>
                <script>
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
                <script>
                var employeeSkillArr = ${ employeeSkill };

                employeeSkillArr.forEach(num => {
                var lang = num
                var Techid = "lang" + num
                $("#" + Techid).attr("checked", true);
                });
                </script>
                
                <script>
                function updateEmployeeTech() {
                let employeeId = $("#id").val();
                let checkedValues = [];

                $('input[name="lang"]:checked').each(function () {
                    checkedValues.push($(this).val());
                });

                let employeeTechUpdateReqDto = {
                    checkedValues: checkedValues
                }

                $.ajax({
                    type: "put",
                    url: "/employee/update/tech/" + employeeId,
                    data: JSON.stringify(employeeTechUpdateReqDto),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done((res) => {
                    console.log(employeeTechUpdateReqDto);
                    alert(res.msg);
                    location.reload();
                }).fail((err) => {
                    console.log(employeeTechUpdateReqDto);
                    console.log(err);

                    alert("수정 실패");
                });
                }
                </script>

        <%@ include file="../layout/footer.jsp" %>
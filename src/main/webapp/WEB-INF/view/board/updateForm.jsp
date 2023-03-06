<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
<div style="height: 100px;"></div>

        <form>
            <input id="id" type="hidden" name="id" value="${boardDetail.id}">
            <input id="userId" type="hidden" name="userId" value="${boardDetail.userId}">
            <div class="container py-3 px-3">
                <div class="justify-content-center d-flex my-3 py-3">
                    <h2>
                        기업 공고 수정
                    </h2>
                </div>

                <div class="my-border-color-default px-3 py-3" style="width: 100%; ">

                    <div class="px-3">
                        <div class="title column py-3">
                            <input id="title" name="title" value="${boardDetail.title}"
                                class="title form-control my-border-color-default" type="text"
                                placeholder="제목을 입력해주세요.">
                        </div>


                        <div class="content column py-4 ps-3  my-border-color-default mb-3" style="display: grid;
                grid-template-columns: 1fr 1fr 1fr 1fr;">

                            <div class="ms-1 ps-2" style="display: grid; grid-template-columns: 2fr 7.3fr;">

                                <div class="mt-1">
                                    <div>경력</div>

                                </div>
                                <div class="mt-1">
                                    <div>
                                        <select id="careerString" name="careerString"
                                            class="career my-border-color-default" style="width: 80%;">
                                            <option ${boardDetail.careerString=="신입" ? "selected" : "" }>신입</option>
                                            <option ${boardDetail.careerString=="1년이상 ~ 3년미만" ? "selected" : "" }>1년이상 ~
                                                3년미만</option>
                                            <option ${boardDetail.careerString=="3년이상 ~ 5년미만" ? "selected" : "" }>3년이상 ~
                                                5년미만</option>
                                            <option ${boardDetail.careerString=="6년이상" ? "selected" : "" }>6년이상</option>
                                            <option ${boardDetail.careerString=="경력무관" ? "selected" : "" }>경력무관</option>
                                        </select>
                                    </div>

                                </div>

                            </div>


                            <div class="ms-1 ps-3" style="display: grid; grid-template-columns: 2fr 7.3fr;">

                                <div class="mt-1">

                                    <div>학력</div>
                                </div>
                                <div class="mt-1">

                                    <div>
                                        <select id="educationString" name="educationString"
                                            class=" field my-border-color-default" style="width: 80%;">
                                            <option ${boardDetail.educationString=="고졸이상" ? "selected" : "" }>고졸이상
                                            </option>
                                            <option ${boardDetail.educationString=="2~3년 대졸이상" ? "selected" : "" }>2~3년
                                                대졸이상</option>
                                            <option ${boardDetail.educationString=="4년 대졸이상" ? "selected" : "" }>4년 대졸이상
                                            </option>
                                            <option ${boardDetail.educationString=="학력무관" ? "selected" : "" }>학력무관
                                            </option>
                                        </select>
                                    </div>
                                </div>

                            </div>






                            <div>

                                <div class="ms-1 ps-2" style="display: grid; grid-template-columns: 3.5fr 7.3fr;">

                                    <div class="mt-1">
                                        <div>근무형태</div>

                                    </div>
                                    <div class="mt-1">
                                        <div>
                                            <select id="jobTypeString" name="jobTypeString"
                                                class="career my-border-color-default" style="width: 85%;">
                                                <option ${boardDetail.jobTypeString=="인턴" ? "selected" : "" }>인턴
                                                </option>
                                                <option ${boardDetail.jobTypeString=="정규직" ? "selected" : "" }>정규직
                                                </option>
                                            </select>
                                        </div>


                                    </div>
                                </div>

                            </div>

                            <div>

                                <div class="ms-1 ps-3" style="display: grid; grid-template-columns: 4fr 8fr;">

                                    <div class="mt-1 me-2">
                                        <div>마감일자</div>
                                    </div>
                                    <div>

                                        <div>
                                            <div class="Deadline">
                                                <input type="date" name="deadline"
                                                    class="form-control text-primary my-border-color-default"
                                                    style="width:90%" id="deadline" value="${boardDetail.deadline}">
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>



                        </div>




                        <div class="content column py-4 ps-2  my-border-color-default mb-3"
                            style="display: grid; grid-template-columns: 5.5fr 7fr;">


                            <div>
                                <div class="ms-1 ps-3 mt-2" style="display: grid; grid-template-columns: 2fr 7fr;">

                                    <div>
                                        <div></div>
                                        <div>우대사항</div>
                                    </div>
                                    <div>
                                        <div>

                                        </div>
                                        <div>
                                            <div class="form-group me-4">
                                                <textarea id="favor" name="favor" class="form-control"
                                                    id="exampleFormControlTextarea1"
                                                    rows="3">${boardDetail.favor}</textarea>
                                            </div>

                                        </div>

                                    </div>
                                </div>

                            </div>


                            <div class="ps-4" style="display: grid; grid-template-columns: 0.8fr 9fr;">


                                <div></div>

                                <div class="ms-2">
                                    <div class="mb-1"></div>
                                    <div class="mb-3">
                                        관심분야
                                    </div>

                                    <div class="mb-2">
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
                                            <label class="form-check-label" for="inlineCheckbox1">Assembly</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="lang8" value="8"
                                                name="lang">
                                            <label class="form-check-label" for="inlineCheckbox2">SQL</label>
                                        </div>
                                    </div>


                                    <div>
                                    </div>


                                </div>





                            </div>


                        </div>



                        <form class="mb-1 mt-4">
                            <div class="form-group">
                                <textarea class="form-control summernote" rows="5" name="content"
                                    id="content">${boardDetail.content}</textarea>
                        </form>

                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="btn-group m-4" role="group" aria-label="Basic example ">
                        <button type="button" class="btn btn-primary" id="btnSave" onclick="updateBoard()">완료</button>
                    </div>
                    <div class="btn-group m-4" role="group" aria-label="Basic example ">
                        <button id="btnCancel" type="button" class="btn btn-secondary"
                            onclick="location.href='/board/list'">취소</button>
                    </div>



                </div>
            </div>
        </form>
        <script>
            var boardSkillArr = ${ boardSkill };

            boardSkillArr.forEach(num => {
                var lang = num
                var id = "lang" + num
                $("#" + id).attr("checked", true);
            });

            function updateBoard() {
                let boardId = `${boardDetail.id}`
                let title = $("#title").val();
                let content = $("#content").val();
                let careerString = $("#careerString").val();
                let educationString = $("#educationString").val();
                let jobTypeString = $("#jobTypeString").val();
                let favor = $("#favor").val();
                let userId = $("#userId").val();
                let deadline = $("#deadline").val();

                let checkedValues = [];
                $('input[name="lang"]:checked').each(function () {
                    checkedValues.push($(this).val());
                });

                let board = {
                    id: boardId,
                    title: title,
                    content: content,
                    careerString: careerString,
                    educationString: educationString,
                    jobTypeString: jobTypeString,
                    favor: favor,
                    userId: userId,
                    deadline: deadline,
                    checkedValues: checkedValues
                };


                $.ajax({
                    type: "put",
                    url: "/board/update/" + boardId,
                    data: JSON.stringify(board),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/board/" + boardId;
                }).fail((err) => {
                      alert(err.responseJSON.msg);
                });
            }

            $('.summernote').summernote({
                tabsize: 2,
                height: 500
            });
        </script>


        <%@ include file="../layout/footer.jsp" %>
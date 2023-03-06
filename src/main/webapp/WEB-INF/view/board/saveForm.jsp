<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
<div style="height: 100px;"></div>

        <form action="/board/save" method="post">

            <div class="container py-3 px-3">
                <div class="justify-content-center d-flex my-3 py-3">
                    <h2>
                        기업 공고 등록
                    </h2>
                </div>

                <div class="my-border-color-default px-3 py-3" style="width: 100%; ">

                    <div class="px-3">
                        <div class="title column py-3">
                            <input id="title" name="title" class="title form-control my-border-color-default"
                                type="text" placeholder="제목을 입력해주세요.">
                        </div>


                        <div class="content column py-4 ps-3  my-border-color-default mb-3" style="display: grid;
                grid-template-columns: 1fr 1fr 1fr 1fr;">

                            <div class="ms-1 ps-2" style="display: grid; grid-template-columns: 2fr 7.3fr;">

                                <div class="mt-1">
                                    <div>경력</div>

                                </div>
                                <div class="mt-1">
                                    <div>
                                        <select name="careerString" class="career my-border-color-default"
                                            style="width: 80%;">
                                            <option>경력선택</option>
                                            <option>신입</option>
                                            <option>1년이상 ~ 3년미만</option>
                                            <option>3년이상 ~ 5년미만</option>
                                            <option>6년이상</option>
                                            <option>경력무관</option>
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
                                        <select name="educationString" class="field my-border-color-default"
                                            style="width: 80%;">
                                            <option>학력선택</option>
                                            <option>고졸이상</option>
                                            <option>2~3년 대졸이상</option>
                                            <option>4년 대졸이상</option>
                                            <option>학력무관</option>
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
                                            <select name="jobTypeString" class="career my-border-color-default"
                                                style="width: 85%;">
                                                <option>근무형태</option>
                                                <option>인턴</option>
                                                <option>정규직</option>
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
                                                <input type="date" name="deadline";
                                                    class="form-control text-primary my-border-color-default"
                                                    style="width:90%" id="Deadline">
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
                                                <textarea name="favor" class="form-control"
                                                    id="exampleFormControlTextarea1" rows="3"></textarea>
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
                                            <input class="form-check-input" type="checkbox" id="java" value="1"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox1">Java</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="cLang" value="2"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox2">C#</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="python" value="3"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox1">Python</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="php" value="4"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox2">PHP</label>
                                        </div>
                                    </div>

                                    <div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="jsc" value="5"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox1">JS</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="ruby" value="6"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox2">Ruby</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="assemblyLang" value="7"
                                                name="checkLang">
                                            <label class="form-check-label" for="inlineCheckbox1">Assembly</label>
                                        </div>
                                        <div class="form-check form-check-inline" style="width: 19%">
                                            <input class="form-check-input" type="checkbox" id="sqlLang" value="8"
                                                name="checkLang">
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
                                    id="content"></textarea>
                        </form>

                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="btn-group m-4" role="group" aria-label="Basic example ">
                        <button type="submit" class="btn btn-primary" id="btnSave">완료</button>
                    </div>
                    <div class="btn-group m-4" role="group" aria-label="Basic example ">
                        <button id="btnCancel" type="button" class="btn btn-secondary"
                            onclick="location.href='/board/list'">취소</button>
                    </div>



                </div>
            </div>
        </form>
        <script>
            $('.summernote').summernote({
                tabsize: 2,
                height: 500
            });
        </script>


        <%@ include file="../layout/footer.jsp" %>
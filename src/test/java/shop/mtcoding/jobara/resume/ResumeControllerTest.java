package shop.mtcoding.jobara.resume;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.resume.dto.ResumeReq.ResumeSaveReq;
import shop.mtcoding.jobara.resume.dto.ResumeReq.ResumeUpdateReq;
import shop.mtcoding.jobara.resume.model.Resume;
import shop.mtcoding.jobara.user.vo.UserVo;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ResumeControllerTest {

      @Autowired
      private MockMvc mvc;

      @Autowired
      ObjectMapper om;

      @Autowired
      private RedisService redisService;

      private MockHttpSession mockSession;

      @BeforeEach
      public void setUp() {
            UserVo principal = new UserVo();
            principal.setId(1);
            principal.setUsername("ssar");
            principal.setRole("employee");
            principal.setProfile(null);
            redisService.setValue("principal", principal);
            mockSession = new MockHttpSession();
            mockSession.setAttribute("principal", principal);
      }

      @Test
      public void resumeList_test() throws Exception {
            // given

            // when

            ResultActions resultActions = mvc.perform(
                        get("/resume/list").session(mockSession));

            Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
            List<Resume> resumeList = (List<Resume>) map.get("resumeList");
            // then
            assertThat(resumeList.get(0).getTitle()).isEqualTo("뛰어난 컴퓨터 프로그래머 능력을 펼쳐보이겠습니다.");
            resultActions.andExpect(status().is2xxSuccessful());

      }

      @Test
      public void resumeSave_test() throws Exception {
            // given
            ResumeSaveReq resume = new ResumeSaveReq();
            resume.setTitle("제목1");
            resume.setContent("내용1");
            String requestBody = om.writeValueAsString(resume);
            // when
            ResultActions resultActions = mvc.perform(
                        post("/resume/save").content(requestBody)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE).session(mockSession));
            // then
            resultActions.andExpect(jsonPath("$.code").value(1));
      }

      @Test
      public void resumeUpdate_test() throws Exception {
            // given
            int id = 1;
            ResumeUpdateReq resume = new ResumeUpdateReq();
            resume.setId(id);
            resume.setTitle("제목1 수정");
            resume.setContent("내용1 수정");
            String requestBody = om.writeValueAsString(resume);
            // when
            ResultActions resultActions = mvc.perform(
                        post("/resume/update/" + id).content(requestBody)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE).session(mockSession));
            // then
            resultActions.andExpect(jsonPath("$.code").value(1));
      }

      @Test
      public void resumeDelete_test() throws Exception {
            // given
            int id = 1;
            int id2 = 2; // 본인것이 아닌 이력서
            // when
            ResultActions resultActions = mvc.perform(delete("/resume/" + id + "/delete").session(mockSession));
            ResultActions resultActions2 = mvc.perform(delete("/resume/" + id2 + "/delete").session(mockSession));

            // then
            resultActions.andExpect(jsonPath("$.code").value(1));
            resultActions2.andExpect(status().is4xxClientError()); // 본인 것이 아닌 이력서 삭제 요청시
      }

}

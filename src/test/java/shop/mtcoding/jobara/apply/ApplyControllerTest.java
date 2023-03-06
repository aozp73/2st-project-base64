package shop.mtcoding.jobara.apply;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.apply.dto.ApplyReq.ApplyDecideReqDto;
import shop.mtcoding.jobara.apply.dto.ApplyReq.ApplyReqDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.CompanyApplyRespDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.EmployeeApplyRespDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        UserVo pricipal = new UserVo();
        pricipal.setId(1);
        pricipal.setUsername("ssar");
        pricipal.setRole("employee");
        pricipal.setProfile(null);
        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", pricipal);
    }

    @Test
    public void apply_test() throws Exception {
        // given
        ApplyReqDto applyReqDto = new ApplyReqDto(3, 1);
        String requestBody = om.writeValueAsString(applyReqDto);

        // when
        ResultActions resultActions = mvc.perform(
                post("/apply")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // verify
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("지원 성공"));
    }

    @Test
    public void companyApplyList_test() throws Exception {
        // given
        int id = 6;

        // when
        ResultActions resultActions = mvc.perform(
                get("/company/" + id + "/apply").session(mockSession));
        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<CompanyApplyRespDto> applyListPS = (List<CompanyApplyRespDto>) map.get("applyList");

        // verify
        resultActions.andExpect(status().isOk());
        assertThat(applyListPS.get(0).getRealName()).isNotEqualTo("김살");
    }

    @Test
    public void employeeApplyList_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/employee/" + id + "/apply").session(mockSession));
        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<EmployeeApplyRespDto> applyListPS = (List<EmployeeApplyRespDto>) map.get("applyList");

        // verify
        resultActions.andExpect(status().isOk());
        assertThat(applyListPS.get(0).getBoardTitle()).isNotEqualTo("공고제목1");
        assertThat(applyListPS.get(0).getResumeTitle()).isNotEqualTo("이력제 제목1");
        assertThat(applyListPS.get(0).getState()).isNotEqualTo(-1);
    }

    @Test
    public void decideApplyment_test() throws Exception {
        // given
        int boardId = 1;
        ApplyDecideReqDto applyDecideReqDto = new ApplyDecideReqDto(2, 1);
        String requestBody = om.writeValueAsString(applyDecideReqDto);
        // System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                put("/board/" + boardId + "/apply")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));
        // String responseBody =
        // resultActions.andReturn().getResponse().getContentAsString();
        // System.out.println("테스트 : " + responseBody);

        // verify
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
    }
}

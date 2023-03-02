package shop.mtcoding.jobara.apply;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.apply.dto.ApplyResp.CompanyApplyRespDto;
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
        pricipal.setId(6);
        pricipal.setUsername("ssar");
        pricipal.setRole("company");
        pricipal.setProfile(null);
        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", pricipal);
    }

    @Test
    public void apply_test() throws Exception {
        // given
        int id = 4;

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/" + id + "/apply").session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        // verify
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("지원 성공"));
    }

    @Test
    public void companyApplyList_test() throws Exception{
        // given
        int id = 6;

        // when
        ResultActions resultActions = mvc.perform(
                get("/company/"+id+"/apply").session(mockSession));
        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<CompanyApplyRespDto> applyListPS = (List<CompanyApplyRespDto>) map.get("applyList");

        // verify
        resultActions.andExpect(status().isOk());
        assertThat(applyListPS.get(0).getRealName()).isEqualTo("김살");
    }
}

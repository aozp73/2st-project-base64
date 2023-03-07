package shop.mtcoding.jobara.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.user.vo.UserVo;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;
    private MockHttpSession mockSession2;

    @Autowired
    private RedisService redisService;

    @BeforeEach
    public void setUp() {
        UserVo principal = new UserVo();
        principal.setId(6);
        principal.setUsername("cos");
        principal.setRole("company");
        // principal.setId(1);
        // principal.setUsername("ssar");
        // principal.setRole("employee");
        principal.setProfile(null);

        redisService.setValue("principal", principal);

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", principal);
    }

    @Test
    public void myBoardList_test() throws Exception {
        // given
        // int id = 7; 여기서 id:userId, 열람권한 없음 체크
        int id = 6;

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/boardList/" + id).session(mockSession));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<MyBoardListRespDto> boardListDto = (List<MyBoardListRespDto>) map.get("myBoardList");
        // String model = om.writeValueAsString(boardListDto);
        // System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
    }

    @Test
    public void update_test() throws Exception {
        // given
        // int id = 3;
        int id = 1;

        BoardUpdateReqDto boardUpdateReqDto = new BoardUpdateReqDto();
        // boardUpdateReqDto.setId(3); 수정권한 체크
        // boardUpdateReqDto.setUserId(2);
        boardUpdateReqDto.setId(1);
        boardUpdateReqDto.setUserId(1);
        boardUpdateReqDto.setTitle("테스트 제목");
        boardUpdateReqDto.setContent("테스트 내용");
        boardUpdateReqDto.setCareerString("1년이상 ~ 3년미만");
        boardUpdateReqDto.setEducationString("4년 대졸이상");
        boardUpdateReqDto.setJobTypeString("정규직");
        boardUpdateReqDto.setFavor("관련 프로젝트 경험");
        // boardUpdateReqDto.setDeadline("2023-03-05");
        // boardUpdateReqDto.setDeadline("2023-12-01");
        // 테스트일자 03-06 -> 1~100일 이내 마감일자 안내구문 테스트 완료
        boardUpdateReqDto.setDeadline("2023-05-21");
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        boardUpdateReqDto.setCheckedValues(arrayList);

        String requestBody = om.writeValueAsString(boardUpdateReqDto);

        // when
        ResultActions resultActions = mvc.perform(
                put("/board/update/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

        // then
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void updateForm_test() throws Exception {
        // given
        // int id = 3; 수정 권한없음 체크
        // int id = 10; 없는 게시물 체크
        int id = 2;

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/updateForm/" + id)
                        .session(mockSession));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        BoardUpdateRespDto boardDto = (BoardUpdateRespDto) map.get("boardDetail");

        // String boardmodel = om.writeValueAsString(boardDto);
        // System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().isOk());
        assertThat(boardDto.getCareerString()).isEqualTo("3년이상 ~ 5년미만");
    }

    @Test
    public void save_test() throws Exception {
        // given
        String requestBody = "title=테스트제목&content=테스트내용&careerString=1년이상 ~ 3년미만&educationString=4년 대졸이상&jobTypeString=정규직&favor=관련프로젝트 경험&userId=6&checkLang=2&checkLang=4&checkLang=6";
        String deadline = "&deadline=2023-05-13";
        // String deadline = "&deadline=2023-03-01";
        // String deadline = "&deadline=2023-12-01";
        // 테스트일자 03-06 -> 1~100일 이내 마감일자 안내구문 테스트 완료

        // when
        ResultActions resultActions = mvc.perform(
                post("/board/save")
                        .content(requestBody + deadline)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .session(mockSession));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void saveForm_test() throws Exception {
        // given
        // board saveForm 들어가기 위해선 기업회원이어야 함
        // 구직회원이 시도할 경우 안내문구 출력 테스트 완료

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/saveForm")
                        .session(mockSession));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        UserVo principal = redisService.getValue("principal");

        // then
        assertThat(principal.getUsername()).isEqualTo("cos");
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void certification_test() throws Exception {
        // given
        UserVo employeeUser = new UserVo();
        employeeUser.setId(1);
        employeeUser.setUsername("ssar");
        employeeUser.setRole("employee");

        mockSession2 = new MockHttpSession();
        mockSession2.setAttribute("principal", employeeUser);

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/saveForm")
                        .session(mockSession));
        // .session(mockSession2)); 일반회원 로그인 시 인증체크 테스트 완료

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        UserVo coPrincipal = (UserVo) session.getAttribute("principal");

        // then
        // assertThat(coPrincipal.getUsername()).isNotEqualTo("ssar");
        assertThat(coPrincipal.getUsername()).isEqualTo("cos");
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void boardDetail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                // get("/board/" + id).session(mockSession)); // 구직회원으로 로그인하여 session 저장 시 좋아요
                // 정보 확인 완료
                get("/board/" + id));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        BoardDetailRespDto board = (BoardDetailRespDto) map.get("board");
        // LoveDetailRespDto love = (LoveDetailRespDto) map.get("love");

        // String boardModel = om.writeValueAsString(board);
        // System.out.println("테스트 : " + boardModel);

        // String loveModel = om.writeValueAsString(love);
        // System.out.println("테스트 : " + loveModel);
        // System.out.println("테스트 : " + love.getId());

        // then
        resultActions.andExpect(status().isOk());
        assertThat(board.getCompanyScale()).isEqualTo("대기업");
        assertThat(board.getCompanyField()).isEqualTo("IT업");
        // assertThat(love.getId()).isEqualTo(1);
        // assertThat(love.getCss()).isEqualTo("fa-solid");
    }

    @Test
    public void boardList_test() throws Exception {
        // given
        // String keyword = "lang";
        // Integer page = 1; userId=1, role="employee" 의 세션 (ssar 로그인) 일 때 테스트 완료
        String keyword = null;
        Integer page = 0;

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/list?page=" + page + "&keyword=" + keyword)
                        .session(mockSession));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        PagingDto boardList = (PagingDto) map.get("pagingDto");

        String model = om.writeValueAsString(boardList.getBoardListDtos());
        System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().isOk());
        assertThat(boardList.getBoardListDtos().size()).isEqualTo(8);
        // assertThat(boardList.getBoardListDtos().get(0).getCss()).isEqualTo("fa-solid");
    }

    // @Test 구직회원 로그인시 테스트 완료
    // public void scrapList_test() throws Exception {
    // // given
    // int id = 1;

    // // when
    // ResultActions resultActions = mvc.perform(
    // get("/board/scrapList/" + id)
    // .session(mockSession));

    // Map<String, Object> map =
    // resultActions.andReturn().getModelAndView().getModel();
    // List<MyScrapBoardListRespDto> myScrapList = (List<MyScrapBoardListRespDto>)
    // map.get("myScrapBoardList");

    // String model = om.writeValueAsString(myScrapList);
    // System.out.println("테스트 : " + model);

    // // then
    // resultActions.andExpect(status().isOk());
    // assertThat(myScrapList.size()).isEqualTo(2);
    // assertThat(myScrapList.get(0).getCss()).isEqualTo("fa-solid");
    // }

    @Test
    public void boardMainList_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(
                get("/"));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<BoardMainRespDto> boardList = (List<BoardMainRespDto>) map.get("boardMainList");

        // String model = om.writeValueAsString(boardList);
        // System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().isOk());
        assertThat(boardList.get(0).getTitle()).isEqualTo("인공지능 솔루션 (AI Solution) 개발");
        assertThat(boardList.get(1).getTitle()).isEqualTo("인공지능 (AI Solution) 개발");
    }

    @Test
    public void delete_test() throws Exception {
        // given
        // int id = 100; // 삭제할 게시물이 존재하지 않습니다 테스트 완료
        // int id = 5; // 게시글 삭제 권한이 없습니다 테스트 완료
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                delete("/board/" + id)
                        .session(mockSession));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.msg").value("게시글을 삭제하였습니다"));
    }
}

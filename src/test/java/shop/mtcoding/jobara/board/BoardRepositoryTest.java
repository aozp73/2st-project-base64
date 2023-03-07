package shop.mtcoding.jobara.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertSkillReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.board.model.BoardTech;
import shop.mtcoding.jobara.board.model.BoardTechRepository;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardTechRepository boardTechRepository;

    @Test
    public void findByIdWithCompany_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int boardId = 1;

        // when
        BoardDetailRespDto boardDetailRespDto = boardRepository.findByIdWithCompany(boardId);
        // String responseBody = om.writeValueAsString(boardDetailRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(boardDetailRespDto.getCompanyScale()).isEqualTo("대기업");
    }

    @Test
    public void paging_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int page = 1;
        String keyword = null;
        // String keyword = "lang";
        int row = page * 8;
        int userId = 1;

        // when
        PagingDto pagingDto = boardRepository.paging(page, keyword, row, userId);
        String responseBody = om.writeValueAsString(pagingDto);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(pagingDto.getCurrentPage()).isEqualTo(1);
        assertThat(pagingDto.getTotalCount()).isEqualTo(9);
        // assertThat(pagingDto.getTotalCount()).isNotEqualTo(1);
        // assertThat(pagingDto.getTotalCount()).isNotEqualTo(3);
    }

    @Test
    public void findAllWithCompany() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int page = 0;

        int startNum = page * 8;
        String keyword = null;
        // String keyword = "lang";
        int row = 8;
        int userId = 1;

        // when
        List<BoardListRespDto> boardListRespDto = boardRepository.findAllWithCompany(startNum, keyword, row,
                userId);
        String responseBody = om.writeValueAsString(boardListRespDto);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(boardListRespDto.size()).isEqualTo(8);
        // assertThat(boardListRespDto.size()).isNotEqualTo(3);

    }

    @Test
    public void findAllByIdWithCompany_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int userId = 6;

        // when
        List<MyBoardListRespDto> myBoardListRespDto = boardRepository.findAllByIdWithCompany(userId);
        // String responseBody = om.writeValueAsString(myBoardListRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(myBoardListRespDto.get(1).getTitle()).isEqualTo("인공지능 솔루션 (AI Solution) 개발");
    }

    @Test
    public void insertSkill_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        List<BoardTech> boardTechList1 = boardTechRepository.findAll();
        String res1 = om.writeValueAsString(boardTechList1);
        // System.out.println("테스트1 : " + res1);

        int boardId = 5;
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        BoardInsertSkillReqDto boardInsertSkillReqDto = new BoardInsertSkillReqDto(5, arrayList);

        // when
        boardTechRepository.insertSkill(boardInsertSkillReqDto);
        List<BoardTech> boardTechList2 = boardTechRepository.findAll();
        String res2 = om.writeValueAsString(boardTechList2);
        // System.out.println("테스트2 : " + res2);

        // then

    }

    @Test
    public void findByIdWithSkillForDetail_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int boardId = 1;

        // when
        ArrayList<Integer> arrayList = boardTechRepository.findByIdWithSkillForDetail(boardId);
        String res = om.writeValueAsString(arrayList);
        // System.out.println("테스트 : " + res);

        // then
    }

    @Test
    public void findAllByUserIdForLangMatching_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int userId = 2;

        // when
        List<BoardListRespDto> boardList = boardRepository.findAllByUserIdForLangMatching(userId);
        String res = om.writeValueAsString(boardList);
        // System.out.println("테스트 : " + res);

        // then
    }
}

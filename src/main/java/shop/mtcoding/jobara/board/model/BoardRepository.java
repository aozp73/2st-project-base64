package shop.mtcoding.jobara.board.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertSkillReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyScrapBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;

@Mapper
public interface BoardRepository {

        public List<Board> findAll();

        public BoardDetailRespDto findByIdWithCompany(int boardId);

        public List<BoardListRespDto> findAllWithCompany();

        public List<BoardListRespDto> findAllByUserIdForLangMatching(int userId);

        public List<BoardMainRespDto> findAllWithCompanyToMain();

        public List<MyBoardListRespDto> findAllByIdWithCompany(int userId);

        public List<MyScrapBoardListRespDto> findAllScrapByIdWithCompany(int userId);

        // 페이징 관련
        public List<BoardListRespDto> findAllWithCompany(@Param("startNum") int startNum,
                        @Param("keyword") String keyword, @Param("row") int row, @Param("userId") int userId);

        public PagingDto paging(@Param("page") int page, @Param("keyword") String keyword, @Param("row") int row,
                        @Param("userId") int userId);

        // ~ 페이징 관련
        public BoardUpdateRespDto findByIdForUpdate(int id);

        public Board findById(int id);

        public int insert(Board board);

        public int insertSkill(BoardInsertSkillReqDto boardInsertSkillReqDto);

        public int updateById(Board board);

        public int deleteById(int id);
}

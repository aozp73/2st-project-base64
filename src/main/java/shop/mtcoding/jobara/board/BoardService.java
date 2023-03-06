package shop.mtcoding.jobara.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertSkillReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyScrapBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.board.model.Board;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.board.model.BoardTechRepository;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.CareerParse;
import shop.mtcoding.jobara.common.util.EducationParse;
import shop.mtcoding.jobara.common.util.JobTypeParse;
import shop.mtcoding.jobara.resume.model.Resume;
import shop.mtcoding.jobara.resume.model.ResumeRepository;
import shop.mtcoding.jobara.user.vo.UserVo;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardTechRepository boardTechRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Transactional(readOnly = true)
    public List<BoardMainRespDto> getListToMain() {
        List<BoardMainRespDto> boardListPS;

        try {
            boardListPS = boardRepository.findAllWithCompanyToMain();
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return boardListPS;
    }

    @Transactional(readOnly = true)
    public BoardDetailRespDto getDetail(int id) {
        BoardDetailRespDto boardDetailPS;

        try {
            boardDetailPS = boardRepository.findByIdWithCompany(id);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String career = CareerParse.careerToString(boardDetailPS.getCareer());
        boardDetailPS.setCareerString(career);

        String education = EducationParse.educationToString(boardDetailPS.getEducation());
        boardDetailPS.setEducationString(education);

        String jobType = JobTypeParse.jopTypeToString(boardDetailPS.getJobType());
        boardDetailPS.setJobTypeString(jobType);

        return boardDetailPS;
    }

    public PagingDto getListWithPaging(Integer page, String keyword, UserVo uservo) {

        if (page == null) {
            page = 0;
        }

        List<BoardListRespDto> boardsList;
        PagingDto pagingDto;
        int startNum = page * PagingDto.ROW;

        if (uservo != null && uservo.getRole().equals("employee")) {

            boardsList = boardRepository.findAllWithCompany(startNum, keyword, PagingDto.ROW, uservo.getId());
            pagingDto = boardRepository.paging(page, keyword, PagingDto.ROW, uservo.getId());

        } else {

            boardsList = boardRepository.findAllWithCompany(startNum, keyword, PagingDto.ROW, 0);
            pagingDto = boardRepository.paging(page, keyword, PagingDto.ROW, 0);
        }

        // if (boardsList.size() == 0)
        // pagingDto.setNotResult(true);
        pagingDto.makeBlockInfo(keyword);
        pagingDto.setBoardListDtos(boardsList);

        return pagingDto;
    }

    @Transactional(readOnly = true)
    public BoardUpdateRespDto getDetailForUpdate(int id, int coPrincipalId) {
        BoardUpdateRespDto boardDetailPS = boardRepository.findByIdForUpdate(id);

        if (boardDetailPS == null) {
            throw new CustomException("없는 게시물을 수정할 수 없습니다");
        }

        if (boardDetailPS.getUserId() != coPrincipalId) {
            throw new CustomException("수정 권한이 없습니다");
        }

        String career = CareerParse.careerToString(boardDetailPS.getCareer());
        boardDetailPS.setCareerString(career);

        String education = EducationParse.educationToString(boardDetailPS.getEducation());
        boardDetailPS.setEducationString(education);

        String jobType = JobTypeParse.jopTypeToString(boardDetailPS.getJobType());
        boardDetailPS.setJobTypeString(jobType);

        return boardDetailPS;
    }

    @Transactional
    public void updateBoard(BoardUpdateReqDto boardUpdateReqDto, int coPrincipalId) {

        Board boardPS;
        boardPS = boardRepository.findById(boardUpdateReqDto.getId());

        try {
        } catch (Exception e) {
            throw new CustomApiException("없는 게시물을 수정할 수 없습니다");
        }

        if (boardPS.getUserId() != coPrincipalId) {
            throw new CustomApiException("수정 권한이 없습니다");
        }

        int career = CareerParse.careerToInt(boardUpdateReqDto.getCareerString());
        int education = EducationParse.educationToInt(boardUpdateReqDto.getEducationString());
        int jobType = JobTypeParse.jobTypeToInt(boardUpdateReqDto.getJobTypeString());

        Board board = new Board(boardUpdateReqDto.getId(),
                boardUpdateReqDto.getUserId(),
                boardUpdateReqDto.getTitle(),
                boardUpdateReqDto.getContent(),
                career,
                jobType,
                education,
                boardUpdateReqDto.getFavor(),
                boardUpdateReqDto.getDeadline());

        try {
            boardRepository.updateById(board);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public int insertBoard(BoardInsertReqDto boardInsertReqDto, int userId) {

        int career = CareerParse.careerToInt(boardInsertReqDto.getCareerString());
        int education = EducationParse.educationToInt(boardInsertReqDto.getEducationString());
        int jobType = JobTypeParse.jobTypeToInt(boardInsertReqDto.getJobTypeString());

        Board board = new Board(userId, boardInsertReqDto.getTitle(),
                boardInsertReqDto.getContent(),
                career,
                jobType,
                education,
                boardInsertReqDto.getFavor(),
                boardInsertReqDto.getDeadline());

        try {
            boardRepository.insert(board);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return board.getId();
    }

    @Transactional(readOnly = true)
    public List<MyBoardListRespDto> getMyBoard(int coPrincipalId, int userId) {
        // 권한 체크
        if (coPrincipalId != userId) {
            throw new CustomException("공고 리스트 열람 권한이 없습니다.");
        }

        List<MyBoardListRespDto> myBoardListPS;
        try {
            myBoardListPS = boardRepository.findAllByIdWithCompany(coPrincipalId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return myBoardListPS;
    }

    @Transactional(readOnly = true)
    public List<MyScrapBoardListRespDto> getMyScrapBoard(int coPrincipalId, int userId) {
        // 권한 체크
        if (coPrincipalId != userId) {
            throw new CustomException("공고 리스트 열람 권한이 없습니다.");
        }

        List<MyScrapBoardListRespDto> myScrapBoardListPS;
        try {
            myScrapBoardListPS = boardRepository.findAllScrapByIdWithCompany(coPrincipalId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return myScrapBoardListPS;
    }

    @Transactional
    public void insertSkill(ArrayList<Integer> checkLang, int boardId) {

        BoardInsertSkillReqDto boardInsertSkillReqDto = new BoardInsertSkillReqDto(boardId, checkLang);

        try {
            boardTechRepository.insertSkill(boardInsertSkillReqDto);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional(readOnly = true)
    public ArrayList<Integer> getSkillForDetail(int boardId) {
        ArrayList<Integer> checkLang;

        try {
            checkLang = boardTechRepository.findByIdWithSkillForDetail(boardId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return checkLang;
    }

    @Transactional
    public void updateTech(ArrayList<Integer> techList, int boardId) {
        try {
            boardTechRepository.deleteByBoardId(boardId);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BoardInsertSkillReqDto boardInsertSkillReqDto = new BoardInsertSkillReqDto(boardId, techList);
        try {
            boardTechRepository.insertSkill(boardInsertSkillReqDto);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<BoardListRespDto> getLangMatchList(int userId) {
        return boardRepository.findAllByUserIdForLangMatching(userId);
    }

    @Transactional(readOnly = true)
    public List<Resume> getResume(int principalId) {
        return resumeRepository.findByUserId(principalId);
    }

    @Transactional
    public void deleteBoard(int boardId, int principalId) {
        Board boardPS = boardRepository.findById(boardId);
        if (boardPS == null) {
            throw new CustomApiException("삭제할 게시물이 존재하지 않습니다");
        }

        if (boardPS.getUserId() != principalId) {
            throw new CustomApiException("게시글 삭제 권한이 없습니다");
        }

        try {
            boardRepository.deleteById(boardId);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

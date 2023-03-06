package shop.mtcoding.jobara.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.board.model.Board;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.love.dto.LoveResp.LoveDetailRespDto;
import shop.mtcoding.jobara.love.model.Love;
import shop.mtcoding.jobara.love.model.LoveRepository;
import shop.mtcoding.jobara.user.vo.UserVo;

@Service
public class LoveService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    LoveRepository loveRepository;

    @Transactional
    public int insertLove(int boardId, int userId) {
        // 게시글 존재 여부 체크
        Board boardPS = boardRepository.findById(boardId);
        if (boardPS == null) {
            throw new CustomApiException("게시글이 존재하지 않습니다");
        }

        Love love = new Love();
        love.setBoardId(boardId);
        love.setUserId(userId);
        loveRepository.insert(love);
        return love.getId();
    }

    @Transactional(readOnly = true)
    public LoveDetailRespDto getLove(int boardId, UserVo principal) {

        Love lovePS = loveRepository.findByBoardIdAndUserId(boardId, principal.getId());

        if (lovePS == null) {
            return new LoveDetailRespDto(0, "");
        } else {
            return new LoveDetailRespDto(lovePS.getId(), "fa-solid");
        }

    }

    @Transactional
    public void deleteLove(int id, int userId) {
        // 좋아요 존재 여부 체크
        Love lovePS = loveRepository.findById(id);
        if (lovePS == null) {
            throw new CustomApiException("좋아요 내역이 존재하지 않습니다");
        }
        // 권한 체크
        if (lovePS.getUserId() != userId) {
            throw new CustomApiException("좋아요 취소 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        int res = loveRepository.deleteById(id);
        if (res != 1) {
            throw new CustomApiException("서버 에러", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

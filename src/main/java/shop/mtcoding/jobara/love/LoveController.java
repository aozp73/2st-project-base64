package shop.mtcoding.jobara.love;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.jobara.common.aop.EmployeeCheckApi;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.love.dto.LoveReq.LoveSaveReqDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class LoveController {

    @Autowired
    HttpSession session;

    @Autowired
    LoveService loveService;

    @PostMapping("/love")
    @EmployeeCheckApi
    public ResponseEntity<?> save(@RequestBody LoveSaveReqDto loveSaveReqDto) {
        // 인증
        UserVo principal = (UserVo) session.getAttribute("principal");

        // if (principal == null) {
        // throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        // if (!principal.getRole().equals("employee")) {
        // throw new CustomApiException("구직 회원으로 로그인 해주세요", HttpStatus.UNAUTHORIZED);
        // }
        // 유효성 검사
        if (loveSaveReqDto.getBoardId() == null) {
            throw new CustomApiException("boardId를 전달해 주세요");
        }

        int loveId = loveService.insertLove(loveSaveReqDto.getBoardId(), principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요성공", loveId), HttpStatus.CREATED);
    }

    @DeleteMapping("/love/{id}")
    @EmployeeCheckApi
    public ResponseEntity<?> cancel(@PathVariable Integer id) {
        // 인증
        UserVo principal = (UserVo) session.getAttribute("principal");
        // if (principal == null) {
        // throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }

        // if (!principal.getRole().equals("employee")) {
        // throw new CustomApiException("구직 회원으로 로그인 해주세요", HttpStatus.UNAUTHORIZED);
        // }

        loveService.deleteLove(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요취소 성공", 0), HttpStatus.OK);
    }

}

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
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.love.dto.LoveReq.LoveSaveReqDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class LoveController {

    @Autowired
    HttpSession session;

    @Autowired
    LoveService loveService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/love")
    @EmployeeCheckApi
    public ResponseEntity<?> save(@RequestBody LoveSaveReqDto loveSaveReqDto) {
        // 인증
        UserVo principal = redisService.getValue("principal");
        
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
        UserVo principal = redisService.getValue("principal");

        loveService.deleteLove(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요취소 성공", 0), HttpStatus.OK);
    }

}

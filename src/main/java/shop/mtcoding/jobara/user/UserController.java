package shop.mtcoding.jobara.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.dto.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final RedisService redisService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "common/loginForm";
    }

    @PostMapping("/login")
    public String login(UserLoginReqDto userLoginReqDto, String remember, HttpServletResponse response) {
        Verify.validateString(userLoginReqDto.getUsername(), "유저네임을 입력하세요.");
        Verify.validateString(userLoginReqDto.getPassword(), "암호를 입력하세요.");
        UserVo userVoPS = userService.getUser(userLoginReqDto);
        Cookie cookie = new Cookie("remember", userVoPS.getUsername());
        response.addCookie(cookie);
        redisService.setValue("principal", userVoPS);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        String sessionId = session.getId();
        redisService.logout(sessionId);
        return "redirect:/";
    }

    @GetMapping("/usernameSameCheck")
    public @ResponseBody ResponseEntity<?> usernameSameCheck(String username) {
        // 유효성 검사
        Verify.validateApiString(username, "유저네임을 입력하세요.");

        if (userService.checkUsername(username) == null) {
            return new ResponseEntity<>(new ResponseDto<>(1, "사용가능한 유저네임 입니다.", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto<>(-1, "이미 존재하는 유저네임 입니다.", false), HttpStatus.OK);
        }

    }
}

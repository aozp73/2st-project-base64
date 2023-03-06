package shop.mtcoding.jobara.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.company.dto.CompanyResp.CompanyUpdateRespDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
@RequiredArgsConstructor
public class CompanyConetroller {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private HttpSession session;

    @GetMapping("/company/joinForm")
    public String joinForm() {
        return "company/joinForm";
    }

    @GetMapping("/company/updateForm")
    public String updateForm(Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/loginForm");
        Verify.checkRole(principal, "company");
        // if (!principal.getRole().equals("company")) {
        // throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        // }
        CompanyUpdateRespDto companyUpdateRespDto = companyService.getCompanyUpdateRespDto(principal.getId());
        model.addAttribute("companyDto", companyUpdateRespDto);
        return "company/updateForm";
    }

    @PostMapping("/company/join")
    public String join(CompanyJoinReqDto companyJoinReqDto) {
        Verify.validateString(companyJoinReqDto.getUsername(), "유저네임을 입력하세요.");
        Verify.validateString(companyJoinReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(companyJoinReqDto.getEmail(), "이메일을 입력하세요.");
        companyService.insertCompany(companyJoinReqDto);
        return "redirect:/loginForm";
    }

    @PostMapping("/company/update")
    public String update(CompanyUpdateReqDto companyUpdateReqDto, MultipartFile profile) {

        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/company/loginForm");
        Verify.checkRole(principal, "company");

        if (profile.isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }
        if (!profile.getContentType().startsWith("image")) {
            throw new CustomException("사진 파일만 업로드 할 수 있습니다.");
        }
        Verify.validateString(companyUpdateReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getEmail(), "이메일을 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getCompanyName(), "회사 이름을 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getAddress(), "주소를 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getDetailAddress(), "상세 주소를 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getCompanyScale(), "회사 규모란을 선택하세요.");
        Verify.validateString(companyUpdateReqDto.getCompanyField(), "회사 업종란을 선택하세요.");
        Verify.validateString(companyUpdateReqDto.getTel(), "전화번호를 입력하세요.");
        UserVo UserVoPS = companyService.updateCompany(companyUpdateReqDto, principal.getId(), profile);
        session.removeAttribute("principal");
        session.setAttribute("principal", UserVoPS);
        return "redirect:/";
    }
}

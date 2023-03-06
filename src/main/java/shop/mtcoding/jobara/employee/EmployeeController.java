package shop.mtcoding.jobara.employee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeJoinReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeTechUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeUpdateRespDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession session;

    @GetMapping("/employee/joinForm")
    public String joinForm() {
        return "employee/joinForm";
    }

    @GetMapping("/employee/loginForm")
    public String loginForm() {
        return "employee/loginForm";
    }

    @GetMapping("/employee/list")
    public String employeeList(Model model, Integer page) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        PagingDto pagingPS = employeeService.getEmployee(page);

        model.addAttribute("pagingDto", pagingPS);
        model.addAttribute("principal", principal);
        if (principal != null) {
            if (principal.getRole().equals("company")) {
                List<EmployeeAndResumeRespDto> recommendEmployeeListPS = employeeService
                        .getRecommendEmployee(principal.getId());
                model.addAttribute("recommendEmployeeList", recommendEmployeeListPS);
            }
        }

        return "employee/list";
    }

    @GetMapping("/employee/{id}")
    public String employeeDetail(@PathVariable int id, Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        EmployeeAndResumeRespDto employeePS = employeeService.getEmployee(id);
        List<String> employeeTechPS = employeeService.getEmployeeTech(id);
        model.addAttribute("employee", employeePS);
        model.addAttribute("employeeTech", employeeTechPS);
        model.addAttribute("principal", principal);
        return "employee/detail";
    }

    @GetMapping("/employee/updateForm")
    public String updateForm(Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다", HttpStatus.UNAUTHORIZED, "/#login");
        Verify.checkRole(principal, "employee");
        EmployeeUpdateRespDto employeeUpdateRespDto = employeeService.getEmployeeUpdateRespDto(principal.getId());
        List<Integer> employeeSkill = employeeService.getSkillForDetail(principal.getId());
        model.addAttribute("employeeDto", employeeUpdateRespDto);
        model.addAttribute("employeeSkill", employeeSkill);
        return "employee/updateForm";
    }

    @PostMapping("/employee/join")
    public String join(EmployeeJoinReqDto employeeJoinReqDto) {
        Verify.validateString(employeeJoinReqDto.getUsername(), "유저네임을 입력하세요.");
        Verify.validateString(employeeJoinReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(employeeJoinReqDto.getEmail(), "이메일을 입력하세요.");
        employeeService.insertEmployee(employeeJoinReqDto);
        return "redirect:/loginForm";
    }

    @PostMapping("/employee/update/{id}")
    public String update(EmployeeUpdateReqDto employeeUpdateReqDto, MultipartFile profile) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다", HttpStatus.UNAUTHORIZED, "/#login");
        Verify.checkRole(principal, "employee");
        Verify.validateString(employeeUpdateReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(employeeUpdateReqDto.getEmail(), "이메일을 입력하세요.");
        Verify.validateString(employeeUpdateReqDto.getAddress(), "주소를 입력하세요.");
        Verify.validateString(employeeUpdateReqDto.getDetailAddress(), "상세 주소를 입력하세요.");
        Verify.validateString(employeeUpdateReqDto.getTel(), "전화번호를 입력하세요.");
        Verify.validateObject(employeeUpdateReqDto.getCareer(), "경력을 입력하세요.");
        Verify.validateString(employeeUpdateReqDto.getEducation(), "학력을 입력하세요.");

        UserVo UserVoPS = employeeService.updateEmpolyee(employeeUpdateReqDto, principal.getId(), profile);
        session.removeAttribute("principal");
        session.setAttribute("principal", UserVoPS);
        return "redirect:/";
    }

    @PutMapping("/employee/update/tech/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
            @RequestBody EmployeeTechUpdateReqDto employeeTechUpdateReqDto) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(principal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        Verify.checkRoleApi(principal, "employee");
        if (employeeTechUpdateReqDto.getCheckedValues() != null) {
            employeeService.updateEmpolyeeTech(employeeTechUpdateReqDto.getCheckedValues(), principal.getId());
        }

        return new ResponseEntity<>(new ResponseDto<>(1, " 수정완료", null), HttpStatus.OK);
    }

}

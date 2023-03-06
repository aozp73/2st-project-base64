package shop.mtcoding.jobara.resume;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.resume.dto.ResumeReq.ResumeSaveReq;
import shop.mtcoding.jobara.resume.dto.ResumeReq.ResumeUpdateReq;
import shop.mtcoding.jobara.resume.model.Resume;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private HttpSession session;

    @PostMapping("/resume/update/{id}")
    public ResponseEntity<?> updateResume(@PathVariable Integer id, @RequestBody ResumeUpdateReq resumeUpdateReq) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(principal, "로그인이 필요합니다.");
        Verify.checkRoleApi(principal, "employee");
        if (resumeUpdateReq.getTitle() == null) {
            resumeUpdateReq.setTitle("무제");
        }
        resumeService.updateResume(principal.getId(), resumeUpdateReq);
        return new ResponseEntity<>(new ResponseDto<>(1, "작성 완료", null), HttpStatus.OK);
    }

    @GetMapping("/resume/{id}")
    public String saveResumeForm(@PathVariable("id") Integer id, Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.");
        Verify.checkRole(principal, "employee");
        Resume resumePS = resumeService.findById(principal.getId(), id);
        model.addAttribute("resume", resumePS);
        return "resume/updateForm";
    }

    @GetMapping("/resume/list")
    public String resumeList(Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.");
        Verify.checkRole(principal, "employee");
        List<Resume> resumeListPS = resumeService.findByUserId(principal.getId());
        model.addAttribute("resumeList", resumeListPS);
        return "resume/list";
    }

    @GetMapping("/resume/saveForm")
    public String saveForm() {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.");
        Verify.checkRole(principal, "employee");
        return "resume/saveForm";
    }

    @PostMapping("/resume/save")
    public ResponseEntity<?> saveResume(@RequestBody ResumeSaveReq resumeSaveReq) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(principal, "로그인이 필요합니다.");
        Verify.checkRoleApi(principal, "employee");
        if (resumeSaveReq.getTitle() == null) {
            resumeSaveReq.setTitle("무제");
        }
        resumeService.saveResume(principal.getId(), resumeSaveReq);
        return new ResponseEntity<>(new ResponseDto<>(1, "작성 완료", null), HttpStatus.OK);
    }

    @DeleteMapping("/resume/{id}/delete")
    public ResponseEntity<?> deleteResume(@PathVariable int id) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(principal, "로그인이 필요합니다.");
        Verify.checkRoleApi(principal, "employee");
        resumeService.deleteResume(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "삭제 완료", null), HttpStatus.OK);
    }
}

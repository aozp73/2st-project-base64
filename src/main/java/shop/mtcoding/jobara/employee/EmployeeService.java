package shop.mtcoding.jobara.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.Hash;
import shop.mtcoding.jobara.common.util.PathUtil;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeInsertSkillReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeJoinReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeLoginReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeUpdateRespDto;
import shop.mtcoding.jobara.employee.model.Employee;
import shop.mtcoding.jobara.employee.model.EmployeeRepository;
import shop.mtcoding.jobara.employee.model.EmployeeTechRepository;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.model.UserRepository;
import shop.mtcoding.jobara.user.vo.UserVo;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeTechRepository employeeTechRepository;

    public PagingDto getEmployee(Integer page) {

        if (page == null) {
            page = 0;
        }

        int startNum = page * PagingDto.ROW;

        List<EmployeeAndResumeRespDto> employeePS = employeeRepository.findAllWithResume(startNum,
                PagingDto.ROW);
        PagingDto pagingPS = employeeRepository.paging(page, PagingDto.ROW);

        pagingPS.makeBlockInfo("");
        pagingPS.setResumeListDtos(employeePS);

        return pagingPS;
    }

    public List<EmployeeAndResumeRespDto> getRecommendEmployee(int id) {
        List<Integer> recommendId = employeeRepository.findRecommendId(id);
        List<EmployeeAndResumeRespDto> employeeListPS = new ArrayList<>();
        for (int i = 0; i < recommendId.size(); i++) {
            EmployeeAndResumeRespDto employeePS = employeeRepository.findRecommendWithResume(recommendId.get(i));
            employeeListPS.add(employeePS);
        }
        if (employeeListPS.size() > 4) {
            return employeeListPS.subList(0, 4);
        }
        return employeeListPS;
    }

    public EmployeeAndResumeRespDto getEmployee(int id) {
        EmployeeAndResumeRespDto employeePS = employeeRepository.findEmployeeByIdWithResume(id);
        return employeePS;
    }

    public EmployeeAndResumeRespDto getEmployee(int id, int resumeId) {
        EmployeeAndResumeRespDto employeePS = employeeRepository.findEmployeeByIdAndResume(id, resumeId);
        return employeePS;
    }

    public UserVo getEmployee(EmployeeLoginReqDto employeeLoginReqDto) {
        UserVo userVo = userRepository.findByUsernameAndPassword(
                new User(employeeLoginReqDto.getUsername(), employeeLoginReqDto.getPassword()));
        if (userVo == null) {
            throw new CustomException("유저네임이나 비밀번호가 틀렸습니다.");
        }
        return userVo;
    }

    @Transactional(readOnly = true)
    public EmployeeUpdateRespDto getEmployeeUpdateRespDto(Integer principalId) {
        User user = userRepository.findById(principalId);
        Employee employee = employeeRepository.findByUserId(principalId);
        EmployeeUpdateRespDto employeeUpdateRespDto = new EmployeeUpdateRespDto(user.getId(), user.getPassword(),
                user.getEmail(),
                user.getAddress(), user.getDetailAddress(), user.getTel(), employee.getRealName(),
                employee.getCareer(), employee.getEducation());
        return employeeUpdateRespDto;
    }

    @Transactional
    public void insertEmployee(EmployeeJoinReqDto employeeJoinReqDto) {
        if (userRepository.findByUsername(employeeJoinReqDto.getUsername()) != null) {
            throw new CustomException("이미 존재하는 유저네임 입니다.");
        }
        String salt = Hash.makeSalt();
        String hashPassword = Hash.encode(employeeJoinReqDto.getPassword() + salt);
        User user = new User(employeeJoinReqDto.getUsername(), hashPassword,
                employeeJoinReqDto.getEmail());
        user.setSalt(salt);
        try {
            userRepository.insertForEmployee(user);
            employeeRepository.insert(user.getId());
        } catch (Exception e) {
            throw new CustomException("서버 오류: 회원 가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public UserVo updateEmpolyee(EmployeeUpdateReqDto employeeUpdateReqDto, Integer principalId,
            MultipartFile profile) {
        String uuidImageName = PathUtil.writeImageFile(profile);

        String salt = Hash.makeSalt();
        String hashPassword = Hash.encode(employeeUpdateReqDto.getPassword() + salt);
        User user = new User(employeeUpdateReqDto, principalId, uuidImageName, hashPassword, salt);
        Employee employee = new Employee(principalId, employeeUpdateReqDto.getRealName(),
                employeeUpdateReqDto.getCareer(),
                employeeUpdateReqDto.getEducation());
        try {
            userRepository.updateById(user);
            employeeRepository.updateByUserId(employee);
        } catch (Exception e) {
            throw new CustomException("회원 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user = userRepository.findById(principalId);
        UserVo userVoPS = new UserVo(user.getId(), user.getUsername(), user.getProfile(), user.getRole());
        return userVoPS;
    }

    @Transactional
    public void updateEmpolyeeTech(ArrayList<Integer> techList, int employeeId) {
        try {
            employeeTechRepository.deleteByUserId(employeeId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EmployeeInsertSkillReqDto employeeInsertSkillReqDto = new EmployeeInsertSkillReqDto(employeeId, techList);
        try {
            employeeTechRepository.insertSkill(employeeInsertSkillReqDto);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Integer> getSkillForDetail(Integer id) {
        List<Integer> employeeTechPS = employeeTechRepository.findByIdWithSkillForDetail(id);
        return employeeTechPS;
    }

    public List<String> getEmployeeTech(Integer id) {
        List<String> employeeTechPS = employeeTechRepository.findByIdWithSkillNameForDetail(id);
        return employeeTechPS;
    }
}

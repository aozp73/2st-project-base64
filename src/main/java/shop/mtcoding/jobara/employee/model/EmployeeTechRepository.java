package shop.mtcoding.jobara.employee.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeInsertSkillReqDto;

@Mapper
public interface EmployeeTechRepository {

    public List<EmployeeTech> findAll();

    public List<Integer> findByIdWithSkillForDetail(int userId);

    public List<String> findByIdWithSkillNameForDetail(int userId);

    public EmployeeTech findByUserId(int userId);

    public int insert(EmployeeTech employeeTech);

    public int insertSkill(EmployeeInsertSkillReqDto employeeInsertSkillReqDto);

    public int updateByUserId(EmployeeTech employeeTech);

    public int deleteByUserId(int userId);
}

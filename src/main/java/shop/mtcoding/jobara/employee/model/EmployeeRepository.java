package shop.mtcoding.jobara.employee.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;

@Mapper
public interface EmployeeRepository {

    public List<Employee> findAll();

    public EmployeeAndResumeRespDto findEmployeeByIdWithResume(int id);

    public EmployeeAndResumeRespDto findEmployeeByIdAndResume(@Param("id") int id, @Param("resumeId") int resumeId);

    public EmployeeAndResumeRespDto findRecommendWithResume(int id);

    public List<Integer> findRecommendId(int id);

    public List<EmployeeAndResumeRespDto> findAllWithResume(@Param("startNum") int startNum, @Param("row") int row);

    public PagingDto paging(@Param("page") int page, @Param("row") int row);

    public Employee findByUserId(int userId);

    public int insert(int userId);

    public int updateByUserId(Employee employee);

    public int deleteByUserId(int userId);
}

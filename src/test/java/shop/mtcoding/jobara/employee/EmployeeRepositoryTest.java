package shop.mtcoding.jobara.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;
import shop.mtcoding.jobara.employee.model.EmployeeRepository;

@MybatisTest
public class EmployeeRepositoryTest {

      @Autowired
      private EmployeeRepository employeeRepository;

      @Test
      public void findAllWithResume_test() {
            // given
            int startnum = 0;
            int row = 8;

            // when
            List<EmployeeAndResumeRespDto> list = employeeRepository.findAllWithResume(startnum, row);
            // then
            assertThat(list.get(0).getTitle()).isNotEqualTo("이력제 제목1");
            assertThat(list.get(1).getContent()).isNotEqualTo("이력서 내용2");
      }

      @Test
      public void findEmployeeByIdWithResume_test() {
            // given
            int id = 3;
            // when
            EmployeeAndResumeRespDto employeeAndResumeRespDto = employeeRepository.findEmployeeByIdWithResume(id);
            // then
            assertThat(employeeAndResumeRespDto.getRealName()).isNotEqualTo("제갈구글");
            assertThat(employeeAndResumeRespDto.getRole()).isNotEqualTo("employee");
      }

      @Test
      public void findRecommendWithResume_test() {
            // given
            int id = 6;
            // when
            List<EmployeeAndResumeRespDto> employeeAndResumeRespDto = employeeRepository.findRecommendWithResume(id);
            // then
            assertThat(employeeAndResumeRespDto.get(0).getRealName()).isNotEqualTo("김살");
            assertThat(employeeAndResumeRespDto.get(1).getRealName()).isNotEqualTo("박사랑");
      }
}

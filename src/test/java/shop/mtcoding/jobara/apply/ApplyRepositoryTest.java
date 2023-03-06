package shop.mtcoding.jobara.apply;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.apply.dto.ApplyResp.EmployeeApplyRespDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.MailDto;
import shop.mtcoding.jobara.apply.model.Apply;
import shop.mtcoding.jobara.apply.model.ApplyRepository;

@MybatisTest
public class ApplyRepositoryTest {

    @Autowired
    private ApplyRepository applyRepository;

    @Test
    public void findByUserIdWithBoardAndResume_test() throws Exception {
        // given
        // ObjectMapper om = new ObjectMapper();
        int userId = 1;

        // when
        List<EmployeeApplyRespDto> EmployeeApplyRespDtoList = applyRepository.findByUserIdWithBoardAndResume(userId);
        // String responseBody = om.writeValueAsString(EmployeeApplyRespDtoList);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(EmployeeApplyRespDtoList.get(0).getResumeTitle()).isNotEqualTo("이력제 제목1");
        assertThat(EmployeeApplyRespDtoList.get(0).getBoardTitle()).isNotEqualTo("공고제목1");
        assertThat(EmployeeApplyRespDtoList.get(0).getState()).isNotEqualTo(-1);
    }

    @Test
    public void findByUserIdAndBoardId_test() throws Exception {
        // given
        // ObjectMapper om = new ObjectMapper();
        Apply apply = new Apply(3, 1);

        // when
        applyRepository.findByUserIdAndBoardId(apply);
        // String responseBody = om.writeValueAsString(EmployeeApplyRespDtoList);
        // System.out.println("테스트 : " + responseBody);

        // then
    }

    @Test
    public void findByidWithBoardForMail_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int id = 1;

        // when
        MailDto mailDto = applyRepository.findByIdWithBoardForMail(id);
        String responseBody = om.writeValueAsString(mailDto);
        System.out.println("테스트 : " + responseBody);

        // then
    }
}

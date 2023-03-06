package shop.mtcoding.jobara.util;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import shop.mtcoding.jobara.common.util.DateParse;

public class DdayTest {

    @Test
    public void Dday_test() {
        // given
        String date = "2023-05-13";

        // when
        ArrayList res = DateParse.Dday(date);

        // then
        System.out.println("공고 종료일 까지의 D-Day : " + res.get(0));
        System.out.println("공고 종료일자 + 100일 : " + res.get(1));
    }
}

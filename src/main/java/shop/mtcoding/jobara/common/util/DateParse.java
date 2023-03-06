package shop.mtcoding.jobara.common.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DateParse {
      public static String format(Timestamp stamp) {
            LocalDateTime nowTime = stamp.toLocalDateTime();
            String nowStr = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return nowStr;
      }

      public static ArrayList<Object> Dday(String date) {
            LocalDate today = LocalDate.now();
            LocalDate givenDate = LocalDate.parse(date);
            LocalDate futureDate = today.plusDays(100);

            Integer dday = (int) ChronoUnit.DAYS.between(today, givenDate);
            String plus100 = futureDate.toString();
            ArrayList<Object> res = new ArrayList<>();
            res.add(dday);
            res.add(plus100);

            return res;
      }
}
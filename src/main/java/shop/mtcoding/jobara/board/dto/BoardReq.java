package shop.mtcoding.jobara.board.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BoardReq {

    @Getter
    @Setter
    public static class BoardUpdateReqDto {
        private Integer id;
        private String title;
        private String content;

        private Integer career;
        private String careerString;
        private Integer education;
        private String educationString;
        private Integer jobType;
        private String jobTypeString;
        private String deadline;

        private String favor;
        private Integer userId;

        private ArrayList<Integer> checkedValues;
    }

    @Getter
    @Setter
    public static class BoardInsertReqDto {
        private String title;
        private String content;

        private Integer career;
        private String careerString;
        private Integer education;
        private String educationString;
        private Integer jobType;
        private String jobTypeString;
        private String deadline;

        private String favor;
        private Integer userId;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class BoardInsertSkillReqDto {
        private Integer boardId;
        private ArrayList<Integer> checkLang;

        public BoardInsertSkillReqDto(Integer boardId, ArrayList<Integer> checkLang) {
            this.boardId = boardId;
            this.checkLang = checkLang;
        }

    }
}

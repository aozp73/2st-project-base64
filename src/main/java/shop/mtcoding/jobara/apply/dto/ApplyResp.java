package shop.mtcoding.jobara.apply.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.common.util.DateParse;

public class ApplyResp {

    @Getter
    @Setter
    public static class CompanyApplyRespDto {
        private Integer userId;
        private Integer boardId;
        private Integer resumeId;
        private String realName;
        private String title;
        private Integer state;
        private Timestamp createdAt;

        public String getCreatedAtToString() {
            return DateParse.format(createdAt);
        }

        public String getStateToString() {
            switch (state) {
                case 1:
                    return "합격";
                case -1:
                    return "불합격";
                default:
                    return "검토중";
            }
        }

        public String getPreview() {
            if (title.length() > 30) {
                return title.substring(0, 30) + "...";
            } else {
                return title;
            }
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeApplyRespDto {
        private Integer userId;
        private Integer boardId;
        private Integer resumeId;
        private String resumeTitle;
        private String boardTitle;
        private Integer state;
        private Timestamp createdAt;

        public String getCreatedAtToString() {
            return DateParse.format(createdAt);
        }

        public String getStateToString() {
            switch (state) {
                case 1:
                    return "합격";
                case -1:
                    return "불합격";
                default:
                    return "검토중";
            }
        }

        public String getBoardTitlePreview() {
            if (boardTitle.length() > 20) {
                return boardTitle.substring(0, 20) + "...";
            } else {
                return boardTitle;
            }
        }

        public String getResumeTitlePreview() {
            if (resumeTitle.length() > 20) {
                return resumeTitle.substring(0, 20) + "...";
            } else {
                return resumeTitle;
            }
        }
    }

    @Getter
    @Setter
    public static class MailDto {
        private Integer id;
        private Integer userId;
        private Integer boardId;
        private Integer resumeId;
        private Integer state;
        private String boardTitle;
    }
}

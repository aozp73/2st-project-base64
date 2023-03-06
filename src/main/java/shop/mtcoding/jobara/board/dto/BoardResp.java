package shop.mtcoding.jobara.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;

public class BoardResp {
    @Getter
    @Setter
    public static class BoardListRespDto {
        private Integer id;
        private String title;
        private String companyName;
        private Integer userId;
        private String profile;
        private String dday;
        private Integer loveId = 0;
        private String css = "";
    }

    @Getter
    @Setter
    public static class MyBoardListRespDto {
        private Integer id;
        private String title;
        private String companyName;
        private Integer userId;
        private String profile;
        private String dday;

    }

    @Getter
    @Setter
    public static class MyScrapBoardListRespDto {
        private Integer id;
        private String title;
        private String companyName;
        private Integer userId;
        private String profile;
        private String dday;
        private Integer loveId;
        private String css;
    }

    @Getter
    @Setter
    public static class BoardMainRespDto {
        private Integer id;
        private String title;
        private String companyName;
        private Integer userId;
        private String profile;
    }

    @Getter
    @Setter
    public static class BoardUpdateRespDto {
        private Integer id;
        private String title;
        private String content;

        private Integer career;
        private String careerString;
        private Integer education;
        private String educationString;
        private Integer jobType;
        private String jobTypeString;

        private String favor;
        private String deadline;
        private Integer userId;
    }

    @Getter
    @Setter
    public static class BoardDetailRespDto {
        private Integer id;
        private String title;
        private String content;

        private Integer career;
        private String careerString;
        private Integer education;
        private String educationString;
        private Integer jobType;
        private String jobTypeString;

        private String favor;
        private Integer userId;
        private String companyName;
        private String companyScale;
        private String companyField;
        private String profile;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PagingDto {
        public static final int ROW = 8;
        private boolean isNotResult;
        private String keyword;
        private Integer blockCount; // 상수 한페이지에 페이지 넘수 개수(5) 1-5, 6-10
        private Integer currentBlock; // 변수
        private Integer currentPage;
        private Integer startPageNum; // 변수 1 -> 6 -> 11
        private Integer lastPageNum; // 변수 5 -> 10 -> 15
        private Integer totalCount;
        private Integer totalPage;
        private boolean isLast; // getter가 만들어지면 isLast() 이름으로 만들어짐. -> el에서는 last로 찾음
        private boolean isFirst; // getter가 만들어지면 isFirst() 이름으로 만들어짐. -> el에서는 first로 찾음

        private List<BoardListRespDto> boardListDtos;
        private List<EmployeeAndResumeRespDto> resumeListDtos;

        public void makeBlockInfo(String keyword) {
            this.keyword = keyword;
            this.blockCount = 5;

            this.currentBlock = currentPage / blockCount;
            this.startPageNum = 1 + blockCount * currentBlock;
            this.lastPageNum = 5 + blockCount * currentBlock;
            this.totalPage = (int) Math.ceil((double) totalCount / ROW);
            if (this.currentPage == totalPage - 1) { // totalCount 14 / 5 - 1
                this.isLast = true;
            } else {
                this.isLast = false;
            }

            if (totalPage < lastPageNum) {
                this.lastPageNum = totalPage;
            }
        }
    }

}

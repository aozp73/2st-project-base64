package shop.mtcoding.jobara.board.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Board {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Integer career;
    private Integer jobType;
    private Integer education;
    private String favor;
    private String deadline;
    private Timestamp createdAt;

    public Board(Integer id, Integer userId, String title, String content, Integer career, Integer jobType,
            Integer education, String favor, String deadline) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.career = career;
        this.jobType = jobType;
        this.education = education;
        this.favor = favor;
        this.deadline = deadline;
    }

    public Board(Integer userId, String title, String content, Integer career, Integer jobType, Integer education,
            String favor, String deadline) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.career = career;
        this.jobType = jobType;
        this.education = education;
        this.favor = favor;
        this.deadline = deadline;
    }

}

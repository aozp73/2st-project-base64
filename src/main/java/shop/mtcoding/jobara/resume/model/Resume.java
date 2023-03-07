package shop.mtcoding.jobara.resume.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.common.util.DateParse;

@Getter
@Setter
@NoArgsConstructor
public class Resume {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Timestamp createdAt;

    public String getContentPreview() {
        if (content.length() < 20) {
            return content;
        } else {
            return content.substring(0, 20);
        }
    }

    public String getTitlePreview() {
        if (title.length() < 20) {
            return title;
        } else {
            return title.substring(0, 20) + "...";
        }
    }

    public Resume(Integer userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Resume(Integer id, Integer userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public String getCreatedAtToString() {
        return DateParse.format(createdAt);
    }
}

package shop.mtcoding.jobara.love.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Love {
    private Integer id;
    private Integer boardId;
    private Integer userId;
    private Timestamp createdAt;

    public Love() {
    }

    public Love(Integer boardId, Integer userId) {
        this.boardId = boardId;
        this.userId = userId;
    }

}
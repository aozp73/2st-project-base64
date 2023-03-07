package shop.mtcoding.jobara.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Integer id;
    private String username;
    private String profile;
    private String role;

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", profile='" + profile + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

package shop.mtcoding.jobara.love.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LoveResp {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class LoveDetailRespDto {
        private Integer id;
        private String css;

        public LoveDetailRespDto(Integer id, String css) {
            this.id = id;
            this.css = css;
        }

    }

}

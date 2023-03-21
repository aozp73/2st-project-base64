package shop.mtcoding.jobara.company.dto;

import lombok.Getter;
import lombok.Setter;

public class CompanyReq {

    @Getter
    @Setter
    public static class CompanyJoinReqDto {
        private String username;
        private String password;
        private String email;
        private String address;
        private String detailAddress;
        private Long companyNumb;
        private String companyName;
    }

    @Getter
    @Setter
    public static class CompanyUpdateReqDto {
        private String password;
        private String email;
        private String address;
        private String detailAddress;
        private String profile;
        private String tel;
        private String companyName;
        private String companyScale;
        private String companyField;
    }
}

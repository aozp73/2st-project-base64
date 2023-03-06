package shop.mtcoding.jobara.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.company.model.Company;
import shop.mtcoding.jobara.user.model.User;

public class CompanyResp {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompanyUpdateRespDto {
        private String password;
        private String email;
        private String address;
        private String detailAddress;
        private String tel;
        private String companyName;
        private String companyScale;
        private String companyField;

        public CompanyUpdateRespDto(User user, Company company) {
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.detailAddress = user.getDetailAddress();
            this.tel = user.getTel();
            this.companyName = company.getCompanyName();
            this.companyScale = company.getCompanyScale();
            this.companyField = company.getCompanyField();
        }
    }

}

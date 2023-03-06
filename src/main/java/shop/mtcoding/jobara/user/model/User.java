package shop.mtcoding.jobara.user.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String address;
    private String detailAddress;
    private String tel;
    private String profile;
    private String role;
    private Timestamp createdAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String address, String detailAddress) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
    }

    public User(Integer id, String password, String email, String address, String detailAddress, String tel,
            String profile) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
        this.tel = tel;
        this.profile = profile;
    }

    public User(CompanyJoinReqDto companyJoinReqDto, String hashPassword, String salt) {
        this.username = companyJoinReqDto.getUsername();
        this.password = hashPassword;
        this.email = companyJoinReqDto.getEmail();
        this.address = companyJoinReqDto.getAddress();
        this.detailAddress = companyJoinReqDto.getDetailAddress();
        this.salt = salt;
    }

    public User(CompanyUpdateReqDto companyUpdateReqDto, int principalId, String profilePath, String hashPassword,
            String salt) {
        this.id = principalId;
        this.password = hashPassword;
        this.salt = salt;
        this.email = companyUpdateReqDto.getEmail();
        this.address = companyUpdateReqDto.getAddress();
        this.detailAddress = companyUpdateReqDto.getDetailAddress();
        this.tel = companyUpdateReqDto.getTel();
        this.profile = profilePath;
    }

    public User(EmployeeUpdateReqDto employeeUpdateReqDto, int principalId, String profilePath, String hashPassword,
            String salt) {
        this.id = principalId;
        this.password = hashPassword;
        this.salt = salt;
        this.email = employeeUpdateReqDto.getEmail();
        this.address = employeeUpdateReqDto.getAddress();
        this.detailAddress = employeeUpdateReqDto.getDetailAddress();
        this.tel = employeeUpdateReqDto.getTel();
        this.profile = profilePath;
    }

}

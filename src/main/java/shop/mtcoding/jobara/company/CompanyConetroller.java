package shop.mtcoding.jobara.company;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.aop.CompanyCheck;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.common.util.RedisServiceSet;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.company.dto.CompanyResp.CompanyUpdateRespDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
@RequiredArgsConstructor
public class CompanyConetroller {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisServiceSet redisServiceSet;

    @GetMapping("/company/joinForm")
    public String joinForm() {
        return "company/joinForm";
    }

    @GetMapping("/company/updateForm")
    @CompanyCheck
    public String updateForm(Model model) {
        UserVo principal = redisService.getValue("principal");
        CompanyUpdateRespDto companyUpdateRespDto = companyService.getCompanyUpdateRespDto(principal.getId());
        model.addAttribute("companyDto", companyUpdateRespDto);
        redisServiceSet.addModel(model);
        return "company/updateForm";
    }

    @PostMapping("/company/join")
    public String join(CompanyJoinReqDto companyJoinReqDto) {
        Verify.validateString(companyJoinReqDto.getUsername(), "유저네임을 입력하세요.");
        Verify.validateString(companyJoinReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(companyJoinReqDto.getEmail(), "이메일을 입력하세요.");
        companyService.insertCompany(companyJoinReqDto);
        return "redirect:/";
    }

    @PostMapping("/company/update")
    public ResponseEntity<?> update(@RequestBody CompanyUpdateReqDto companyUpdateReqDto) throws IOException {
        // 유효성 체크
        if (companyUpdateReqDto.getProfile() == null || companyUpdateReqDto.getProfile().isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }

        String base64Image = companyUpdateReqDto.getProfile();
        String[] parts = base64Image.split(",");
        // base64Data : data:image/png;base64, 없앤 base64 String 값
        String base64Data = parts[1];
        // 하드 디스크는 이진데이터를 읽어 저장하므로 base64 문자셋 -> 이진 데이터 디코딩
        byte[] decodedData = Base64.getDecoder().decode(base64Data);
        // mimeType : image/png
        String mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(decodedData));

        if (!mimeType.startsWith("image/")) {
            throw new CustomException("사진 파일만 업로드 할 수 있습니다.");
        }
        // 유효성 체크 완료

        // 하드 저장 및 저장경로 반환
        // StaticFolder() :
        // c:\workspace\project_lab\project-group-2-2st\src\main\resources\static\
        String staticFolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        UUID uuid = UUID.randomUUID();
        String filePath = "\\images\\" + uuid + "_" + System.currentTimeMillis() + "."
                + mimeType.split("/")[1];

        // filePath :
        // \images\ uuid값_시간.프로필사진.png
        Path imageFilePath = Paths.get(staticFolder + "\\" + filePath);
        Files.write(imageFilePath, decodedData);

        // DB에 파일 경로 저장 후

        return ResponseEntity.ok().body(filePath);
    }
}

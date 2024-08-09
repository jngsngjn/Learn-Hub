package project.homelearn.dto.common.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.entity.user.Gender;

@Data
public class RegisterDto {

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\\d]{6,12}$",
            message = "아이디 규칙 검증 실패")
    private String username; // 영문자 반드시 포함, 숫자 포함 가능, 최소 6자리, 최대 12자리

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{10,18}$",
            message = "비밀번호 규칙 검증 실패")
    private String password; // 대문자 최소 1개, 특수문자 최소 1개, 최소 10자리, 최대 18자리

    @NotBlank
    private String name;

    private Gender gender;

    private MultipartFile image;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;
}

/*
학생
{
    "username": "jngsngjn",
    "password": "1234",
    "name": "정성진",
    "gender": "MALE",
    "phone": "010-1222-1333",
    "email": "wjdtjdwls93@gmail.com"
}

강사
{
    "username": "sonny",
    "password": "1234",
    "name": "손흥민",
    "phone": "010-1222-1343",
    "email": "jkj1544@naver.com"
}
 */
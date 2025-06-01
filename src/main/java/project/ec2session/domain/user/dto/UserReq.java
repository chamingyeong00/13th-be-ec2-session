package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import project.ec2session.domain.user.entity.User;

public class UserReq {

    @Schema(name = "SignUpDto", description = "회원가입 요청 DTO")
    public record SignUpDto(
            @Schema(description = "사용자 아이디", example = "mingyeong")
            @NotBlank(message = "아이디는 필수 입력 값입니다.")
            String username,

            @Schema(description = "사용자 비밀번호", example = "1234")
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            String password,

            @Schema(description = "사용자 닉네임", example = "MG")
            @NotBlank(message = "닉네임은 필수 입력 값입니다.")
            String nickname
    ) {
        public User toEntity(String encodedPassword) {
            return User.builder()
                    .username(username)
                    .password(encodedPassword)
                    .nickname(nickname)
                    .build();
        }
    }

    @Schema(name = "SignInReqDto", description = "로그인 요청 DTO")
    public record SignInDto(
            @Schema(description = "사용자 아이디", example = "mingyeong")
            @NotBlank(message = "아이디는 필수 입력 값입니다.")
            String username,
            @Schema(description = "사용자 비밀번호", example = "1234")
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            String password
    ) { }

    @Schema(name = "UpdateInReqDto", description = "업데이트 요청 DTO")
    public record UpdateInfo(
            @Schema(description = "사용자 닉네임", example = "MG")
            @NotBlank(message = "닉네임은 필수 입력 값입니다.")
            String nickname
    ) { }
}

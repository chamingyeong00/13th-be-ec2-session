package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ec2session.common.auth.CustomUserDetails;
import project.ec2session.domain.user.dto.UserReq;
import project.ec2session.domain.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "[유저 관련 API]", description = "유저 관련 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "자신 정보 조회", description = "자신의 정보 반환 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "자신의 정보 반환 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
						  			{
							  			"accessToken" : "<accessToken>",
							  			"refreshToken" : "<refreshToken>"
							  		}
							  		""")
                    })),
            @ApiResponse(responseCode = "400", description = "자신의 정보 조회 실패")
    })

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(userService.readById(userDetails.getUserId()));
    }

    @Operation(summary = "회원 목록 조회", description = "모든 회원의 정보 반환 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모든 회원의 정보 반환 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
						  			{
							  			"accessToken" : "<accessToken>",
							  			"refreshToken" : "<refreshToken>"
							  		}
							  		""")
                    })),
            @ApiResponse(responseCode = "400", description = "모든 회원의 정보 조회 실패")
    })

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.readAll());
    }

    @Operation (summary = "자신 정보 수정", description = "자신의 정보 수정 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "자신의 정보 수정 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
						  			{
							  			"accessToken" : "<accessToken>",
							  			"refreshToken" : "<refreshToken>"
							  		}
							  		""")
                    })),
            @ApiResponse(responseCode = "400", description = "자신의 정보 수정 실패")
    })

    @PutMapping
    public ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails,
                                            @RequestBody @Valid UserReq.UpdateInfo request) {
        userService.update(userDetails.getUserId(), request);
        return ResponseEntity.ok("요청 성공");
    }
}

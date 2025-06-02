package project.ec2session.domain.user.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.common.auth.CustomUserDetails;
import project.ec2session.domain.user.dto.UserReq;

@Tag(name = "[유저 관련 API]", description = "유저 관련 API")
public interface UserApi {
    @Operation(summary = "로그인한 유저 정보 조회", description = "로그인한 유저 정보 반환 시도 (인증된 유저만 접근 가능)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인한 유저 정보 반환 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                    	"userId": 1,
                                        "username": "mingyeong",
                                        "nickname": "MG"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "404", description = "정보 조회 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "message": "존재하지 않는 회원입니다."
                                    }
                                    """)
                    }))
    })
    ResponseEntity<?> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "모든 회원 목록 조회", description = "모든 회원의 정보 반환 시도 (인증된 유저만 접근 가능)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모든 회원의 정보 반환 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    [
                                              {
                                                  "userId": 1,
                                                  "username": "mingyeong",
                                                  "nickname": "MG"
                                              },
                                              {
                                                  "userId": 2,
                                                  "username": "gildong",
                                                  "nickname": "GD"
                                              }
                                    ]
                                    """)
                    }))
    })
    ResponseEntity<?> getAllUser();

    @Operation (summary = "로그인한 유저 정보 수정", description = "로그인한 유저 정보 수정 시도 (인증된 유저만 접근 가능)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인한 유저 정보 수정 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
						  			    "요청 성공"
							  		""")
                    })),
            @ApiResponse(responseCode = "404", description = "로그인한 정보 수정 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "message": "존재하지 않는 회원입니다."
                                    }
                                    """)
                    }))
    })
    ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @RequestBody @Valid UserReq.UpdateInfo request);
}


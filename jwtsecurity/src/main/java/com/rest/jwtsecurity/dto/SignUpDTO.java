package com.rest.jwtsecurity.dto;

import com.rest.jwtsecurity.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpDTO {

    @ApiModelProperty("이메일")
    private String email;

    @ApiModelProperty("닉네임")
    private String nickname;

    @ApiModelProperty("프로필 사진")
    private String profileImg;

    @ApiModelProperty("유저 권한")
    private List<String> roles;


    public Member transMember(){
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .profileImg(this.profileImg)
                .roles(this.roles)
                .build();
    }
}

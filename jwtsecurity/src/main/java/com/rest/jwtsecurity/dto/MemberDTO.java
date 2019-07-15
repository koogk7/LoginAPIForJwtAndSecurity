package com.rest.jwtsecurity.dto;

import com.rest.jwtsecurity.entity.Member;
import lombok.Getter;

@Getter
public class MemberDTO {
    private Long id;
    private String nickname;
    private String email;
    private String profileImg;

    public MemberDTO(Member member){
        this.id = member.getId();
        if(this.id == -1){
            return ;
        }
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImg = member.getProfileImg();
    }
}

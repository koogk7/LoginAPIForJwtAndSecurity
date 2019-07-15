package com.rest.jwtsecurity.service;

import com.rest.jwtsecurity.config.security.JwtTokenProvider;
import com.rest.jwtsecurity.repo.MemberRepository;
import com.rest.jwtsecurity.dto.SignUpDTO;
import com.rest.jwtsecurity.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Member> findById(Long id){
        return memberRepo.findById(id);
    }


    public Optional<Member> findByEmail(String email){
        return memberRepo.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return memberRepo.existsByEmail(email);
    }

    public String signIn(String email, String password){
        Member member = memberRepo.findByEmail(email).orElseGet(()-> Member.builder().id(-1L).build());
        String encodePassword = passwordEncoder.encode(password);
        logger.info(member.toString());

        if(member.getId() == -1) return "존재하지 않는 이메일입니다";

        if(!passwordEncoder.matches(password, encodePassword))
            return "올바르지 않은 계정";
        return jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
    }

    public String signUp(SignUpDTO signUpDTO, String password){
        //검증하는 함수 구현 필요
        if(memberRepo.existsByEmail(signUpDTO.getEmail()))
            return "이미 존재하는 이메일입니다.";

        Member member = signUpDTO.transMember();
        member.setPassword(passwordEncoder.encode(password));
        memberRepo.save(member);
        return "회원가입 성공";
    }
}

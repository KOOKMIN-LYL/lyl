package com.kookmin.lyl.web;

import com.kookmin.lyl.infra.support.JwtTokenProvider;
import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.dto.MemberCreateInfo;
import com.kookmin.lyl.module.member.repository.MemberRepository;
import com.kookmin.lyl.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @PostMapping(value = "/member/join")
    public String join(@RequestBody MemberCreateInfo memberCreateInfo) {
        memberService.join(memberCreateInfo);

        return "ok";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Map<String, String> user) {
        System.out.println("USER : " + user);
        Member member = memberRepository.findByMemberId(user.get("username"))
                .orElseThrow(EntityNotFoundException::new);

        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> roles = new ArrayList<String>();
        roles.add(member.getMemberType().toString());

        return jwtTokenProvider.createToken(member.getMemberId(), roles);
    }

    @PostConstruct
    public void setUpMember() {
        MemberCreateInfo memberCreateInfo = new MemberCreateInfo();
        memberCreateInfo.setId("user");
        memberCreateInfo.setPassword("123");
        memberCreateInfo.setAddress("주소");
        memberCreateInfo.setName("유저1");
        memberCreateInfo.setEmail("dlwlsrn9411@kookmin.ac.kr");
        memberCreateInfo.setPhone("00-00-00");
        memberService.join(memberCreateInfo);
    }
}

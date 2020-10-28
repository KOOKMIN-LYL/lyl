package com.kookmin.lyl.web;

import com.kookmin.lyl.module.member.dto.MemberCreateInfo;
import com.kookmin.lyl.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/member/join")
    public String join(MemberCreateInfo memberCreateInfo) {
        memberService.join(memberCreateInfo);

        return "ok";
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

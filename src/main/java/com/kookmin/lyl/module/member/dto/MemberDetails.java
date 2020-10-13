package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.domain.MemberStatus;
import com.kookmin.lyl.module.member.domain.MemberType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDetails {
    // 멤버의 세부정보를 가져오기
    // 그래도 비밀번호는 가져오면 안되겠지
    private String memberName;
    private String memberId;
//    private String password;
    private String address;
    private String phone;
    private String email;
    private MemberType memberType;  // 관리자니 유저니?
    private MemberStatus memberStatus;

    // get으로 가져온다
    public MemberDetails(Member member) {
        this.memberName = member.getMemberName();
        this.memberId = member.getMemberId();
//        this.password = member.getPassword();
        this.address = member.getAddress();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.memberType = member.getMemberType();
        this.memberStatus = member.getStatus();
    }
}

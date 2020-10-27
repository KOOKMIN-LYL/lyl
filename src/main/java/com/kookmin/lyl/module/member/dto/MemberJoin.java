package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;

@Data
public class MemberJoin {
    // 생성부
    private Long usn;   // 여기서는 usn 나오고 usn은 자동 ++ 그걸로 구현을 해야겠지?
    private String memberName;
    private String memberId;
    private String password;
    private String address;
    private String phone;
    private String email;
}

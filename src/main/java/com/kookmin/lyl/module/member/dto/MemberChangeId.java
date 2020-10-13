package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;

@Data
public class MemberChangeId {
    private String id;
    private String password;

    private void ChangeId(Member member){
        if(password.equals(member.getPassword())){
            member.setMemberId(id);
        }
        // TODO:: 아닌 경우 다시 돌려보내는건 API 뿅 하면 될 거 같음
    }
}

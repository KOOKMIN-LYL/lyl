package com.kookmin.lyl.module.member.dto;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Data;

import java.util.Scanner;

@Data
public class MemberChangePassword {

    private void ChangePassword(Member member){
        // TODO:: 와 에이 스캐너는 좀...
        Scanner sc = new Scanner(System.in);
        String passwordCheck = sc.next();
        // 맞다면 이걸로 바꿀거야. 새로운 비밀번호
        String newPassword;
        // 비밀번혹가 맞았다면
        if(passwordCheck.equals(member.getPassword())){
            newPassword = sc.next();    // 새로 변경할 비밀번호를 입력받는다
            String doublecheckPassword;
            doublecheckPassword = sc.next();    // 새로 변경할 비밀번호를 한 번 더 체크하기
            if(newPassword.equals(doublecheckPassword)){
                member.setPassword(newPassword);    // 다 맞게 했다면 그걸로 변경해줌
            }
        }
        sc.close(); // 스캐너 끝내고.
    }
}

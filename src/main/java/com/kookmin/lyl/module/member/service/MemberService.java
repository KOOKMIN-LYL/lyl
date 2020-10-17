package com.kookmin.lyl.module.member.service;
import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.dto.*;
import java.util.List;
public interface MemberService {

    public Long join (MemberJoin memberJoin);   // 회원가입 (join)      ok
    public void changeMemberType(MemberGetId memberGetId);  // 회원 타입 변경     ok
    public void validateDuplicateMember(MemberValidateDuplicate memberValidateDuplicate, MemberJoin memberJoin);   // id, email로 중복 검사하기
    public List<Member> findMembers();          // 회원 전체를 조회
    public Member findByEmail(MemberFindByEmail memberFindByEmail);    // email 로 한 사람 검색
    public Member findOneByMemberId(MemberId memberId);    // id로 한 사람 검색
    public List<Member> findByMemberName(MemberFindByName memberFindByName);  // 이름으로 멤버 검색
    public void editProfile(MemberId memberId, MemberEditInfo memberEditInfo);   // 개인정보 변경하기
    public boolean checkPassword(String password, MemberPasswordCheck memberPasswordCheck);
}

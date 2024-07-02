package org.koreait;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
    private List<Member> members;
    static private int lastMemberID;

    public MemberController() {
        members = new ArrayList<Member>();
        lastMemberID = 0;
    }

    public void addMember(String loginId, String loginPw, String nickName) {
        Member member = new Member(++lastMemberID, Util.getNow(), loginId, loginPw, nickName);
        members.add(member);
    }

    public void removeMember(String loginId, String loginPw) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
                members.remove(member);
                break;
            }else {
                System.out.println("해당 정보를 가지고 있는 회원은 없습니다.");
            }
        }
    }
}

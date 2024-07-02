package org.koreait;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
    private List<Member> members;
    private int lastMemberID;

    private List<String> loginId_DB;

    public MemberController() {
        members = new ArrayList<Member>();
        loginId_DB = new ArrayList<>();
        lastMemberID = 0;
    }

    public int addMember() { // String loginId, String loginPw, String nickName
        String loginId = "";
        String loginPw = "";
        String nickName = "";
        String re_loginPw = "";

        // 아이디 입력
        System.out.print("가입할 아이디 입력 : ");
        loginId = Container.getScanner().nextLine();

        // 아이디 중복 확인
        for (String lId : loginId_DB) {
            if (lId.equals(loginId)) {
                System.out.println("아이디 중복");
                return -1; // 아이디 중복
            }
        }
        // 비밀번호 입력
        System.out.print("비밀번호 입력 : ");
        loginPw = Container.getScanner().nextLine();

        // 비밀번호 재입력
        System.out.print("입력했던 비밀번호를 다시 입력해주세요. : ");
        re_loginPw = Container.getScanner().nextLine();

        if (re_loginPw.equals(loginPw)) {
            Member member = new Member(++lastMemberID, Util.getNow(), loginId, loginPw, nickName);

            loginId_DB.add(loginId);

            members.add(member);

            System.out.println("== 회원 가입 완료 ==");
            return 0;
        } else {
            System.out.println("비밀번호 재입력 오류");
            return -2; // 비밀번호 재입력 오류
        }

    }

    public void removeMember() {
        String loginId = "";
        String loginPw = "";

        System.out.println("삭제할 아이디 : ");

        for (Member member : members) {
            if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
                members.remove(member);
                break;
            } else {
                System.out.println("해당 정보를 가지고 있는 회원은 없습니다.");
            }
        }
    }

    public void makeTestData() {
        System.out.println("== 멤버 테스트 데이터 추가 ==");
        for (int i = 0; i < 3; i++) {
            Member member = new Member(++lastMemberID, Util.getNow(), ("test0" + i), ("test0" + i), ("test0" + i));
            loginId_DB.add(("test0" + i));
            members.add(member);
        }
        System.out.println("== 멤버 테스트 데이터 추가 완료 ==");
    }
}

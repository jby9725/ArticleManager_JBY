package org.koreait;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
    private List<Member> members;
    private int lastMemberID;

    private List<String> loginId_DB;

    private String nowLoginId;
    private boolean loginState;

    public MemberController() {
        members = new ArrayList<Member>();
        loginId_DB = new ArrayList<>();
        lastMemberID = 0;
        loginState = false;
    }

    public void joinMember() { // String loginId, String loginPw, String nickName
        String loginId = "";
        String loginPw = "";
        String nickName = "";
        String re_loginPw = "";

        System.out.println("== 회원가입 ==");
        // 아이디 입력
        while (true) {
            System.out.print("가입할 아이디 입력 : ");
            loginId = Container.getScanner().nextLine();
            // 아이디 중복 확인
            if (!isJoinableLoginId(loginId)) {
                System.out.println("아이디 중복");
                continue;
            }
            break;
        }

        while (true) {
            // 비밀번호 입력
            System.out.print("비밀번호 입력 : ");
            loginPw = Container.getScanner().nextLine();

            // 비밀번호 재입력
            System.out.print("입력했던 비밀번호를 다시 입력해주세요. : ");
            re_loginPw = Container.getScanner().nextLine();

            if(!re_loginPw.equals(loginPw)) {
                System.out.println("비밀번호를 다시 처음부터 입력해주세요.");
                continue;
            }
            break;
        }

        System.out.print("닉네임 입력 : ");
        nickName = Container.getScanner().nextLine();

        if (re_loginPw.equals(loginPw)) {
            Member member = new Member(++lastMemberID, Util.getNow(), loginId, loginPw, nickName);

            loginId_DB.add(loginId);

            members.add(member);

            System.out.printf("%d번 회원이 가입되었습니다.\n", lastMemberID);
            System.out.println("== 회원 가입 완료 ==");
            return;
        } else {
            System.out.println("비밀번호 재입력 오류");
            return; // 비밀번호 재입력 오류
        }

    }

    private boolean isJoinableLoginId(String loginId) {

        for (String lId : loginId_DB) {
            if (lId.equals(loginId)) {
                // 중복 됬다.
                return false;
            }
        }
        // 중복 없다.
        return true;
    }

    public void removeMember() {
        String loginId = "";
        String loginPw = "";

        System.out.print("삭제할 아이디 : ");
        loginId = Container.getScanner().nextLine();
        System.out.print("삭제할 아이디의 비밀번호 : ");
        loginPw = Container.getScanner().nextLine();

        for (Member member : members) {
            if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
                members.remove(member);
                System.out.printf("%s 의 회원 정보를 삭제하였습니다.\n", member.getLoginId());
                break;
            } else {
                System.out.println("해당 정보를 가지고 있는 회원은 없습니다.");
            }
        }
    }

    public void loginMember() {
        String loginId = "";
        String loginPw = "";

        if (loginState) {
            System.out.printf("이미 로그인이 되어있습니다. 아이디 : %s\n", nowLoginId);
            return;
        }

        System.out.print("아이디 : ");
        loginId = Container.getScanner().nextLine();
        System.out.print("비밀번호 : ");
        loginPw = Container.getScanner().nextLine();

        for (Member member : members) {
            if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
                System.out.printf("%s 로 로그인 하였습니다.\n", member.getLoginId());
                this.nowLoginId = member.getLoginId();
                this.loginState = true;
                return;
            }
        }
        System.out.println("해당 정보를 가지고 있는 회원은 없습니다.");
    }

    public void logoutMember() {
        if (!loginState) {
            System.out.println("현재 로그인 중인 계정이 없습니다.");
        } else {
            System.out.printf("%s 계정에서 로그아웃 합니다.\n", nowLoginId);
            loginState = false;
            nowLoginId = "";
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

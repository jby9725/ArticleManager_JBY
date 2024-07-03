package org.koreait;

public class App {

    MemberController memberController = new MemberController();
    ArticleController articleController = new ArticleController();

    public void run() {
        System.out.println("== 프로그램 시작 ==");

        articleController.makeTestData();
        memberController.makeTestData();

        while (true) {
            System.out.print("명령어) ");
            String cmd = Container.getScanner().nextLine().trim();
            // CommendParser commendParser = new CommendParser(cmd); // 아직 필요 없다.

            if (cmd.isEmpty()) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;
            }

            if (cmd.equals("member join")) {

                memberController.joinMember();

            } else if (cmd.equals("member login")) {

                memberController.loginMember();

            } else if (cmd.equals("member logout")) {

                memberController.logoutMember();

            } else if (cmd.equals("article write")) {

                articleController.write();

            } else if (cmd.startsWith("article list")) {

                articleController.list(cmd);

            } else if (cmd.startsWith("article detail")) {

                articleController.detail(cmd);

            } else if (cmd.startsWith("article delete")) {

                articleController.delete(cmd);

            } else if (cmd.startsWith("article modify")) {

                articleController.modify(cmd);

            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }

        }

    }

}

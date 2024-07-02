package org.koreait;

public class App {

    static ArticleController articleController = new ArticleController();
    static MemberController memberController = new MemberController();

    public void run() {
        System.out.println("== 프로그램 시작 ==");

        articleController.makeTestData();
        memberController.makeTestData();

        while (true) {
            System.out.print("명령어) ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.isEmpty()) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                System.out.println("==프로그램 종료==");
                break;
            }

            if (cmd.equals("member add")) {
                int state = memberController.addMember();

                continue;
            }
//            else if(cmd.equals("member remove")){
//                memberController.removeMember();
//            }

            if (cmd.equals("article write")) {

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

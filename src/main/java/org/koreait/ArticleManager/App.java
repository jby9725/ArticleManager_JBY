package org.koreait.ArticleManager;

import org.koreait.Container;
import org.koreait.User;
import org.koreait.controller.ArticleController;
import org.koreait.controller.Controller;
import org.koreait.controller.MemberController;

public class App {

    public void run() {
        System.out.println("== 프로그램 시작 ==");

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();

        articleController.makeTestData();
        memberController.makeTestData();

        Controller controller = null;

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

            String[] cmdBits = cmd.split(" ");

            String controllerName = cmdBits[0];

            if (cmd.length() == 1) {
                System.out.println("명령어를 확실히 입력하세요");
                continue;
            }

            String actionMethodName = cmdBits[1];

            String forLoginCheck = controllerName+ '/' + actionMethodName;

            switch (forLoginCheck) {
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    if (User.getUser() == null) {
                        System.out.println("로그인 필요");
                        continue;
                    }
                    break;
            }

            switch (forLoginCheck) {
                case "member/login":
                case "member/join":
                    if (User.getUser() != null) {
                        System.out.println("로그아웃 필요");
                        continue;
                    }
                    break;
            }

            if(controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);

        }

    }

}

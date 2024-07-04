package org.koreait.controller;

import org.koreait.ArticleManager.Container;
import org.koreait.dto.Article;
import org.koreait.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ArticleController extends Controller {

    private int lastArticleId = 0;
    private List<Article> articles;
    private String cmd;

    public ArticleController() {
        articles = Container.articleDao.articles;
    }

    @Override
    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                write();
                break;
            case "list":
                list(cmd);
                break;
            case "detail":
                detail(cmd);
                break;
            case "delete":
                delete(cmd);
                break;
            case "modify":
                modify(cmd);
                break;
            default:
                System.out.println("Unknown Action Method: " + actionMethodName);
                break;
        }
    }

    private void write() {
        System.out.println("== 게시글 작성 ==");

        int id = ++lastArticleId;
        String regDate = Util.getNow();
        String updateDate = regDate;
        System.out.print("제목 : ");
        String title = Container.getScanner().nextLine();
        System.out.print("내용 : ");
        String body = Container.getScanner().nextLine();

        Article article = new Article(id, regDate, updateDate, title, body, getUser().getLoginId(), getUser().getNickName());
        articles.add(article);
        System.out.println(id + "번 글이 생성되었습니다");
    }

    private void delete(String cmd) {
        System.out.println("== 게시글 삭제 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article foundArticle = found_article(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        if (foundArticle.getAuthorId().equals(getUser().getLoginId())) {
            articles.remove(foundArticle);
            System.out.println(id + "번 게시글이 삭제되었습니다");
        } else {
            System.out.println("해당 게시글을 작성한 사람만 삭제할 수 있습니다.");
        }
    }

    private void modify(String cmd) {
        System.out.println("== 게시글 수정 ==");

        int id = -1;
        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("올바른 명령어가 아닙니다. ex) modify 뒤에 삭제할 게시물의 id값이 없음");
            return;
        }

        Article foundArticle = found_article(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        if (foundArticle.getAuthorId().equals(getUser().getLoginId())) {
            System.out.println("기존 제목 : " + foundArticle.getTitle());
            System.out.println("기존 내용 : " + foundArticle.getBody());
            System.out.print("새 제목 : ");
            String newTitle = Container.getScanner().nextLine();
            System.out.print("새 내용 : ");
            String newBody = Container.getScanner().nextLine();

            foundArticle.setTitle(newTitle);
            foundArticle.setBody(newBody);
            foundArticle.setUpdateDate(Util.getNow());

            System.out.println(id + "번 게시글이 수정되었습니다");
        } else {
            System.out.println("해당 게시글을 작성한 사람만 수정할 수 있습니다.");
        }
    }

    private void list(String cmd) {
        if (cmd.split(" ").length > 2 && !cmd.split(" ")[2].isEmpty()) { // 안 비어있으면
            list_selected(cmd.split(" ")[2].trim());
        } else {
            list_all();
        }
    }

    private void list_all() {
        System.out.println("== 게시글 목록 ==");

        if (articles.isEmpty()) {
            System.out.println("게시글이 아무것도 없어요");
        } else {
            System.out.println("  번호   /    작성자   /    날짜   /   제목   /   내용   ");
            for (int i = articles.size() - 1; i >= 0; i--) {
                Article article = articles.get(i);
                if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                    System.out.printf("  %d   /   %s      /   %s      /   %s   /   %s  \n", article.getId(), article.getAuthorName(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                } else {
                    System.out.printf("  %d   /   %s      /   %s      /   %s   /   %s  \n", article.getId(), article.getAuthorName(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                }
            }
        }
    }

    private void list_selected(String option) {

        List<Article> selected_articles = new ArrayList<>();

        for (Article article : articles) {
            if (article.getTitle().contains(option)) {
                selected_articles.add(article);
            }
        }

        if (selected_articles.isEmpty()) {
            System.out.println("선택한 옵션을 가진 게시글이 아무것도 없어요");
        } else {
            System.out.println("  번호   /    작성자   /    날짜   /   제목   /   내용   ");
            for (int i = selected_articles.size() - 1; i >= 0; i--) {
                Article article = selected_articles.get(i);
                if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                    System.out.printf("  %d   /   %s      /   %s      /   %s   /   %s  \n", article.getId(), article.getAuthorName(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                } else {
                    System.out.printf("  %d   /   %s      /   %s      /   %s   /   %s  \n", article.getId(), article.getAuthorName(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                }

            }
        }
    }

    private void detail(String cmd) {
        System.out.println("== 게시글 상세보기 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article foundArticle = found_article(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성자 : " + foundArticle.getAuthorName() + "(" + foundArticle.getAuthorId() + ")");
        System.out.println("작성날짜 : " + foundArticle.getRegDate());
        System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());
    }

    private Article found_article(int id) {
        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        return foundArticle;
    }

    public void makeTestData() {
        System.out.println("== 테스트 데이터 추가 ==");
        for (int i = 0; i < 3; i++) {
            int id = lastArticleId + 1;
            String regDate = Util.getNow();
            String updateDate = regDate;
            String title = "test0" + (i + 1);
            String body = "test0" + (i + 1);

            Article article = new Article(id, regDate, updateDate, title, body, ("test0" + (i + 1)), ("t0" + (i + 1)));
            articles.add(article);

            System.out.println(id + "번 글이 생성되었습니다");
            lastArticleId++;
        }
        System.out.println("== 테스트 데이터 추가 완료 ==");
    }
}

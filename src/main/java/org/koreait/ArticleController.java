package org.koreait;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {

    static int lastArticleId = 0;
    static List<Article> articles = new ArrayList<>();

    public void write() {
        System.out.println("== 게시글 작성 ==");

        int id = ++lastArticleId;
        String regDate = Util.getNow();
        String updateDate = regDate;
        System.out.print("제목 : ");
        String title = Container.getScanner().nextLine();
        System.out.print("내용 : ");
        String body = Container.getScanner().nextLine();

        Article article = new Article(id, regDate, updateDate, title, body);
        articles.add(article);
        System.out.println(id + "번 글이 생성되었습니다");

    }

    public void delete(String cmd) {
        System.out.println("== 게시글 삭제 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article foundArticle = found_article(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        articles.remove(foundArticle);
        System.out.println(id + "번 게시글이 삭제되었습니다");
    }

    public void modify(String cmd) {
        System.out.println("== 게시글 수정 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article foundArticle = found_article(id);

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
    }

    public void list(String cmd) {
        if (cmd.split(" ").length > 2 && !cmd.split(" ")[2].isEmpty()) { // 안 비어있으면
            list_selected(cmd.split(" ")[2].trim());
        } else {
            list_all();
        }
    }

    private void list_all() {
        System.out.println("== 게시글 목록 ==");

        if (articles.size() == 0) {
            System.out.println("게시글이 아무것도 없어요");
        } else {
            System.out.println("  번호   /    날짜   /   제목   /   내용   ");
            for (int i = articles.size() - 1; i >= 0; i--) {
                Article article = articles.get(i);
                if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                    System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                } else {
                    System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
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
            System.out.println("  번호   /    날짜   /   제목   /   내용   ");
            for (int i = selected_articles.size() - 1; i >= 0; i--) {
                Article article = selected_articles.get(i);
                if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                    System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                } else {
                    System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                }

            }
        }
    }

    public void detail(String cmd) {
        System.out.println("== 게시글 상세보기 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article foundArticle = found_article(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        System.out.println("번호 : " + foundArticle.getId());
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
            String title = "test" + (i + 1);
            String body = "test" + (i + 1);

            Article article = new Article(id, regDate, updateDate, title, body);
            articles.add(article);

            System.out.println(id + "번 글이 생성되었습니다");
            lastArticleId++;
        }
        for (int i = 0; i < 2; i++) {
            int id = lastArticleId + 1;
            String regDate = Util.getNow();
            String updateDate = regDate;
            String title = "제목" + (i + 1);
            String body = "내용" + (i + 1);

            Article article = new Article(id, regDate, updateDate, title, body);
            articles.add(article);

            System.out.println(id + "번 글이 생성되었습니다");
            lastArticleId++;
        }
        System.out.println("== 테스트 데이터 추가 완료 ==");
    }
}

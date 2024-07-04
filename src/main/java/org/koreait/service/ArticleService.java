package org.koreait.service;

import org.koreait.ArticleManager.Container;
import org.koreait.dao.ArticleDao;
import org.koreait.dto.Article;

import java.util.List;

public class ArticleService {

    private static ArticleDao articleDao;

    public ArticleService() {
        articleDao = Container.articleDao;
    }

    public void add(Article article) {
        articleDao.add(article);

    }

    public int getSize() {
        return articleDao.getSize();
    }


    public static List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public void delete(Article article) {
        articleDao.remove(article);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }
}

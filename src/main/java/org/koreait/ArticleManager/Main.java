package org.koreait.ArticleManager;

import org.koreait.Container;

public class Main {
    public static void main(String[] args) {

        Container.init();

        new App().run();

        Container.close();
    }
}
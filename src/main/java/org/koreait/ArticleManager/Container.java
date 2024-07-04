package org.koreait.ArticleManager;

import org.koreait.dao.ArticleDao;
import org.koreait.dao.MemberDao;

import java.util.Scanner;

public class Container {
    private static Scanner sc;

    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
    }

    // 공유 자원을 모아두는 공간 초기화
    public static void init() {
        sc = new Scanner(System.in);
    }

    // 공유 자원을 모아두는 공간 자원 해제
    public static void close() {
        sc.close();
    }

    public static Scanner getScanner() {
        return sc;
    }
}
package org.koreait.controller;

import org.koreait.dto.Member;

public abstract class Controller {

    private static Member user = null;
    private static boolean isLogined = false;

    public abstract void doAction(String cmd, String actionMethodName);

    public static Member getUser() {
        return user;
    }

    public static void setUser(Member member) {
        user = member;
    }
}
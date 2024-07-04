package org.koreait.dao;

import org.koreait.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    public static List<Member> members;

    public MemberDao() {
        members = new ArrayList<>();
    }

    public static List<Member> getMembers() {
        return members;
    }
}
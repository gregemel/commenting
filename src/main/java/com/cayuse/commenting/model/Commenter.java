package com.cayuse.commenting.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Commenter {

    public enum Role {Analyst, Reviewer, Admin, PI, Researcher}

    @Getter @Setter
    UUID userId;

    @Getter
    Role role;

    private Commenter() {
    }

    public static Commenter of(UUID userId, Role role) {
        Commenter commenter = new Commenter();

        commenter.userId = userId;
        commenter.role = role;

        return commenter;
    }

    boolean isReviewer() {
        return role == Role.Reviewer;
    }

    boolean isAnalyst() {
        return role == Role.Analyst;
    }

    boolean isAdmin() {
        return role == Role.Admin;
    }

    boolean isAuthor(Comment comment) {
        return isEqual(comment.getAuthor());
    }

    boolean isEqual(Commenter commenter) {
        return userId == commenter.getUserId();
    }
}

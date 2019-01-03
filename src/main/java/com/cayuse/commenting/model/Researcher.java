package com.cayuse.commenting.model;

public class Researcher implements Commenter {
    @Override
    public void addComment(EformQuestion question, Comment comment) {

        question.addComment(comment);

    }
}

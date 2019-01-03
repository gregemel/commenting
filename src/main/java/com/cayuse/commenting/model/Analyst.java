package com.cayuse.commenting.model;

public class Analyst implements Commenter {
    @Override
    public void addComment(EformQuestion question, Comment comment) {
        question.addComment(comment);
    }
}
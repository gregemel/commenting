package com.cayuse.commenting.model;

import java.util.ArrayList;
import java.util.List;

class EformQuestion {
    private List<Comment> comments = new ArrayList<>();

    List<Comment> getComments() {
        return comments;
    }

    void addComment(Comment comment) {
        comments.add(comment);
    }
}

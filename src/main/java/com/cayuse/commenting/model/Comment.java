package com.cayuse.commenting.model;

import lombok.Getter;
import lombok.Setter;

class Comment {
    private Comment() {
        this.status = Status.General;
    }

    static Comment create(Commenter commenter, String text) {
        Comment comment = new Comment();
        comment.commenter = commenter;
        return comment;
    }

    @Getter @Setter
    private Visibility visibility;

    @Getter @Setter
    private Status status;

    @Getter @Setter
    private Commenter commenter;
}

enum Visibility {Researchers, Everyone}

enum Status {General, Unaddressed, Addressed, Resolved}

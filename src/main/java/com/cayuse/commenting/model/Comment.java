package com.cayuse.commenting.model;

import lombok.Getter;
import lombok.Setter;

class Comment {
    Comment() {
        this.status = Status.General;
    }
    @Getter @Setter
    private Visibility visibility;
    @Getter @Setter
    private Status status;
}

enum Visibility {Private, Everyone}

enum Status {General, Unaddressed, Addressed, Resolved}
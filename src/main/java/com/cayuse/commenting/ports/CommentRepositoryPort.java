package com.cayuse.commenting.ports;

import com.cayuse.commenting.model.Comment;

public interface CommentRepositoryPort {
    void save(Comment comment);
}

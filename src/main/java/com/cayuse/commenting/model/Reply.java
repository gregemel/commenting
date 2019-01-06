package com.cayuse.commenting.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class Reply {
    @Getter @Setter
    Commenter author;

    @Getter @Setter
    String comment;

    @Getter @Setter
    LocalDateTime timeStamp;

    @Getter @Setter
    Comment originalComment;

    private Reply() {}

    public static Reply of(
        Commenter author,
        String text,
        LocalDateTime timeStamp
    ) {
        Reply reply = new Reply();

        reply.author = author;
        reply.comment = text;
        reply.timeStamp = timeStamp;

        return reply;
    }

    public static Reply of(Commenter commenter, String text) {
        return of(commenter, text, LocalDateTime.now());
    }

}

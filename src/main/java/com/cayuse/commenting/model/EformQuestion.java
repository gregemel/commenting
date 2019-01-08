package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.ComplianceException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

class EformQuestion {

    private EformQuestion() {}

    public static EformQuestion of(Submission submission) {
        EformQuestion question = new EformQuestion();

        question.submission = submission;

        return question;
    }

    @Getter
    private Submission submission;

    private List<Comment> comments = new ArrayList<>();

    List<Comment> getComments() {
        return comments;
    }

    void addComment(Comment comment) throws ComplianceException {
        if(getSubmission().getStatus() == Submission.Status.Closed)
            throw new ComplianceException("Cannot add comment to a closed submission.");
        if(getSubmission().getStatus() == Submission.Status.Approved)
            throw new ComplianceException("Cannot add comment to an approved submission.");
        comments.add(comment);
    }
}

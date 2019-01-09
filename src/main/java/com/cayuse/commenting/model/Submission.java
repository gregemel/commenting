package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.ComplianceException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

class Submission {
    private Submission() {
    }

    public static Submission of(Status status) {
        Submission submission = new Submission();

        submission.setStatus(status);
        submission.questions = new ArrayList<>();
        submission.researchers = new ArrayList<>();
        submission.reviewers = new ArrayList<>();

        return submission;
    }

    @Getter
    private List<EformQuestion> questions;

    @Getter
    private List<Commenter> researchers;

    @Getter
    private List<Commenter> reviewers;

    void addQuestion(EformQuestion question) {
        questions.add(question);
    }

    void submit(Commenter submitter) throws ComplianceException {
        if(hasUnaddressedComments(submitter))
            throw new ComplianceException("Submission still has unaddressed comment(s) on question(s).");
    }

    private boolean hasUnaddressedComments(Commenter submitter) {

        //todo: stream map these
        for (EformQuestion question: questions) {
            for (Comment comment: question.getComments(submitter)) {
                if (comment.getStatus() == Comment.Status.Unaddressed)
                    return true;
            }
        }
        return false;
    }

    enum Status {Open, Closed, Approved}

    @Getter @Setter
    private Status status;
}

package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.ComplianceException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

class Submission {
    @Getter
    private List<EformQuestion> questions = new ArrayList<>();

    @Getter
    private List<Commenter> researchers = new ArrayList<>();

    void addQuestion(EformQuestion question) {
        questions.add(question);
    }

    void addResearcher(Commenter researcher) {
        researchers.add(researcher);
    }

    void submit() throws ComplianceException {
        if(hasUnaddressedComments())
            throw new ComplianceException("Submission still has unaddressed comment(s) on question(s).");
    }

    private boolean hasUnaddressedComments() {

        //todo: stream map these
        for (EformQuestion question: questions) {
            for (Comment comment: question.getComments()) {
                if (comment.getStatus() == Comment.Status.Unaddressed)
                    return true;
            }
        }
        return false;
    }
}

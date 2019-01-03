package com.cayuse.commenting.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

class Submission {
    @Getter
    private List<EformQuestion> questions = new ArrayList<>();

    @Getter
    private List<Researcher> researchers = new ArrayList<>();

    void addQuestion(EformQuestion question) {
        questions.add(question);
    }

    void addResearcher(Researcher researcher) {
        researchers.add(researcher);
    }

    boolean submit() {
        return !hasUnaddressedComments();
    }

    private boolean hasUnaddressedComments() {

        //todo: stream map these
        for (EformQuestion question: questions) {
            for (Comment comment: question.getComments()) {
                if (comment.getStatus() == Status.Unaddressed)
                    return true;
            }
        }
        return false;
    }
}

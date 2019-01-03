package com.cayuse.commenting.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.cayuse.commenting.model.Status.Addressed;
import static com.cayuse.commenting.model.Status.Unaddressed;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionSpec {

    @Test
    public void shouldBeAbleToAddQuestions() {
        Submission target = new Submission();
        EformQuestion question = new EformQuestion();

        target.addQuestion(question);

        assert(target.getQuestions().contains(question));
    }

    @Test
    public void shouldBeAbleToAddResearchers() {
        Submission target = new Submission();
        Researcher researcher = new Researcher();

        target.addResearcher(researcher);

        assert(target.getResearchers().contains(researcher));
    }

    @Test
    public void shouldNotBeAbleToSubmitWithUnaddressedComments() {
        //setup
        Submission target = new Submission();

        Researcher researcher = new Researcher();
        target.addResearcher(researcher);

        EformQuestion question = new EformQuestion();
        Comment comment = Comment.create(researcher, "");
        comment.setStatus(Unaddressed);
        question.addComment(comment);
        target.addQuestion(question);

        //execute
        boolean success = target.submit();

        assert(!success);
    }

    @Test
    public void shouldBeAbleToSubmitWithNoUnaddressedComments() {
        //setup
        Submission target = new Submission();

        Researcher researcher = new Researcher();
        target.addResearcher(researcher);

        EformQuestion question = new EformQuestion();
        Comment comment = Comment.create(researcher, "");
        comment.setStatus(Addressed);
        question.addComment(comment);
        target.addQuestion(question);

        //execute
        boolean success = target.submit();

        assert(success);
    }
}

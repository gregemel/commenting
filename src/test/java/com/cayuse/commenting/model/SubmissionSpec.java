package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.ComplianceException;
import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static com.cayuse.commenting.model.Comment.Status.Addressed;
import static com.cayuse.commenting.model.Comment.Visibility.Everyone;
import static com.cayuse.commenting.model.Comment.Status.Unaddressed;
import static com.cayuse.commenting.model.Commenter.Role.Researcher;
import static com.cayuse.commenting.model.Commenter.Role.Reviewer;

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
        Commenter researcher = Commenter.of(UUID.randomUUID(), Researcher);

        target.addResearcher(researcher);

        assert(target.getResearchers().contains(researcher));
    }

    @Test(expected = ComplianceException.class)
    public void shouldNotBeAbleToSubmitWithUnaddressedComments() throws UnauthorizedException, ComplianceException {
        //setup
        Submission target = new Submission();

        Commenter reviewer = Commenter.of(UUID.randomUUID(), Reviewer);

        EformQuestion question = new EformQuestion();
        Comment comment = Comment.of(reviewer, "", Everyone, Unaddressed);
        question.addComment(comment);
        target.addQuestion(question);

        //execute
        target.submit();

        assert(false);  //should not get here
    }

    @Test
    public void shouldBeAbleToSubmitWithNoUnaddressedComments() throws UnauthorizedException, ComplianceException {
        //setup
        Submission target = new Submission();

        Commenter researcher = Commenter.of(UUID.randomUUID(), Researcher);
        target.addResearcher(researcher);

        EformQuestion question = new EformQuestion();
        Comment comment = Comment.of(researcher, "", Everyone, Addressed);
        question.addComment(comment);
        target.addQuestion(question);

        //execute
        target.submit();

        assert(true);
    }
}

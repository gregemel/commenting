package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.ComplianceException;
import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static com.cayuse.commenting.model.Comment.Status.Addressed;
import static com.cayuse.commenting.model.Comment.Status.Unaddressed;
import static com.cayuse.commenting.model.Comment.Visibility.Everyone;
import static com.cayuse.commenting.model.Commenter.Role.Researcher;
import static com.cayuse.commenting.model.Commenter.Role.Reviewer;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionSpec {

    @Test(expected = ComplianceException.class)
    public void shouldNotBeAbleToSubmitWithUnaddressedComments() throws UnauthorizedException, ComplianceException {
        //setup
        Submission target = Submission.of(Submission.Status.Open);
        EformQuestion question = EformQuestion.of(target);
        target.addQuestion(question);
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Reviewer);
        Comment comment = Comment.of(reviewer, "", Everyone, Unaddressed);
        question.addComment(comment);

        //execute
        target.submit();

        assert(false);  //should not get here
    }

    @Test
    public void shouldBeAbleToSubmitWithNoUnaddressedComments() throws UnauthorizedException, ComplianceException {
        //setup
        Submission target = Submission.of(Submission.Status.Open);
        EformQuestion question = EformQuestion.of(target);
        target.addQuestion(question);
        Commenter researcher = Commenter.of(UUID.randomUUID(), Researcher);
        Comment comment = Comment.of(researcher, "", Everyone, Addressed);
        question.addComment(comment);

        //execute
        target.submit();

        assert(true);
    }
}

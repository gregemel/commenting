package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.ComplianceException;
import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static com.cayuse.commenting.model.Submission.Status.*;

@RunWith(MockitoJUnitRunner.class)
public class EformQuestionSpec {

    //Reviewer should be able to create Comment on EformQuestion
    @Test
    public void shouldBeAbleToCreateCommentOnEformQuestionAsReviewer() throws UnauthorizedException, ComplianceException {
        Submission submission = Submission.of(Open);
        EformQuestion target = EformQuestion.of(submission);
        submission.addQuestion(target);
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment comment = Comment.of(reviewer, "reviewer comment");

        target.addComment(comment);

        assert(target.getComments().contains(comment));
    }

    //  * Reviewers can add Comment to Questions until the Submission is closed, approved.
    @Test(expected = ComplianceException.class)
    public void shouldNotBeAbleToAddCommentToClosedSubmission() throws UnauthorizedException, ComplianceException {
        Submission submission = Submission.of(Closed);
        EformQuestion target = EformQuestion.of(submission);
        submission.addQuestion(target);

        Commenter author = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment comment = Comment.of(author, "doesn't matter");

        target.addComment(comment);

        assert(false);      //should not get here
    }

    @Test(expected = ComplianceException.class)
    public void shouldNotBeAbleToAddCommentToApprovedSubmission() throws UnauthorizedException, ComplianceException {
        Submission submission = Submission.of(Approved);
        EformQuestion target = EformQuestion.of(submission);
        submission.addQuestion(target);

        Commenter author = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment comment = Comment.of(author, "doesn't matter");

        target.addComment(comment);

        assert(false);      //should not get here
    }
}

package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;

import java.util.UUID;

import static com.cayuse.commenting.model.Comment.Status.*;
import static com.cayuse.commenting.model.Comment.Visibility.Everyone;
import static com.cayuse.commenting.model.Comment.Visibility.ReviewersOnly;

public class CommenterSpec {

    /*
     * x Reviewer should be able to create Comment on EformQuestion
     * x Reviewer should be able to set Visibility on Comment to ReviewersOnly, Everyone.
     * Reviewer can save Comment on Question on Submission
     * Reviewer can edit own Comments
     * Reviewers should only be able to change a visibility of own comment
     * Reviewers should not be able to change the visibility of another reviewer's comment (unless Analyst or Admin)
     * Reviewers should be able to change Status of own Comment, but cannot change the status of other's comments (unless Analyst or Admin)
     * Reviewers should be able to change the status of a comment with visibility set to "Visible to Everyone"
     * Reviewers can add Comment to Questions until the Submission is closed, approved.
     * Reviewer: when Reviewer saves edited Comment, the date/timestamp will be marked Edited
     * Reviewer should be able to see the Name of the Reviewer on a Comment
     *
     * Researcher should be able to reply to Comment on Submission with Status "unaddressed"
     * Researcher cannot change Comment Visibility
     * Researcher can edit own Comments
     * */

    @Test(expected = UnauthorizedException.class)
    public void shouldNotBeAbleToSetStatusToResolvedAsResearcher() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(reviewer, "ipsum");

        target.setStatus(researcher, Resolved);

        assert(false); //should not get here
    }

    //Reviewer should be able to set Visibility on Comment to ReviewersOnly, Everyone.
    @Test
    public void shouldBeAbleToSetCommentVisibleToReviewersOnly() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(reviewer, "reviewer comment");

        target.setVisibility(reviewer, ReviewersOnly);

        assert(target.getVisibility() == ReviewersOnly);
    }

    @Test
    public void shouldBeAbleToSetVisibleToEveryone() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(reviewer, "text");

        target.setVisibility(reviewer, Everyone);

        assert(target.getVisibility() == Everyone);
    }

    @Test
    public void shouldBeAbleToSetStatusToResolved() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(reviewer, "ipsum");

        target.setStatus(reviewer, Resolved);

        assert(target.getStatus() == Resolved);
    }

    //non-analyst/admin reviewer should not be able to change visibility of another reviewer's comment
    @Test(expected = UnauthorizedException.class)
    public void shouldNotBeAbleToChangeVisibilityOnAnotherReviewersComment() throws UnauthorizedException {
        Commenter author = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(author, "ipsum");
        target.setVisibility(author, ReviewersOnly);

        Commenter changer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        assert(!changer.isAdmin());
        assert(!changer.isAnalyst());

        target.setVisibility(changer, Everyone);
        assert(false);  //does not get here
    }

    @Test
    public void shouldBeAbleToChangeVisibilityOnAnotherReviewersCommentWhenAnalyst() throws UnauthorizedException {
        Commenter author = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(author, "ipsum");
        target.setVisibility(author, ReviewersOnly);

        Commenter changer = Commenter.of(UUID.randomUUID(), Commenter.Role.Analyst);
        assert(changer.isAnalyst());

        target.setVisibility(changer, Everyone);
        assert(target.getVisibility() == Everyone);
    }

    @Test
    public void shouldBeAbleToChangeVisibilityOnAnotherReviewersCommentWhenAdmin() throws UnauthorizedException {
        Commenter author = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(author, "ipsum", ReviewersOnly);

        Commenter changer = Commenter.of(UUID.randomUUID(), Commenter.Role.Admin);
        assert(changer.isAdmin());

        target.setVisibility(changer, Everyone);
        assert(target.getVisibility() == Everyone);
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldNotBeAbleToChangeStatusOnAnotherReviewersComment() throws UnauthorizedException {
        Commenter author = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(author, "ipsum", ReviewersOnly, Unaddressed);

        Commenter changer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        assert(!changer.isAdmin());
        assert(!changer.isAnalyst());

        target.setStatus(changer, Addressed);

        assert(false); //should not get here
    }
}

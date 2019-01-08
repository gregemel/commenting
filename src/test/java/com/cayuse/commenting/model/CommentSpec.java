package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.cayuse.commenting.model.Comment.Status.*;
import static java.lang.Thread.sleep;


@RunWith(MockitoJUnitRunner.class)
public class CommentSpec {

    /*
     * Comment which is visible to everyone can have multiple statuses, default to "General"
     * Comment status can also be set to "Unaddressed / Addressed and Resolved"
     * Comment save requires authorization (user with IACUC Analyst role) or Sys Admin(user with IACUC System Administrator role)
     * Comment can be Reply to Comment
     * Comment can be a new thread
     * Comments have timestamps and Edited flags
     * Comments have authors to determine ownership, right to edit and change status
     * Comment replies are not ordered by timestamp, after editing they keep their position within the thread
     */

    @Test
    public void shouldSetStatusToGeneralByDefault() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);

        Comment target = Comment.of(reviewer, "comment");

        assert(target.getStatus() == General);
    }

    @Test
    public void reviewerShouldBeAbleToSetStatusToUnaddressed() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(reviewer, "needs work");

        target.setStatus(reviewer, Unaddressed);

        assert(target.getStatus() == Unaddressed);
    }

    @Test(expected = UnauthorizedException.class)
    public void researcherShouldNotBeAbleToSetStatusToUnaddressed() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(reviewer, "needs work");

        target.setStatus(reviewer, Unaddressed);

        assert(false);  //should never get here
    }

    @Test
    public void shouldBeAbleToSetStatusToAddressed() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(researcher, "a comment");

        target.setStatus(researcher, Addressed);

        assert(target.getStatus() == Addressed);
    }

    @Test
    public void shouldBeAbleToSetStatusToResolved() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(researcher, "a comment");

        target.setStatus(researcher, Resolved);

        assert(target.getStatus() == Resolved);
    }

    @Test(expected = UnauthorizedException.class)
    public void researcherShouldNotBeAbleToSetStatusToResolved() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(researcher, "needs work");

        target.setStatus(researcher, Resolved);

        assert(false);  //should never get here
    }

    @Test
    public void shouldBeAbleToEditOwnComment() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(researcher, "needs work");

        String change = "different text";
        target.setComment(researcher, change);

        assert(target.getComment().equals(change));
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldNoBeAbleToEditAnothersComment() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(researcher, "needs work");
        Commenter changer = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);


        String change = "different text";
        target.setComment(changer, change);

        assert(false);
    }

    @Test
    public void shouldUpdateTimeStampOnEdit() throws UnauthorizedException, InterruptedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(researcher, "needs work");
        LocalDateTime timeStamp = target.getTimeStamp();
        sleep(100);

        target.setComment(researcher, "different text");

        assert(timeStamp != target.getTimeStamp());
    }

    @Test
    public void changingCommentShouldUpdateEditFlag() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Researcher);
        Comment target = Comment.of(researcher, "needs work");

        assert(!target.isEdited());

        target.setComment(researcher, "different text");

        assert(target.isEdited());
    }
}

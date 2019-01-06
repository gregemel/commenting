package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static com.cayuse.commenting.model.Comment.Status.*;
//import static com.cayuse.commenting.model.Status.*;


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
    public void shouldBeAbleToSetStatusToUnaddressed() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(reviewer, "needs work");

        target.setStatus(reviewer, Unaddressed);

        assert(target.getStatus() == Unaddressed);
    }

    @Test
    public void shouldBeAbleToSetStatusToAddressed() throws UnauthorizedException {
        Commenter researcher = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment target = Comment.of(researcher, "a comment");

        target.setStatus(researcher, Addressed);

        assert(target.getStatus() == Addressed);
    }

}

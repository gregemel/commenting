package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class EformQuestionSpec {

    //Reviewer should be able to create Comment on EformQuestion
    @Test
    public void shouldBeAbleToAddCommentToEformQuestionAsReviewer() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment comment = Comment.of(reviewer, "");
        EformQuestion target = new EformQuestion();

        target.addComment(comment);

        assert(target.getComments().contains(comment));
    }

    //Reviewer should be able to create Comment on EformQuestion
    @Test
    public void shouldBeAbleToCreateCommentOnEformQuestionAsReviewer() throws UnauthorizedException {
        Commenter reviewer = Commenter.of(UUID.randomUUID(), Commenter.Role.Reviewer);
        Comment comment = Comment.of(reviewer, "reviewer comment");
        EformQuestion target = new EformQuestion();

        target.addComment(comment);

        assert(target.getComments().contains(comment));
    }
}

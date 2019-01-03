package com.cayuse.commenting.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResearcherSpec {

    @Test
    public void shouldBeAbleToAddCommentToEformQuestion() {
        Researcher target = new Researcher();
        EformQuestion question = new EformQuestion();
        Comment comment = Comment.create(target, "");

        target.addComment(question, comment);

        assert(question.getComments().contains(comment));
    }
}

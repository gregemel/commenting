package com.cayuse.commenting.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AnalystSpec {

    @Test
    public void shouldBeAbleToAddCommentToEformQuestion() {
        Analyst target = new Analyst();
        EformQuestion question = new EformQuestion();
        Comment comment = new Comment();

        target.addComment(question, comment);

        assert(question.getComments().contains(comment));
    }

}
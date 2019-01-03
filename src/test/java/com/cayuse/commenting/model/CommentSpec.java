package com.cayuse.commenting.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.cayuse.commenting.model.Status.*;
import static com.cayuse.commenting.model.Visibility.*;


@RunWith(MockitoJUnitRunner.class)
public class CommentSpec {

    @Test
    public void shouldBeAbleToSetVisibleToPrivate() {
        Researcher researcher = new Researcher();
        Comment target = Comment.create(researcher, "");

        target.setVisibility(Researchers);

        assert(target.getVisibility() == Researchers);
    }

    @Test
    public void shouldBeAbleToSetVisibleToEveryone() {
        Researcher researcher = new Researcher();
        Comment target = Comment.create(researcher, "");

        target.setVisibility(Everyone);

        assert(target.getVisibility() == Everyone);
    }

    @Test
    public void shouldSetStatusToGeneralByDefault() {
        Researcher researcher = new Researcher();
        Comment target = Comment.create(researcher, "");

        assert(target.getStatus() == General);
    }

    @Test
    public void shouldBeAbleToSetStatusToUnaddressed() {
        Researcher researcher = new Researcher();
        Comment target = Comment.create(researcher, "");

        target.setStatus(Unaddressed);

        assert(target.getStatus() == Unaddressed);
    }
    @Test
    public void shouldBeAbleToSetStatusToAddressed() {
        Researcher researcher = new Researcher();
        Comment target = Comment.create(researcher, "");

        target.setStatus(Addressed);

        assert(target.getStatus() == Addressed);
    }
    @Test
    public void shouldBeAbleToSetStatusToResolved() {
        Researcher researcher = new Researcher();
        Comment target = Comment.create(researcher, "");

        target.setStatus(Resolved);

        assert(target.getStatus() == Resolved);
    }
}

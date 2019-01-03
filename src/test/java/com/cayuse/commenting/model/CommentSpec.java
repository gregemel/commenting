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
        Comment target = new Comment();

        target.setVisibility(Private);

        assert(target.getVisibility() == Private);
    }

    @Test
    public void shouldBeAbleToSetVisibleToEveryone() {
        Comment target = new Comment();

        target.setVisibility(Everyone);

        assert(target.getVisibility() == Everyone);
    }

    @Test
    public void shouldSetStatusToGeneralByDefault() {
        Comment target = new Comment();

        assert(target.getStatus() == General);
    }

    @Test
    public void shouldBeAbleToSetStatusToUnaddressed() {
        Comment target = new Comment();

        target.setStatus(Unaddressed);

        assert(target.getStatus() == Unaddressed);
    }
    @Test
    public void shouldBeAbleToSetStatusToAddressed() {
        Comment target = new Comment();

        target.setStatus(Addressed);

        assert(target.getStatus() == Addressed);
    }
    @Test
    public void shouldBeAbleToSetStatusToResolved() {
        Comment target = new Comment();

        target.setStatus(Resolved);

        assert(target.getStatus() == Resolved);
    }
}

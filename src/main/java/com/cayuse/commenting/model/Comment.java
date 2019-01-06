package com.cayuse.commenting.model;

import com.cayuse.commenting.model.Exceptions.UnauthorizedException;
import lombok.Getter;
import lombok.Setter;

public class Comment {
    private Comment() {
    }

    public enum Visibility {ReviewersOnly, Everyone}
    public enum Status {General, Unaddressed, Addressed, Resolved}

    public static Comment of(
            Commenter commenter,
            String text,
            Visibility visibility,
            Status status
    ) throws UnauthorizedException {
        Comment comment = new Comment();
        comment.author = commenter;
        comment.comment = text;
        comment.setVisibility(commenter, visibility);
        comment.setStatus(commenter, status);
        return comment;
    }

    public static Comment of(
            Commenter commenter,
            String text,
            Visibility visibility
    ) throws UnauthorizedException {
        return of(commenter, text, visibility, Status.General);
    }

    public static Comment of(
            Commenter commenter,
            String text
    ) throws UnauthorizedException {
        return of(commenter, text, Visibility.Everyone, Status.General);
    }

    @Getter
    private String comment;

    @Getter
    private Visibility visibility;

    void setVisibility(Commenter changer, Visibility visibility) throws UnauthorizedException {
        if(!getAuthor().isReviewer() && visibility == Visibility.ReviewersOnly)
            throw new UnauthorizedException("Only Reviewer can set visibility to Reviewers Only");
        if(!changer.isAuthor(this) && !changer.isAdmin() && !changer.isAnalyst())
            throw new UnauthorizedException("Only original author, admin, or analyst can change visibility.");
        this.visibility = visibility;
    }

    @Getter
    private Status status;

    void setStatus(Commenter changer, Status status) throws UnauthorizedException {
        if(!changer.isAdmin() && !changer.isAnalyst() && !changer.isAuthor(this) && !changer.isReviewer())
            throw new UnauthorizedException("Only original author, analyst, or admin can change status of this comment.");
        if(!changer.isAuthor(this))
            throw new UnauthorizedException("Only Reviewer can set visibility to Reviewers Only");
        this.status = status;
    }

    @Getter @Setter
    private Commenter author;
}

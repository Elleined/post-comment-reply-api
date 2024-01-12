package com.elleined.forumapi.model;

import com.elleined.forumapi.model.hashtag.HashTag;
import com.elleined.forumapi.model.mention.PostMention;
import com.elleined.forumapi.model.react.PostReact;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_post")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "attached_picture", columnDefinition = "MEDIUMTEXT")
    private String attachedPicture;

    @Column(name = "comment_section_status")
    @Enumerated(EnumType.STRING)
    private CommentSectionStatus commentSectionStatus;

    @ManyToMany
    @JoinTable(
            name = "tbl_post_hashtag",
            joinColumns = @JoinColumn(name = "post_id",
                    referencedColumnName = "post_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id",
                    referencedColumnName = "id"
            )
    )
    private Set<HashTag> hashTags;

    @ManyToOne
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "FK_author_id")
    )
    private User author;

    @OneToOne
    @JoinColumn(
            name = "pinned_comment_id",
            referencedColumnName = "comment_id"
    )
    private Comment pinnedComment;

    // post id reference is in comment table
    @OneToMany(mappedBy = "post")
    @Setter(AccessLevel.NONE)
    private List<Comment> comments;

    // post id refernce is on tbl mention post
    @OneToMany(mappedBy = "post")
    @Setter(AccessLevel.NONE)
    private Set<PostMention> mentions;

    // post id reference is in tbl post emoji
    @OneToMany(mappedBy = "post")
    @Setter(AccessLevel.NONE)
    private List<PostReact> reactions;

    @ManyToMany(mappedBy = "savedPosts")
    @Setter(AccessLevel.NONE)
    private Set<User> savingUsers;

    @ManyToMany(mappedBy = "sharedPosts")
    @Setter(AccessLevel.NONE)
    private Set<User> sharers;

    public enum CommentSectionStatus {OPEN, CLOSED}

    public boolean isActive() {
        return this.getStatus() == Status.ACTIVE;
    }

    public boolean isInactive() {
        return this.getStatus() == Status.INACTIVE;
    }

    public boolean isCommentSectionClosed() {
        return this.getCommentSectionStatus() == CommentSectionStatus.CLOSED;
    }

    public boolean doesNotHave(Comment comment) {
        return this.getComments().stream().noneMatch(comment::equals);
    }

    public boolean isCommentSectionOpen() {
        return this.getCommentSectionStatus() == CommentSectionStatus.OPEN;
    }
}

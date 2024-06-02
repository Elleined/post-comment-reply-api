package com.elleined.socialmediaapi.model.user;

import com.elleined.socialmediaapi.model.PrimaryKeyIdentity;
import com.elleined.socialmediaapi.model.friend.FriendRequest;
import com.elleined.socialmediaapi.model.main.comment.Comment;
import com.elleined.socialmediaapi.model.main.post.Post;
import com.elleined.socialmediaapi.model.main.reply.Reply;
import com.elleined.socialmediaapi.model.note.Note;
import com.elleined.socialmediaapi.model.notification.follow.FollowerNotification;
import com.elleined.socialmediaapi.model.notification.main.CommentNotification;
import com.elleined.socialmediaapi.model.notification.main.ReplyNotification;
import com.elleined.socialmediaapi.model.notification.mention.CommentMentionNotification;
import com.elleined.socialmediaapi.model.notification.mention.PostMentionNotification;
import com.elleined.socialmediaapi.model.notification.mention.ReplyMentionNotification;
import com.elleined.socialmediaapi.model.notification.mention.StoryMentionNotification;
import com.elleined.socialmediaapi.model.notification.post.SharedPostNotification;
import com.elleined.socialmediaapi.model.notification.reaction.CommentReactionNotification;
import com.elleined.socialmediaapi.model.notification.reaction.PostReactionNotification;
import com.elleined.socialmediaapi.model.notification.reaction.ReplyReactionNotification;
import com.elleined.socialmediaapi.model.notification.reaction.StoryReactionNotification;
import com.elleined.socialmediaapi.model.notification.vote.VoteNotification;
import com.elleined.socialmediaapi.model.reaction.Reaction;
import com.elleined.socialmediaapi.model.story.Story;
import com.elleined.socialmediaapi.model.vote.Vote;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "picture")
    private String picture;

    @Column(
            name = "uuid",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String UUID;

    @OneToOne(mappedBy = "creator")
    private Note note;

    @OneToOne(mappedBy = "creator")
    private Story story;

    @OneToMany(mappedBy = "creator")
    private Set<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "requestedUser")
    private Set<FriendRequest> receiveFriendRequests;

    @OneToMany(mappedBy = "creator")
    private List<Post> posts;

    @OneToMany(mappedBy = "creator")
    private List<Comment> comments;

    @OneToMany(mappedBy = "creator")
    private List<Reply> replies;

    @OneToMany(mappedBy = "creator")
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "creator")
    private List<Vote> votedComments;

    @ManyToMany(mappedBy = "savingUsers")
    private Set<Post> savedPosts;

    @ManyToMany(mappedBy = "sharers")
    private Set<Post> sharedPosts;

    @OneToMany(mappedBy = "receiver")
    private List<CommentNotification> commentNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<ReplyNotification> replyNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<PostMentionNotification> postMentionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<CommentMentionNotification> commentMentionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<ReplyMentionNotification> replyMentionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<StoryMentionNotification> storyMentionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<PostReactionNotification> postReactionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<CommentReactionNotification> commentReactionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<ReplyReactionNotification> replyReactionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<StoryReactionNotification> storyReactionNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<FollowerNotification> followerNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<VoteNotification> voteNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<SharedPostNotification> sharedPostNotifications;

    @ManyToMany
    @JoinTable(
            name = "tbl_blocked_user",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "blocked_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<User> blockedUsers;

    @ManyToMany
    @JoinTable(
            name = "tbl_friend",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "friend_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<User> friends;

    @ManyToMany
    @JoinTable(
            name = "tbl_follower",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "follower_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<User> followers;

    @ManyToMany
    @JoinTable(
            name = "tbl_following",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "following_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<User> followings;

    // Get all id
    public Set<Integer> getAllBlockedUserIds() {
        return this.getBlockedUsers().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllSharedPostIds() {
        return this.getSharedPosts().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllSavedPostIds() {
        return this.getSavedPosts().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllVotedCommentIds() {
        return this.getVotedComments().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllFollowerIds() {
        return this.getFollowers().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllFollowingIds() {
        return this.getFollowings().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllFriendIds() {
        return this.getFriends().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllSentFriendRequestIds() {
        return this.getSentFriendRequests().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllReceiveFriendRequestIds() {
        return this.getReceiveFriendRequests().stream()
                .map(PrimaryKeyIdentity::getId)
                .collect(Collectors.toSet());
    }

    public List<Integer> getAllPostIds() {
        return this.getPosts().stream()
                .map(PrimaryKeyIdentity::getId)
                .toList();
    }

    public List<Integer> getAllCommentIds() {
        return this.getComments().stream()
                .map(PrimaryKeyIdentity::getId)
                .toList();
    }

    public List<Integer> getAllReplyIds() {
        return this.getReplies().stream()
                .map(PrimaryKeyIdentity::getId)
                .toList();
    }

    public List<Integer> getAllReactionIds() {
        return this.getReactions().stream()
                .map(PrimaryKeyIdentity::getId)
                .toList();
    }

    public boolean has(CommentNotification commentNotification) {
        return this.getCommentNotifications().contains(commentNotification);
    }

    public boolean has(ReplyNotification replyNotification) {
        return this.getReplyNotifications().contains(replyNotification);
    }

    public boolean has (PostMentionNotification postMentionNotification) {
        return this.getPostMentionNotifications().contains(postMentionNotification);
    }

    public boolean has (CommentMentionNotification commentMentionNotification) {
        return this.getCommentMentionNotifications().contains(commentMentionNotification);
    }

    public boolean has (ReplyMentionNotification replyMentionNotification) {
        return this.getReplyMentionNotifications().contains(replyMentionNotification);
    }

    public boolean has (StoryMentionNotification storyMentionNotification) {
        return this.getStoryMentionNotifications().contains(storyMentionNotification);
    }

    public boolean has (PostReactionNotification postReactionNotification) {
        return this.getPostReactionNotifications().contains(postReactionNotification);
    }

    public boolean has (CommentReactionNotification commentReactionNotification) {
        return this.getCommentReactionNotifications().contains(commentReactionNotification);
    }

    public boolean has (ReplyReactionNotification replyReactionNotification) {
        return this.getReplyReactionNotifications().contains(replyReactionNotification);
    }

    public boolean has (StoryReactionNotification storyReactionNotification) {
        return this.getStoryReactionNotifications().contains(storyReactionNotification);
    }

    public boolean has(FollowerNotification followerNotification) {
        return this.getFollowerNotifications().contains(followerNotifications);
    }

    public boolean has(VoteNotification voteNotification) {
        return this.getVoteNotifications().contains(voteNotifications);
    }

    public boolean has(SharedPostNotification sharedPostNotification) {
        return this.getSharedPostNotifications().contains(sharedPostNotifications);
    }

}

package com.elleined.socialmediaapi.mapper.notification.mention;

import com.elleined.socialmediaapi.dto.notification.CommentNotification;
import com.elleined.socialmediaapi.dto.notification.PostNotification;
import com.elleined.socialmediaapi.dto.notification.ReplyNotification;
import com.elleined.socialmediaapi.service.Formatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Formatter.class)
public interface MentionNotificationMapper {

    @Mappings({
            @Mapping(target = "id", source = "postMention.id"),
            @Mapping(target = "receiverId", expression = "java(postMention.getReceiverId())"),
            @Mapping(target = "message", expression = "java(postMention.getMessage())"),
            @Mapping(target = "respondentId", source = "postMention.mentioningUser.id"),
            @Mapping(target = "respondentPicture", source = "postMention.mentioningUser.picture"),
            @Mapping(target = "formattedDate", expression = "java(Formatter.formatDate(postMention.getCreatedAt()))"),
            @Mapping(target = "formattedTime", expression = "java(Formatter.formatTime(postMention.getCreatedAt()))"),
            @Mapping(target = "notificationStatus", source = "postMention.notificationStatus"),

            @Mapping(target = "postId", source = "postMention.post.id"),
    })
    PostNotification toNotification(PostMention postMention);

    @Mappings({
            @Mapping(target = "id", source = "commentMention.id"),
            @Mapping(target = "receiverId", expression = "java(commentMention.getReceiverId())"),
            @Mapping(target = "message", expression = "java(commentMention.getMessage())"),
            @Mapping(target = "respondentId", source = "commentMention.mentioningUser.id"),
            @Mapping(target = "respondentPicture", source = "commentMention.mentioningUser.picture"),
            @Mapping(target = "formattedDate", expression = "java(Formatter.formatDate(commentMention.getCreatedAt()))"),
            @Mapping(target = "formattedTime", expression = "java(Formatter.formatTime(commentMention.getCreatedAt()))"),
            @Mapping(target = "notificationStatus", source = "commentMention.notificationStatus"),

            @Mapping(target = "postId", source = "commentMention.comment.post.id"),
            @Mapping(target = "commentId", source = "commentMention.comment.id"),
            @Mapping(target = "count", expression = "java(1)")
    })
    CommentNotification toNotification(CommentMention commentMention);

    @Mappings({
            @Mapping(target = "id", source = "replyMention.id"),
            @Mapping(target = "receiverId", expression = "java(replyMention.getReceiverId())"),
            @Mapping(target = "message", expression = "java(replyMention.getMessage())"),
            @Mapping(target = "respondentId", source = "replyMention.mentioningUser.id"),
            @Mapping(target = "respondentPicture", source = "replyMention.mentioningUser.picture"),
            @Mapping(target = "formattedDate", expression = "java(Formatter.formatDate(replyMention.getCreatedAt()))"),
            @Mapping(target = "formattedTime", expression = "java(Formatter.formatTime(replyMention.getCreatedAt()))"),
            @Mapping(target = "notificationStatus", source = "notificationStatus"),

            @Mapping(target = "postId", source = "replyMention.reply.comment.post.id"),
            @Mapping(target = "commentId", source = "replyMention.reply.comment.id"),
            @Mapping(target = "replyId", source = "replyMention.reply.id"),
            @Mapping(target = "count", expression = "java(1)")
    })
    ReplyNotification toNotification(ReplyMention replyMention);
}


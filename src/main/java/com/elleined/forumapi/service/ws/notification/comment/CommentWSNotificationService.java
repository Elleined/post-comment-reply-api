package com.elleined.forumapi.service.ws.notification.comment;

import com.elleined.forumapi.dto.notification.CommentNotification;
import com.elleined.forumapi.mapper.notification.comment.CommentNotificationMapper;
import com.elleined.forumapi.model.Comment;
import com.elleined.forumapi.service.ws.notification.BaseWSNotificationService;
import com.elleined.forumapi.service.ws.notification.WSNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentWSNotificationService extends BaseWSNotificationService
        implements WSNotificationService<Comment> {

    private final CommentNotificationMapper commentNotificationMapper;

    public CommentWSNotificationService(SimpMessagingTemplate simpMessagingTemplate, CommentNotificationMapper commentNotificationMapper) {
        super(simpMessagingTemplate);
        this.commentNotificationMapper = commentNotificationMapper;
    }

    @Override
    public void broadcast(Comment comment) {
        if (comment.isRead()) return;
        CommentNotification commentNotificationResponse = commentNotificationMapper.toNotification(comment);
        int authorId = comment.getPost().getAuthor().getId();
        final String destination = "/notification/comments/" + authorId;
        simpMessagingTemplate.convertAndSend(destination, commentNotificationResponse);
        log.debug("Comment notification successfully sent to author with id of {}", authorId);
    }
}

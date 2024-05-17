package com.elleined.socialmediaapi.service.notification;

import com.elleined.socialmediaapi.mapper.notification.NotificationMapper;
import com.elleined.socialmediaapi.model.friend.FriendRequest;
import com.elleined.socialmediaapi.model.main.comment.Comment;
import com.elleined.socialmediaapi.model.main.post.Post;
import com.elleined.socialmediaapi.model.main.reply.Reply;
import com.elleined.socialmediaapi.model.mention.Mention;
import com.elleined.socialmediaapi.model.notification.Notification;
import com.elleined.socialmediaapi.model.react.React;
import com.elleined.socialmediaapi.model.user.User;
import com.elleined.socialmediaapi.repository.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<Notification> getAllById(Set<Integer> ids) {
        return List.of();
    }

    @Override
    public List<Notification> getAllNotification(User currentUser) {
        return List.of();
    }

    @Override
    public List<Notification> getAllNotification(User currentUser, Notification.Status status) {
        return List.of();
    }

    @Override
    public void read(User currentUser, Post post) {

    }

    @Override
    public void read(User currentUser, Comment comment) {

    }

    @Override
    public void read(User currentUser, Reply reply) {

    }

    @Override
    public void read(User currentUser, Mention mention) {

    }

    @Override
    public void read(User currentUser, React react) {

    }

    @Override
    public void read(User currentUser, FriendRequest friendRequest) {

    }
}

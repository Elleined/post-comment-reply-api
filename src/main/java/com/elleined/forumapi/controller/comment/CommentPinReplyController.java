package com.elleined.forumapi.controller.comment;

import com.elleined.forumapi.dto.CommentDTO;
import com.elleined.forumapi.dto.ReplyDTO;
import com.elleined.forumapi.mapper.CommentMapper;
import com.elleined.forumapi.mapper.ReplyMapper;
import com.elleined.forumapi.model.Comment;
import com.elleined.forumapi.model.Reply;
import com.elleined.forumapi.model.User;
import com.elleined.forumapi.service.UserService;
import com.elleined.forumapi.service.comment.CommentService;
import com.elleined.forumapi.service.pin.CommentPinReplyService;
import com.elleined.forumapi.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{currentUserId}/posts/{postId}/comments")
public class CommentPinReplyController {
    private final UserService userService;

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final CommentPinReplyService commentPinReplyService;

    private final ReplyMapper replyMapper;
    private final ReplyService replyService;

    @GetMapping("/{commentId}/pinned-reply")
    public ReplyDTO getPinnedReply(@PathVariable("commentId") int commentId) {
        Comment comment = commentService.getById(commentId);
        Reply pinnedReply = commentPinReplyService.getPinned(comment);

        return replyMapper.toDTO(pinnedReply);
    }

    @PatchMapping("/{commentId}/pin-reply/{replyId}")
    public CommentDTO pinReply(@PathVariable("currentUserId") int currentUserId,
                               @PathVariable("commentId") int commentId,
                               @PathVariable("replyId") int replyId) {

        User currentUSer = userService.getById(currentUserId);
        Comment comment = commentService.getById(commentId);
        Reply reply = replyService.getById(replyId);

        commentPinReplyService.pin(currentUSer, comment, reply);
        return commentMapper.toDTO(comment);
    }
}

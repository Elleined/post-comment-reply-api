package com.elleined.socialmediaapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class ReplyDTO {
    private int id;
    private String body;
    private String replierName;
    private LocalDateTime dateCreated;
    private String formattedDate;
    private String formattedTime;
    private int commentId;
    private String commentBody;
    private int replierId;
    private String replierPicture;
    private String status;
    private int postId;
    private String notificationStatus;
    private String attachedPicture;
    private int commenterId;
    private Set<UserDTO> mentionedUsers;
}

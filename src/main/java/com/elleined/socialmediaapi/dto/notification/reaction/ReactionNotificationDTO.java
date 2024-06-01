package com.elleined.socialmediaapi.dto.notification.reaction;

import com.elleined.socialmediaapi.dto.notification.NotificationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class ReactionNotificationDTO extends NotificationDTO {
    private int reactionId;
}

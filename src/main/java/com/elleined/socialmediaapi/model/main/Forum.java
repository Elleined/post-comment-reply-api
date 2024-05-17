package com.elleined.socialmediaapi.model.main;

import com.elleined.socialmediaapi.model.PrimaryKeyIdentity;
import com.elleined.socialmediaapi.model.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Forum extends PrimaryKeyIdentity {

    @Column(
            name = "body",
            nullable = false
    )
    private String body;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "attached_picture")
    private String attachedPicture;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "creator_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User creator;

    @Builder
    public Forum(int id,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt,
                 String body,
                 Status status,
                 String attachedPicture,
                 User creator) {
        super(id, createdAt, updatedAt);
        this.body = body;
        this.status = status;
        this.attachedPicture = attachedPicture;
        this.creator = creator;
    }

    public boolean isActive() {
        return this.getStatus() == Status.ACTIVE;
    }

    public boolean isInactive() {
        return this.getStatus() == Status.INACTIVE;
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}

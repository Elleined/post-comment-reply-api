package com.elleined.forumapi.model.emoji;

import com.elleined.forumapi.model.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_emoji")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "emoji_type",
            nullable = false
    )
    private Type type;

    @ManyToMany
    @JoinTable(
            name = "tbl_comment_emoji",
            joinColumns = @JoinColumn(
                    name = "emoji_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "comment_id",
                    referencedColumnName = "comment_id"
            )
    )
    private List<Comment> commentEmojis;

    public Emoji(Type type) {
        this.type = type;
    }

    public enum Type {
        LIKE,
        HEART,
        CARE,
        HAHA,
        WOW,
        SAD,
        ANGRY
    }
}

package com.elleined.forumapi.model.hashtag;

import com.elleined.forumapi.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_post_hashtag")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private int id;

    @Column(
            name = "keyword",
            nullable = false,
            updatable = false,
            length = 25
    )
    private String keyword;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "post_id",
            updatable = false,
            nullable = false
    )
    private Post post;
}

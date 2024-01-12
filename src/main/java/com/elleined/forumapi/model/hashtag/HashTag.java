package com.elleined.forumapi.model.hashtag;

import com.elleined.forumapi.model.Comment;
import com.elleined.forumapi.model.Post;
import com.elleined.forumapi.model.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbl_hashtag")
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
            unique = true,
            length = 25
    )
    private String keyword;

    @ManyToMany(mappedBy = "hashTags")
    private Set<Post> posts;

    @ManyToMany(mappedBy = "hashTags")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "hashTags")
    private Set<Reply> replies;
}

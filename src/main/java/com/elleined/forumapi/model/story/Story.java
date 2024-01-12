package com.elleined.forumapi.model.story;

import com.elleined.forumapi.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class Story {

    private int id;
    private String text;
    private MultipartFile attachment;
    private User author;
    private User taggedUsers;
    private LocalDateTime createdAt;
}

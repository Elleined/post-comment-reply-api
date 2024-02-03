package com.elleined.forumapi.model.mention;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.elleined.forumapi.model.Post;
import com.elleined.forumapi.model.Status;
import com.elleined.forumapi.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostMentionTest {

    @Test
    void getMessage() {
        // Expected and Actual Value

        // Mock Data
        PostMention postMention = spy(PostMention.class);
        postMention.setPost(Post.builder()
                .body("Post body")
                .build());

        postMention.setMentioningUser(User.builder()
                .name("Mentioning user")
                .build());

        // Stubbing methods

        // Calling the method
        // Assertions
        assertNotNull(postMention.getPost());
        assertNotNull(postMention.getPost().getBody());

        assertNotNull(postMention.getMentioningUser());
        assertNotNull(postMention.getMentioningUser().getName());

        assertNotNull(postMention.getMessage(), "Post mention message cannot be null!");
        // Behavior Verifications
    }

    @Test
    void getReceiverId() {
        // Expected and Actual Value
        int expected = 1;

        // Mock Data
        PostMention postMention = spy(PostMention.class);
        postMention.setMentionedUser(User.builder()
                .id(expected)
                .build());

        // Stubbing methods

        // Calling the method
        // Assertions
        assertNotNull(postMention.getMentionedUser());

        assertEquals(expected, postMention.getReceiverId());

        // Behavior Verifications
    }

    @Test
    void isEntityActive() {
        // Expected and Actual Value

        // Mock Data
        PostMention postMention = spy(PostMention.class);
        postMention.setPost(Post.builder()
                .status(Status.ACTIVE)
                .build());

        // Stubbing methods

        // Calling the method
        // Assertions
        assertNotNull(postMention.getPost());
        assertNotNull(postMention.getPost().getStatus());

        assertTrue(postMention.isEntityActive());

        // Behavior Verifications
    }
}
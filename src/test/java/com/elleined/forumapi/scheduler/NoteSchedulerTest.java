package com.elleined.forumapi.scheduler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.elleined.forumapi.service.note.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NoteSchedulerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteScheduler noteScheduler;

    @Test
    void deleteExpiredNotes() {
        // Expected and Actual Value

        // Mock Data

        // Stubbing methods
        doNothing().when(noteService).deleteExpiredNotes();

        // Calling the method
        // Assertions
        assertDoesNotThrow(() -> noteScheduler.deleteExpiredNotes());

        // Behavior Verifications
        verify(noteService).deleteExpiredNotes();
    }
}
package com.elleined.socialmediaapi.service.note;

import com.elleined.socialmediaapi.exception.NoteException;
import com.elleined.socialmediaapi.model.user.User;
import com.elleined.socialmediaapi.model.note.Note;

public interface NoteService {
    Note save(User currentUser, String thought) throws NoteException;
    Note update(User currentUser, String newThought);
    void delete(User currentUser);
    Note getNote(User currentUser);

    public boolean hasNote() {
        return this.getNote() != null;
    }
    void deleteExpiredNotes();
}

package com.elleined.forumapi.service.pin;

import com.elleined.forumapi.exception.NotOwnedException;
import com.elleined.forumapi.exception.ResourceNotFoundException;
import com.elleined.forumapi.model.User;

public interface PinService<T, U> {
    void pin(User currentUser, T t, U u) throws NotOwnedException, ResourceNotFoundException;
    void unpin(T t);

    U getPinned(T t) throws ResourceNotFoundException;
}

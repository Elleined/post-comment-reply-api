package com.elleined.forumapi.mapper;

import com.elleined.forumapi.model.User;
import com.elleined.forumapi.model.note.Note;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel =  "spring")
public interface NoteMapper {

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "thought", expression = "java(thought)"),
            @Mapping(target = "user", expression = "java(currentUser)")
    })
    Note toEntity(User currentUser, @Context String thought);
}

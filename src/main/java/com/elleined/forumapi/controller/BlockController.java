package com.elleined.forumapi.controller;

import com.elleined.forumapi.dto.ResponseMessage;
import com.elleined.forumapi.dto.UserDTO;
import com.elleined.forumapi.mapper.UserMapper;
import com.elleined.forumapi.model.User;
import com.elleined.forumapi.service.block.BlockService;
import com.elleined.forumapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blocking/{currentUserId}")
public class BlockController {
    private final BlockService blockService;

    private final UserService userService;
    private final UserMapper userMapper;

    @PatchMapping("/blockUser/{userToBeBlockedId}")
    public ResponseMessage blockUser(@PathVariable("currentUserId") int currentUserId,
                                     @PathVariable("userToBeBlockedId") int userToBeBlockedId) {

        User currentUser = userService.getById(currentUserId);
        User userToBeBlocked = userService.getById(userToBeBlockedId);

        blockService.blockUser(currentUser, userToBeBlocked);
        return new ResponseMessage(HttpStatus.OK, "User with id of " + userToBeBlockedId + " blocked successfully");
    }

    @PatchMapping("/unblockUser/{userToBeUnblockedId}")
    public ResponseMessage unblockUser(@PathVariable("currentUserId") int currentUserId,
                                       @PathVariable("userToBeUnblockedId") int userToBeUnblockedId) {

        User currentUser = userService.getById(currentUserId);
        User userToBeUnblocked = userService.getById(userToBeUnblockedId);

        blockService.unBlockUser(currentUser, userToBeUnblocked);
        return new ResponseMessage(HttpStatus.OK, "User with id of " + userToBeUnblockedId + " unblocked successfully");
    }

    @GetMapping("/isBlockedBy/{userToCheckId}")
    public boolean isBlockedBy(@PathVariable("currentUserId") int currentUserId,
                               @PathVariable("userToCheckId") int userToCheckId) {

        User currentUser = userService.getById(currentUserId);
        User userToCheck = userService.getById(userToCheckId);

        return blockService.isBlockedBy(currentUser, userToCheck);
    }

    @GetMapping("/isYouBeenBlockedBy/{suspectedBlockerId}")
    public boolean isYouBeenBlockedBy(@PathVariable("currentUserId") int currentUserId,
                                      @PathVariable("suspectedBlockerId") int suspectedBlockerId) {

        User currentUser = userService.getById(currentUserId);
        User suspectedBlocker = userService.getById(suspectedBlockerId);
        return blockService.isYouBeenBlockedBy(currentUser, suspectedBlocker);
    }

    @GetMapping("/getAllBlockedUsers")
    public Set<UserDTO> getAllBlockedUserOf(@PathVariable("currentUserId") int currentUserId) {
        User currentUser = userService.getById(currentUserId);
        return blockService.getAllBlockedUsers(currentUser).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toSet());
    }
}

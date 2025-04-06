package com.message.routing.input.rest;

import com.message.routing.domain.model.User;
import com.message.routing.input.rest.dto.UserDTO;
import com.message.routing.output.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(final UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody final User user) {
        final String username = userService.finByUsernameAndPassword(user).username();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UserDTO(username));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody final User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
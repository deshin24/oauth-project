package sde24.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sde24.post.service.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * user 권한 변경
     */
    @PostMapping("/{email}")
    public ResponseEntity updateRole(@PathVariable String email){

        return new ResponseEntity(userService.updateRoleAdmin(email), HttpStatus.OK);
    }

}

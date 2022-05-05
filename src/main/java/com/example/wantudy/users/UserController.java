package com.example.wantudy.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api")
@Transactional
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/hello")
    public String hi(@RequestBody UserDto userDto){
        User user = new User(userDto.getUsername(), userDto.getPassword());
        userService.saveUser(user);
        return "hi";
    }
}

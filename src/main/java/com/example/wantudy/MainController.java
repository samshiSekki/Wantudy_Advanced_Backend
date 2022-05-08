package com.example.wantudy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String checkNickname() {
        return "thymeleaf/firstPage";
    }
}

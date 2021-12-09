package com.ph.phbackend.controllers;
import com.ph.phbackend.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;
}

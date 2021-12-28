package com.ph.phbackend.controllers;
import com.ph.phbackend.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    UserService userService;
}

package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class AdminController {

    @GetMapping(value = "/")
    public String adminhome() {
        return "admin";
    }

}

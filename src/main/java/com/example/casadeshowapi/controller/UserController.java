package com.example.casadeshowapi.controller;

import com.example.casadeshowapi.entities.User;
import com.example.casadeshowapi.repository.UserRepository;
import com.example.casadeshowapi.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    UserRepository service;

    @GetMapping("/cadastro")
    public ModelAndView user(User user) {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView novoUser(@Valid User user) {

        ModelAndView mv = new ModelAndView("shows");

        service.findAll();
        user.setRole("USUARIO");

        service.save(user);

        return mv;

    }

}

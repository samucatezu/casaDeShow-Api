package com.example.casadeshowapi.controller;

import com.example.casadeshowapi.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

    @Autowired
    private ShowService service;


}

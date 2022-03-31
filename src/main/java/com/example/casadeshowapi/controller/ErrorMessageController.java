package com.example.casadeshowapi.controller;

import com.example.casadeshowapi.exception.RecordNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ErrorMessageController {

    @RequestMapping(path = "/acessonegado")
    public ModelAndView deleteShowById() throws RecordNotFoundException {

        ModelAndView mv = new ModelAndView("/pagerro");

        return mv;
    }

}

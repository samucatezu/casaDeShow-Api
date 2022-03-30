package com.example.casadeshowapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.casadeshowapi.entities.Casa;
import com.example.casadeshowapi.exception.RecordNotFoundException;
import com.example.casadeshowapi.services.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CasaController {


    @Autowired
    private CasaService service;

    @GetMapping("/casa")
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView("casa");
        mv.addObject("casa", service.findAll());

        return mv;
    }


    @GetMapping("/adicionarcasa")
    public ModelAndView add(Casa casa) {

        ModelAndView mv = new ModelAndView("/addcasa");
        mv.addObject("casa", casa);
        mv.addObject("listar", service.findAll());

        return mv;
    }

    @PostMapping("/salvacasa")
    public ModelAndView salve(@Valid Casa casa, BindingResult result) {

        if (result.hasErrors()) {
            return add(casa);
        }

        service.criarAtualizarCasa(casa);

        return findAll();
    }

    @RequestMapping(path = {"/editcasa", "/editcasa/{idCasa}"})
    public String editarPorId(Model model, @PathVariable("idCasa") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Casa entity = service.acharPorId(id.get());
            model.addAttribute("casa", entity);
        } else {
            model.addAttribute("casa", new Casa());
        }
        return "addcasa";
    }

    @RequestMapping(path = "/deletecasa/{idCasa}")
    public String deleteCasaById(Model model, @PathVariable("idCasa") Long id) throws RecordNotFoundException {
        service.apagarCasa(id);
        return "redirect:/casa";
    }
}
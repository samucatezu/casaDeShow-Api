package com.example.casadeshowapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.casadeshowapi.entities.Casa;
import com.example.casadeshowapi.entities.Show;
import com.example.casadeshowapi.exception.RecordNotFoundException;
import com.example.casadeshowapi.repository.ShowRepository;
import com.example.casadeshowapi.services.CasaService;
import com.example.casadeshowapi.services.ShowService;
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
public class ShowController {

    @Autowired
    private ShowService service;

    @Autowired
    private CasaService home;

    @Autowired
    private ShowRepository repositorio;

    @GetMapping("/")
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView("shows");
        mv.addObject("shows", service.findAll());

        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView addShow(Show show) {

        ModelAndView mv = new ModelAndView("/addshow");
        mv.addObject("shows", show);


        return mv;
    }

    @ModelAttribute(value="casinha")
    public List<Casa> buscarCasas() {

        return home.findAll();
    }

    @PostMapping("/saveshow")
    public ModelAndView saveShow(@Valid Show shows, BindingResult result, Casa casa) {

        if (result.hasErrors()) {
            return addShow(shows);
        }

        System.out.println(casa.getNome());
        repositorio.save(shows);

        return findAll();
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editarPorId(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Show entity = service.acharPorId(id.get());
            model.addAttribute("show", entity);
        } else {
            model.addAttribute("show", new Show());
        }
        return "addshow";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteShowById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.apagarShow(id);
        return "redirect:/";
    }


}



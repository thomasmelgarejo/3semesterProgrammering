package com.jpa.thom.controllers;

import com.jpa.thom.models.Kommune;
import com.jpa.thom.models.Sogn;
import com.jpa.thom.repositoires.KommuneRepository;
import com.jpa.thom.repositoires.SognRepository;
import com.jpa.thom.services.SogneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SogneController {
    private KommuneRepository kommuneRepository;
    private SognRepository sognRepository;
    private SogneService sogneService;

    public SogneController(KommuneRepository kommuneRepository, SognRepository sognRepository, SogneService sogneService) {
        this.kommuneRepository = kommuneRepository;
        this.sognRepository = sognRepository;
        this.sogneService = sogneService;
    }

    @GetMapping("sogne")
    public String index(Model model){


        model.addAttribute("sogne", sogneService.sognsWithCheckedValueUpdated(sognRepository.findAll()));

        sogneService.KommuneOgSmittetryk();

        return "sogne";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model modelMods){
        modelMods.addAttribute("sogne", sognRepository.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Sogn sogne, @ModelAttribute Kommune kommune ){

        sognRepository.save(sogne);

        return "redirect:/sogne";
    }

    @GetMapping("/delete/{id}") //skal ænderes til POST
    public String delete(@PathVariable("id") long id){
        sognRepository.deleteById(id);
        return "redirect:/sogne";
    }

    @GetMapping("/create")
    public String create(Model model){

        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Sogn sogne, Model model, @RequestParam("kommuneId") Long kommuneId){

        Sogn newSogn = sognRepository.save(sogne);

        //opret ny kommune med newSogn som reference
        Kommune kommune = kommuneRepository.findById(kommuneId).get();
        kommuneRepository.save(kommune);

        //sæt reference til kommune i newSogn
        newSogn.setKommune(kommune);
        sognRepository.save(newSogn);

        return "redirect:/sogne";
    }

    @GetMapping("smittetryk")
    public String smittetryk(Model model){
        model.addAttribute("smittetryk", sogneService.KommuneOgSmittetryk() );
        return "smittetryk";
    }
}

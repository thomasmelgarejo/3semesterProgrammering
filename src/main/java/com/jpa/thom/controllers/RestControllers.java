package com.jpa.thom.controllers;

import com.jpa.thom.models.Kommune;
import com.jpa.thom.models.Sogn;
import com.jpa.thom.repositoires.KommuneRepository;
import com.jpa.thom.repositoires.SognRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class RestControllers {

    @Autowired
    SognRepository sognRepository;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("restsogne")
    public Iterable<Sogn> findAll(){

       return sognRepository.findAll();
    }

    //TODO Mangler exceptions
    @DeleteMapping("/restsogne/{id}")
    public void deleteStudent(@PathVariable long id) {
        sognRepository.deleteById(id);
    }

    @PostMapping("/restsogne")
    public ResponseEntity<Object> createStudent(@RequestBody Sogn sogn) {
        Sogn savedSogn = sognRepository.save(sogn);
        Kommune kommune = new Kommune("kommuneDummy");
        savedSogn.setKommune(kommune);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSogn.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

//    @PostMapping("/restsogne")
//    public void createStudent(@RequestBody Sogn sogn) {
//        Sogn savedSogn = sognRepository.save(sogn);
//    }

    @PutMapping("/restsogne/{id}")
    public ResponseEntity<Object> updateSogn(@RequestBody Sogn sogn, @PathVariable long id) {

        Optional<Sogn> sognOptional = sognRepository.findById(id);
        Long kommuneId = sognOptional.get().getKommune().getId();
        Kommune kommuneOld = kommuneRepository.findById(kommuneId).get();

        if (!sognOptional.isPresent())
            return ResponseEntity.notFound().build();

        sogn.setId(id);
        sogn.setKommune(kommuneOld);

        sognRepository.save(sogn);

        return ResponseEntity.noContent().build();
    }

}

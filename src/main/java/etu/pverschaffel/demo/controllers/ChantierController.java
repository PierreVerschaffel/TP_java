package etu.pverschaffel.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.dao.ChantierDao;
import etu.pverschaffel.demo.models.Chantier;
import etu.pverschaffel.demo.views.ChantierView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {

    @Autowired
    ChantierDao chantierDao;

    @GetMapping("/admin/chantier/list")
    @JsonView(ChantierView.class)
    public List<Chantier> liste(){

        List<Chantier> chantierList = chantierDao.findAll();


        return chantierList;

    }

    @GetMapping("/chantier/{id}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> get(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if(optionalChantier.isPresent()){
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/chantier/{id}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> delete(@PathVariable int id){
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if(optionalChantier.isPresent()){
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/chantier")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier chantier){
        chantier.setId(null);
        chantierDao.save(chantier);
        return new ResponseEntity<>(chantier, HttpStatus.CREATED);
    }

    @PutMapping("/admin/chantier/{id}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier chantier, @PathVariable int id){
        chantier.setId(id);

        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if(optionalChantier.isPresent()){
            chantierDao.save(chantier);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/chantier-by-nom/{nom}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> getByNom(@PathVariable String nom) {
        Optional<Chantier> optionalChantier = chantierDao.findByNom(nom);

        if(optionalChantier.isPresent()){
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

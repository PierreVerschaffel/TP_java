package etu.pverschaffel.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.dao.ConsommableDao;
import etu.pverschaffel.demo.models.Consommable;
import etu.pverschaffel.demo.views.ConsommableView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsommableController {

    @Autowired
    ConsommableDao consommableDao;

    @GetMapping("/consommable/list")
    @JsonView(ConsommableView.class)
    public List<Consommable> liste(){

        List<Consommable> consommableList = consommableDao.findAll();


        return consommableList;

    }

    @GetMapping("/consommable/{id}")
    @JsonView(ConsommableView.class)
    public ResponseEntity<Consommable> get(@PathVariable int id) {
        Optional<Consommable> optionalConsommable = consommableDao.findById(id);

        if(optionalConsommable.isPresent()){
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/consommable/{id}")
    @JsonView(ConsommableView.class)
    public ResponseEntity<Consommable> delete(@PathVariable int id){
        Optional<Consommable> optionalConsommable = consommableDao.findById(id);

        if(optionalConsommable.isPresent()){
            consommableDao.deleteById(id);
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/consommable")
    @JsonView(ConsommableView.class)
    public ResponseEntity<Consommable> create(@RequestBody @Valid Consommable consommable){
        consommable.setId(null);
        consommableDao.save(consommable);
        return new ResponseEntity<>(consommable, HttpStatus.CREATED);
    }

    @PutMapping("/admin/consommable/{id}")
    @JsonView(ConsommableView.class)
    public ResponseEntity<Consommable> update(@RequestBody @Valid Consommable consommable, @PathVariable int id){
        consommable.setId(id);

        Optional<Consommable> optionalConsommable = consommableDao.findById(id);

        if(optionalConsommable.isPresent()){
            consommableDao.save(consommable);
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/consommable-by-nom/{nom}")
    @JsonView(ConsommableView.class)
    public ResponseEntity<Consommable> getByNom(@PathVariable String nom) {
        Optional<Consommable> optionalConsommable = consommableDao.findByNom(nom);

        if(optionalConsommable.isPresent()){
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

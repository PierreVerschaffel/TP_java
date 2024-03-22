package etu.pverschaffel.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.dao.TacheDao;
import etu.pverschaffel.demo.models.Tache;
import etu.pverschaffel.demo.views.TacheView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TacheController {

    @Autowired
    TacheDao tacheDao;

    @GetMapping("/tache/list")
    @JsonView(TacheView.class)
    public List<Tache> liste(){

        List<Tache> tacheList = tacheDao.findAll();


        return tacheList;

    }

    @GetMapping("/tache/{id}")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> get(@PathVariable int id) {
        Optional<Tache> optionalTache = tacheDao.findById(id);

        if(optionalTache.isPresent()){
            return new ResponseEntity<>(optionalTache.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/tache/{id}")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> delete(@PathVariable int id){
        Optional<Tache> optionalTache = tacheDao.findById(id);

        if(optionalTache.isPresent()){
            tacheDao.deleteById(id);
            return new ResponseEntity<>(optionalTache.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/tache")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> create(@RequestBody @Valid Tache tache){
        tache.setId(null);
        tacheDao.save(tache);
        return new ResponseEntity<>(tache, HttpStatus.CREATED);
    }

    @PutMapping("/admin/tache/{id}")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> update(@RequestBody @Valid Tache tache, @PathVariable int id){
        tache.setId(id);

        Optional<Tache> optionalTache = tacheDao.findById(id);

        if(optionalTache.isPresent()){
            tacheDao.save(tache);
            return new ResponseEntity<>(optionalTache.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tache-by-nom/{nom}")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> getByNom(@PathVariable String nom) {
        Optional<Tache> optionalTache = tacheDao.findByNom(nom);

        if(optionalTache.isPresent()){
            return new ResponseEntity<>(optionalTache.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}

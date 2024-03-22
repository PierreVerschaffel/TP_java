package etu.pverschaffel.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.dao.OperationDao;
import etu.pverschaffel.demo.models.Operation;
import etu.pverschaffel.demo.views.OperationView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OperationController {

    @Autowired
    OperationDao operationDao;

    @GetMapping("/operation/list")
    @JsonView(OperationView.class)
    public List<Operation> liste(){

        List<Operation> operationList = operationDao.findAll();


        return operationList;

    }

    @GetMapping("/operation/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> get(@PathVariable int id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if(optionalOperation.isPresent()){
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/operation/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> delete(@PathVariable int id){
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if(optionalOperation.isPresent()){
            operationDao.deleteById(id);
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/operation")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> create(@RequestBody @Valid Operation operation){
        operation.setId(null);
        operationDao.save(operation);
        return new ResponseEntity<>(operation, HttpStatus.CREATED);
    }

    @PutMapping("/admin/operation/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> update(@RequestBody @Valid Operation operation, @PathVariable int id){
        operation.setId(id);

        Optional<Operation> optionalOperation = operationDao.findById(id);

        if(optionalOperation.isPresent()){
            operationDao.save(operation);
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/operation-by-nom/{nom}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> getByNom(@PathVariable String nom) {
        Optional<Operation> optionalOperation = operationDao.findByNom(nom);

        if(optionalOperation.isPresent()){
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

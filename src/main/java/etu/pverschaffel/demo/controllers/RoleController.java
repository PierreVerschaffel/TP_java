package etu.pverschaffel.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.dao.RoleDao;
import etu.pverschaffel.demo.models.Role;
import etu.pverschaffel.demo.views.RoleView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    RoleDao roleDao;

    @GetMapping("/role/list")
    @JsonView(RoleView.class)
    public List<Role> liste(){

        List<Role> roleList = roleDao.findAll();


        return roleList;

    }

    @GetMapping("/role/{id}")
    @JsonView(RoleView.class)
    public ResponseEntity<Role> get(@PathVariable int id) {
        Optional<Role> optionalRole = roleDao.findById(id);

        if(optionalRole.isPresent()){
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/role/{id}")
    @JsonView(RoleView.class)
    public ResponseEntity<Role> delete(@PathVariable int id){
        Optional<Role> optionalRole = roleDao.findById(id);

        if(optionalRole.isPresent()){
            roleDao.deleteById(id);
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/role")
    @JsonView(RoleView.class)
    public ResponseEntity<Role> create(@RequestBody @Valid Role role){
        role.setId(null);
        roleDao.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping("/admin/role/{id}")
    @JsonView(RoleView.class)
    public ResponseEntity<Role> update(@RequestBody @Valid Role role, @PathVariable int id){
        role.setId(id);

        Optional<Role> optionalRole = roleDao.findById(id);

        if(optionalRole.isPresent()){
            roleDao.save(role);
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/role-by-designation/{designation}")
    @JsonView(RoleView.class)
    public ResponseEntity<Role> getByDesignation(@PathVariable String designation) {
        Optional<Role> optionalRole = roleDao.findByDesignation(designation);

        if(optionalRole.isPresent()){
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

package etu.pverschaffel.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.dao.RoleDao;
import etu.pverschaffel.demo.dao.UtilisateurDao;
import etu.pverschaffel.demo.models.Role;
import etu.pverschaffel.demo.models.Utilisateur;
import etu.pverschaffel.demo.security.AppUserDetailsService;
import etu.pverschaffel.demo.security.JwtUtils;
import etu.pverschaffel.demo.views.UtilisateurView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public void signIn(@RequestBody Utilisateur utilisateur){

        if (utilisateur.getRole().getId() == 1) {
            utilisateur.setAdmin(true);
        } else if (utilisateur.getRole().getId() == 2) {
            utilisateur.setAdmin(false);
        }
        utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));

        utilisateurDao.save(utilisateur);
    }

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur user) {

        try {
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getPseudo(),
                    user.getPassword()
            )).getPrincipal();

            return jwtUtils.generateJwt(userDetails);

        } catch (Exception ex) {
            return null;
        }

    }

    @GetMapping("/utilisateur/list")
    @JsonView(UtilisateurView.class)
    public List<Utilisateur> liste(){

        List<Utilisateur> utilisateurList = utilisateurDao.findAll();


        return utilisateurList;

    }

    @GetMapping("/utilisateur/{id}")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> get(@PathVariable int id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if(optionalUtilisateur.isPresent()){
            return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> delete(@PathVariable int id){
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if(optionalUtilisateur.isPresent()){
            utilisateurDao.deleteById(id);
            return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/utilisateur")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> create(@RequestBody @Valid Utilisateur utilisateur){
        utilisateur.setId(null);
        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    @PutMapping("/admin/utilisateur/{id}")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> update(@RequestBody @Valid Utilisateur utilisateur, @PathVariable int id){
        utilisateur.setId(id);

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if(optionalUtilisateur.isPresent()){
            utilisateurDao.save(utilisateur);
            return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/utilisateur-by-pseudo/{pseudo}")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> getByPseudo(@PathVariable String pseudo) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByPseudo(pseudo);

        if(optionalUtilisateur.isPresent()){
            return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

package etu.pverschaffel.demo.security;

import etu.pverschaffel.demo.dao.UtilisateurDao;
import etu.pverschaffel.demo.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUser = utilisateurDao.findByPseudo(pseudo);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("pseudo introuvable");
        }

        return new AppUserDetails(optionalUser.get());
    }
}

package etu.pverschaffel.demo.dao;

import etu.pverschaffel.demo.models.Chantier;
import etu.pverschaffel.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Optional<Role> findByDesignation(String nomRecherche);


}

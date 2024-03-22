package etu.pverschaffel.demo.dao;

import etu.pverschaffel.demo.models.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChantierDao extends JpaRepository<Chantier, Integer> {

    Optional<Chantier> findByNom(String nomRecherche);


}

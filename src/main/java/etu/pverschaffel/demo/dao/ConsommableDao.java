package etu.pverschaffel.demo.dao;

import etu.pverschaffel.demo.models.Consommable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsommableDao extends JpaRepository<Consommable, Integer> {

    Optional<Consommable> findByNom(String nomRecherche);


}

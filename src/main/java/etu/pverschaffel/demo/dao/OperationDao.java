package etu.pverschaffel.demo.dao;

import etu.pverschaffel.demo.models.Chantier;
import etu.pverschaffel.demo.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Integer> {

    Optional<Operation> findByNom(String nomRecherche);


}

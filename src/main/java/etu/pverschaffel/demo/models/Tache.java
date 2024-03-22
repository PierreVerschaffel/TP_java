package etu.pverschaffel.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.views.OperationView;
import etu.pverschaffel.demo.views.TacheView;
import etu.pverschaffel.demo.views.UtilisateurView;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(TacheView.class)
    protected Integer id;

    @Length(min = 3, max = 50, message = "Le nom de la tâche doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom est obligatoire")
    @JsonView({UtilisateurView.class, TacheView.class, OperationView.class})
    protected String nom;

    @JsonView(TacheView.class)
    @Min(value = 0, message = "Le temps de la tâche doit être supérieur à 0")
    protected int temps;

    @OneToMany(mappedBy = "tache")
    @JsonView(TacheView.class)
    protected List<Operation> opertationList = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "tache_consommable",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "consommable_id")
    )
    @JsonView(TacheView.class)
    protected List<Consommable> tacheList = new ArrayList<>();
}

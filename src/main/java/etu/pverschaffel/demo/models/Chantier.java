package etu.pverschaffel.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.views.ChantierView;
import etu.pverschaffel.demo.views.OperationView;
import etu.pverschaffel.demo.views.UtilisateurView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Chantier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @Length(min = 3, max = 50, message = "Le nom du chantier doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom du chantier est obligatoire")
    @JsonView({UtilisateurView.class, ChantierView.class, OperationView.class})
    protected String nom;

    @JsonView(ChantierView.class)
    protected String adresse;

    @ManyToOne(optional = false)
    @JsonView(ChantierView.class)
    protected Utilisateur utilisateur;

    @OneToMany(mappedBy = "chantier")
    @JsonView(ChantierView.class)
    protected List<Operation> opertationList = new ArrayList<>();
}

package etu.pverschaffel.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.views.ChantierView;
import etu.pverschaffel.demo.views.OperationView;
import etu.pverschaffel.demo.views.TacheView;
import etu.pverschaffel.demo.views.UtilisateurView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OperationView.class)
    protected Integer id;

    @Column(unique = true, length = 50)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom est obligatoire")
    @JsonView({UtilisateurView.class, OperationView.class, ChantierView.class, TacheView.class})
    protected String nom;

    @JsonView(OperationView.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date date;

    @ManyToOne(optional = false)
    @JsonView(OperationView.class)
    protected Utilisateur utilisateur;

    @ManyToOne(optional = false)
    @JsonView(OperationView.class)
    protected Chantier chantier;

    @ManyToOne(optional = false)
    @JsonView(OperationView.class)
    protected Tache tache;


}

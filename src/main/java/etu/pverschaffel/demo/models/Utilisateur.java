package etu.pverschaffel.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.views.ChantierView;
import etu.pverschaffel.demo.views.OperationView;
import etu.pverschaffel.demo.views.RoleView;
import etu.pverschaffel.demo.views.UtilisateurView;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UtilisateurView.class)
    protected Integer id;

    @Column(unique = true, length = 50)
    @Length(min = 3, max = 50, message = "Le pseudo doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le pseudo est obligatoire")
    @JsonView({UtilisateurView.class, ChantierView.class, OperationView.class, RoleView.class})
    protected String pseudo;

    @JsonView(UtilisateurView.class)
    protected String password;

    @OneToMany(mappedBy = "utilisateur")
    @JsonView(UtilisateurView.class)
    protected List<Chantier> chantierList = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonView(UtilisateurView.class)
    protected List<Operation> opertationList = new ArrayList<>();

    @ManyToOne(optional = false)
    @JsonView(UtilisateurView.class)
    protected Role role;

    protected boolean admin;

}

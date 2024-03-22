package etu.pverschaffel.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.views.RoleView;
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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(unique = true, length = 50)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom est obligatoire")
    @JsonView({RoleView.class, UtilisateurView.class})
    protected String designation;

    @OneToMany(mappedBy = "role")
    @JsonView(RoleView.class)
    protected List<Utilisateur> utilisateurList = new ArrayList<>();

}

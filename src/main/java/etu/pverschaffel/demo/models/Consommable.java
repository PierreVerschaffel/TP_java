package etu.pverschaffel.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import etu.pverschaffel.demo.views.ConsommableView;
import etu.pverschaffel.demo.views.TacheView;
import etu.pverschaffel.demo.views.UtilisateurView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class Consommable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ConsommableView.class)
    protected Integer id;

    @Column(unique = true, length = 50)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom est obligatoire")
    @JsonView({TacheView.class, ConsommableView.class})
    protected String nom;
}

package it.fabio.ristorante.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Ristorante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String codiceUtente;

    private String email;

   private String nomeRistorante;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Piatto> listaPiatti = new HashSet<>();

    public Ristorante(String email, String nomeRistorante, String codiceUtente) {
        this.email = email;
        this.nomeRistorante = nomeRistorante;
        this.codiceUtente = codiceUtente;
    }

    public Ristorante(Set<Piatto> listaPiatti) {
        this.listaPiatti = listaPiatti;
    }

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ingrediente> listaIngredienti = new HashSet<>();
}



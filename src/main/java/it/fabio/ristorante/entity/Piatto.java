package it.fabio.ristorante.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ingrediente> listaIngredienti = new HashSet<>();

    public Piatto(String nome, Set<Ingrediente> listaIngredienti) {
        this.nome = nome;
        this.listaIngredienti = listaIngredienti;
    }
}

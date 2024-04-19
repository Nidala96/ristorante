package it.fabio.ristorante.repository;

import it.fabio.ristorante.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByusername(String username);

    boolean existsByusername(String username);
}

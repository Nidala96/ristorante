package it.fabio.ristorante.repository;

import it.fabio.ristorante.entity.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {

    boolean existsByEmail(String email);

    Optional<Ristorante> findByEmail(String email);

    void deleteBynomeRistorante(String utenteId);
}

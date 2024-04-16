package it.fabio.ristorante.repository;

import it.fabio.ristorante.entity.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {

    boolean existsByEmail(String email);

    Ristorante findByEmail(String email);

    void deleteBylistaPiattiId(Long piattoId);
}

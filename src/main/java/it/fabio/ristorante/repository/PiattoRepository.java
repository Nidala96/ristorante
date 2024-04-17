package it.fabio.ristorante.repository;

import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.service.RistoranteService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PiattoRepository extends JpaRepository<Piatto, Long> {
    Set<Piatto> findBylistaIngredientiId(long ingredienteId);
}

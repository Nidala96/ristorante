package it.fabio.ristorante.repository;

import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.service.RistoranteService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PiattoRepository extends JpaRepository<Piatto, Long> {
    Piatto findBylistaIngredientiId(long ingredienteId);
}

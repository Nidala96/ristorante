package it.fabio.ristorante.service;


import it.fabio.ristorante.entity.Ingrediente;
import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.entity.Ristorante;
import it.fabio.ristorante.exception.ResourceNotFoundException;
import it.fabio.ristorante.repository.IngredienteRepository;
import it.fabio.ristorante.repository.PiattoRepository;
import it.fabio.ristorante.repository.RistoranteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final RistoranteRepository ristoranteRepository;

    private final IngredienteRepository ingredienteRepository;

    private final PiattoRepository piattoRepository;

    @Transactional
    public ResponseEntity<?> addIngrediente(@AuthenticationPrincipal OidcUser principal, String descrizione) {
        if(!ristoranteRepository.existsByEmail(principal.getEmail()))
            return  new ResponseEntity<>("nessun Ristorante con quella email", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getEmail());
        if (ristorante.isEmpty())
            return new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        Ingrediente ingrediente = new Ingrediente(descrizione);
        ingredienteRepository.save(ingrediente);
        ristorante.get().getListaIngredienti().add(ingrediente);
        return new ResponseEntity<>("New ingrediente aggiunto", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> eliminaIngrediente(OidcUser principal, long ingredienteId) {
        if(!ristoranteRepository.existsByEmail(principal.getEmail()))
            return  new ResponseEntity<>("nessun ingrediente collegato alla email", HttpStatus.NOT_FOUND);
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(ingredienteId);
        if(ingrediente.isEmpty())
            return  new ResponseEntity<>("nessun ingrediente con quel id", HttpStatus.NOT_FOUND);
        piattoRepository.findBylistaIngredientiId(ingredienteId).forEach(piatto -> piatto.getListaIngredienti().remove(ingrediente.get()));
        ristoranteRepository.findByEmail(principal.getEmail()).get().getListaIngredienti().remove(ingrediente.get());
        ingredienteRepository.deleteById(ingredienteId);
        return new ResponseEntity<>("Ingrediente eliminato", HttpStatus.OK);
    }
}


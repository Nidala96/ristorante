package it.fabio.ristorante.service;


import it.fabio.ristorante.entity.Ingrediente;
import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.entity.Ristorante;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PiattoService {

    private final PiattoRepository piattoRepository;
    private final RistoranteRepository ristoranteRepository;
    private final IngredienteRepository ingredienteRepository;

    @Transactional
    public ResponseEntity<?> addPiatto(@AuthenticationPrincipal OidcUser principal, String nome, Set<Long> listaIngredientiId) {
        if(!ristoranteRepository.existsByEmail(principal.getEmail()))
            return  new ResponseEntity<>("nessun Ristorante con quella email", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getEmail());
        if(ristorante.isEmpty())
            return  new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        List<Ingrediente> listaIngredienti = ingredienteRepository.findAllById(listaIngredientiId);
        Set<Ingrediente> setIngredienti = new HashSet<>(listaIngredienti);
        Piatto piatto = new Piatto(nome, setIngredienti);
        piattoRepository.save(piatto);
        ristorante.get().getListaPiatti().add(piatto);
        return new ResponseEntity<>("New piatto created", HttpStatus.CREATED);
    }
    @Transactional
    public ResponseEntity<?> eliminaPiatto(OidcUser principal, Long piattoId) {
        if(!ristoranteRepository.existsByEmail(principal.getEmail()))
            return  new ResponseEntity<>("nessun piatto collegato alla email", HttpStatus.NOT_FOUND);
        Optional<Piatto> piatto = piattoRepository.findById(piattoId);
        if(piatto.isEmpty())
            return  new ResponseEntity<>("nessun piatto con quel id", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getEmail());
        if(ristorante.isEmpty())
            return  new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        ristorante.get().getListaPiatti().remove(piatto.get());
        piattoRepository.deleteById(piattoId);
        return new ResponseEntity<>("Piatto eliminato", HttpStatus.OK);
    }
}

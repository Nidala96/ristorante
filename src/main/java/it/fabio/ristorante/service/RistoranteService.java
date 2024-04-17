package it.fabio.ristorante.service;

import it.fabio.ristorante.entity.Ingrediente;
import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.entity.Ristorante;
import it.fabio.ristorante.repository.RistoranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RistoranteService {

    private final PasswordEncoder passwordEncoder;
    private final RistoranteRepository ristoranteRepository;

    public ResponseEntity<?> addRistorante(@AuthenticationPrincipal OidcUser principal,String password) {
        if(ristoranteRepository.existsByEmail(principal.getEmail()))
            return  new ResponseEntity<>("Ristorante con quell'email gia' creato", HttpStatus.BAD_REQUEST);
        Ristorante ristorante = new Ristorante(principal.getEmail(),principal.getName(),passwordEncoder.encode(password));
        ristoranteRepository.save(ristorante);
        return new ResponseEntity<>("New ristorante created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> getListaPiatti(OidcUser principal) {
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getEmail());
        if(ristorante.isEmpty())
            return  new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ristorante.get().getListaPiatti(), HttpStatus.OK);
    }

    public ResponseEntity<?> getListaIngredienti(OidcUser principal) {
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getEmail());
        if(ristorante.isEmpty())
            return  new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        Set<Piatto> listaPiatti = ristorante.get().getListaPiatti();
        Set<Ingrediente> listaIngredienti = new HashSet<>();
        for(Piatto p : listaPiatti){
            listaIngredienti.addAll(p.getListaIngredienti());
        }
        return new ResponseEntity<>(listaIngredienti, HttpStatus.OK);
    }
}

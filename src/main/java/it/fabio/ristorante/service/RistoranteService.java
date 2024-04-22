package it.fabio.ristorante.service;

import it.fabio.ristorante.entity.Ingrediente;
import it.fabio.ristorante.entity.Ristorante;
import it.fabio.ristorante.entity.Utente;
import it.fabio.ristorante.exception.ResourceNotFoundException;
import it.fabio.ristorante.repository.RistoranteRepository;
import it.fabio.ristorante.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RistoranteService {

    private final RistoranteRepository ristoranteRepository;

    private final UtenteRepository utenteRepository;

    @Transactional
    public ResponseEntity<?> addRistorante(UserDetails principal, String nomeRistorante) {
        Utente utente = utenteRepository.findByusername(principal.getUsername()).orElseThrow(() -> new ResourceNotFoundException("utente", "username", principal.getUsername()));
        if(ristoranteRepository.existsByEmail(utente.getEmail()))
            return new ResponseEntity<>("Ristorante con quell'email gia' creato", HttpStatus.BAD_REQUEST);
        Ristorante ristorante = new Ristorante(utente.getEmail(), nomeRistorante);
        ristoranteRepository.save(ristorante);
        utente.setRistorante(ristorante);
        return new ResponseEntity<>("New ristorante created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> getListaPiatti(UserDetails principal) {
        Utente utente = (Utente) principal;
        if(utente == null)
            return  new ResponseEntity<>("Utente non esiste", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(utente.getEmail());
        if (ristorante.isEmpty())
            return new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ristorante.get().getListaPiatti(), HttpStatus.OK);
    }

    public ResponseEntity<?> getListaIngredienti(UserDetails principal) {
        Utente utente = (Utente) principal;
        if(utente == null)
            return  new ResponseEntity<>("Utente non esiste", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(utente.getEmail());
        if (ristorante.isEmpty())
            return new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        Set<Ingrediente> listaIngredienti = ristorante.get().getListaIngredienti();
        return new ResponseEntity<>(listaIngredienti, HttpStatus.OK);
    }
}


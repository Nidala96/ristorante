package it.fabio.ristorante.service;

import it.fabio.ristorante.entity.Ingrediente;
import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.entity.Ristorante;
import it.fabio.ristorante.repository.RistoranteRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RistoranteService {

    @Value("${okta.oauth2.bearer-token}")
    private String secretKey;
    private final PasswordEncoder passwordEncoder;
    private final RistoranteRepository ristoranteRepository;

    public ResponseEntity<?> addRistorante(@AuthenticationPrincipal OidcUser principal, String nomeRistorante) {
        if(!checkUtente(principal))
            return new ResponseEntity<>("Utente con quel'email non esiste", HttpStatus.UNAUTHORIZED);
        if (ristoranteRepository.existsByEmail(principal.getEmail()))
            return new ResponseEntity<>("Ristorante con quell'email gia' creato", HttpStatus.BAD_REQUEST);
        Ristorante ristorante = new Ristorante(principal.getEmail(), nomeRistorante, principal.getName());
        ristoranteRepository.save(ristorante);
        return new ResponseEntity<>("New ristorante created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> getListaPiatti(OidcUser principal) {
        if(!checkUtente(principal))
            return new ResponseEntity<>("Utente con quel'email non esiste", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getEmail());
        if (ristorante.isEmpty())
            return new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ristorante.get().getListaPiatti(), HttpStatus.OK);
    }

    public ResponseEntity<?> getListaIngredienti(OAuth2User principal) {
//        System.out.println(principal.getIdToken().getTokenValue());
//        System.out.println(principal);
//        if(!checkUtente(principal))
//            return new ResponseEntity<>("Utente con quel'email non esiste", HttpStatus.NOT_FOUND);
        Optional<Ristorante> ristorante = ristoranteRepository.findByEmail(principal.getAttribute("email"));
        if (ristorante.isEmpty())
            return new ResponseEntity<>("Ristorante non ancora creato", HttpStatus.BAD_REQUEST);
        Set<Ingrediente> listaIngredienti= ristorante.get().getListaIngredienti();
        return new ResponseEntity<>(listaIngredienti, HttpStatus.OK);
    }

    private boolean checkUtente(OidcUser principal) {
        HttpResponse<String> response =  Unirest.get("https://dev-5um7h52037bdwgaf.eu.auth0.com/api/v2/users-by-email?email=" + principal.getEmail())
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + secretKey)
                .asString();
        System.out.println(response.getBody());
        if(response.getStatus() == 200 && response.getBody().contains("email")){
            return true;
        }
        return false;
    }
}


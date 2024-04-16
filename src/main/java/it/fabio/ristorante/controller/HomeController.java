package it.fabio.ristorante.controller;

import it.fabio.ristorante.entity.Piatto;
import it.fabio.ristorante.service.IngredienteService;
import it.fabio.ristorante.service.PiattoService;
import it.fabio.ristorante.service.RistoranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for the home page.
 */
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final RistoranteService ristoranteService;

    private final PiattoService piattoService;

    private final IngredienteService ingredienteService;

    @PostMapping("/registra-ristorante")
    public ResponseEntity<?> registraRistorante(@AuthenticationPrincipal OidcUser principal, String password) {
        ristoranteService.addRistorante(principal, password);
        return new ResponseEntity<>("Test", HttpStatus.CREATED);
    }

    @PostMapping("/aggiungi-ingrediente")
    public ResponseEntity<?> aggiungiIngrediente(@AuthenticationPrincipal OidcUser principal, String descrizione) {
        ingredienteService.addIngrediente(principal, descrizione);
        return new ResponseEntity<>("Test", HttpStatus.CREATED);
    }

    @PutMapping("/aggiungi-piatto")
    public ResponseEntity<?> aggiungiPiatto(@AuthenticationPrincipal OidcUser principal, String nome,@RequestParam Set<Long> ingredienti) {
        piattoService.addPiatto(principal, nome, ingredienti);
        return new ResponseEntity<>("Piatto aggiunto", HttpStatus.CREATED);
    }

    @GetMapping("/lista-piatti")
    public ResponseEntity<?> listaPiatti(@AuthenticationPrincipal OidcUser principal) {
        return new ResponseEntity<>(ristoranteService.getListaPiatti(principal), HttpStatus.OK);
    }

    @GetMapping("/lista-ingredienti")
    public ResponseEntity<?> listaIngredienti(@AuthenticationPrincipal OidcUser principal) {
        return new ResponseEntity<>(ristoranteService.getListaIngredienti(principal), HttpStatus.OK);
    }

    @DeleteMapping("/elimina-ingrediente")
    public ResponseEntity<?> eliminaIngrediente(@AuthenticationPrincipal OidcUser principal, Long ingredienteId) {
        return new ResponseEntity<>(ingredienteService.eliminaIngrediente(principal, ingredienteId), HttpStatus.OK);
    }

    @DeleteMapping("/elimina-piatto")
    public ResponseEntity<?> eliminaPiatto(@AuthenticationPrincipal OidcUser principal, Long piattoId) {
        return new ResponseEntity<>(piattoService.eliminaPiatto(principal, piattoId), HttpStatus.OK);
    }



}


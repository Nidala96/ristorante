package it.fabio.ristorante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.fabio.ristorante.service.Auth0Service;
import it.fabio.ristorante.service.IngredienteService;
import it.fabio.ristorante.service.PiattoService;
import it.fabio.ristorante.service.RistoranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for the home page.
 */
@RestController
@RequestMapping(path = "home")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {

    private final RistoranteService ristoranteService;

    private final PiattoService piattoService;

    private final IngredienteService ingredienteService;

    private final Auth0Service auth0Service;

    @Operation(
            description = "END POINT FOR ADDING A RESTAURANT",
            summary = "Creating RESTAURANT",
            responses ={
                    @ApiResponse(description = "Restaurant created successfully", responseCode = "201"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @PostMapping("/registra-ristorante")
    public ResponseEntity<?> registraRistorante(@AuthenticationPrincipal OidcUser principal, @RequestParam String nomeRistorante) {
        return ristoranteService.addRistorante(principal, nomeRistorante);
    }

    @Operation(
            description = "END POINT FOR ADDING INGREDIENT",
            summary = "Creating ingredient",
            responses ={
                    @ApiResponse(description = "Ingredient created successfully", responseCode = "201"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @PostMapping("/aggiungi-ingrediente")
    public ResponseEntity<?> aggiungiIngrediente(@AuthenticationPrincipal OidcUser principal, String descrizione) {
        return ingredienteService.addIngrediente(principal, descrizione);
    }

    @Operation(
            description = "END POINT FOR ADDING DISH",
            summary = "Creating dish",
            responses ={
                    @ApiResponse(description = "Dish created successfully", responseCode = "201"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @PutMapping("/aggiungi-piatto")
    public ResponseEntity<?> aggiungiPiatto(@AuthenticationPrincipal OidcUser principal, String nome,@RequestParam Set<Long> ingredienti) {
        return piattoService.addPiatto(principal, nome, ingredienti);
    }

    @Operation(
            description = "END POINT FOR LIST OF DISHES",
            summary = "List of dishes",
            responses ={
                    @ApiResponse(description = "List of dishes", responseCode = "200"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @GetMapping("/lista-piatti")
    public ResponseEntity<?> listaPiatti(@AuthenticationPrincipal OidcUser principal) {
        System.out.println(principal.getIdToken());
        return ristoranteService.getListaPiatti(principal);
    }

    @Operation(
            description = "END POINT FOR LIST OF INGREDIENTS",
            summary = "List of ingredients",
            responses ={
                    @ApiResponse(description = "List of ingredients", responseCode = "200"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @GetMapping("/lista-ingredienti")
    public ResponseEntity<?> listaIngredienti(@AuthenticationPrincipal OidcUser principal) {
        return ristoranteService.getListaIngredienti(principal);
    }

    @Operation(
            description = "END POINT FOR DELETING A INGREDIENT",
            summary = "Deleting a ingredient",
            responses ={
                    @ApiResponse(description = "DELETING THE INGREDIENT", responseCode = "200"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @DeleteMapping("/elimina-ingrediente")
    public ResponseEntity<?> eliminaIngrediente(@AuthenticationPrincipal OidcUser principal, Long ingredienteId) {
        return ingredienteService.eliminaIngrediente(principal, ingredienteId);
    }

    @Operation(
            description = "END POINT FOR DELETING DISH",
            summary = "Deleting dish",
            responses ={
                    @ApiResponse(description = "Dish deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @DeleteMapping("/elimina-piatto")
    public ResponseEntity<?> eliminaPiatto(@AuthenticationPrincipal OidcUser principal, Long piattoId) {
        return piattoService.eliminaPiatto(principal, piattoId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getSomeResource(OAuth2AuthenticationToken token) {
        System.out.println(token.getPrincipal());
        token.setAuthenticated(false);
        System.out.println(token.getPrincipal());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied!");

    }

}


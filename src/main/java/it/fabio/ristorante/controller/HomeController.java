package it.fabio.ristorante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.fabio.ristorante.service.IngredienteService;
import it.fabio.ristorante.service.PiattoService;
import it.fabio.ristorante.service.RistoranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for the home page.
 */
@RestController
@RequestMapping(path = "home")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class HomeController {

    private final RistoranteService ristoranteService;

    private final PiattoService piattoService;

    private final IngredienteService ingredienteService;



    @Operation(
            description = "END POINT FOR ADDING A RESTAURANT",
            summary = "Creating RESTAURANT",
            responses ={
                    @ApiResponse(description = "Restaurant created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Not Authorized", responseCode = "401"),
                    @ApiResponse(description = "Resource not found", responseCode = "404")
            }
    )
    @PostMapping("/registra-ristorante")
    public ResponseEntity<?> registraRistorante(@AuthenticationPrincipal UserDetails principal, @RequestParam String nomeRistorante) {
        return ristoranteService.addRistorante(principal, nomeRistorante);
    }

    @Operation(
            description = "END POINT FOR ADDING INGREDIENT",
            summary = "Creating ingredient",
            responses ={
                    @ApiResponse(description = "Ingredient created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Not Authorized", responseCode = "401"),
                    @ApiResponse(description = "Resource not found", responseCode = "404")
            }
    )
    @PostMapping("/aggiungi-ingrediente")
    public ResponseEntity<?> aggiungiIngrediente(@AuthenticationPrincipal UserDetails principal, String descrizione) {
        return ingredienteService.addIngrediente(principal, descrizione);
    }

    @Operation(
            description = "END POINT FOR ADDING DISH",
            summary = "Creating dish",
            responses ={
                    @ApiResponse(description = "Dish created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Not Authorized", responseCode = "401"),
                    @ApiResponse(description = "Resource not found", responseCode = "404")
            }
    )
    @PostMapping("/aggiungi-piatto")
    public ResponseEntity<?> aggiungiPiatto(@AuthenticationPrincipal UserDetails principal, String nome,@RequestParam Set<Long> ingredienti) {
        return piattoService.addPiatto(principal, nome, ingredienti);
    }

    @Operation(
            description = "END POINT FOR LIST OF DISHES",
            summary = "List of dishes",
            responses ={
                    @ApiResponse(description = "List of dishes", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Not Authorized", responseCode = "401"),
                    @ApiResponse(description = "Resource not found", responseCode = "404")
            }
    )
    @GetMapping("/lista-piatti")
    public ResponseEntity<?> listaPiatti(@AuthenticationPrincipal UserDetails principal) {
        try {
            return ristoranteService.getListaPiatti(principal);
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    @Operation(
            description = "END POINT FOR LIST OF INGREDIENTS",
            summary = "List of ingredients",
            responses ={
                    @ApiResponse(description = "List of ingredients", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Not Authorized", responseCode = "401")
            }
    )
    @GetMapping("/lista-ingredienti")
    public ResponseEntity<?> listaIngredienti(@AuthenticationPrincipal UserDetails principal) {
        return ristoranteService.getListaIngredienti(principal);
    }

    @Operation(
            description = "END POINT FOR DELETING DISH",
            summary = "Deleting dish",
            responses ={
                    @ApiResponse(description = "Dish deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Not Authorized", responseCode = "401"),
                    @ApiResponse(description = "Resource not found", responseCode = "404")
            }
    )
    @DeleteMapping("/elimina-ingrediente")
    public ResponseEntity<?> eliminaIngrediente(@AuthenticationPrincipal UserDetails principal, Long ingredienteId) {
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
    public ResponseEntity<?> eliminaPiatto(@AuthenticationPrincipal UserDetails principal, Long piattoId) {
        return piattoService.eliminaPiatto(principal, piattoId);
    }

}


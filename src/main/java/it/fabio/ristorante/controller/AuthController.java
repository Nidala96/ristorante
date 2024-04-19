package it.fabio.ristorante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.fabio.ristorante.service.Auth0Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final Auth0Service auth0Service;

    @Operation(
            description = "END POINT FOR DELETING USER",
            summary = "Deleting user",
            responses ={
                    @ApiResponse(description = "User deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @DeleteMapping("/elimina-utente")
    public ResponseEntity<?> eliminaUtente(String utenteId, @AuthenticationPrincipal OidcUser principal) {
        return auth0Service.eliminaUtente(utenteId, principal);
    }
}

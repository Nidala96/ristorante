package it.fabio.ristorante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.fabio.ristorante.payload.request.SigninRequest;
import it.fabio.ristorante.payload.request.SignupRequest;
import it.fabio.ristorante.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {


    private final AuthenticationService authenticationService;
    @Operation(
            description = "Registra Utente",
            summary = "Creating Utente",
            responses ={
                    @ApiResponse(description = "Restaurant created successfully", responseCode = "201"),
                    @ApiResponse(description = "BaD ReQuest", responseCode = "400"),
            }
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest signupRequest){
        return authenticationService.signup(signupRequest);
    }

    @Operation(
            description = "Login Utente",
            summary = "Login in the application",
            responses ={
                    @ApiResponse(description = "Restaurant created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad request", responseCode = "400"),
            }
    )
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninRequest signinRequest) {
        return authenticationService.signin(signinRequest);
    }

    @Operation(
            description = "Elimina Utente",
            summary = "Elimina utente dal database",
            responses ={
                    @ApiResponse(description = "Restaurant created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad request", responseCode = "400"),
            }
    )
    @DeleteMapping("/delete-utente")
    public ResponseEntity<?> deleteUtente(@RequestParam long id){
        return authenticationService.deleteUtente(id);
    }

}

package it.fabio.ristorante.controller;

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

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest signupRequest){
        return authenticationService.signup(signupRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninRequest signinRequest){
        return authenticationService.signin(signinRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUtente(@RequestParam long id){
        return authenticationService.deleteUtente(id);
    }

}

package it.fabio.ristorante.service;

import it.fabio.ristorante.repository.RistoranteRepository;
import jakarta.transaction.Transactional;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class Auth0Service {

    @Value("${okta.oauth2.bearer-token}")
    private String secretKey;
    private final RistoranteRepository ristoranteRepository;

    @Transactional
    public ResponseEntity<?> eliminaUtente(String utenteId, OidcUser principal) {
        HttpResponse<String> response =  Unirest.delete("https://dev-5um7h52037bdwgaf.eu.auth0.com/api/v2/users/" + utenteId.replace("|","%7C"))
                .header("content-type", "application/json")
                .header("Authorization", "Bearer " + secretKey)
                .body("{\"client_id\":\"80f3Gluzp0UedkmDIyRY7hqbv8iHcoiJ\",\"client_secret\":\"eeyrM3VG99eI1bvfww6iCSTIyoYfZjB-_d3K86hyRB2QerzXCvNtZEfNg2jVR3TD\",\"audience\":\"https://dev-5um7h52037bdwgaf.eu.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
                .asString();
        if(response.getStatus() == 204)
            ristoranteRepository.deleteBycodiceUtente(utenteId);
        return new ResponseEntity<>(response.getStatus(), HttpStatusCode.valueOf(response.getStatus()));
    }
}

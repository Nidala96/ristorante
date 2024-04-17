package it.fabio.ristorante.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        email = "pittaufabio2@gmail.com"
                ),
                description = "OpenApi documentation for mini ristorante.",
                title = "OpenApi specification - RISTORANTE",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "DEV ENV",
                        url = "http://localhost:${server.port}"
                )
        }
)

public class OpenApiConfig {
}


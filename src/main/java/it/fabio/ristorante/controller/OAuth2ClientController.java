//package it.fabio.ristorante.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class OAuth2ClientController {
//
//    @Autowired
//    private OAuth2AuthorizedClientManager authorizedClientManager;
//
//    @GetMapping("/")
//    public String index(Authentication authentication,
//                        HttpServletRequest servletRequest,
//                        HttpServletResponse servletResponse) {
//
//        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("okta")
//                .principal(authentication)
//                .attributes(attrs -> {
//                    attrs.put(HttpServletRequest.class.getName(), servletRequest);
//                    attrs.put(HttpServletResponse.class.getName(), servletResponse);
//                })
//                .build();
//        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
//
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//
//        return "index";
//    }
//}

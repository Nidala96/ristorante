//package it.fabio.ristorante.service;
//
//import it.fabio.ristorante.repository.RistoranteRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class OAuth2RistoranteService extends DefaultOAuth2UserService {
//
//    private final RistoranteRepository ristoranteRepository;
//
//    @Override
//    @SneakyThrows
//    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
//        log.trace("Load user {}", oAuth2UserRequest);
//        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
//        return processOAuth2User(oAuth2UserRequest, oAuth2User);
//    }
//
////    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
////        Oauth2UserInfoDto userInfoDto = Oauth2UserInfoDto
////                .builder()
////                .name(oAuth2User.getAttributes().get("name").toString())
////                .id(oAuth2User.getAttributes().get("sub").toString())
////                .email(oAuth2User.getAttributes().get("email").toString())
////                .picture(oAuth2User.getAttributes().get("picture").toString())
////                .build();
////
////        log.trace("User info is {}", userInfoDto);
////        Optional<Ristorante> userOptional = ristoranteRepository.findByUsername(userInfoDto.getEmail());
////        log.trace("User is {}", userOptional);
////        Ristorante ristorante = userOptional
////                .map(existingUser -> updateExistingUser(existingUser, userInfoDto))
////                .orElseGet(() -> registerNewUser(oAuth2UserRequest, userInfoDto));
////        return UserPrincipal.create(user, oAuth2User.getAttributes());
////    }
////
////    private Ristorante registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2User userInfoDto) {
////        Ristorante ristorante = new Ristorante();
////        ristorante.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
////        ristorante.setProviderId(userInfoDto.getId());
////        ristorante.setName(userInfoDto.getName());
////        ristorante.setUsername(userInfoDto.getEmail());
////        ristorante.setPicture(userInfoDto.getPicture());
////        ristorante.setId(UUID.randomUUID());
////        return ristoranteRepository.save(ristorante);
////    }
////
////    private Ristorante updateExistingUser(Ristorante existingUser, Oauth2UserInfoDto userInfoDto) {
////        existingUser.setEmail(userInfoDto.getName());
////        existingUser.setPassword(userInfoDto.getPicture());
////        return ristoranteRepository.save(existingUser);
////    }
//
//}

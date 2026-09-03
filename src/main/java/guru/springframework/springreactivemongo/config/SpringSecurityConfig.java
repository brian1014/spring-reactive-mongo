package guru.springframework.springreactivemongo.config;

import org.springframework.boot.security.autoconfigure.actuate.web.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Bean
    @Order(1)
    public SecurityWebFilterChain actuatorFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeExchange(auth -> auth.anyExchange().permitAll())
                .build();
    }

    @Bean
    @Order(2)
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(
                authorizeExchangeSpec ->
                        authorizeExchangeSpec.anyExchange().authenticated())
                .oauth2ResourceServer(
                        oAuth2ResourceServerSpec ->
                                oAuth2ResourceServerSpec.jwt(Customizer.withDefaults())
                );


        return httpSecurity.build();
    }
}

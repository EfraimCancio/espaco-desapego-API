package desapego.brecho.api.infra.security;

/*
        Definição da CLASSE: Classe de configuração para a segurança da aplicação

        Principais Atribuições:
        1. @Configuration: Indica que esta classe é uma classe de configuração do Spring.
        Ela será registrada como um bean e usada para configurar o contexto da aplicação.
        2. @EnableWebSecurity: Ativa o suporte à segurança da Web com o Spring Security,
        permitindo que a classe personalize o comportamento padrão.luxo da requisição HTTP.
        3. Ultilizasse de da Classe SecurityFilter para as regras de validação das requisiçõe
        e seus respectivos tokens.
        4. Definição do comportamento STATELESS "Sem estado" da API.
        5. Trata da Autenticação dos dados recebidos na requisição tenha suas devidas permissões
        e indica quais rotas estão autorizadas para cada tipo de usuário.
        6. Define as rotas e permições que a documentação oficial(SWAGGUER) terá acesso.
        7. Contem o método PasswordEncoder, reponsável por codificar e descodificar os Tokens
        usados nas transações. Salva os dados de forma segura no banco e Ajuda na validação das
        das autorirações e das rotas.
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/login").permitAll();
                            req.requestMatchers("/v3/api-docs/**").permitAll();
                            req.requestMatchers("/swagger-ui.html").permitAll();
                            req.requestMatchers("/swagger-ui/**").permitAll();
                            req.anyRequest().authenticated();
                        })
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

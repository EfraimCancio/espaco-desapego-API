package desapego.brecho.api.infra.security;

/*
        Definição da CLASSE: Filtro responsável por interceptar as requisições HTTP da Api.

        Principais Atribuições:
        1. Recupera o token que vem no cabeçalho AuthAuthorisation da requisição:
        2. Verifica se o token é válido, no tempo de validade e não nulo!
        3. Compara os valores de loguin recebidos no subject do token recuperado;
        4. Testa as permições do usuario logado, liberando acesso de acordo com as pemissões
                do metodo user.getAuthorities();
        5. Conclui o processo de autenticação e finaliza dando continuidade ao fluxo da requisição HTTP.
*/

import desapego.brecho.api.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = takeToken(request);
        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var user = userRepository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String takeToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}

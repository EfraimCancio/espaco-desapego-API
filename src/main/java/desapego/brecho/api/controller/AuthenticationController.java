package desapego.brecho.api.controller;

import desapego.brecho.api.domain.user.DataAuthenticationDTO;
import desapego.brecho.api.domain.user.User;
import desapego.brecho.api.infra.security.DataTokenJWTDTO;
import desapego.brecho.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthenticationDTO dataAuthentication) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthentication.login(), dataAuthentication.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWTDTO(tokenJWT));
    }
}


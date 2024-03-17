package desapego.brecho.api.domain.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public record DataAuthenticationDTO(@RequestBody @Valid String login, String password) {
}

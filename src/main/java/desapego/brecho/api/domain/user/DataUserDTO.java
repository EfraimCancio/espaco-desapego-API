package desapego.brecho.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataUserDTO(
        @NotBlank
        String login,
        String password,
        String name
) {

}

package desapego.brecho.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataUserBreakdownDTO(

        Long id,
        String login,
        String password,
        String name
) {
    public DataUserBreakdownDTO (User user) {
        this(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getName()
        );
    }
}

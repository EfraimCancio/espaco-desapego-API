package desapego.brecho.api.domain.origin;

import jakarta.validation.constraints.NotBlank;

public record DataOriginDTO(

        @NotBlank
        String descOrigem,

        @NotBlank
        Type tipoOrigem,

        Boolean status
    ) {
}

package desapego.brecho.api.domain.origin;

import jakarta.validation.constraints.NotBlank;

public record DataOriginDTO(

        @NotBlank
        String descOrigem,

        Type tipoOrigem,

        Boolean status
    ) {
}

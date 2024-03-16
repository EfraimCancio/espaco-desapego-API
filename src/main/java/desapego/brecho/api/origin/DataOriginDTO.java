package desapego.brecho.api.origin;

import jakarta.validation.constraints.NotBlank;

public record DataOriginDTO(

        @NotBlank
        String descOrigem,

        @NotBlank
        Type tipoOrigem,

        Boolean status
    ) {
}

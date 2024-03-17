package desapego.brecho.api.domain.origin;

import jakarta.validation.constraints.NotNull;

public record DataUpdateOriginDTO(

        @NotNull
        Long id,

        String descOrigem,

        Type tipoOrigem
) {
}

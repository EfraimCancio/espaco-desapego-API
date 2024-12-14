package desapego.brecho.api.domain.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateBrandDTO(

        @NotNull
        Long id,

        @NotBlank
        String descMarca,

        Boolean status
) {
}

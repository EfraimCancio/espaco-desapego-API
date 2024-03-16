package desapego.brecho.api.size;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateSizeDTO(

        @NotNull
        Long id,

        @NotBlank
        String descTamanho
) {
}

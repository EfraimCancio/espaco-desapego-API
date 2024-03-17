package desapego.brecho.api.domain.size;

import jakarta.validation.constraints.NotBlank;

public record DataSizeDTO(
        @NotBlank
        String descTamanho
) {
}

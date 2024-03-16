package desapego.brecho.api.size;

import jakarta.validation.constraints.NotBlank;

public record DataSizeDTO(
        @NotBlank
        String descTamanho
) {
}

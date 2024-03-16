package desapego.brecho.api.brand;

import jakarta.validation.constraints.NotBlank;

public record DataBrandDTO(

        @NotBlank
        String descMarca) {
}

package desapego.brecho.api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCategoryDTO(

        @NotNull
        Long id,

        @NotBlank
        String descCategoria
) {
}

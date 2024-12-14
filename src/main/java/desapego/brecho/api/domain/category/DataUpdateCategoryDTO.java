package desapego.brecho.api.domain.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCategoryDTO(

        @NotNull
        Long id,

        @NotBlank
        String descCategoria,

        Boolean status
) {
}

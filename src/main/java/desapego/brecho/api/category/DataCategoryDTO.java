package desapego.brecho.api.category;

import jakarta.validation.constraints.NotNull;

public record DataCategoryDTO(

        @NotNull
        String descCategoria,

        Boolean status) {
}

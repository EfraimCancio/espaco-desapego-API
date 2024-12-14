package desapego.brecho.api.domain.brand;

import jakarta.validation.constraints.NotBlank;

public record DataBrandDTO(

        @NotBlank
        String descMarca,
        Boolean status
) {


}

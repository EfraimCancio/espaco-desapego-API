package desapego.brecho.api.domain.product;

import jakarta.validation.constraints.NotNull;

public record DataUpdateProductsDTO(

        @NotNull
        Long id,
        String descProduto,
        Long  idCategoria,
        Long  idMarca,
        Long idTamanho,
        Long  idOrigem,
        Float vlCusto,
        Float vlVenda
) {
}

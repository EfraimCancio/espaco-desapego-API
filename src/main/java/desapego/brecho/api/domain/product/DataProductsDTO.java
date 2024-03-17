package desapego.brecho.api.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DataProductsDTO(

        @NotBlank
        String descProduto,

        @NotNull
        Long idCategoria,

        @NotNull
        Long  idMarca,

        @NotNull
        Long idTamanho,

        @NotNull
        Long  idOrigem,

        @NotNull
        Float vlCusto,
        Float vlVenda,

        LocalDate dtEntrada,

        LocalDate dtSaida,

        Boolean status) {


}

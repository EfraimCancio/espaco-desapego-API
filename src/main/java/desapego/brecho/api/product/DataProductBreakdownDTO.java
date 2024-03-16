package desapego.brecho.api.product;

import java.time.LocalDate;

public record DataProductBreakdownDTO(
      Long id,

      String descProduto,

      Long  idCategoria,

      Long  idMarca,

      Long idTamanho,

      Long  idOrigem,

      Float vlCusto,

      Float vlVenda,

      LocalDate dtEntrada,

      LocalDate dtSaida,

     Boolean status
) {

    public DataProductBreakdownDTO(Product product) {
        this(
                product.getId(),
                product.getDescProduto(),
                product.getIdCategoria(),
                product.getIdMarca(),
                product.getIdTamanho(),
                product.getIdOrigem(),
                product.getVlCusto(),
                product.getVlVenda(),
                product.getDtEntrada(),
                product.getDtSaida(),
                product.getStatus()
        );
    }
}

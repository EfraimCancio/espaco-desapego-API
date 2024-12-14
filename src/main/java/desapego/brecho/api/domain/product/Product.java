package desapego.brecho.api.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name="TB_PRODUTO")
@Entity(name = "Produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESC_PRODUTO")
    private String descProduto;

    @Column(name = "ID_CATEGORIA")
    private Long  idCategoria;

    @Column(name = "ID_MARCA")
    private Long  idMarca;

    @Column(name = "ID_TAMANHO")
    private Long idTamanho;

    @Column(name = "ID_ORIGEM")
    private Long  idOrigem;

    @Column(name = "VL_CUSTO")
    private Float vlCusto;

    @Column(name = "VL_VENDA")
    private Float vlVenda;

    @Column(name = "DT_ENTRADA")
    private LocalDate dtEntrada;

    @Column(name = "DT_SAIDA")
    private LocalDate dtSaida;

    @Column(name = "STATUS")
    private Boolean status;


    public Product(DataProductsDTO data) {
        this.descProduto = data.descProduto();
        this.idCategoria = data.idCategoria();
        this.idMarca = data.idMarca();
        this.idTamanho = data.idTamanho();
        this.idOrigem = data.idOrigem();
        this.vlCusto = data.vlCusto();
        this.dtEntrada = LocalDate.now();
        this.status = true;
    }

    public void updateProduct(DataUpdateProductsDTO data) {
        if (data.descProduto() != null) {
            this.descProduto = data.descProduto();
        }
        if (data.idCategoria() != null) {
            this.idCategoria = data.idCategoria();
        }
        if (data.idMarca() != null) {
            this.idMarca = data.idMarca();
        }
        if (data.idTamanho() != null) {
            this.idTamanho = data.idTamanho();
        }
        if (data.idOrigem() != null) {
            this.idOrigem = data.idOrigem();
        }
        if (data.vlCusto() != null) {
            this.vlCusto = data.vlCusto();
        }
    }

    public void inactivateProduct() {
        this.status = false;
    }
}

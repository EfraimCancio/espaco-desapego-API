package desapego.brecho.api.domain.brand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Table(name="TB_MARCA")
@Entity(name = "Marca")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Brand {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESC_MARCA")
    private String descMarca;

    @Column(name = "STATUS")
    private Boolean status;

    public Brand (DataBrandDTO data) {
        this.descMarca = data.descMarca();
        this.status = data.status();
    }



    public void updateBrand(DataUpdateBrandDTO dataBrand) {
        if (dataBrand.descMarca() != null) {
            this.descMarca = dataBrand.descMarca();
        }
        if(dataBrand.status() != null) {
            this.status = dataBrand.status();
        }
    }

    public void inactivateBrand() {
        this.status = false;
    }
}

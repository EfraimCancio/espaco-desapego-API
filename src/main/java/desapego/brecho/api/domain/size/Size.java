package desapego.brecho.api.domain.size;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="TB_TAMANHO")
@Entity(name = "Tamanho")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Size {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="DESC_TAMANHO")
    private String descTamanho;

    @Column(name = "STATUS")
    private Boolean status;

    public Size( DataSizeDTO dataSize) {
        this.descTamanho = dataSize.descTamanho();
        this.status = true;
    }

    public void updateSize(DataUpdateSizeDTO dataSize) {

        if (dataSize.descTamanho() != null && !dataSize.descTamanho().isEmpty()) {
            this.descTamanho = dataSize.descTamanho();
        }
    }

    public void inactivateSize() {
        this.status = false;
    }
}

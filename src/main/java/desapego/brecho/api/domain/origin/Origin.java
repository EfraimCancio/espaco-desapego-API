package desapego.brecho.api.domain.origin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="TB_ORIGEM")
@Entity(name = "Origem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Origin {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESC_ORIGEM")
    private String descOrigem;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ORIGEM")
    private Type tipoOrigem;

    @Column(name = "STATUS")
    private Boolean status;

    public Origin(DataOriginDTO dataOrigim) {
        this.descOrigem = dataOrigim.descOrigem();
        this.tipoOrigem = dataOrigim.tipoOrigem();
    }

    public void updateBrand(DataUpdateOriginDTO dataOrigin) {
        if (dataOrigin.descOrigem() != null) {
            this.descOrigem = dataOrigin.descOrigem();
        }
        if (dataOrigin.tipoOrigem() != null) {
            this.tipoOrigem = dataOrigin.tipoOrigem();
        }
    }

    public void inactivateOrigin() {
        this.status = false;
    }
}

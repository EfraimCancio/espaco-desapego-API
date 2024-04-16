package desapego.brecho.api.domain.image;

import desapego.brecho.api.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagePK implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;

    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name="ID_PRODUTO", referencedColumnName = "ID")
    private Product product;

}

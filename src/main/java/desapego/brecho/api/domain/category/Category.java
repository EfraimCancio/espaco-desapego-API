package desapego.brecho.api.domain.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="TB_CATEGORIA")
@Entity(name = "Categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESC_CATEGORIA")
    private String descCategoria;

    @Column(name = "STATUS")
    private Boolean status;

    public Category(DataCategoryDTO data) {
        this.descCategoria = data.descCategoria();
    }

    public void updateProduct(DataUpdateCategoryDTO dataCategory) {
        if (dataCategory.descCategoria() != null) {
            this.descCategoria = dataCategory.descCategoria();
        }
    }

    public void inactivateCategory() {
        this.status = false;
    }
}

package desapego.brecho.api.domain.image;
import jakarta.persistence.*;
import lombok.Data;


@Table(name="TB_IMAGE")
@Entity(name = "Image")
@Data
public class Image {

    @EmbeddedId
    private ImagePK id;

    @Column(name = "Image")
    private byte[] image;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "status")
    private Boolean status;

    public Image(
            ImagePK id,
            byte[] bytes,
            String originalFilename,
            Boolean status
    ) {
        this.id = id;
        this.image = bytes;
        this.fileName = originalFilename;
        this.status = status;
    }

    public Image () {}
}

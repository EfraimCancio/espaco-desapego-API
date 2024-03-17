package desapego.brecho.api.domain.size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizesRepository extends JpaRepository<Size, Long> {
    Page<Size> findAllByStatusTrue(Pageable pagination);
}

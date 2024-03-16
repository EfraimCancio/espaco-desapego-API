package desapego.brecho.api.origin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OriginRepository extends JpaRepository<Origin, Long> {
    Page<Origin> findAllByStatusTrue(Pageable pagination);
}

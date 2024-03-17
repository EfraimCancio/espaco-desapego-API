package desapego.brecho.api.domain.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Page<Brand> findAllByStatusTrue(Pageable pagination);
}

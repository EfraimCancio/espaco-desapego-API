package desapego.brecho.api.controller;

import desapego.brecho.api.brand.*;
import desapego.brecho.api.category.DataCategoryBreakdownDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("marcas")
public class BrandsController {

    @Autowired
    private BrandRepository brandRepository;

    @PostMapping
    @Transactional
    public ResponseEntity resgisterBrand (@RequestBody @Valid DataBrandDTO data, UriComponentsBuilder uriBuider) {
        var brand = new Brand(data);
        brandRepository.save(brand);
        var uri = uriBuider.path("/marcas/{id}").buildAndExpand(brand.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataBrandBreakdownDTO(brand));
    }

    @GetMapping
    public ResponseEntity<Page<Brand>> listBrands(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page =  brandRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterBrand(@RequestBody @Valid DataUpdateBrandDTO dataBrand) {
        var brand = brandRepository.getReferenceById(dataBrand.id());
        brand.updateBrand(dataBrand);
        return ResponseEntity.ok(new DataBrandBreakdownDTO(brand));    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBrand(@PathVariable Long id) {
        var brand = brandRepository.getReferenceById(id);
        brand.inactivateBrand();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<Brand>> listActiveProducts(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page =  brandRepository.findAllByStatusTrue(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity datailBrand(@PathVariable Long id) {
        var brand = brandRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataBrandBreakdownDTO(brand));
    }
}

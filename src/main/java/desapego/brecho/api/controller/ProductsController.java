package desapego.brecho.api.controller;

import desapego.brecho.api.product.*;
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
@RequestMapping("produtos")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid DataProductsDTO dataProduct, UriComponentsBuilder uriBuider) {
        var product = new Product(dataProduct);
        productsRepository.save(product);
        var uri = uriBuider.path("/produtos/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataProductBreakdownDTO(product));
    }

    @GetMapping
    public ResponseEntity<Page<Product>> listProducts(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page = productsRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity alterProduct(@RequestBody @Valid DataUpdateProductsDTO dataProduct) {
        var product = productsRepository.getReferenceById(dataProduct.id());
        product.updateProduct(dataProduct);
        return ResponseEntity.ok(new DataProductBreakdownDTO(product));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        var product = productsRepository.getReferenceById(id);
        product.inactivateProduct();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<Product>> listActiveProducts(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page =  productsRepository.findAllByStatusTrue(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity datailProduct(@PathVariable Long id) {
        var product = productsRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataProductBreakdownDTO(product));
    }
}

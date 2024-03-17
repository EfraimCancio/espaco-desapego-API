package desapego.brecho.api.controller;

import desapego.brecho.api.domain.category.*;
import desapego.brecho.api.domain.category.Category;
import desapego.brecho.api.domain.category.CategoryRepository;
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
@RequestMapping("categorias")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity RegisterCategory(@RequestBody  @Valid DataCategoryDTO data, UriComponentsBuilder uriBuider) {
        var category = new Category(data);
        categoryRepository.save(category);
        var uri = uriBuider.path("/categorias/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataCategoryBreakdownDTO(category));    }

    @GetMapping
    public ResponseEntity<Page<Category>> listCategory(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page = categoryRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterCategory(@RequestBody @Valid DataUpdateCategoryDTO dataCategory) {
        var category = categoryRepository.getReferenceById(dataCategory.id());
        category.updateProduct(dataCategory);
        return ResponseEntity.ok(new DataCategoryBreakdownDTO(category));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        var category = categoryRepository.getReferenceById(id);
        category.inactivateCategory();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<Category>> listActiveCategorys(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page = categoryRepository.findAllByStatusTrue(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity datailCategory(@PathVariable Long id) {
        var category = categoryRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataCategoryBreakdownDTO(category));
    }
}

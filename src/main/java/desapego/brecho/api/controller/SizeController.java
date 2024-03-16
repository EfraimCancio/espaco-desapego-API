package desapego.brecho.api.controller;

import desapego.brecho.api.size.*;
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
@RequestMapping("tamanho")
public class SizeController {

    @Autowired
    private SizesRepository sizesRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerSize(@RequestBody @Valid DataSizeDTO dataSize, UriComponentsBuilder uriBuider) {
        var size =  new Size(dataSize);
        sizesRepository.save(size);
        var uri = uriBuider.path("/tamanho/{id}").buildAndExpand(size.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataSizeBreakdownDTO(size));
    }

    @GetMapping
    public ResponseEntity<Page<Size>> listSize(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page = sizesRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterSize(@RequestBody @Valid DataUpdateSizeDTO dataSize) {
        var size = sizesRepository.getReferenceById(dataSize.id());
        size.updateSize(dataSize);
        return ResponseEntity.ok(new DataSizeBreakdownDTO(size));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSize(@PathVariable Long id) {
        var brand = sizesRepository.getReferenceById(id);
        brand.inactivateSize();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<Size>> listActiveProducts(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page =  sizesRepository.findAllByStatusTrue(pagination);
        return ResponseEntity.ok(page);
    }

}

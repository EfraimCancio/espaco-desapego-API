package desapego.brecho.api.controller;

import desapego.brecho.api.domain.origin.*;
import desapego.brecho.api.domain.origin.*;
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
@RequestMapping("origem")
public class OriginController {

    @Autowired
    OriginRepository originRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerOrigin(@RequestBody @Valid DataOriginDTO data, UriComponentsBuilder uriBuider){
        var origin = new Origin(data);
        originRepository.save(origin);
        var uri = uriBuider.path("/origem/{id}").buildAndExpand(origin.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataOriginBreakdownDTO(origin));
    }

    @GetMapping
    public ResponseEntity<Page<Origin>> listOrigin(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
        var page = originRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterOrigin(@RequestBody @Valid DataUpdateOriginDTO dataOrigin) {
        var origin = originRepository.getReferenceById(dataOrigin.id());
        origin.updateBrand(dataOrigin);
        return ResponseEntity.ok(new DataOriginBreakdownDTO(origin));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        var origin = originRepository.getReferenceById(id);
        origin.inactivateOrigin();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<Origin>> listActiveOrigins(@PageableDefault(size = 15, sort={"id"}) Pageable pagination) {
      var page = originRepository.findAllByStatusTrue(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity datailOrigin(@PathVariable Long id) {
        var origin = originRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataOriginBreakdownDTO(origin));
    }
}



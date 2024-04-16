package desapego.brecho.api.controller;


import desapego.brecho.api.domain.image.Image;
import desapego.brecho.api.domain.image.ImagePK;
import desapego.brecho.api.domain.image.ImageRepository;
import desapego.brecho.api.domain.product.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@RestController
@RequestMapping("imagem")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity registerImage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable("id") Long id) throws IOException {

        var product = productsRepository.findById(id);

        if (product.isPresent()) {
            var UUID = java.util.UUID.randomUUID().toString();
            var image = new Image(new ImagePK(UUID, product.get()), multipartFile.getBytes(), multipartFile.getOriginalFilename(), true);
            imageRepository.save(image);
        }
        return ResponseEntity.ok("Imagem cadastrada com sucesso!");
    }

}

package desapego.brecho.api.controller;

import desapego.brecho.api.domain.size.DataSizeBreakdownDTO;
import desapego.brecho.api.domain.size.DataSizeDTO;
import desapego.brecho.api.domain.size.Size;
import desapego.brecho.api.domain.user.DataUserBreakdownDTO;
import desapego.brecho.api.domain.user.DataUserDTO;
import desapego.brecho.api.domain.user.User;
import desapego.brecho.api.domain.user.UserRepository;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(@PageableDefault(sort={"id"}) Pageable pagination) {
        var page = userRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid DataUserDTO dataUser, UriComponentsBuilder uriBuider) {
        var user =  new User(dataUser);
        userRepository.save(user);
        var uri = uriBuider.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataUserBreakdownDTO(user));
    }
}

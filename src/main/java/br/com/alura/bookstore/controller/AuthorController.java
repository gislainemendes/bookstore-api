package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<AuthorsDto> toList(Pageable pageable) {
        return authorService.toList(pageable);
    }

    @PostMapping
    public ResponseEntity<AuthorsDto> toSave(@RequestBody @Valid AuthorsFormDto dto,
                                             UriComponentsBuilder uriBuilder) {
        AuthorsDto authorsDto = authorService.toSave(dto);

        URI uri = uriBuilder.path("/authors/{id}").buildAndExpand(authorsDto.getId()).toUri();
        return ResponseEntity.created(uri).body(authorsDto);
    }
}

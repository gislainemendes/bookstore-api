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
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<AuthorsDto> getAllAuthorsById(Pageable pageable) {
        return authorService.toList(pageable);
    }

    @GetMapping("/id/{id}")
    public Optional<AuthorsDto> getAuthorById(@PathVariable(value = "id") @NotNull Long id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    public ResponseEntity<AuthorsDto> saveAuthor(@RequestBody @Valid AuthorsFormDto dto,
                                                 UriComponentsBuilder uriBuilder) {
        AuthorsDto authorsDto = authorService.toSave(dto);

        URI uri = uriBuilder.path("/authors/{id}").buildAndExpand(authorsDto.getId()).toUri();
        return ResponseEntity.created(uri).body(authorsDto);
    }

    /*
      @RequestBody
      @RequestParam
      @PathVariable
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable(value = "id") @NotNull Long id) {
        authorService.toDelete(id);
        return ResponseEntity.ok().build();
    }

}

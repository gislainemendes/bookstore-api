package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @ApiOperation("List all authors")
    public Page<AuthorsDto> getAllAuthorsById(Pageable pageable) {
        return authorService.findAllAuthors(pageable);
    }

    @GetMapping("/id/{id}")
    @ApiOperation("Author details")
    public Optional<AuthorsDto> getAuthorById(@PathVariable(value = "id") @NotNull Long id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    @ApiOperation("Register an author")
    public ResponseEntity<AuthorsDto> saveAuthor(@RequestBody @Valid AuthorsFormDto dto,
                                                 UriComponentsBuilder uriBuilder) {
        AuthorsDto authorsDto = authorService.saveAuthors(dto);

        URI uri = uriBuilder.path("/authors/{id}").buildAndExpand(authorsDto.getId()).toUri();
        return ResponseEntity.created(uri).body(authorsDto);
    }

    @PutMapping("/id/{id}")
    @ApiOperation("Update an author")
    public ResponseEntity<AuthorsDto> updateAuthor(@RequestBody @Valid AuthorsFormDto dto,
                                                   @PathVariable(value = "id") @NotNull Long id){
        Optional<AuthorsDto> optionalAuthorDto = authorService.updateAuthors(dto, id);

        if(optionalAuthorDto.isPresent()){
            AuthorsDto authorsDto = optionalAuthorDto.get();
            return ResponseEntity.ok(authorsDto);
        }
        return ResponseEntity.notFound().build();


    }

    /*
      @RequestBody
      @RequestParam
      @PathVariable
     */
    @DeleteMapping("/id/{id}")
    @ApiOperation("Remove an author")
    public ResponseEntity<Void> deleteAuthor(@PathVariable(value = "id") @NotNull Long id) {
        authorService.deleteAuthors(id);
        return ResponseEntity.ok().build();
    }

}

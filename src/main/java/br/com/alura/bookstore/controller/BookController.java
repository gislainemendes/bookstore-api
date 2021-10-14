package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
import br.com.alura.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<BooksDto> toList(Pageable pageable) {
        return bookService.toList(pageable);
    }

    @PostMapping
    public ResponseEntity<BooksDto> toSave(@RequestBody @Valid BooksFormDto dto,
                                             UriComponentsBuilder uriBuilder) {
        BooksDto booksDto = bookService.toSave(dto);

        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(booksDto.getId()).toUri();
        return ResponseEntity.created(uri).body(booksDto);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> toDelete(@PathVariable(value="id") @NotNull Long id) {
        bookService.toDelete(id);
        return ResponseEntity.ok().build();
    }

}

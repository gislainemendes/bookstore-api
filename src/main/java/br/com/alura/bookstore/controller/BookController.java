package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.*;
import br.com.alura.bookstore.service.BookService;
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
@RequestMapping("/books")
@Api(tags = "Book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @ApiOperation("List all books")
    public Page<BooksDto> getAllBooks(Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }

    @GetMapping("/id/{id}")
    @ApiOperation("Book details")
    public ResponseEntity<BooksDto> getBookById(@PathVariable @NotNull Long id) {
        BooksDto dto = bookService.findBookById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Register a book")
    public ResponseEntity<BooksDto> saveBook(@RequestBody @Valid BooksFormDto dto,
                                             UriComponentsBuilder uriBuilder) {
        BooksDto booksDto = bookService.saveBook(dto);

        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(booksDto.getId()).toUri();
        return ResponseEntity.created(uri).body(booksDto);
    }

    @PutMapping
    @ApiOperation("Update a book")
    public ResponseEntity<BooksDto> updateBook(@RequestBody @Valid UpdateBooksFormDto dto) {
        BooksDto updateBooksDto = bookService.updateBooks(dto);
        return ResponseEntity.ok(updateBooksDto);
    }

    @DeleteMapping("/id/{id}")
    @ApiOperation("Remove a book")
    public ResponseEntity<BooksDto> deleteBook(@PathVariable @NotNull Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}

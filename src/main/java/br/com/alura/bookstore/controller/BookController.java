package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
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
    public Optional<BooksDto> getBookById(@PathVariable(value="id") @NotNull Long id){
        return bookService.findBookById(id);
    }

    @PostMapping
    @ApiOperation("Register a book")
    public ResponseEntity<BooksDto> saveBook(@RequestBody @Valid BooksFormDto dto,
                                             UriComponentsBuilder uriBuilder) {
        BooksDto booksDto = bookService.saveBook(dto);

        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(booksDto.getId()).toUri();
        return ResponseEntity.created(uri).body(booksDto);
    }

    @DeleteMapping("/id/{id}")
    @ApiOperation("Remove a book")
    public ResponseEntity<Void> deleteBook(@PathVariable(value="id") @NotNull Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}

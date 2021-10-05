package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
import br.com.alura.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BooksDto> toList() {
        return bookService.toList();
    }

    @PostMapping
    public void toSave(@RequestBody @Valid BooksFormDto dto) {
        bookService.toSave(dto);
    }

}

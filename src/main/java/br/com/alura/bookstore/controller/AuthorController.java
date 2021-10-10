package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void toSave(@RequestBody @Valid AuthorsFormDto dto) {
        authorService.toSave(dto);
    }
}

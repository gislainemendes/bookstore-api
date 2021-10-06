package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AuthorService authorService;

    public List<BooksDto> toList() {
        return books.stream().map(b -> modelMapper.map(b, BooksDto.class)).collect(Collectors.toList());
    }

    public void toSave(@RequestBody @Valid BooksFormDto dto) {
        Author author = authorService.findAuthorByName(dto.getAuthor());
        Book book = modelMapper.map(dto, Book.class);
        book.setAuthor(author);
        books.add(book);
    }
}

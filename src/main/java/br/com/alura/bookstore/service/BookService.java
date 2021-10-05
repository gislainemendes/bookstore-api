package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
import br.com.alura.bookstore.model.Book;
import org.modelmapper.ModelMapper;
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

    public List<BooksDto> toList() {
        return books.stream().map(b -> modelMapper.map(b, BooksDto.class)).collect(Collectors.toList());
    }

    public void toSave(@RequestBody @Valid BooksFormDto dto) {
        Book book = modelMapper.map(dto, Book.class);
        books.add(book);
    }
}

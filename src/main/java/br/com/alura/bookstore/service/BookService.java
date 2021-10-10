package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Book;
import br.com.alura.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AuthorService authorService;

    public List<BooksDto> toList() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(b -> modelMapper.map(b, BooksDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public void toSave(BooksFormDto dto) {
        Author author = authorService.findAuthorByName(dto.getAuthor());
        Book book = modelMapper.map(dto, Book.class);
        book.setAuthor(author);
        bookRepository.save(book);
    }
}

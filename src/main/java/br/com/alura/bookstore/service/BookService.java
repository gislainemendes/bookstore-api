package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.BooksDto;
import br.com.alura.bookstore.dto.BooksFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Book;
import br.com.alura.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AuthorService authorService;

    public Page<BooksDto> toList(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(b -> modelMapper.map(b, BooksDto.class));
    }

    @Transactional
    public BooksDto toSave(BooksFormDto dto) {
        Author author = authorService.findAuthorByName(dto.getAuthor());
        Book book = modelMapper.map(dto, Book.class);
        book.setAuthor(author);
        bookRepository.save(book);
        return modelMapper.map(book, BooksDto.class);
    }

    public void toDelete(Long id) {
        bookRepository.deleteById(id);
    }


}

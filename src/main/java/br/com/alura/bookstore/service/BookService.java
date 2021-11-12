package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.*;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Book;
import br.com.alura.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    private final ModelMapper modelMapper = new ModelMapper();

    public Page<BooksDto> findAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(b -> modelMapper.map(b, BooksDto.class));
    }

    public BooksDto findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(book, BooksDto.class);
    }

    @Transactional
    public BooksDto saveBook(BooksFormDto dto) {
        Author author = authorService.findAuthorByName(dto.getAuthor());
        Book book = modelMapper.map(dto, Book.class);
        book.setAuthor(author);
        bookRepository.save(book);
        return modelMapper.map(book, BooksDto.class);
    }

    @Transactional
    public BooksDto updateBooks(UpdateBooksFormDto dto) {
        Book book = bookRepository.getById(dto.getId());
        book.updateBookInformation(dto.getTitle(), dto.getNumberOfPages(), dto.getReleaseDate(), dto.getAuthor());
        return modelMapper.map(book, BooksDto.class);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


}

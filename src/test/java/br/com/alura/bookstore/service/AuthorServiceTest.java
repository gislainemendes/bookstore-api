package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.repository.AuthorRepository;
import br.com.alura.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void shouldSaveAnAuthor() {
        AuthorsFormDto authorsFormDto = new AuthorsFormDto(
                "Autor teste",
                "autorTeste@gmail.com",
                LocalDate.now(),
                "curriculo teste"
        );

        AuthorsDto dto = authorService.saveAuthors(authorsFormDto);

        assertEquals(authorsFormDto.getName(), dto.getName());
        assertEquals(authorsFormDto.getEmail(), dto.getEmail());
        assertEquals(authorsFormDto.getBirthDate(), dto.getBirthDate());
        assertEquals(authorsFormDto.getCurriculo(), dto.getCurriculo());
    }


    @Test
    void shouldFindAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        assertTrue(authors.isEmpty());
    }
//
//    @Test
//    void findAuthorById() {
//    }
//
//    @Test
//    void findAuthorByName() {
//    }
//
//    @Test
//    void updateAuthors() {
//    }
//
//    @Test
//    void deleteAuthors() {
//    }
}
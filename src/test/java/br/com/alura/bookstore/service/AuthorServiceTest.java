package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.dto.UpdateAuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.repository.AuthorRepository;
import br.com.alura.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void shouldReturnDifferentObjectWithSameValuesWhenSaveAnAuthor() {
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

        assertThat(dto).isNotSameAs(authorsFormDto);
    }

    @Test
    void shouldReturnAnAuthorDtoWhenSearchByIdThatExists() {
        Author author = new Author(
                1L, "AuthorTest", "authorTest@gmail.com", LocalDate.of(1989, 03, 12), "curriculoTest");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        var authorDto = authorService.findAuthorById(1L);

        assertThat(authorDto).isNotNull();

        assertThat(authorDto.getName()).isEqualTo("AuthorTest");
        assertThat(authorDto.getEmail()).isEqualTo("authorTest@gmail.com");
        assertThat(authorDto.getBirthDate()).isEqualTo(LocalDate.of(1989, 03, 12));
        assertThat(authorDto.getCurriculo()).isEqualTo("curriculoTest");

    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenSearchByIdThatDoesNotExist() {

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            authorService.findAuthorById(1L);
        }).isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    void shouldReturnACorrectAuthorWhenSearchByName() {

        Author authorA = new Author(1L, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        Author authorB = new Author(2L, "AuthorB", "authorB@gmail.com", LocalDate.of(1989, 03, 13), "curriculo Teste");
        Author authorC = new Author(3L, "AuthorC", "authorC@gmail.com", LocalDate.of(1989, 03, 14), "curriculo Teste");
        List<Author> authors = List.of(authorA, authorB, authorC);

        when(authorRepository.findAll()).thenReturn(authors);

        var authorFound = authorService.findAuthorByName("AuthorA");

        assertThat(authorFound).isNotNull();
        assertThat(authorFound.getId()).isEqualTo(1L);
        assertThat(authorFound.getName()).isEqualTo("AuthorA");
        assertThat(authorFound.getEmail()).isEqualTo("authorA@gmail.com");
        assertThat(authorFound.getBirthDate()).isEqualTo(LocalDate.of(1989, 03, 12));
        assertThat(authorFound.getCurriculo()).isEqualTo("curriculo Teste");


    }

    @Test
    void shouldReturnTheFirstAuthorOfTwoOrMoreAuthorsWithTheSameNameWhenSearchByName() {

        Author authorA = new Author(1L, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        Author authorB = new Author(2L, "AuthorA", "authorB@gmail.com", LocalDate.of(1989, 03, 13), "curriculo Teste");
        Author authorC = new Author(3L, "AuthorC", "authorC@gmail.com", LocalDate.of(1989, 03, 14), "curriculo Teste");
        List<Author> authors = List.of(authorA, authorB, authorC);

        when(authorRepository.findAll()).thenReturn(authors);

        var authorFound = authorService.findAuthorByName("AuthorA");

        assertThat(authorFound).isNotNull();
        assertThat(authorFound.getId()).isEqualTo(1L);
        assertThat(authorFound.getName()).isEqualTo("AuthorA");
        assertThat(authorFound.getEmail()).isEqualTo("authorA@gmail.com");
        assertThat(authorFound.getBirthDate()).isEqualTo(LocalDate.of(1989, 03, 12));
        assertThat(authorFound.getCurriculo()).isEqualTo("curriculo Teste");

    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSearchANameThatDoesNotExistOnDatabase() {

        Author authorA = new Author(1L, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        Author authorB = new Author(2L, "AuthorA", "authorB@gmail.com", LocalDate.of(1989, 03, 13), "curriculo Teste");
        Author authorC = new Author(3L, "AuthorC", "authorC@gmail.com", LocalDate.of(1989, 03, 14), "curriculo Teste");
        List<Author> authors = List.of(authorA, authorB, authorC);

        when(authorRepository.findAll()).thenReturn(authors);

        assertThatThrownBy(() -> {
            authorService.findAuthorByName("AuthorD");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTheListOfAuthorsIsEmpty() {

        when(authorRepository.findAll()).thenReturn(List.of());

        assertThatThrownBy(() -> {
            authorService.findAuthorByName("AuthorD");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldReturnDifferentObjectWithSameValuesWhenUpdateAnAuthor() {

        UpdateAuthorsFormDto update = new UpdateAuthorsFormDto();
        update.setId(1L);
        update.setName("AuthorTest");
        update.setEmail("authorTest@gmail.com");
        update.setBirthDate(LocalDate.of(1989, 03, 12));
        update.setCurriculo("curriculo");

        Author author = new Author(1L, "AuthorTestA", null, null, null);

        when(authorRepository.getById(1L)).thenReturn((author));

        AuthorsDto dto = authorService.updateAuthors(update);

        assertEquals(update.getName(), dto.getName());
        assertEquals(update.getEmail(), dto.getEmail());
        assertEquals(update.getBirthDate(), dto.getBirthDate());
        assertEquals(update.getCurriculo(), dto.getCurriculo());

        assertThat(dto).isNotSameAs(update);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenToUpdateAnAuthorTheIdDoesNotExist() {
        UpdateAuthorsFormDto update = new UpdateAuthorsFormDto();
        update.setId(1L);
        update.setName("AuthorTest");
        update.setEmail("authorTest@gmail.com");
        update.setBirthDate(LocalDate.of(1989, 03, 12));
        update.setCurriculo("curriculo");

        when(authorRepository.getById(1L)).thenReturn(null);

        assertThatThrownBy(() -> {
            authorService.updateAuthors(update);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
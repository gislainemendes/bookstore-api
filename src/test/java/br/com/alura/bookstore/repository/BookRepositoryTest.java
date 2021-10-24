package br.com.alura.bookstore.repository;

import br.com.alura.bookstore.dto.BooksReportDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldReturnBooksGroupedByAuthor() {

        Author author = new Author("Autor teste 3", "teste3@gmail.com", LocalDate.now(), "Eu sou um autora de vários livros");
        testEntityManager.persist(author);

        Author author1 = new Author("Autor teste 4", "teste4@gmail.com", LocalDate.now(), "Eu sou um autora de vários livros");
        testEntityManager.persist(author1);

        Author author2 = new Author("Autor teste 5", "teste5@gmail.com", LocalDate.now(), "Eu sou um autora de vários livros");
        testEntityManager.persist(author2);

        Book b1 = new Book("Livro Teste6", LocalDate.now(), 138, author);
        testEntityManager.persist(b1);
        Book b2 = new Book("Livro Teste7", LocalDate.now(), 138, author1);
        testEntityManager.persist(b2);
        Book b3 = new Book("Livro Teste5", LocalDate.now(), 138, author2);
        testEntityManager.persist(b3);
        Book b4 = new Book("Livro Teste6", LocalDate.now(), 138, author2);
        testEntityManager.persist(b4);

        List<BooksReportDto> booksReport = bookRepository.booksByAuthorReport();
        Assertions
                .assertThat(booksReport)
                .hasSize(3)
                .extracting(BooksReportDto::getBooks, BooksReportDto::getName, BooksReportDto::getPercentile)
                .containsExactlyInAnyOrder(
                        Assertions.tuple(1L, "Autor teste 3", 0.25),
                        Assertions.tuple(1L, "Autor teste 4", 0.25),
                        Assertions.tuple(2L, "Autor teste 5", 0.5));
    }

}
package br.com.alura.bookstore.repository;

import br.com.alura.bookstore.dto.BooksReportDto;
import br.com.alura.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long> {

    @Query("select new br.com.alura.bookstore.dto.BooksReportDto( " +
            " a.name,  " +
            " count(b.title), " +
            " (count(b.title) * 1.0 / (select count(b2.title) from Book b2)) * 1.0) " +
            "from  " +
            " Book b  " +
            "join  " +
            " Author a  " +
            "on  " +
            " a.id = b.author.id  " +
            " group by b.author.id")
    List<BooksReportDto> booksByAuthorReport();
}
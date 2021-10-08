package br.com.alura.bookstore.dao;

import br.com.alura.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Long> {

}
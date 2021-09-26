package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BooksDto {

    private String title;
    private LocalDate releaseDate;
    private Integer numberOfPages;
}

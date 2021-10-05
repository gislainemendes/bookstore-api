package br.com.alura.bookstore.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;
    private LocalDate releaseDate;
    private Integer numberOfPages;
    private Author author;

}

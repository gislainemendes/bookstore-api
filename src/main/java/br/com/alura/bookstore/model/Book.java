package br.com.alura.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "releaseDate")
    private LocalDate releaseDate;

    @Column(name = "numberOfPages")
    private Integer numberOfPages;

    @ManyToOne
    private Author author;

}

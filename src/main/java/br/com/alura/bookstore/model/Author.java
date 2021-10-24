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
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "curriculo")
    private String curriculo;

    public void updateAuthorInformation(String name, String email, LocalDate birthDate, String curriculo) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.curriculo = curriculo;
    }

    public Author(String name, String email, LocalDate birthDate, String curriculo) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.curriculo = curriculo;
    }
}

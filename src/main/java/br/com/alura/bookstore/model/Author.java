package br.com.alura.bookstore.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String name;
    private String email;
    private LocalDate birthDate;
    private String curriculo;


}

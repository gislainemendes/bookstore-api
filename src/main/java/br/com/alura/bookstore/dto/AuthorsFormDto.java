package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorsFormDto {

    private String name;
    private String email;
    private LocalDate birthDate;
    private String curriculo;


}

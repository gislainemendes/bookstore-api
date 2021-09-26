package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorsDto {

    private String name;
    private String email;
    private LocalDate birthDate;


}

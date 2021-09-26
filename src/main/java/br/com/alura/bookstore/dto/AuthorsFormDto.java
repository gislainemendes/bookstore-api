package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class AuthorsFormDto {

    @NotNull
    @NotEmpty
    private String name;
    private String email;
    @PastOrPresent
    private LocalDate birthDate;
    @Size(max=180)
    private String curriculo;


}

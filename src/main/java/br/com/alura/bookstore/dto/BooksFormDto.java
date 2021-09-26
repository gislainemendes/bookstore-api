package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class BooksFormDto {

    @NotBlank
    @Size(min=10)
    private String title;
    @PastOrPresent
    private LocalDate releaseDate;
    @Min(100)
    private Integer numberOfPages;
}

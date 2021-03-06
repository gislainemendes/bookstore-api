package br.com.alura.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class BooksFormDto {

    @NotBlank
    @Size(min=10, message = "Quantidade min permitida é de 10 caracteres")
    private String title;

    @NotNull
    @PastOrPresent(message = "Data não pode ser posterior a data de hoje!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @NotNull
    @Min(100)
    private Integer numberOfPages;

    @NotBlank
    private String author;
}

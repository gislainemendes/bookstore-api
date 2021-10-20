package br.com.alura.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateBooksFormDto extends BooksFormDto {

    @NotNull
    private Long Id;
}

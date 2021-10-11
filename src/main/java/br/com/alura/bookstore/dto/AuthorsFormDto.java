package br.com.alura.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class AuthorsFormDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    @PastOrPresent(message = "Data não pode ser posterior a data de hoje!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Size(max=180, message = "Quantidade máx permitida é de 180 caracteres")
    @NotBlank
    private String curriculo;


}

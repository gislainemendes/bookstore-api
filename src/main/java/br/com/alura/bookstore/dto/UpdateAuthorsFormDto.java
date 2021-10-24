package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAuthorsFormDto extends AuthorsFormDto {

    @NotNull
    private Long id;

    public UpdateAuthorsFormDto(Long id, String name, String email, LocalDate birthDate, String curriculo){
        super(name, email, birthDate, curriculo);
        this.id = id;

    }

}

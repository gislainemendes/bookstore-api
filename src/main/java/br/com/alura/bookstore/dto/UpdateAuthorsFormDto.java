package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateAuthorsFormDto extends AuthorsFormDto {

    @NotNull
    private Long Id;

}

package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserFormDto {

    @NotBlank
    private String name;

    @NotBlank
    private String login;

}

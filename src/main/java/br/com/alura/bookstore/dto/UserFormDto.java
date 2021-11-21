package br.com.alura.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserFormDto {

    @NotBlank
    private String name;

    @NotBlank
    private String login;

    @NotNull
    private Long profileId;

    @NotBlank
    @Email
    private String email;

}

package br.com.alura.bookstore.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(exclude = {"senha"})
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String login;
    private String password;
}

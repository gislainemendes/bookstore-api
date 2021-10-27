package br.com.alura.bookstore.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"senha"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;
}

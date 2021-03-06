package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.dto.UpdateAuthorsFormDto;
import br.com.alura.bookstore.infra.security.TokenService;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Profile;
import br.com.alura.bookstore.model.User;
import br.com.alura.bookstore.repository.AuthorRepository;
import br.com.alura.bookstore.repository.ProfileRepository;
import br.com.alura.bookstore.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    private String getToken() {
        User loggedInUser = new User("Gislaine", "gislaine", "123456");
        Profile admin = profileRepository.findById(1L).get();
        loggedInUser.addProfile(admin);
        userRepository.save(loggedInUser);


        Authentication authentication = new UsernamePasswordAuthenticationToken(loggedInUser, loggedInUser.getLogin());
        return tokenService.generateToken(authentication);
    }

    @Test
    void shouldNotSaveAnAuthorWithIncompleteData() throws Exception {
        AuthorsFormDto author = new AuthorsFormDto("Author1", null, null, null);
        String authorDtoJson = objectMapper.writeValueAsString(author);

        String token = getToken();

        mvc.perform(
                post("/authors/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
                        .header("Authorization","Bearer " + token)
        ).andExpect(
                status().isBadRequest()
        );

    }

    @Test
    void shouldSaveAnAuthorWithCompleteData() throws Exception {
        AuthorsFormDto author = new AuthorsFormDto(
                "Author1",
                "author1@gmail.com",
                LocalDate.of(1988, 04, 10),
                "eu sou um autor de livros"
        );
        String authorDtoJson = objectMapper.writeValueAsString(author);

        String token = getToken();

        mvc.perform(
                post("/authors/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
                        .header("Authorization","Bearer " + token)
        ).andExpect(status().isCreated()
        ).andExpect(header().exists("Location")
        ).andExpect(content().json(authorDtoJson));

    }

    @Test
    void shouldGetAnAuthorById() throws Exception {
        Author authorA = new Author(null, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        authorRepository.save(authorA);

        Long id = authorA.getId();
        AuthorsDto authorsDto = new AuthorsDto(
                id,
                "AuthorA",
                "authorA@gmail.com",
                LocalDate.of(1989, 03, 12),
                "curriculo Teste"
        );

        String authorDtoJson = objectMapper.writeValueAsString(authorsDto);

        String token = getToken();


        mvc.perform(get("/authors/id/" + id)
                .header("Authorization","Bearer " + token)
        ).andExpect(status().isOk()
        ).andExpect(content().json(authorDtoJson)
        ).andDo(MockMvcResultHandlers.print());

    }

    @Test
    void shouldGetNotFoundStatusWhenAuthorIdDoesNotExistOnGetRequest() throws Exception {
        Author authorA = new Author(null, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        authorRepository.save(authorA);

        Long id = 1L;
        List<Author> authorList = authorRepository.findAll();
        Author uniqueAuthor = authorList.get(0);

        assertThat(authorList).hasSize(1);
        assertThat(uniqueAuthor.getId()).isNotEqualTo(id);

        String token = getToken();

        mvc.perform(get("/authors/id/" + id)
                .header("Authorization","Bearer " + token)
        ).andExpect(status().isNotFound()
        ).andDo(MockMvcResultHandlers.print());

    }

    @Test
    void shouldUpdateAnAuthorWhenIdExists() throws Exception {
        Author authorA = new Author(null, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        authorRepository.save(authorA);

        Long id = authorA.getId();
        UpdateAuthorsFormDto updateAuthorsFormDto = new UpdateAuthorsFormDto(
                id,
                "AuthorB",
                "authorB@gmail.com",
                LocalDate.of(1989, 03, 12),
                "curriculo Teste update"
        );

        AuthorsDto authorsDto = new AuthorsDto(
                id,
                "AuthorB",
                "authorB@gmail.com",
                LocalDate.of(1989, 03, 12),
                "curriculo Teste update"
        );

        String authorDtoJson = objectMapper.writeValueAsString(updateAuthorsFormDto);
        String authorDtoResponse = objectMapper.writeValueAsString(authorsDto);

        String token = getToken();

        mvc.perform(
                put("/authors/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
                        .header("Authorization","Bearer " + token)
        ).andExpect(status().isOk()
        ).andExpect(content().json(authorDtoResponse));

    }

    @Test
    void shouldGet4xxClientErrorStatusWhenAuthorIdDoesNotExistOnUpdateRequest() throws Exception {
        Long id = 1L;
        UpdateAuthorsFormDto updateAuthorsFormDto = new UpdateAuthorsFormDto(
                id,
                "AuthorB",
                "authorB@gmail.com",
                LocalDate.of(1989, 03, 12),
                "curriculo Teste update"
        );

        String authorDtoJson = objectMapper.writeValueAsString(updateAuthorsFormDto);

        mvc.perform(
                put("/authors/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(status().is4xxClientError()
        ).andDo(MockMvcResultHandlers.print());

    }

    @Test
    void shouldDeleteAnAuthorWhenIdExists() throws Exception {
        Author authorA = new Author(null, "AuthorA", "authorA@gmail.com", LocalDate.of(1989, 03, 12), "curriculo Teste");
        authorRepository.save(authorA);

        Long id = authorA.getId();

        List<Author> authorsBefore = authorRepository.findAll();

        String token = getToken();

        mvc.perform(
                delete("/authors/id/" + id)
                        .header("Authorization","Bearer " + token)
        ).andExpect(status().isNoContent()
        ).andDo(MockMvcResultHandlers.print());

        assertThat(authorsBefore).hasSize(1);

        assertThat(authorRepository.findAll()).hasSize(0);
    }

    @Test
    void shouldGetAllAuthorsWhenIdExists() throws Exception{
        Author authorA = new Author(
                null, "AuthorA",
                "authorA@gmail.com",
                LocalDate.of(1989, 03, 12),
                "curriculo Teste");

        Author authorB = new Author(
                null, "AuthorB",
                "authorB@gmail.com",
                LocalDate.of(1988, 05, 12),
                "curriculo Teste2");

        authorRepository.saveAll(List.of(authorA, authorB));

        String token = getToken();

        mvc.perform(
                get("/authors/")
                        .param("page", "0")
                        .param("size", "1")
                        .header("Authorization","Bearer " + token)
        ).andExpect(status().isOk()
        ).andExpect(jsonPath("$.content", hasSize(equalTo(1)))
        ).andExpect(jsonPath("$.content[0].name", equalTo("AuthorA"))
        ).andExpect(jsonPath("$.pageable.pageNumber" , equalTo(0))
        ).andExpect(jsonPath("$.pageable.pageSize", equalTo(1))
        ).andExpect(jsonPath("$.totalPages", equalTo(2))
        ).andExpect(jsonPath("$.totalElements", equalTo(2))
        ).andDo(MockMvcResultHandlers.print());

    }

}



package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.repository.AuthorRepository;
import br.com.alura.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Page<AuthorsDto> findAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(a -> modelMapper.map(a, AuthorsDto.class));
    }

    public Optional<AuthorsDto> findAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(a -> modelMapper.map(a, AuthorsDto.class));
    }

    public Author findAuthorByName(String name) {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().filter(author -> author
                        .getName()
                        .equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Author doesn't exist!"));
    }

    @Transactional
    public AuthorsDto saveAuthors(AuthorsFormDto dto) {
        Author author = modelMapper.map(dto, Author.class);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorsDto.class);
    }

    public Optional<AuthorsDto> updateAuthors(AuthorsFormDto dto, Long id){
        return authorRepository.findById(id)
                .map(update -> {
                    update.setName(dto.getName());
                    update.setEmail(dto.getEmail());
                    update.setBirthDate(dto.getBirthDate());
                    update.setCurriculo(dto.getCurriculo());
                    Author updatedAuthor = authorRepository.save(update);
                    return modelMapper.map(updatedAuthor, AuthorsDto.class);
                });
    }

    @Transactional
    @Modifying
    public void deleteAuthors(Long id) {
        bookRepository.deleteByAuthor_Id(id);
        authorRepository.deleteById(id);
    }

}

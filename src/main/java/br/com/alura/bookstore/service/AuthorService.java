package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.dto.UpdateAuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.repository.AuthorRepository;
import br.com.alura.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Page<AuthorsDto> findAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(a -> modelMapper.map(a, AuthorsDto.class));
    }

    public AuthorsDto findAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(author, AuthorsDto.class);
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

    @Transactional
    public AuthorsDto updateAuthors(UpdateAuthorsFormDto dto) {
        Author author = authorRepository.getById(dto.getId());
        if(author==null){throw new IllegalArgumentException("An author with this Id does not exist!");}
        author.updateAuthorInformation(dto.getName(), dto.getEmail(), dto.getBirthDate(), dto.getCurriculo());
        return modelMapper.map(author, AuthorsDto.class);
    }

    @Transactional
    public void deleteAuthors(Long id) {
        bookRepository.deleteByAuthor_Id(id);
        authorRepository.deleteById(id);
    }

}

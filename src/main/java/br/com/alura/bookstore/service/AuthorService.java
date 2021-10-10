package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public Page<AuthorsDto> toList(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(a -> modelMapper.map(a, AuthorsDto.class));
    }

    @Transactional
    public AuthorsDto toSave(AuthorsFormDto dto) {
        Author author = modelMapper.map(dto, Author.class);
        authorRepository.save(author);
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
}

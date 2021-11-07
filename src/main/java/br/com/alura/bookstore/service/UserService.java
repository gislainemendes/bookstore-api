package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.UserDto;
import br.com.alura.bookstore.dto.UserFormDto;
import br.com.alura.bookstore.model.Profile;
import br.com.alura.bookstore.model.User;
import br.com.alura.bookstore.repository.ProfileRepository;
import br.com.alura.bookstore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Page<UserDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(u -> modelMapper.map(u, UserDto.class));
    }

    @Transactional
    public UserDto saveUser(UserFormDto userFormDto){
        User user = modelMapper.map(userFormDto, User.class);
        user.setId(null);

        Profile profile = profileRepository.getById(userFormDto.getProfileId());
        user.addProfile(profile);

        String password = new Random().nextInt(999999) + "";
        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }


}

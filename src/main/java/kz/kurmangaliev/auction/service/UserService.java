package kz.kurmangaliev.auction.service;

import kz.kurmangaliev.auction.db.model.Role;
import kz.kurmangaliev.auction.db.model.User;
import kz.kurmangaliev.auction.db.repo.RoleRepository;
import kz.kurmangaliev.auction.db.repo.UserRepository;
import kz.kurmangaliev.auction.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public boolean saveUser(UserDto userDto) {
        Role role = roleRepository.getById(1L);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .firstname(userDto.getFirstName())
                .lastname(userDto.getLastName())
                .role(role)
                .password(bCryptPasswordEncoder.encode(userDto.getPassword())).build());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("find user by email");
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            log.info("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        log.info("User is {}", user.get());
        return user.get();
    }

    @PostConstruct
    private void init() {
        saveUser(UserDto.builder().firstName("admin").lastName("admin").password("admin").email("admin@admin.com").build());
    }


}

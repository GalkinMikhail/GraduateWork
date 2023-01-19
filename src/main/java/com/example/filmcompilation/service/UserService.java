package com.example.filmcompilation.service;

import com.example.filmcompilation.model.Film;
import com.example.filmcompilation.model.Role;
import com.example.filmcompilation.model.User;
import com.example.filmcompilation.repository.RoleRepository;
import com.example.filmcompilation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean registration(User user){
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null) {
            return false;
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        userRepository.save(user);
        return true;
    }

    public Set<Film> getRecommendations(User user){
        return user.getRecommendationsFilms();
    }

    public Set<Film> getWatchedFilms(User user){
        return user.getWatchedFilms();
    }

//    public Set<Film> getRecs(Long id){
//        return userRepository.getRecs(id);
//    }
    public boolean isWatched(User user,Film film){
        boolean flag = false;
        for (Film film1 : user.getWatchedFilms()) {
            if (film1.getTitle().equals(film.getTitle())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isSurveyPassed(User user){
        return user.getRecommendationsFilms().size() == 0;
    }
}

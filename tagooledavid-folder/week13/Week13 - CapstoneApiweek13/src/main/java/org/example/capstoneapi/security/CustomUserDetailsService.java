package org.example.capstoneapi.security;

import org.example.capstoneapi.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;

    public CustomUserDetailsService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return studentRepository.findByName(username).map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("No student found with name"+username));
    }
}

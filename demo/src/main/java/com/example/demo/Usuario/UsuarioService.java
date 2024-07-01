package com.example.demo.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> getAllUser() {
        return userRepository.findAll();
    }

    public Usuario getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario saveUser(Usuario user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Page<Usuario> findUsers(String search, Pageable pageable) {
        return userRepository.findUsersBySearch(search, pageable);
    }

    public Usuario getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}

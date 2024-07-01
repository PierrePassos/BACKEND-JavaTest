package com.example.demo.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.PasswordEncryptionService;

@RestController
@RequestMapping("api/user")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping
    public List<Usuario> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Usuario user = userService.getUserById(id);
        if (user != null) {
            user = skipPassword(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Usuario user) {
        String password = user.getPassword();
        System.out.println("Usuário antes de salvar: " + user.toString());

        try {
            String passwordEncrypted = PasswordEncryptionService.encryptPassword(password);
            System.out.println(passwordEncrypted);

            if (passwordEncrypted != null) {
                user.setPassword(passwordEncrypted);
                Usuario savedUser = userService.saveUser(user);
                return new ResponseEntity<>(savedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erro ao salvar usuário, senha vazia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao salvar usuário", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario user) {
        Usuario existingUser = userService.getUserById(id);
        if (existingUser != null) {
            user.setId(id);
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginator")
    public ResponseEntity<?> getProfiles(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "search", defaultValue = "") String search) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> users = userService.findUsers(search, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("users", this.skipPassword(users.getContent()));
        response.put("total", users.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public List<Usuario> skipPassword(List<Usuario> users) {
        return users.stream()
                .peek(user -> user.setPassword(null))
                .collect(Collectors.toList());
    }

    public Usuario skipPassword(Usuario user) {
        user.setPassword(null);
        return user;
    }

}

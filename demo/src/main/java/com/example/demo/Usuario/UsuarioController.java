package com.example.demo.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

// import com.example.demo.Profile.Profile;

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
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Usuario createUser(@RequestBody Usuario user) {
        // Profile profile = profileService.getProfileById(user.getProfile().getId());
        // user.setProfile(profile);
        return userService.saveUser(user);
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

        System.out.println("Received request with parameters:");
        System.out.println("page: " + page);
        System.out.println("size: " + size);
        System.out.println("search: " + search);

        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> users = userService.findUsers(search, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("users", users.getContent());
        response.put("total", users.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

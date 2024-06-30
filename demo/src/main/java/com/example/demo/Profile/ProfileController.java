package com.example.demo.Profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<Profile> getAllProfile() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        return profile != null ? new ResponseEntity<>(profile, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
        Profile existingProfile = profileService.getProfileByName(profile.getName());
        if (existingProfile != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O perfil com o nome " + profile.getName() + " já existe.");
        }

        Profile savedProfile = profileService.saveProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Profile existingProfile = profileService.getProfileByName(profile.getName());
        if (existingProfile != null && !existingProfile.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O perfil com o nome " + profile.getName() + " já existe.");
        }

        profile.setId(id);
        return new ResponseEntity<>(profileService.saveProfile(profile), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

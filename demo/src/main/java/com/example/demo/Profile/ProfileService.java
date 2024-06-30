package com.example.demo.Profile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        Sort sortByIdAsc = Sort.by(Sort.Direction.ASC, "id");
        return profileRepository.findAll(sortByIdAsc);
    }

    public Profile getProfileByName(String name) {
        Optional<Profile> optionalProfile = profileRepository.findByName(name);
        return optionalProfile.orElse(null);
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}

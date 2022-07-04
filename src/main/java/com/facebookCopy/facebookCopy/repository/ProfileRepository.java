package com.facebookCopy.facebookCopy.repository;

import com.facebookCopy.facebookCopy.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByFirstName(String userName);
    Profile findByUserName(String userName);
}

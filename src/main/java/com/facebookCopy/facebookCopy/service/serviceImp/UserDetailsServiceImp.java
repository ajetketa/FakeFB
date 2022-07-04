package com.facebookCopy.facebookCopy.service.serviceImp;

import com.facebookCopy.facebookCopy.model.Profile;
import com.facebookCopy.facebookCopy.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Profile profile=profileRepository.findByUserName(userName);
        if(profile!=null){
            return User.builder()
                    .username(profile.getUserName())
                    .password(bCryptPasswordEncoder.encode(profile.getPassword()))
                    .disabled(false)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .roles("PROFILE")
                    .build();
        }else{
            return null;
        }
    }
}

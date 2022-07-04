package com.facebookCopy.facebookCopy.controller;

import com.facebookCopy.facebookCopy.model.Profile;
import com.facebookCopy.facebookCopy.service.serviceImp.ProfileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fakefb")
public class RegisterController {
    @Autowired
    private ProfileServiceImp profileService;
    @PostMapping("/register")
    public HttpStatus registerProfile(@RequestBody Profile profile){
        System.out.println("something");
        return profileService.register(profile);
    }
}

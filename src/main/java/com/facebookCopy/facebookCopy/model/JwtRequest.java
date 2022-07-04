package com.facebookCopy.facebookCopy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}

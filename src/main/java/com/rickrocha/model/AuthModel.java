package com.rickrocha.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthModel implements Serializable {
    private UUID id;
    private String password;
    private Date registrationData;
}

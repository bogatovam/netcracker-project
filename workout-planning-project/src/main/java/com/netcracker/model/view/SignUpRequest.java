package com.netcracker.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpRequest {
    private String name;

    private String username;
    private String email;

    private Set<String> role;

    private String password;
}

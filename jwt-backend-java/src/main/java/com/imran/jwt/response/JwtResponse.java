package com.imran.jwt.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtResponse {
    private String username;
    private String token;

    public JwtResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}

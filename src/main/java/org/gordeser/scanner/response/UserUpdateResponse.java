package org.gordeser.scanner.response;

import lombok.Data;

@Data
public class UserUpdateResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private Long expiresIn;
}

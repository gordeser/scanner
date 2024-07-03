package org.gordeser.scanner.dao.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}

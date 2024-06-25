package org.gordeser.scanner.dao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull(message = "required")
    private String username;

    @NotNull(message = "required")
    @Email(message = "email should be valid")
    private String email;

    @NotNull(message = "required")
    private String password;

    private String firstName;
    private String lastName;
}
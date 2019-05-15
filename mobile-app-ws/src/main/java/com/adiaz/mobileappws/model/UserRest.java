package com.adiaz.mobileappws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {
    private String userId;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 30, message = "First name incorrect.")
    @NotEmpty
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email format incorrect")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min=8, max = 16, message = "Password size incorrect.")
    private String password;
}

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
public class UserRestUpdate {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 30, message = "First name incorrect.")
    @NotEmpty
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 30, message = "Last name incorrect.")
    @NotEmpty
    private String lastName;
}

package com.inmobiliaria.InmoGestion.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String email;
    String password;
    String firstName;
    String lastName;
    String country;


}

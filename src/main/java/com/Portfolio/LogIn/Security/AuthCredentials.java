// 3. Creamos una clase llamada AuthCredentials con las credenciales (email y password)

package com.Portfolio.LogIn.Security;

import lombok.Data;
// Asignamos data para los Getters y Setters.
@Data
public class AuthCredentials {
    private String email;
    private String password;
}

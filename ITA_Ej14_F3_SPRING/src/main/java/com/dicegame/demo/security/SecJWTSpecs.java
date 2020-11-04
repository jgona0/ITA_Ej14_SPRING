package com.dicegame.demo.security;


//Constantes usadas para generar el token.

public class SecJWTSpecs {

    public static final String SECRET = "SecretKeyJWT";  //Debería ser más larga pero para el fin del ejercicio está OK
    public static final long EXPIRATION_TIME = 600000; //10 minutos. También debería ser más tiempo, pero para probar cosas del ejercicio está OK          
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";

}

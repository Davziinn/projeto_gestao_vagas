package br.com.davi.gestaovagas.exceptions;

public class UserFoudException extends RuntimeException {
    public UserFoudException() {
        super("Já existe um candidato com esse nome de usuário ou email.");
    }
}

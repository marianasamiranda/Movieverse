package data.daos;

public class InvalidTokenException extends Exception {
    public InvalidTokenException() {
        super("Wrong token");
    }
}
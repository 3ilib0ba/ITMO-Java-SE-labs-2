package commands.exceptions;

public class InvalidArg extends RuntimeException {
    public InvalidArg(String message) {
        super(message);
    }
}

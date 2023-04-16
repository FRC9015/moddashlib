package frc.qefrc.moddash.widgets;

/**
 * This exception is thrown if a widget with the same name as one that already exists but is of a different type is trying to be created. For example,
 */
public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String message) {
        super(message);
    }
}

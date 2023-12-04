package node_value.projects.cerasync_back.util.exceptions;

public class EventAlreadyExistsException extends RuntimeException {
    public EventAlreadyExistsException (String msg) {
        super(msg);
    }

    public EventAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

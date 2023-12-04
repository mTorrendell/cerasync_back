package node_value.projects.cerasync_back.util.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException (String msg) {
        super(msg);
    }

    public EventNotFoundException (String msg, Throwable cause) {
        super(msg, cause);
    }

}

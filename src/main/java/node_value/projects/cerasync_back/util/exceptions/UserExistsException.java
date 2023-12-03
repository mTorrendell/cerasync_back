package node_value.projects.cerasync_back.util.exceptions;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String username) {
        super(createMsgErr(username));
    }

    public UserExistsException(String username, Throwable cause) {
        super(createMsgErr(username), cause);
    }

    private static String createMsgErr(String username) {
        return "User with email " + username + " aleready exists";
    }
}

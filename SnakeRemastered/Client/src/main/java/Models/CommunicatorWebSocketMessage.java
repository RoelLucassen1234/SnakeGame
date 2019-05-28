
package Models;

/**
 * Message to be sent from client to server and vice versa  using WebSockets.
 * @author Nico Kuijpers
 */
public class CommunicatorWebSocketMessage {

    // Operation that is requested at client side
    private CommunicatorWebSocketMessageOperation operation;

    // Property
    private String property;

    // Content
    private String content;

    public CommunicatorWebSocketMessageOperation getOperation() {
        return operation;
    }

    public void setOperation(CommunicatorWebSocketMessageOperation operation) {
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

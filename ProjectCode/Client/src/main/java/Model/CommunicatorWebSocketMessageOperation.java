
package Model;

public enum CommunicatorWebSocketMessageOperation {
    REGISTERPROPERTY,         // Register a property (client only)
    UNREGISTERPROPERTY,       // Unregister a registered property (client only
    SUBSCRIBETOPROPERTY,      // Subscribe to a property (client only)
    UNSUBSCRIBEFROMPROPERTY,  // Unsubscribe from a property (client only)
    UPDATEPROPERTY,           // Update property (client and server)
}
